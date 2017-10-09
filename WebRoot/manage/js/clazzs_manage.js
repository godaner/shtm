
/**
 * 变量申明
 */
//类型数据表格
var clazzs_datagrid = undefined;
//进行类型的表单
var clazzsSearchForm = undefined;

//类型属性编辑弹窗 
var editClazzDialog;
//进行类型编辑的form表单
var editClazzForm;

//新增clazz的弹窗
var insertClazzDialog;
//新增clazz表单
var insertClazzForm;
$(function() {//html的dom加载完成后再执行

	//初始化变量
	initClazzsManageVar();
	//加载界面
	loadClazzsManageUI();

});
/**
 * 初始化变量
 */
function initClazzsManageVar() {
	clazzs_datagrid = $("#clazzs_datagrid");
	clazzsSearchForm = $("#clazzsSearchForm");
	
	editClazzDialog = $("#editClazzDialog");
	editClazzForm = $("#editClazzForm");
	
	
	insertClazzDialog = $("#insertClazzDialog");
	insertClazzForm = $("#insertClazzForm");
}
/**
 * 加载界面
 */
function loadClazzsManageUI() {
	
	//申明clazzs_datagrid的参数clazzs_datagrid_options
	var clazzs_datagrid_options = {
		//请求数据的地址
		url : manageForwardUrl + "/clazzs/selectClazzsDatagrid.action",
		//指定工具欄
		toolbar : "#clazzs_dg_tb",
		//queryParams:users_to_goods_tab_context.getContent(),
		//分頁
		pagination : true,
		striped : true,
		fitColumns : true,
		fit : true,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		singleSelect : true,
		checkOnSelect : true,
		selectOncheck : true,
		sortName : 'num',
		sortOrder : 'desc', //降序
		onLoadSuccess:function(data){
			responseHandler.handleSuccess(data, function() {

			}, function() {

			});
		    	
	    },
	    onLoadError:function(){
	    	responseHandler.handleFailure();
	    },
	    //将返回的json对象填入datagrid指定列名的列;[{"id":"01","text":"aa"},{"id":"02","text":"bb"}]
		columns : [ [ {
			field : 'id',
			title : '主键',
			width:100,
			sortable : true,
			formatter : function(value, row, index) {
				return value;
			}
		},
		{
			field : 'text',
			title : '名稱',
			sortable : true,
			width:100,
			formatter : function(value, row, index) {
				return value;
			}
		},
		{
			field : 'num',
			title : '商品數量',
			sortable : true,
			width:100,
			formatter : function(value, row, index) {
				return value;
			}
		}] ]
	};
	
	//初始化类型列表的datagrid
	clazzs_datagrid.datagrid(clazzs_datagrid_options);
	
	
	//新增clazz弹窗
	insertClazzDialog.dialog({   
	    title:"新增",
		resizable : true,
		modal : true,
		closed : true,
		borer:false
	});
	
	//编辑clazz弹窗
	editClazzDialog.dialog({   
	    title:"属性编辑",
		resizable : true,
		modal : true,
		closed : true,
		borer:false
	});
	
	
}

/**
 * 搜索類型
 */
function searchClazzs(){
	//input[name#value]=>{"id":"1","name":"lhy"}
	var searchConditions = clazzsSearchForm.serializeObject();
//	c(searchConditions);
	clazzs_datagrid.datagrid('load',searchConditions);
}

/**
 * 清空搜索信息
 */
function clearGoodSearch(){
	
	clazzsSearchForm.clearEasyuiForm();
	searchClazzs();
	
}
/**
 * 新增clazzs
 */
function insertClazzs(){
	
}
/**
 * 编辑clazzs
 */
var currtClzzsRow;
function editClazzs(){
	//获取datagrid当前选择行
	var row = clazzs_datagrid.datagrid('getSelected');
	if(isEmpty(row)){
		showMsg("请先选择行");
		return ;
	}
	
	//保存当前操作的管理员的行
	currtClzzsRow = row;
	
	//清空旧的文本记录
	editClazzForm.clearEasyuiForm();
	
	//打开信息编辑
	editClazzDialog.dialog("open");
	
	//注入对象的name与form的id对应的表单对象
	editClazzForm.writeEasyuiForm(row);
}




/**
 * 提交管理员修改后的信息
 */
function submitClazzEdit(){

	//提示信息
	pro.show("正在修改");
	
	//用easyui提交表单的方式提交;
	editClazzForm.form('submit', {  
	    url:manageForwardUrl+"/clazzs/updateClazzs.action",    
	    ajax:true,
	    //提交表单前的回调事件
	    onSubmit: function(){   
	    	//使用easyui的表单验证
	    	if(!editClazzForm.form('validate')){
	    		pro.close();
	    		return false;
	    	}
	    	//关闭信息编辑
	    	editClazzDialog.dialog('close');
	    },   
	    //服务器回复
	    success:function(data){ 

	    	data = JSON.parse(data);
	    	responseHandler.handleSuccess(data, function(){
	    		//关闭信息编辑
		    	editClazzDialog.dialog('close');
				//刷新表格
				clazzs_datagrid.datagrid("reload");
	    	}, function(){

				//失敗
				//打開信息编辑
				editClazzDialog.dialog('open');
	    	});
		    	
	    	
	    } ,
	    //连接服务器失败
	    onLoadError:function(){
		    responseHandler.handleFailure(function(){

				//打開信息编辑
				editClazzDialog.dialog('open');
		    });
	    }
	});  

	
	

}
/**
 * 提交一个新clazz
 */
function submitNewClazz(){
	insertClazzForm.form('submit', {    
	    url:manageForwardUrl+"/clazzs/insertClazz.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!insertClazzForm.form('validate')){
	    		
	    		return false;
	    	}
	    	//关闭信息编辑
	    	insertClazzDialog.dialog('close');
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	responseHandler.handleSuccess(data, function(){
	    		//更新成功
				//刷新表格
				clazzs_datagrid.datagrid("reload");
	    	}, function(){
	    		//失敗
				//打開信息编辑
				insertClazzDialog.dialog('open');
	    	});
	    	
	    } ,
	    onLoadError:function(){
	    	
	    	responseHandler.handleFailure(function(){

				//打開信息编辑
		    	insertClazzDialog.dialog('open');
	    	});
	    	
	    }
	});  
}

/**
 * 清除添加表单的数据
 */
function clearInsertClazzForm(){
	//清空旧的文本记录
	insertClazzForm.clearEasyuiForm();
}

/**
 * 重置clazz信息
 */
function resetClazz(){
	
	editClazzs();
	
	showMsg("重置成功");
}