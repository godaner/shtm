package com.shtm.manage.websocket;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.shtm.manage.po.AdminsReplier;
import com.shtm.util.ProjectUtil;

/**
 * Title:OnlineAdminsWS
 * <p>
 * Description:统计在线管理员的websocket
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年10月5日 上午11:15:30
 * @version 1.0
 */
@ServerEndpoint("/onlineAdminsWS/{adminId}")
public class OnlineAdminsWS extends ProjectUtil{

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	public static Map<String, OnlineAdminsWS> clients = new ConcurrentHashMap<String, OnlineAdminsWS>();  
	public static Map<String, AdminsReplier> onlineAdmins = new ConcurrentHashMap<String, AdminsReplier>();  
	//websocket的session
	public Session session;      
	private String adminId;  

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(@PathParam("adminId") String adminId, Session session) {

		this.session = session;
		
		this.adminId = adminId;
		
		clients.put(adminId, this);
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		/**
		 * 不做任何事情
		 */
		
		// 从集合中删除
		/*clients.remove(adminId); 
		loginLogs.remove(adminId);
		//推送最新登陆记录信息
		broadcastOnlineAdminWS();*/
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Throwable error) {
		error.printStackTrace();
	}
	
    /********************************************功能性方法****************************************************/
    /**
     * Title:
     * <p>
     * Description:发送信息
     * <p>
     * @author Kor_Zhang
     * @date 2017年10月7日 下午2:20:58
     * @version 1.0
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
//        this.session.getBasicRemote().sendText(message);
        this.session.getAsyncRemote().sendText(message);
    }
	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message) {
		AdminsReplier replier = JSON.parseObject(message, AdminsReplier.class);
		
		//是否有新的admin登陸記錄,没有新的登陆记录说明其只是想获取登陆列表
		if(replier != null && replier.getId() !=null && !replier.getId().trim().isEmpty()){
			
			onlineAdmins.put(replier.getId(), replier);
		}
		//推送最新登陆记录信息
		broadcastOnlineAdminWS();
		
	}

	
	
	
	/**
	 * 
	 * Title:
	 * <p>
	 * Description:向ws容器中剩下的的websocket發送廣播;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月7日 上午10:54:00
	 * @version 1.0
	 */
	public static synchronized void broadcastOnlineAdminWS() {
		//推送最新登陆记录信息
		for (OnlineAdminsWS ws : clients.values()) {
			sendSpecialMsgToWS(ws, onlineAdmins.values(), RESULT.TRUE);
		}
    }
	
	/**
	 * 
	 * Title:
	 * <p>
	 * Description:發送指定信息到某個ws;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月7日 上午11:21:08
	 * @version 1.0
	 * @param ws
	 * @param rows
	 * @param result
	 */
	public static synchronized void sendSpecialMsgToWS(OnlineAdminsWS ws,Collection<?> rows,Integer result){
		try {
			if(ws == null || !ws.session.isOpen()){//是否允許發送信息
				return;
			}
			//json格式的第一层是客户端自身的admin信息
			AdminsReplier replier = new AdminsReplier();
			replier.setId(ws.adminId);//本客戶端的adminId
			replier.setRows(rows);
			replier.setResult(result);
			String jsonStr = JSON.toJSONString(replier).toString();
			ws.sendMessage(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Title:
     * <p>
     * Description:通過某個adminid獲取websocket;
     * <p>
     * @author Kor_Zhang
     * @date 2017年10月7日 下午1:59:12
     * @version 1.0
     * @param adminId
     * @param newLoginLog
     * @return 
     */
    public static synchronized OnlineAdminsWS getWS(String adminId){
    	return clients.get(adminId);
    }
    
    /**
     * Title:
     * <p>
     * Description:從容器中移除指定的套接字;
     * <p>
     * @author Kor_Zhang
     * @date 2017年10月7日 下午2:16:11
     * @version 1.0
     * @param adminId	key;
     * @param closeWS	移除時是否關閉套接字連接;
     * @return
     */
    public static synchronized OnlineAdminsWS removeWS(String adminId,boolean closeWS){
    	OnlineAdminsWS ws = getWS(adminId);
		if(closeWS){
        	try {
        		if(ws != null){
            		ws.session.close();
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		
		//移除其客户端websocket
        OnlineAdminsWS.clients.remove(adminId);
        
        return ws;
    }
    /**
     * Title:
     * <p>
     * Description:通過某個adminid剛更新某個在綫管理員在ws中的信息;
     * <p>
     * @author Kor_Zhang
     * @date 2017年10月7日 下午1:59:12
     * @version 1.0
     * @param adminId
     * @param newLoginLog
     */
    public static synchronized void updateAdmin(String adminId,AdminsReplier newAdmin){
    	onlineAdmins.put(adminId, newAdmin);
    }
    /**
     * Title:
     * <p>
     * Description:通過某個adminid剛獲取某個在綫管理員在ws中的信息;
     * <p>
     * @author Kor_Zhang
     * @date 2017年10月7日 下午2:01:03
     * @version 1.0
     * @return
     */
    public static synchronized AdminsReplier getAdmin(String adminId){
    	return onlineAdmins.get(adminId);
    }
    /**
     * Title:
     * <p>
     * Description:移除容器中的管理員記錄;
     * <p>
     * @author Kor_Zhang
     * @date 2017年10月7日 下午2:18:03
     * @version 1.0
     * @param adminId	key;
     * @return	返回被移除的管理員
     */
    public static synchronized AdminsReplier removeAdmin(String adminId){
    	
    	AdminsReplier r = onlineAdmins.get(adminId);
    	
    	//移除其记录
        OnlineAdminsWS.onlineAdmins.remove(adminId);
        
    	return r;
    }

    
    
	/**
	 * Title:
	 * <p>
	 * Description:从容器中移除某個id的在綫用戶的相關信息:socket和在綫用戶信息;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月7日 下午2:12:16
	 * @version 1.0
	 * @param adminId	key;
	 * @param closeWS	是否關閉套接字;
	 */
	public static synchronized void removeWSAndAdmin(String adminId,boolean closeWS) {
		removeWS(adminId, closeWS);
		removeAdmin(adminId);
	}
}