/**
 * admins_manage界面的js
 */

/**
 * 变量申明
 */
var admins_datagrid;
var adminsSearchForm;
//管理员属性表格
//var editAdminPG;
//管理员信息编辑弹窗
var editAdminDialog;
//编辑表单
var editAdminForm;
//新增管理员表单
var insertAdminForm;
//新增管理员的弹窗
var insertAdminDialog;
//sex
var sexInput;
//status
var statusInput;
//birthday
var birthday;
//datebox的buttons
var buttons;
//保存当前编辑的管理员的信息
var currtEditDatagridRow;
//请求的管理员头像的尺寸
var headimgSize = 60;
var adminsSearchId;
var admins_roles_datagrid;

var editAdminRoleDialog;
$(function(){
	
	initAdminsManageVar();
	
	loadAdminsManageUI();
	
});

/**
 * 初始化变量
 */
function initAdminsManageVar(){
	admins_datagrid = $("#admins_datagrid");
	adminsSearchForm = $("#adminsSearchForm");
	editAdminPG = $("#editAdminPG");
	editAdminDialog = $("#editAdminDialog");
	editAdminForm = $("#editAdminForm");

	insertAdminDialog = $("#insertAdminDialog");
	insertAdminForm = $("#insertAdminForm");

	adminsSearchId = adminsSearchForm.find("#id");
	
	editAdminRoleDialog = $("#editAdminRoleDialog");
	
	admins_roles_datagrid= $("#admins_roles_datagrid");
	
	//sex
	sexInput = $("#sex");
	//status
	statusInput = $("#status");
	
	birthday = $("#birthday");
	
	
	//设置按钮
	birthday.datebox({
	});
	
	//设置datebox清空按钮
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
		text: '清空',
		handler: function(target){
			birthday.datebox('setText','');
		}
	});
	//设置datebox按钮
	birthday.datebox({
		buttons: buttons
	});

	
}
/**
 * 加载界面
 */
function loadAdminsManageUI(){
	
	
	//新增admin信息窗口
	insertAdminDialog.dialog({   
	    title:"新增管理员",
		resizable : true,
		modal : true,
		closed : true,
		borer:false
	});
	//修改admin信息窗口
	editAdminDialog.dialog({   
	    title:"属性编辑",
		resizable : true,
		modal : true,
		closed : true,
		borer:false
	});
	
	
	/*id	1
	username	123
	password	4297f44b13955235245b2497399d7a93
	salt	123
	status	1
	createtime	1505441889000
	creator	null
	theme	bootstrap
	email	null*/
	
	
	//加载admins的datagrid
	admins_datagrid.datagrid({    
	    url:manageForwardUrl+"/admins/selectAdminsDatagrid.action",
	    toolbar:"#admins_dg_tb",
	    pagination:true,
	    striped:true,
	    fitColumns:true,
	    fit: true,
	    pageList: [5 , 10, 20, 30, 40, 50],
	    singleSelect:true,
		checkOnSelect:true,
		selectOncheck:true,
	    sortName : 'createtime',
	    sortOrder : 'desc', //降序
	    onLoadSuccess:function(data){
	    	responseHandler.handleSuccess(data, function(){
	    		
	    	}, function(){
	    		
	    	});
	    	
	    },
	    onLoadError:function(){
	    	responseHandler.handleFailure();
	    },
		hideColumn:[[
			{
				field:'creator',
				title:'创建者id'
			}
		             ]],
		columns:[[ 
			{
				field:'id',
				title:'id'/*,
				width:270,
				sortable : true*/
			},
	        {
    			field:'username',
    			title:'名称',
	    		sortable : true
    		},    
	        {
    			field:'email',
    			title:'邮箱',
	    		sortable : true
    		},    
	        {
	        	field:'password',
	        	title:'密码',
	    		sortable : true,
        		formatter: function(value,row,index){
					return "******";
				}
	        },    
	        /*{
	        	field:'salt',
	        	title:'盐',
	    		sortable : true
	        },*/    
	        {
	        	field:'creatorName',
	        	title:'创建者',
	    		sortable : true,
				formatter: function(value,row,index){
					
					if(isEmpty(value)){
						return "无";
					}
					
					return "<a href=javascript:checkAdmin('"+row.creator+"');>"+value+"</a>";
				}
	        },
	        {
	        	field:'theme',
	        	title:'主题',
	    		sortable : true
	        },
	        {
	        	field:'createtime',
	        	title:'创建时间',
	    		sortable : true,
				formatter: function(value,row,index){
					
					return new Date(value).format("yyyy-MM-dd HH:mm:ss");
				}
	        },     
	        {
	        	field:'status',
	        	title:'状态',
	    		sortable : true,
				formatter: function(value,row,index){
					if(value == 1){
						return "激活";
					}
					if(value == 0){
						return "冻结";
					}
					return value;
				}
	        }      
	    ]]    
	});  


}


/**
 * 删除管理员
 */
function deleteAdmin(){
	
}


/**
 * 编辑admins,弹出编辑框
 */
function editAdmin(){
	//获取datagrid当前选择行
	var row = admins_datagrid.datagrid('getSelected');
	if(isEmpty(row)){
		showMsg("请先选择行");
		return ;
	}
	
	//保存当前操作的管理员的行
	currtEditDatagridRow = row;
	
	//清空旧的文本记录
	editAdminForm.clearEasyuiForm();
	
	
	
	
	//打开信息编辑
	editAdminDialog.dialog('open');
	
	//修改格式
	row.createtime = new Date(row.createtime).format("yyyy-MM-dd HH:mm:ss");
	
	row.password = "";
	
//	row.status = row.status==0?"冻结":row.status==1?"激活":row.status;
	//注入对象的name与form的id对应的表单对象
	editAdminForm.writeEasyuiForm(row);
	
}

/**
 * 提交管理员修改后的信息
 */
function submitAdminEdit(){

	//提示信息
	pro.show("正在修改");
	
	editAdminForm.form('submit', {  
	    url:manageForwardUrl+"/admins/updateAdmin.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!editAdminForm.form('validate')){
	    		pro.close();
	    		return false;
	    	}
	    	//关闭信息编辑
	    	editAdminDialog.dialog('close');
	    },    
	    success:function(data){ 

	    	data = JSON.parse(data);
	    	responseHandler.handleSuccess(data, function(){
	    		//更新成功
				
				//刷新表格
				admins_datagrid.datagrid("reload");
	    	}, function(){

				//失敗
				//打開信息编辑
				editAdminDialog.dialog('open');
	    	});
		    	
	    	
	    } ,
	    onLoadError:function(){
		    responseHandler.handleFailure(function(){

				//打開信息编辑
				editAdminDialog.dialog('open');
		    });
	    }
	});  

	
	

}
/**
 * 重置用戶信息
 */
function resetAdmin(){
	
	editAdmin();
	
	showMsg("重置成功");
}

/**
 * 删除管理员
 */
function deleteAdmin(){
	//确认删除?
	confirm("确认删除"+currtEditDatagridRow.username+"?",function(r){
		if(r){
			//进度条
			
			
			var id = currtEditDatagridRow.id;
			
			
			var url = manageForwardUrl+"/admins/deleteAdmin.action?id="+id;
			ajax.send(url, function(data){
				//显示信息
				showMsg(data.msg);
				
				//关闭信息编辑
				editAdminDialog.dialog('close');
				
				//刷新表格
				admins_datagrid.datagrid("reload");
				
			}, function(){
				
			}, function(){
				
			});
		}
	});
	
	
	
}

/**
 * 按条件搜索admins
 */
function searchAdmins(){
	//form转js对象
	var searchConditions = adminsSearchForm.serializeObject();
	
	admins_datagrid.datagrid('load',searchConditions);
}

function clearAdminSearch(){
	//清空条件
	adminsSearchForm.clearEasyuiForm();
	//重新加载数据
	admins_datagrid.datagrid('load',{});
}


/********************************添加管理员****************************/

/**
 * 新增admins,弹出新增框
 */
function insertAdmin(){

	
	insertAdminDialog.dialog('open');
	
	
}


/**
 * 新增管理员
 */
function submitNewAdmin(){

	
	insertAdminForm.form('submit', {    
	    url:manageForwardUrl+"/admins/insertAdmin.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!insertAdminForm.form('validate')){
	    		
	    		return false;
	    	}
	    	//关闭信息编辑
	    	insertAdminDialog.dialog('close');
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	responseHandler.handleSuccess(data, function(){
	    		//更新成功
				//刷新表格
				admins_datagrid.datagrid("reload");
	    	}, function(){
	    		//失敗
				//打開信息编辑
				insertAdminDialog.dialog('open');
	    	});
	    	
	    } ,
	    onLoadError:function(){
	    	
	    	responseHandler.handleFailure(function(){

				//打開信息编辑
		    	insertAdminDialog.dialog('open');
	    	});
	    	
	    }
	});  

	
	

}

/**
 * 清空旧的文本记录
 */
function clearInsertAdminForm(){
	//清空旧的文本记录
	insertAdminForm.clearEasyuiForm();
	
}

/**
 * 查看某个管理员信息
 * @param adminId
 */
function checkAdmin(adminId){
	
	clearAdminSearch();
	
	adminsSearchId.textbox("setValue",adminId);
	
	searchAdmins();
};

/**
 * 编辑管理员角色
 */
function editAdminRole(){
	
	pro.show();
	
	//获取datagrid当前选择行
	var row = admins_datagrid.datagrid('getSelected');
	if(isEmpty(row)){
		showMsg("请先选择行");
		return ;
	}
	
	//保存当前操作的管理员的行
	currtEditDatagridRow = row;
	
	
	var adminId = currtEditDatagridRow.id;
	
	admins_roles_datagrid.datagrid({ 
	    url: manageForwardUrl +"/admins/selectAdminRolesById.action?id="+adminId, 
	    fitColumns: true,
	    singleSelect:false,
	    selectOnCheck: true,
	    checkOnSelect: true, 
	    sortName : 'name',
	    sortOrder : 'desc', //降序
	    hideColumn:[[
	    ]],
	    columns:[[
  	        /*{
	        	field:'ck',
	     	    checkbox: true,
				width:'5%'
	        },*/
			{
				field:'id',
				title:'id',
				width:'25%'
			},
			{
				field:'name',
				title:'角色名',
				width:'25%',
				sortable:true,
				formatter: function(value,row,index){
					
					return value;  
				}
			},{
				field:'description',
				title:'简介',
				width:'25%',
				sortable:true,
				formatter: function(value,row,index){
					
					return value;  
				}
			},
			{
				field:'grantTime',
				title:'分配角色的时间',
				width:'25%',
				sortable:true,
				formatter: function(value,row,index){
					
					if(!isEmpty(value)){
						return timeFormatter.formatTime(value);
					}
					
					return value;  
				}
			}   
	              
	              
	    ]],
	    onLoadSuccess:function(data){
	    	responseHandler.handleSuccess(data, function(){
	    		
	    		
	    		//修改datalist的checked
	    		var rows = data.rows;
	    		for (var i = 0; i < rows.length; i++) {
	    			var r = rows[i];
	    			var grantTime = r.grantTime;
	    			if (!isEmpty(grantTime)) {
	    				admins_roles_datagrid.datagrid("checkRow", i);
	    				admins_roles_datagrid.datagrid("selectRow", i);
	    	        }else{

	    				admins_roles_datagrid.datagrid("unselectRow", i);
	    				admins_roles_datagrid.datagrid("uncheckRow", i);
	    	        }
				}
	    		

	    		//加載成功顯示彈窗
	    		editAdminRoleDialog.dialog({   
	    		    title:"管理员角色编辑",
	    			resizable : true,
	    			modal : true,
	    			closed : false,
	    			borer:false
	    		});
	    		
	    		
	    	}, function(){
	    		
	    	});
	    	
	    },
	    onLoadError:function(){
	    	responseHandler.handleFailure();
	    }
	});  

	
}

/**
 * 提交对管理员角色的修改
 */
function submitAdminRolesEdit(){

	editAdminRoleDialog.dialog("close");
	var selects = admins_roles_datagrid.datagrid("getChecked");
	var newRolesId = [];
	for (var i = 0; i < selects.length; i++) {
		var s = selects[i];
		newRolesId.push(s.id);
	}
	var id = currtEditDatagridRow.id;
	var url = manageForwardUrl+"/admins/updateAdminRoles.action";
	var data = {"rolesIds":newRolesId,"id":id};
	ajax.sendJson(url, 
	data, 
	function(data){
		c(data);
		editAdminRoleDialog.dialog("close");
	}, function(){
		editAdminRoleDialog.dialog("open");
	}, function(){
		
	});
}