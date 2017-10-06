/*在綫用戶統計的websocket*/

/**
 * 统计在线管理员的websocket对象
 */
var onlineAdminsSocket;
/**
 * 鏈接在綫用戶統計服務器
 */
function connectOnlineAdminsSocket(adminId){
	if(onlineAdminsSocket != undefined){
		onlineAdminsSocket.close();
	}
	if ('WebSocket' in window) {
		onlineAdminsSocket = new WebSocket(manageWebSocketUrl+"/onlineAdminsWS/"+adminId);
	} else {
		alert('当前浏览器 Not support websocket')
	};
	
	
	onlineAdminsSocket.onmessage = function (event){
		var data = $.parseJSON(event.data);
		c("ws----");
		c(data.length);
		if(data.length == 0){
			//获取的管理员登陆记录列表为0,表示当前的客户端的session已经离线
			
			//关闭所有的dialog
			closeAllEasyuiDialog();
			
			//显示登录弹窗
			loginDialog.show();
			//清空用户名显示
			setUsername("");
			
			//断开websocket
			onlineAdminsSocket.close();
		}
		//刷行在线列表
		refreshOnlineAdminDG(data);
		c("----ws");
		
	};
	
	
}