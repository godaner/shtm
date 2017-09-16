/**
 * index界面的js
 */

/**
 * 变量申明
 */
var verifyCodeImg;
var loginBtn;
var loginForm;
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
}
/**
 * 加载界面
 */
function loadLoginUI() {

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
		
		//回调index.js的正在登录方法
		loging();
		//执行ajax
		var url = "/admins/login.action";

		var formObject = loginForm.serializeObject();

		ajax.sendForm(url, formObject, function(data) {
			//连接到服务器后,回调index.js的函數
			afterLogin(data,true);
		},function(data){//连接服务器错误
			afterLogin(data,false);
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
