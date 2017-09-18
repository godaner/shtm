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
		
		//调用util.js的方法
		pro.show("正在注销");
		
		var url = "/admins/logout.action";
		
		ajax.send(
				url,
				function(data){
					//连接服务器成功

					//重设主题为默认defaultTheme在index.js
					setLocalTheme(defaultTheme);

					//调用util.js的方法
					pro.close();

					//调用util.js的方法
					showMsg(data.msg);

					if (data.result == 1) {
						loginDialog.show();
						//调用north方法
						setUsername("");
						return;
					}
					
					
				},function(data){
					//调用util.js的方法
					pro.close();
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

			showMsg(data.msg);
			
			//同步成功
			if(data.result == 1){
				//设置主题
				setLocalTheme(newThemeString);
				
			}
			
			
		},function(data){
			pro.close();
			showMsg("同步主题失敗");
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
