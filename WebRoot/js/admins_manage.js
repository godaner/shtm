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
	    url:getWebProjectName()+"/admins/selectAdminsDatagrid.action",
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
					
					if(!isEmpty(value)){
						return "<a href=javascript:showAdmin('"+row.creator+"');>"+value+"</a>"
					}
					return "";
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
	    url:getWebProjectName()+"/admins/updateAdmin.action",    
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
//	    	c(data);
	    	data = JSON.parse(data);
	    	
	    	pro.close();
			//提示信息
			showMsg(data.msg);

			if(data.result == 1){
				//更新成功
				
				//刷新表格
				admins_datagrid.datagrid("reload");
				
			}else{
				//失敗
				//打開信息编辑
				editAdminDialog.dialog('open');
			}
	    } ,
	    onLoadError:function(){
	    	//失敗
			//提示信息
			showMsg(data.msg);
	    	//关闭进度条
	    	pro.close();
			//打開信息编辑
			editAdminDialog.dialog('open');
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
			
			
			var url = "/admins/deleteAdmin.action?id="+id;
			ajax.send(url, function(data){
				//显示信息
				showMsg(data.msg);
				
				//关闭信息编辑
				editAdminDialog.dialog('close');
				
				//刷新表格
				admins_datagrid.datagrid("reload");
				
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
	    url:getWebProjectName()+"/admins/insertAdmin.action",    
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
//	    	c(data);
	    	data = JSON.parse(data);
	    	
	    	pro.close();
			//提示信息
			showMsg(data.msg);

			if(data.result == 1){
				//更新成功
				
				//刷新表格
				admins_datagrid.datagrid("reload");
				
			}else{
				//失敗
				//打開信息编辑
				insertAdminDialog.dialog('open');
			}
	    } ,
	    onLoadError:function(){
	    	//失敗
			//提示信息
			showMsg(data.msg);
	    	//关闭进度条
	    	pro.close();
			//打開信息编辑
	    	insertAdminDialog.dialog('open');
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

