/**
 * index界面的js
 */

/**
 * 变量申明
 */
//北部面板
var north_panel;
//西部面板
var west_panel;
//东部面板
var east_panel;
//北部面板
var center_panel;
//登录弹窗
var login_dialog;
//登录框加载后是否直接关闭,不现实
var login_dialog_closed = true
$(function() {

	
	initIndexVar();
	
	loadIndexUI();
	
	//加载页面时第一个执行的方法;这个方法的执行顺序是由index.js的拜访顺序决定的;
	onLoadPage();
	
});

/**
 * 加载页面时第一个执行的方法;
 */
function onLoadPage(){

	//获取index.jsp的onlineUser参数,判断用户数是否在线
	if (onlineUsername == undefined || onlineUsername == "") {
		//离线
		login_dialog_closed = false;
	}else{
		//在线
		
		//加载主题
		setLocalTheme(onlineUserTheme);
		
		
	}
	
	
	
	
}

/**
 * 初始化变量
 */
function initIndexVar() {
	//北部面板
	north_panel = $("#north_panel");
	//西部面板
	west_panel = $("#west_panel");
	//东部面板
	east_panel = $("#east_panel");
	//北部面板
	center_panel = $("#center_panel");
	//登录弹窗
	login_dialog = $("#login_dialog");
}
/**
 * 加载界面
 */
function loadIndexUI() {

	/**
	 * 加载北部
	 */
	north_panel.panel({
		fit : true,
		border : false,
		href : getWebProjectName() + '/view/north.jsp',
		onLoad : function() {
		}
	});

	/**
	 * 加载西部
	 */
	west_panel.panel({
		fit : true,
		border : false,
		href : getWebProjectName() + '/view/west.jsp',
		onLoad : function() {
		}
	});
	/**
	 * 加载东部
	 */
	east_panel.panel({
		fit : true,
		border : false,
		href : getWebProjectName() + '/view/east.jsp',
		onLoad : function() {
		}
	});
	/**
	 * 加载北部
	 */
	center_panel.panel({
		fit : true,
		border : false,
		href : getWebProjectName() + '/view/center.jsp',
		onLoad : function() {
		}
	});
	/**
	 * 登录弹窗
	 */
	
	login_dialog.dialog({
		title : '登录',
		width : 320,
		height : 285,
		closed : login_dialog_closed,
		closable : false,
		cache : true,
		modal : true,
		href : getWebProjectName() + "/view/login.jsp"
	});

}

/**
 * 登录弹窗
 */
var loginDialog = {
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

/**********************************其他js的回调方法 start **********************************/

/**
 * north.js正在注销时的回调
 */
function loguting() {
	//调用util.js的方法
	pro.show("正在注销");
}
/**
 * north.js注销后的回调
 */
function afterLogout(d,success) {
	if(success == false){//连接服务其错误
		//调用util.js的方法
		pro.close();
		
		return;
	}
	//连接服务器成功
	
	//重设主题为默认defaultTheme在index.js
	setLocalTheme(defaultTheme);
	
	//调用util.js的方法
	pro.close();

	//调用util.js的方法
	showMsg(d.msg);

	if (d.result == 1) {
		loginDialog.show();
		//调用north方法
		setUsername("");
		return;
	}

}

/**
 * login.js登陸后回调的方法
 */
function afterLogin(d,success) {
	if(success == false){//连接服务其错误
		//调用util.js的方法
		pro.close();
		//显示登录窗口
		loginDialog.show();
		return;
	}
	//连接服务器成功

	//调用util.js的方法
	showMsg(d.msg);

	//调用util.js的方法
	pro.close();

	if (d == undefined || d == "" || d.result == undefined || d.result == 0) {

		loginDialog.show();
		return;
	}
	/**
	 * 登录成功
	 */
	//如果连接服务器成功
	//调用north.js的setUsername()加载用户名
	setUsername(d.username);
	
	//调用north.js的加载主题
	
	setLocalTheme(d.theme);
	

}

/**
 * login.js正在登录时的回调
 */
function loging() {
	loginDialog.close();
	pro.show("正在登录");
}

/**********************************其他js的回调方法 end **********************************/
