/**
 * admins_edit_self_dialog界面的js
 */

/**
 * 变量申明
 */
var editAdminSelfDialog;
var editAdminSelfForm; 
$(function(){
	
	initAdminsEditSelfVar();
	
	loadAdminsEditSelfUI();
	
});

/**
 * 初始化变量
 */
function initAdminsEditSelfVar(){
	editAdminSelfDialog = $("#editAdminSelfDialog");
	editAdminSelfForm = $("#editAdminSelfForm");

}
/**
 * 初始化界面
 */
function loadAdminsEditSelfUI(){
	
}

/**
 * 显示编辑自身信息的弹窗
 */
function showEditAdminSelfDialog(){
	/**
	 * 獲取在綫的admin
	 */
	ajax.send(manageForwardUrl+"/admins/getOnlineAdmin.action", 
	function(data){
		
		//格式化數據
		data.createtime = timeFormatter.formatTime(data.createtime);
		//填充数据
		editAdminSelfForm.writeEasyuiForm(data);

		//修改admin自身信息窗口
		editAdminSelfDialog.dialog({   
		    title:"屬性编辑",
			resizable : true,
			modal : true,
			closed : false,
			borer:false
		});

	}, function(data){
		
	}, function(){
		
	});
	
	
}

/**
 * 提交修改
 */
function submitAdminSelfEdit(){
	pro.show();
	editAdminSelfForm.form('submit', {  
	    url:manageForwardUrl+"/admins/updateAdminSelf.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!editAdminSelfForm.form('validate')){
	    		pro.close();
	    		return false;
	    	}
	    	//关闭信息编辑
	    	editAdminSelfDialog.dialog('close');
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	responseHandler.handleSuccess(data, function(){
	    		
	    		//更新成功
	    		//重设主题为默认defaultTheme在index.js
				setLocalTheme(data.theme);
				
				//调用north方法
				setUsername(data.username);
				//重新連接websocket
				onlineAdminsSocket.close();
				onlineAdminsSocket.onclose = function(){
					connectOnlineAdminsSocket(data.id);
					onlineAdminsSocket.onopen = function(){
						var params = JSON.stringify(data.adminsLoginLogReplier);
						onlineAdminsSocket.send(params);
					};
				}
				
					
				
	    	}, function(){

				//失敗
				//打開信息编辑
	    		editAdminSelfDialog.dialog('open');
	    	});
		    	
	    	
	    } ,
	    onLoadError:function(){
		    responseHandler.handleFailure(function(){

				//打開信息编辑
		    	editAdminSelfDialog.dialog('open');
		    });
	    }
	});  
}



