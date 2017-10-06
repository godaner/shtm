/**
 * index界面的js
 */

/**
 * 变量申明
 */
var verifyCodeImg;
var loginBtn;
var loginForm;
//登录弹窗
var login_dialog;
/**
 * 登录弹窗对象;在login_dialog加载完成后被初始化;
 */
var loginDialog
$(function() {

	initLoginVar();

	loadLoginUI();

	/**
	 * 监听事件
	 */
	initLoginLis();

});

/**
 * 初始化变量
 */
function initLoginVar() {
	verifyCodeImg = $("#verifyCodeImg");
	loginBtn = $("#loginBtn");
	loginForm = $('#loginForm');
	//登录弹窗
	login_dialog = $("#login_dialog");
	
	
	
	//初始化loginDialog
	loginDialog = {
		/**
		 * 显示登录弹窗
		 */
		show : function() {
			login_dialog.dialog('open');
			refreshVerifyCode();
		},
		/**
		 * 关闭登录弹窗
		 */
		close : function() {
			login_dialog.dialog('close');
		}
	}
	
}
/**
 * 加载界面
 */
function loadLoginUI() {

	
	login_dialog.dialog({
		title : '登录',
		width : 320,
		height : 285,
		closed : true,
		closable : false,
		cache : true,
		modal : true,
		onLoad : function() {
		}
	});
	
	
	/**
	 * 獲取在綫的admin,判斷是否應該打開登錄框
	 */
	ajax.send(manageForwardUrl+"/admins/getOnlineAdmin.action", 
	function(data){
		//在线(不在线的话,ajax内部会处理)
		
		//加载主题
		setLocalTheme(data.theme);

		//初始化websocket
		connectOnlineAdminsSocket(data.id);
		//初始化websocket
		onlineAdminsSocket.onopen = function(){
			var p = JSON.stringify(data.adminsLoginLogReplier);
			onlineAdminsSocket.send(p);
		}
			

	}, function(data){
		
	}, function(){
		
	})
	
}
/**
 * 监听事件
 */
function initLoginLis() {
	/**
	 * 点击验证码刷新
	 */
	verifyCodeImg.on("click", function() {
		refreshVerifyCode();
	});
	/**
	 * 点击登录按钮
	 */
	loginBtn.on("click", function() {
		
		loginDialog.close();
		pro.show("正在登录");
		
		//获取登录信息
		var loginInfo = getLoginInfo();
		//执行ajax
		var url = manageForwardUrl+"/admins/login.action?"+loginInfo;


		var formParams = loginForm.serializeParams();

		url = url + "&" + formParams;
		

//		c(url);
		
		ajax.send(url, function(data) {
//			c(data);
			
			/**
			 * 登录成功
			 */
			//如果连接服务器成功
			//加载用户名
			setUsername(data.username);

			//加载主题

			setLocalTheme(data.theme);
			
			//连接websocket,记录在线管理员信息
			

			var wsParams = JSON.stringify(data.adminsLoginLogReplier);
			

			//初始化websocket
			connectOnlineAdminsSocket(data.id);

			onlineAdminsSocket.onopen = function(){
				
				onlineAdminsSocket.send(wsParams);
			}
			
			
		}, function(){//登录失败
			loginDialog.show();
		},function(data){//连接服务器错误
			
			//显示登录窗口
			loginDialog.show();
			
			
		});

	});

}




/**
 * 刷新登录验证码
 */
function refreshVerifyCode() {
	verifyCodeImg.attr("src", manageForwardUrl
			+ "/admins/verifyCode.action?t=" + (new Date().getTime()));
}












