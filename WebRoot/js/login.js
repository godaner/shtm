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
//登录框加载后是否直接关闭,不现实
var login_dialog_closed = true;
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
	/**
	 * 登录弹窗
	 */
	if (onlineUsername == undefined || onlineUsername == "") {
		//离线
		login_dialog_closed = false;
	}else{
		//在线
		//加载主题
		setLocalTheme(onlineUserTheme);
	}
	login_dialog.dialog({
		title : '登录',
		width : 320,
		height : 285,
		closed : login_dialog_closed,
		closable : false,
		cache : true,
		modal : true,
		onLoad : function() {
		}
	});
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
		//执行ajax
		var url = "/admins/login.action";

		var formObject = loginForm.serializeObject();

		ajax.sendForm(url, formObject, function(data) {
			//连接服务器成功

			//调用util.js的方法
			showMsg(data.msg);

			//调用util.js的方法
			pro.close();

			if (data == undefined || data == "" || data.result == undefined || data.result == 0) {

				loginDialog.show();
				return;
			}
			
			/**
			 * 登录成功
			 */
			//如果连接服务器成功
			//加载用户名
			setUsername(data.username);

			//加载主题

			setLocalTheme(data.theme);
			
		},function(data){//连接服务器错误
			
			pro.close();
			//显示登录窗口
			loginDialog.show();
		});

	});

}




/**
 * 刷新登录验证码
 */
function refreshVerifyCode() {
	verifyCodeImg.attr("src", getWebProjectName()
			+ "/admins/verifyCode.action?t=" + (new Date().getTime()));
}
