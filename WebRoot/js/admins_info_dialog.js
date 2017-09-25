/**
 * index界面的js
 */

/**
 * 变量申明
 */
var admins_info_dialog;
var adminsInfoDialog;
var admins_info_form;
$(function() {

	initAdminsInfoDialogVar();

	loadAdminsInfoDialogUI();

	/**
	 * 监听事件
	 */
	initAdminsInfoDialogLis();

});

/**
 * 初始化变量
 */
function initAdminsInfoDialogVar() {

	admins_info_dialog = $("#admins_info_dialog");
	
	admins_info_form = $("#admins_info_form");
	
	//初始化loginDialog
	adminsInfoDialog = {
		/**
		 * 显示登录弹窗,传入要显示的数据对象(js对象)
		 */
		show : function(title,formDataObj) {
			admins_info_form.writeEasyuiForm(formDataObj);
			
			
			admins_info_dialog.dialog({title:title});
			
			admins_info_dialog.dialog('open');
		},
		/**
		 * 关闭登录弹窗
		 */
		close : function() {
			admins_info_dialog.dialog('close');
		}
	}
}
/**
 * 加载界面
 */
function loadAdminsInfoDialogUI() {
	/**
	 * 管理员信息弹窗
	 */
	admins_info_dialog.dialog({
		title : '管理员信息',
		closed : true,
		closable : true,
		resizable:true,
		cache : true,
		modal : true,
		onLoad : function() {
		}
	});
}
/**
 * 监听事件
 */
function initAdminsInfoDialogLis() {

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











