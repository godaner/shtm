/**
 * index界面的js
 */

/**
 * 变量申明
 */
var layout_west;
$(function() {
//	creatReq("http://localhost:8080/users/info.action");

	
	initIndexVar();
	
	loadIndexUI();
	
	
});
/**
 * 加载界面
 */
function loadIndexUI() {
	
}


/**
 * 初始化变量
 */
function initIndexVar() {
	
	layout_west = $("#layout_west");
	
	layout_west.panel({
		tools:[
               {
				   iconCls:'icon-reload',
				   handler:function(){
					   //重新加載西部
					   loadWestUI();
				   }
               }]
	});
}





