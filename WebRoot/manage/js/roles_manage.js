/**
 * roles_manage界面的js
 */

/**
 * 变量申明
 */
var roles_datagrid;
var rolesSearchForm;
//管理员属性表格
//var editRolePG;
//管理员信息编辑弹窗
var editRoleDialog;
//编辑表单
var editRoleForm;
//新增管理员表单
var insertRoleForm;
//新增管理员的弹窗
var insertRoleDialog;
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
var rolesSearchId;
var roles_permissions_datagrid;

var editRolePermissionDialog;
$(function(){
	
	initRolesManageVar();
	
	loadRolesManageUI();
	
});

/**
 * 初始化变量
 */
function initRolesManageVar(){
	roles_datagrid = $("#roles_datagrid");
	rolesSearchForm = $("#rolesSearchForm");
	editRolePG = $("#editRolePG");
	editRoleDialog = $("#editRoleDialog");
	editRoleForm = $("#editRoleForm");

	insertRoleDialog = $("#insertRoleDialog");
	insertRoleForm = $("#insertRoleForm");

	rolesSearchId = rolesSearchForm.find("#id");
	
	editRolePermissionDialog = $("#editRolePermissionDialog");
	
	roles_permissions_datagrid= $("#roles_permissions_datagrid");
	
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
function loadRolesManageUI(){
	
	
	//新增role信息窗口
	insertRoleDialog.dialog({   
	    title:"新增管理员",
		resizable : true,
		modal : true,
		closed : true,
		borer:false
	});
	//修改role信息窗口
	editRoleDialog.dialog({   
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
	
	
	//加载roles的datagrid
	roles_datagrid.datagrid({    
	    url:manageForwardUrl+"/roles/selectRolesDatagrid.action",
	    toolbar:"#roles_dg_tb",
	    pagination:true,
	    striped:true,
	    fitColumns:true,
	    fit: true,
	    pageList: [5 , 10, 20, 30, 40, 50],
	    singleSelect:true,
		checkOnSelect:true,
		selectOncheck:true,
	    sortName : 'name',
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
					
					return "<a href=javascript:checkRole('"+row.creator+"');>"+value+"</a>";
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
function deleteRole(){
	
}


/**
 * 编辑roles,弹出编辑框
 */
function editRole(){
	//获取datagrid当前选择行
	var row = roles_datagrid.datagrid('getSelected');
	if(isEmpty(row)){
		showMsg("请先选择行");
		return ;
	}
	
	//保存当前操作的管理员的行
	currtEditDatagridRow = row;
	
	//清空旧的文本记录
	editRoleForm.clearEasyuiForm();
	
	
	
	
	//打开信息编辑
	editRoleDialog.dialog('open');
	
	//修改格式
	row.createtime = new Date(row.createtime).format("yyyy-MM-dd HH:mm:ss");
	
	row.password = "";
	
//	row.status = row.status==0?"冻结":row.status==1?"激活":row.status;
	//注入对象的name与form的id对应的表单对象
	editRoleForm.writeEasyuiForm(row);
	
}

/**
 * 提交管理员修改后的信息
 */
function submitRoleEdit(){

	//提示信息
	pro.show("正在修改");
	
	editRoleForm.form('submit', {  
	    url:manageForwardUrl+"/roles/updateRole.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!editRoleForm.form('validate')){
	    		pro.close();
	    		return false;
	    	}
	    	//关闭信息编辑
	    	editRoleDialog.dialog('close');
	    },    
	    success:function(data){ 

	    	data = JSON.parse(data);
	    	responseHandler.handleSuccess(data, function(){
	    		//更新成功
				
				//刷新表格
				roles_datagrid.datagrid("reload");
	    	}, function(){

				//失敗
				//打開信息编辑
				editRoleDialog.dialog('open');
	    	});
		    	
	    	
	    } ,
	    onLoadError:function(){
		    responseHandler.handleFailure(function(){

				//打開信息编辑
				editRoleDialog.dialog('open');
		    });
	    }
	});  

	
	

}
/**
 * 重置用戶信息
 */
function resetRole(){
	
	editRole();
	
	showMsg("重置成功");
}

/**
 * 删除管理员
 */
function deleteRole(){
	//确认删除?
	confirm("确认删除"+currtEditDatagridRow.username+"?",function(r){
		if(r){
			//进度条
			
			
			var id = currtEditDatagridRow.id;
			
			
			var url = manageForwardUrl+"/roles/deleteRole.action?id="+id;
			ajax.send(url, function(data){
				//显示信息
				showMsg(data.msg);
				
				//关闭信息编辑
				editRoleDialog.dialog('close');
				
				//刷新表格
				roles_datagrid.datagrid("reload");
				
			}, function(){
				
			}, function(){
				
			});
		}
	});
	
	
	
}

/**
 * 按条件搜索roles
 */
function searchRoles(){
	//form转js对象
	var searchConditions = rolesSearchForm.serializeObject();
	
	roles_datagrid.datagrid('load',searchConditions);
}

function clearRoleSearch(){
	//清空条件
	rolesSearchForm.clearEasyuiForm();
	//重新加载数据
	roles_datagrid.datagrid('load',{});
}


/********************************添加管理员****************************/

/**
 * 新增roles,弹出新增框
 */
function insertRole(){

	
	insertRoleDialog.dialog('open');
	
	
}


/**
 * 新增管理员
 */
function submitNewRole(){

	
	insertRoleForm.form('submit', {    
	    url:manageForwardUrl+"/roles/insertRole.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!insertRoleForm.form('validate')){
	    		
	    		return false;
	    	}
	    	//关闭信息编辑
	    	insertRoleDialog.dialog('close');
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	responseHandler.handleSuccess(data, function(){
	    		//更新成功
				//刷新表格
				roles_datagrid.datagrid("reload");
	    	}, function(){
	    		//失敗
				//打開信息编辑
				insertRoleDialog.dialog('open');
	    	});
	    	
	    } ,
	    onLoadError:function(){
	    	
	    	responseHandler.handleFailure(function(){

				//打開信息编辑
		    	insertRoleDialog.dialog('open');
	    	});
	    	
	    }
	});  

	
	

}

/**
 * 清空旧的文本记录
 */
function clearInsertRoleForm(){
	//清空旧的文本记录
	insertRoleForm.clearEasyuiForm();
	
}

/**
 * 查看某个管理员信息
 * @param roleId
 */
function checkRole(roleId){
	
	clearRoleSearch();
	
	rolesSearchId.textbox("setValue",roleId);
	
	searchRoles();
};

/**
 * 编辑管理员角色
 */
function editRolePermission(){
	
	pro.show();
	
	//获取datagrid当前选择行
	var row = roles_datagrid.datagrid('getSelected');
	if(isEmpty(row)){
		showMsg("请先选择行");
		return ;
	}
	
	//保存当前操作的管理员的行
	currtEditDatagridRow = row;
	
	
	var roleId = currtEditDatagridRow.id;
	
	roles_permissions_datagrid.datagrid({ 
	    url: manageForwardUrl +"/roles/selectRolePermissionsById.action?id="+roleId, 
	    fitColumns: true,
	    singleSelect:false,
	    selectOnCheck: true,
	    checkOnSelect: true, 
	    sortName : 'name',
	    sortOrder : 'desc', //降序
	    hideColumn:[[
			{
				field:'id',
				title:'id',
				width:'25%'
			}
	    ]],
	    columns:[[
  	        {
	        	field:'ck',
	     	    checkbox: true
	        },
			{
				field:'name',
				title:'角色名',
				width:'30%',
				sortable:true,
				formatter: function(value,row,index){
					
					return value;  
				}
			},{
				field:'description',
				title:'简介',
				width:'30%',
				sortable:true,
				formatter: function(value,row,index){
					
					return value;  
				}
			},
			{
				field:'grantTime',
				title:'分配角色的时间',
				width:'30%',
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
	    				roles_permissions_datagrid.datagrid("checkRow", i);
	    				roles_permissions_datagrid.datagrid("selectRow", i);
	    	        }else{

	    				roles_permissions_datagrid.datagrid("unselectRow", i);
	    				roles_permissions_datagrid.datagrid("uncheckRow", i);
	    	        }
				}
	    		

	    		//加載成功顯示彈窗
	    		editRolePermissionDialog.dialog({   
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
function submitRolePermissionsEdit(){

	editRolePermissionDialog.dialog("close");
	var selects = roles_permissions_datagrid.datagrid("getChecked");
	var newPermissionsId = [];
	for (var i = 0; i < selects.length; i++) {
		var s = selects[i];
		newPermissionsId.push(s.id);
	}
	var id = currtEditDatagridRow.id;
	var url = manageForwardUrl+"/roles/updateRolePermissions.action";
	var data = {"permissionsIds":newPermissionsId,"id":id};
	ajax.sendJson(url, 
	data, 
	function(data){
		c(data);
		editRolePermissionDialog.dialog("close");
	}, function(){
		editRolePermissionDialog.dialog("open");
	}, function(){
		
	});
}