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
		//格式化参数
		data.createtime = new Date(data.createtime).format("yyyy-MM-dd HH:mm:ss");
		
		data.status = data.status==0?"冻结":data.status==1?"激活":data.status;
		
		//传入需要显示的数据对象(js对象)
		adminsInfoDialog.show("管理员 '"+data.username+"' 的信息",data);
	}, function(){
		
	});
}

