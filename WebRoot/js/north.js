/**
 * north界面的js
 */

/**
 * 变量申明
 */
var logoutBtn;
var adminUsernameDiv;
$(function(){
	
	
	initNorthVar();
	
	loadNorthUI();

	initNorthLis();
	
	 
});

/**
 * 初始化变量
 */
function initNorthVar(){
	adminUsernameDiv = $("#adminUsernameDiv");
	logoutBtn = $("#logoutBtn");
	
}
/**
 * 加载界面
 */
function loadNorthUI(){
	

}
/**
 * 初始化监听事件
 */
function initNorthLis(){
	/**
	 * 注销按钮的触发事件
	 */
	
	logoutBtn.on("click",function(){
		//回调index.js的loguting
		loguting();
		
		var url = "/admins/logout.action";
		
		ajax.send(
				url,
				function(data){
					//回调index.js的afterLogout
					afterLogout(data);
				});
	});
}

/**
 * 设置username
 * @param un
 */
function setUsername(un){
	adminUsernameDiv.html(un);
	
}



/**
 * 修改主题，更新数据库数据
 */
function updateThemes(newThemeString) {

	/*zkAjax("/adminsAction!updateTheme.action?theme=" + newThemeString,
			function(data) {
				var success = data.success;
				showMsg(data);
				try {
					isOnline(data);
				} catch (e) {
					return;
				}
				if (success) {
					setThemes(newThemeString);
				}
			});*/
	setThemes(newThemeString);
}

/**
 * 设置主题
 * 将easyui导入主题的的css添加id为 easyuiTheme
 * @param newThemeString
 */
function setThemes(newThemeString) {

	setTimeout(function() {
		showProgress("正在加载主题");
	}, 1);
	setTimeout(function() {
		/* 得到现有主题信息 */
		/* ../jquery-easyui-1.4.1/themes/default/easyui.css */
		var currtThemeString = $("#easyuiTheme").attr("href");
		/* 得到替代结束位置 */
		var replaceEndPosition = currtThemeString.indexOf("themes") + 7;
		/* 替代就主题 */
		var newTheme = currtThemeString.substring(0, replaceEndPosition)
				+ newThemeString + "/easyui.css";
		$("#easyuiTheme").attr("href", newTheme);
		/* 保存cookkie主题信息 
		$.cookie("easyuiTheme", newTheme, {
			expires : 365,
			path : '/'
		});*/
	}, 250);
	setTimeout(function() {
		closeProgress();
	}, 500);

}