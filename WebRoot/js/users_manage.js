/**
 * users_manage界面的js
 */

/**
 * 变量申明
 */
var users_datagrid;
var usersSearchForm;
//用户属性表格
var editUserPG;
//用户信息编辑弹窗
var editUserDialog;
//编辑表单
var editUserForm;
//sex
var sexInput;
//status
var statusInput;
//birthday
var birthday;
//datebox的buttons
var buttons;
//保存当前编辑的用户的信息
var currtEditDatagridRow;
$(function(){
	
	initUsersManageVar();
	
	loadUsersManageUI();
	
});

/**
 * 初始化变量
 */
function initUsersManageVar(){
	users_datagrid = $("#users_datagrid");
	usersSearchForm = $("#usersSearchForm");
	editUserPG = $("#editUserPG");
	editUserDialog = $("#editUserDialog");
	editUserForm = $("#editUserForm");
	
	//sex
	sexInput = $("#sex");
	//status
	statusInput = $("#status");
	
	birthday = $("#birthday");
	
	//设置按钮
	birthday.datebox({
	});
	
	//设置清空按钮
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
		text: '清空',
		handler: function(target){
			birthday.datebox('setText','');
		}
	});
	//设置按钮
	birthday.datebox({
		buttons: buttons
	});

	
}
/**
 * 加载界面
 */
function loadUsersManageUI(){
	//修改user信息窗口
	editUserDialog.dialog({   
	    title:"属性编辑",
		resizable : true,
		modal : true,
		closed : true,
		borer:false
	});
	
	
	//加载users的datagrid
	users_datagrid.datagrid({    
	    url:getWebProjectName()+"/users/selectUsersDatagrid.action",
	    toolbar:"#c_i_tb",
	    autoRowHeight:true,
	    fitColumns:true,
	    pagination:true,
	    striped:true,
	    pageList: [5 , 10, 20, 30, 40, 50],
	    fit: true,
	    sortName : 'registtime',
	    sortOrder : 'asc', //降序
	    singleSelect:true,
		checkOnSelect:true,
		selectOncheck:true,
	    columns:[[      
	    	{
	    		field:'headimg',title:'头像',
	    		sortable : true,
				formatter: function(value,row,index){
					if(isEmpty(value)){
						value = "";
					}
					var img = "<img style='width:100px;' src ='"+getWebProjectName()+"/users/getHeadImg.action?headimg="+value+"&t="+new Date().getTime()+"'/>";
					return img;
				}
    		},  
	        {
    			field:'id',
    			title:'id',
	    		sortable : true
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
	        	field:'sex',
	        	title:'性别',
	    		sortable : true,
				formatter: function(value,row,index){
					if(value == 1){
						return "男";
					}
					if(value == 0){
						return "女";
					}
					if(value == -1){
						return "未设置";
					}
					return value;
				}
	        },   
	        {
	        	field:'birthday',
	        	title:'生日',
	    		sortable : true,
	        	formatter: function(value,row,index){
//	        		c(value);
	        		if(!isEmpty(value)){
		        		value = new Date(value).format("yyyy-MM-dd");
	        		}else{
	        			value="未设置";
	        		}
					return value;
				}
	        	
	        },    
	        {
	        	field:'description',
	        	title:'介绍',
	    		sortable : true,
				formatter: function(value,row,index){
					if(!isEmpty(value) && value.length>15){
						return value.substring(0,15)+"...";
					}
					return value;
				}
	        	
	        },     
	        {
	        	field:'score',
	        	title:'积分',
	    		sortable : true
	        },     
	        {
	        	field:'registtime',
	        	title:'注册时间',
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
 * 删除用户
 */
function deleteUser(){
	
}


/**
 * 编辑users
 */
function editUser(){
	//获取datagrid当前选择行
	var row = users_datagrid.datagrid('getSelected');
	if(isEmpty(row)){
		showMsg("请先选择行");
		return ;
	}
	
	//保存当前操作的用户的行
	currtEditDatagridRow = row;
	
	//打开信息编辑
	editUserDialog.dialog('open');
	
	//修改格式
	row.registtime = new Date(row.registtime).format("yyyy-MM-dd HH:mm:ss");
	
	if(!isEmpty(row.birthday)){
		row.birthday = new Date(row.birthday).format("yyyy-MM-dd");
	}else{
		row.birthday = "";
	}
	row.password = "";
	//注入普通参数(input)
	editUserForm.writeEasyuiForm(row);
	//注入select
//	sexInput.combobox("setValue",row.sex);
//	statusInput.combobox("setValue",row.status);
	
}

/**
 * 提交用户修改后的信息
 */
function submitUserEdit(){

	//提示信息

	pro.show("正在修改");

	//关闭信息编辑
	editUserDialog.dialog('close');
	
	
	editUserForm.form('submit', {    
	    url:getWebProjectName()+"/users/updateUser.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!editUserForm.form('validate')){
	    		return false;
	    	}
	    },    
	    success:function(data){ 
//	    	c(data);
	    	data = JSON.parse(data);
	    	
	    	pro.close();
			//提示信息
			showMsg(data.msg);

			if(data.result == 1){
				//更新成功
				
				//刷新表格
				users_datagrid.datagrid("reload");
				
			}else{
				//失敗
				//打開信息编辑
				editUserDialog.dialog('open');
			}
	    }    
	});  

	
	/*//验证表单
	if(!editUserForm.form('validate')){
		return ;
	}
	//提示信息

	pro.show("正在修改");

	//关闭信息编辑
	editUserDialog.dialog('close');
	
	//转化form表单的easyui的input域为表单参数
	var params = editUserForm.readEasyuiForm();
	
	
	var url = "/users/updateUser.action?"+params;

//	c(url);
	
	//请求更新
	ajax.send(url, function(data){
		
		pro.close();
		//提示信息
		showMsg(data.msg);

		if(data.result == 1){
			//更新成功
			
			//刷新表格
			users_datagrid.datagrid("reload");
			
		}else{
			//失敗
			//打開信息编辑
			editUserDialog.dialog('open');
		}
	}, function(){
		//提示信息
		showMsg("修改失败");

		pro.close();
		
		//打開信息编辑
		editUserDialog.dialog('open');
	});*/

}
/**
 * 重置用戶信息
 */
function resetUser(){
	
	editUser();
}
/**
 * 按条件搜索users
 */
function searchUsers(){
	//form转js对象
	var searchConditions = usersSearchForm.serializeObject();
	
	users_datagrid.datagrid('load',searchConditions);
}