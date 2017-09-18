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
}
/**
 * 加载界面
 */
function loadUsersManageUI(){
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
					return value;
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
	        {
	        	field:'salt',
	        	title:'盐',
	    		sortable : true
	        },    
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
					return new Date(value).toLocaleDateString();
				}
	        	
	        },    
	        {
	        	field:'description',
	        	title:'介绍',
	    		sortable : true,
				formatter: function(value,row,index){
					if(value.length>15){
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
					
					return new Date(value).toLocaleTimeString();
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
 * 编辑users
 */
function editUser(){
	//获取datagrid当前选择行
	var row = users_datagrid.datagrid('getSelected');
	if(row == null){
		showMsg("请先选择行");
		return ;
	}
	
	//转化数据格式,放入propertygrid
	var rows = [];
	for (name in row)
	{
		var value = row[name];
		var o = {};
		
		if(name == "headimg"){
			continue;
		}else if(name == "id"){
			o = {"name":name,"value":value};
		}else if(name == "email"){
			o = {"name":name,"value":value,"editor":{
				"type":"textbox",    
		        "options":{    
		            "validType":"email",
		            "required":true
		            	
		        }    

			}};
		}else if(name == "sex"){
			value = value==1?"男":value==0?"女":"未设置";
			o = {"name":name,"value":value,"editor":{
				"type":"combobox",
				"options":{
					"editable":false,
					"data": [
	 				         	{
									"id" : '1',
									"text" : '男'
								},
								{
									"id" : '0',
									"text" : '女'
								},
								{
									"id" : '-1',
									"text" : '未选择'
								}
							]
				}
				
  
			}};
		}else if(name == "birthday"){
			o = {"name":name,"value":value,"editor":{
				"type":"datebox",    
		        "options":{    
		        	"editable":false
		        }    

			}};
		}else {
			o = {"name":name,"value":value,"editor":"text"};
		}
		
		
		
		
		rows.push(o);
		

	}  
	//propertygrid数据
	var data = {"rows":rows};
	
	editUserDialog.dialog({
		resizable : true,
		modal : true,
		closed : false,
		width : 500,
		height : 400
	});
	editUserPG.propertygrid({    
	       
	    showGroup: true,  
	    fit:true,
	    scrollbarSize: 0,
	    showGroup:false,
	    data:data
	    
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