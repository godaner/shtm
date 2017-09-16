/**
 * north界面的js
 */

/**
 * 变量申明
 */
var logoutBtn;
var usernameDisplayer;
$(function(){
	
	
	initNorthVar();
	
	loadNorthUI();

	initNorthLis();
	
	 
});

/**
 * 初始化变量
 */
function initNorthVar(){
	usernameDisplayer = $("#usernameDisplayer");
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
	usernameDisplayer.html(un);
	
}



/**
 * 修改管理员的主题,并且同步远端
 * @param newThemeString 	新的主题
 * @param sync	是否更新数据库
 *  
 */
function updateThemes(newThemeString) {

	
	pro.show("正在同步主题");
	
	//执行ajax
	var url = "/admins/updateTheme.action";

	ajax.sendJson(
		url, 
		{theme:newThemeString}, 
		function(data) {
			
			pro.close();
		

			//设置主题
			setLocalTheme(newThemeString);
			
			showMsg("同步主题成功");
			
		},function(){
			pro.close();
		});
	
	
	
}
/**
 * 设置本地主题
 * @param newThemeString
 */
function setLocalTheme(newThemeString){
	var theme = "default";
	if(newThemeString != undefined && newThemeString != ""){
		theme = newThemeString;
	}	
	//得到现有主题信息 
	// ../jquery-easyui-1.4.1/themes/default/easyui.css 
	var currtThemeString = $("#easyuiTheme").attr("href");
	// 得到替代结束位置 
	var replaceEndPosition = currtThemeString.indexOf("themes") + 7;
	// 替代就主题 
	var newTheme = currtThemeString.substring(0, replaceEndPosition)
			+ theme + "/easyui.css";
	$("#easyuiTheme").attr("href", newTheme);
}
