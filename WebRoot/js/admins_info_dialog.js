/**
 * index界面的js
 */

/**
 * 变量申明
 */
var admins_info_dialog;
var adminsInfoDialog;
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
	
	
	
	//初始化loginDialog
	adminsInfoDialog = {
		/**
		 * 显示登录弹窗
		 */
		show : function() {
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
		width : 320,
		height : 285,
		closed : true,
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
function initAdminsInfoDialogLis() {

}
















