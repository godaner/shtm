
/**
 * 变量申明
 */
var clazzs_datagrid = undefined;
var clazzsSearchForm = undefined;
$(function() {//html的dom加载完成后再执行

	initClazzsManageVar();

	loadClazzsManageUI();

});
/**
 * 初始化变量
 */
function initClazzsManageVar() {
	clazzs_datagrid = $("#clazzs_datagrid");
	clazzsSearchForm = $("#clazzsSearchForm");
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
		sortName : 'createtime',
		sortOrder : 'desc', //降序
		onLoadSuccess:function(data){
			responseHandler.handleSuccess(data, function() {

			}, function() {

			});
		    	
	    },
	    onLoadError:function(){
	    	responseHandler.handleFailure();
	    },
		columns : [ [ {
			field : 'id',
			title : '主键',
			sortable : true,
			formatter : function(value, row, index) {
				return value;
			}
		},
		{
			field : 'text',
			title : '名稱',
			sortable : true,
			formatter : function(value, row, index) {
				return value;
			}
		},
		{
			field : 'num',
			title : '商品數量',
			sortable : true,
			formatter : function(value, row, index) {
				return value;
			}
		}] ]
	};
	
	//初始化类型列表的datagrid
	clazzs_datagrid.datagrid(clazzs_datagrid_options);
}

/**
 * 搜索類型
 */
function searchClazzs(){
	//form转js对象
	var searchConditions = clazzsSearchForm.serializeObject();
	clazzs_datagrid.datagrid('load',searchConditions);
}

/**
 * 清空搜索信息
 */
function clearGoodSearch(){
	
	clazzsSearchForm.clearEasyuiForm();
	
}
/**
 * 新增clazzs
 */
function insertClazzs(){
	
}
/**
 * 编辑clazzs
 */
function editClazzs(){
	
}