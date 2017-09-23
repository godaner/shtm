/**
 * index界面的js
 */

/**
 * 变量申明
 */

$(function() {
//	creatReq("http://localhost:8080/users/info.action");

	
	initIndexVar();
	
	loadIndexUI();
	
	
});


/**
 * 初始化变量
 */
function initIndexVar() {
}
/**
 * 加载界面
 */
function loadIndexUI() {

}

/**
 * 显示指定id的admin
 * @param adminId
 */
function showAdmin(adminId){
	var url = "/admins/selectAdmin.action?id="+adminId;
	ajax.send(url, function(data){
		
	}, function(){
		
	});
}

