/**
 * users_manage界面的js
 */

/**
 * 变量申明
 */
var users_datagrid;
var usersSearchForm;
//用户属性表格
//var editUserPG;
//用户信息编辑弹窗
var editUserDialog;
//编辑表单
var editUserForm;
//新增用户表单
var insertUserForm;
//新增用户的弹窗
var insertUserDialog;
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
//请求的用户头像的尺寸
var headimgSize = 60;
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

	insertUserDialog = $("#insertUserDialog");
	insertUserForm = $("#insertUserForm");
	
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
function loadUsersManageUI(){
	
	
	//新增user信息窗口
	insertUserDialog.dialog({   
	    title:"新增用户",
		resizable : true,
		modal : true,
		closed : true,
		borer:false
	});
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
	    pagination:true,
	    striped:true,
	    fitColumns:true,
	    fit: true,
	    pageList: [5 , 10, 20, 30, 40, 50],
	    singleSelect:true,
		checkOnSelect:true,
		selectOncheck:true,
	    sortName : 'registtime',
	    sortOrder : 'desc', //降序
		hideColumn:[[
			{
				field:'id',
				title:'id'/*,
				width:270,
				sortable : true*/
			}
		             ]],
		columns:[[      
	    	{
	    		field:'headimg',
	    		title:'头像',
	    		sortable : true,
				formatter: function(value,row,index){
					if(isEmpty(value)){
						value = "";
					}
					var img = "<img style='width:"+headimgSize+"px;' src ='"+getWebProjectName()+"/users/getHeadImg.action?headimg="+value+"&size="+headimgSize+"&t="+new Date().getTime()+"'/>";
					return img;
				}
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
					var len = 15;
					var v = value;
					
					if(isEmpty(value)){
						return "";
					}
					
					if(value.length>len){
						 v = value.substring(0,len)+"...";
					}
					return "<span title='"+value+"'>"+v+"</span>";
				}
	        	
	        },     
	        {
	        	field:'score',
	        	title:'积分',
	    		sortable : true
	        },     
	        {
	        	field:'money',
	        	title:'余额',
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
 * 编辑users,弹出编辑框
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
	
	//清空旧的文本记录
	editUserForm.clearEasyuiForm();
	
	
	
	
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
	//注入对象的name与form的id对应的表单对象
	editUserForm.writeEasyuiForm(row);
	
}

/**
 * 提交用户修改后的信息
 */
function submitUserEdit(){

	//提示信息
	pro.show("正在修改");
	
	editUserForm.form('submit', {    
	    url:getWebProjectName()+"/users/updateUser.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!editUserForm.form('validate')){
	    		pro.close();
	    		return false;
	    	}
	    	//关闭信息编辑
	    	editUserDialog.dialog('close');
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
	    } ,
	    onLoadError:function(){
	    	//失敗
			//提示信息
			showMsg(data.msg);
	    	//关闭进度条
	    	pro.close();
			//打開信息编辑
			editUserDialog.dialog('open');
	    }
	});  

	
	

}
/**
 * 重置用戶信息
 */
function resetUser(){
	
	editUser();
	
	showMsg("重置成功");
}

/**
 * 删除用户
 */
function deleteUser(){
	//确认删除?
	confirm("确认删除"+currtEditDatagridRow.username+"?",function(r){
		if(r){
			//进度条
			
			
			var id = currtEditDatagridRow.id;
			
			
			var url = "/users/deleteUser.action?id="+id;
			ajax.send(url, function(data){
				//显示信息
				showMsg(data.msg);
				
				//关闭信息编辑
				editUserDialog.dialog('close');
				
				//刷新表格
				users_datagrid.datagrid("reload");
				
			}, function(){
				
			});
		}
	});
	
	
	
}

/**
 * 按条件搜索users
 */
function searchUsers(){
	//form转js对象
	var searchConditions = usersSearchForm.serializeObject();
	
	users_datagrid.datagrid('load',searchConditions);
}

function clearSearch(){
	//清空条件
	usersSearchForm.clearEasyuiForm();
	//重新加载数据
	users_datagrid.datagrid('load',{});
}


/********************************添加用户****************************/

/**
 * 新增users,弹出新增框
 */
function insertUser(){

	
	insertUserDialog.dialog('open');
	
	
}


/**
 * 新增用户
 */
function submitNewUser(){

	
	insertUserForm.form('submit', {    
	    url:getWebProjectName()+"/users/insertUser.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!insertUserForm.form('validate')){
	    		
	    		return false;
	    	}
	    	//关闭信息编辑
	    	insertUserDialog.dialog('close');
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
				insertUserDialog.dialog('open');
			}
	    } ,
	    onLoadError:function(){
	    	//失敗
			//提示信息
			showMsg(data.msg);
	    	//关闭进度条
	    	pro.close();
			//打開信息编辑
	    	insertUserDialog.dialog('open');
	    }
	});  

	
	

}

/**
 * 清空旧的文本记录
 */
function clearInsertUserForm(){
	//清空旧的文本记录
	insertUserForm.clearEasyuiForm();
	
}

