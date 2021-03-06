/*在綫用戶統計的websocket*/

/**
 * 统计在线管理员的websocket对象
 */
var onlineAdminsSocket;
/**
 * 鏈接在綫用戶統計服務器
 */

//在线
var admin;
function connectOnlineAdminsSocket(adminId){
	if(!isEmpty(onlineAdminsSocket)){//關閉原有的ws
		if(onlineAdminsSocket.readyState == onlineAdminsSocket.CONNECTING){
			closeOnlineAdminsSocket(onlineAdminsSocket);
		}
	}
	if ('WebSocket' in window) {
		onlineAdminsSocket = new WebSocket(manageWebSocketUrl+"/onlineAdminsWS/"+adminId);
	} else {
		alert('当前浏览器 Not support websocket')
	};
	
	
	onlineAdminsSocket.onmessage = function (event){
		var data = $.parseJSON(event.data);
		c("ws----");
		c(data);
		if(data.result == -1){//离线
			//表示当前的客户端的session已经离线
			
			//关闭所有的dialog
			closeAllEasyuiDialog();
			
			//显示登录弹窗
			loginDialog.show();
			//清空用户名显示
			setUsername("");
			
			//重设主题为默认defaultTheme在index.js
			setLocalTheme(defaultTheme);
			
			//關閉ws
			closeOnlineAdminsSocket(onlineAdminsSocket);
		}
		
		//刷行在线列表
		//提取每个admin的登陆记录
		var rows = [];
		for(var i = 0; i<data.rows.length;i++){
			var d  = data.rows[i];
			var loginLog = d.adminsLoginLogReplier;
			if(!isEmpty(loginLog)){
				rows.push(loginLog);
			}
			//獲取本客戶端的admin數據
			if(d.id == data.id){
				admin = d;
			}
			
		}
		
		
		//刷新在线列表
		refreshOnlineAdminDG(rows);
		
//		c(admin);
		//重设主题
		setLocalTheme(admin.theme);

		//用户名显示
		setUsername(admin.username);
		
		c("----ws");
		
	};
	
	
}
/**
 * 關閉在綫用戶統計ws
 * @param ws
 */
function closeOnlineAdminsSocket(ws){
	if(!isEmpty(ws)){
		ws.close();
		ws = undefined;
	}
}