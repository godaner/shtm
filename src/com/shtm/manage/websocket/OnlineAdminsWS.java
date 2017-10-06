package com.shtm.manage.websocket;

import java.io.IOException;
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
import com.shtm.manage.po.AdminsLoginLogReceiver;
import com.shtm.manage.po.AdminsLoginLogReplier;
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
	public static Map<String, AdminsLoginLogReplier> loginLogs = new ConcurrentHashMap<String, AdminsLoginLogReplier>();  
	//websocket的session
	private Session session;      
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
		// 从集合中删除
		clients.remove(adminId); 
		loginLogs.remove(adminId);
		//发送最新登陆记录信息
		for (OnlineAdminsWS ws : clients.values()) {
			try {
				AdminsReplier<AdminsLoginLogReplier> replier = new AdminsReplier<AdminsLoginLogReplier>();
				replier.setRows(loginLogs.values());
				replier.setResult(RESULT.TRUE);
				String jsonStr = JSON.toJSONString(replier).toString();
				ws.sendMessage(jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
		AdminsLoginLogReceiver receiver = JSON.parseObject(message, AdminsLoginLogReceiver.class);
		
		//是否有新的admin登陸記錄,没有新的登陆记录说明其只是想获取登陆列表
		if(receiver != null && receiver.getLoginAdmin() !=null && !receiver.getLoginAdmin().trim().isEmpty()){
			AdminsLoginLogReplier replier = new AdminsLoginLogReplier();
			
			BeanUtils.copyProperties(receiver, replier);
			
			loginLogs.put(replier.getLoginAdmin(), replier);
		}
		
		//推送最新登陆记录信息
		for (OnlineAdminsWS ws : clients.values()) {
			try {
				AdminsReplier<AdminsLoginLogReplier> replier = new AdminsReplier<AdminsLoginLogReplier>();
				replier.setRows(loginLogs.values());
				replier.setResult(RESULT.TRUE);
				String jsonStr = JSON.toJSONString(replier).toString();
				ws.sendMessage(jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
	/**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
}