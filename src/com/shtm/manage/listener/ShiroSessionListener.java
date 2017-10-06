package com.shtm.manage.listener;

import java.io.IOException;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.shtm.manage.po.AdminsLoginLogReplier;
import com.shtm.manage.po.AdminsReplier;
import com.shtm.manage.websocket.OnlineAdminsWS;
import com.shtm.util.ProjectUtil;
import com.shtm.util.Static.RESULT;

@Component
public class ShiroSessionListener extends SessionListenerAdapter{  
  
    @Override  
    public void onStart(Session session) {//会话创建触发 已进入shiro的过滤连就触发这个方法  
        // TODO Auto-generated method stub  
        System.out.println("会话创建：" + session.getId());  
    }  
  
    @Override  
    public void onStop(Session session) {//退出  
        System.out.println("退出会话：" + session.getId());  
        AdminsReplier admin = (AdminsReplier)session.getAttribute(ProjectUtil.FILED_ONLINE_ADMIN);
        if(admin == null){
        	return ;
        }
        //提示websocket的客户端
        String stopAdminId = admin.getId();
        
        //获取离线的websocket客户端
        OnlineAdminsWS stopWS = OnlineAdminsWS.clients.get(stopAdminId);
        
        //移除其客户端
        OnlineAdminsWS.clients.remove(stopAdminId);

        //移除其登录记录
        OnlineAdminsWS.loginLogs.remove(stopAdminId);
    	//通知未离线的websocket最新的登陆记录
        for (OnlineAdminsWS ws : OnlineAdminsWS.clients.values()) {
        	//发送最新登陆记录信息
			try {
				AdminsReplier<AdminsLoginLogReplier> replier = new AdminsReplier<AdminsLoginLogReplier>();
				replier.setRows(OnlineAdminsWS.loginLogs.values());
				replier.setResult(RESULT.TRUE);
				String jsonStr = JSON.toJSONString(replier).toString();
				ws.sendMessage(jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        //通知离线的websoket它的session已离线,它可以做一些善后操作(发送一个空json数组)
        try {
			AdminsReplier<AdminsLoginLogReplier> replier = new AdminsReplier<AdminsLoginLogReplier>();
			replier.setRows(ProjectUtil.getList());
			replier.setResult(RESULT.UNONLINE);
			String jsonStr = JSON.toJSONString(replier).toString();
			stopWS.sendMessage(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }  
  
    @Override  
    public void onExpiration(Session session) {//会话过期时触发  
        // TODO Auto-generated method stub  
        System.out.println("会话过期：" + session.getId());   
        AdminsReplier admin = (AdminsReplier)session.getAttribute(ProjectUtil.FILED_ONLINE_ADMIN);
        if(admin == null){
        	return ;
        }
        //提示websocket的客户端
        String stopAdminId = admin.getId();
        
        //获取离线的websocket客户端
        OnlineAdminsWS stopWS = OnlineAdminsWS.clients.get(stopAdminId);
        
        //移除其客户端
        OnlineAdminsWS.clients.remove(stopAdminId);

        //移除其登录记录
        OnlineAdminsWS.loginLogs.remove(stopAdminId);
    	//通知未离线的websocket最新的登陆记录
        for (OnlineAdminsWS ws : OnlineAdminsWS.clients.values()) {
        	//发送最新登陆记录信息
			try {
				AdminsReplier<AdminsLoginLogReplier> replier = new AdminsReplier<AdminsLoginLogReplier>();
				replier.setRows(OnlineAdminsWS.loginLogs.values());
				replier.setResult(RESULT.TRUE);
				String jsonStr = JSON.toJSONString(replier).toString();
				ws.sendMessage(jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        //通知离线的websoket它的session已离线,它可以做一些善后操作(发送一个空json数组)
        try {
			AdminsReplier<AdminsLoginLogReplier> replier = new AdminsReplier<AdminsLoginLogReplier>();
			replier.setRows(ProjectUtil.getList());
			replier.setResult(RESULT.UNONLINE);
			String jsonStr = JSON.toJSONString(replier).toString();
			stopWS.sendMessage(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }  
  
}  