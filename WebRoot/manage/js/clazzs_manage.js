
/**
 * 变量申明
 */
var clazzs_datagrid;
$(function() {

	initClazzsManageVar();

	loadClazzsManageUI();

});
/**
 * 初始化变量
 */
function initClazzsManageVar() {
	clazzs_datagrid = $("#clazzs_datagrid");
}
/**
 * 加载界面
 */
function loadClazzsManageUI() {

	//類型列表
	clazzs_datagrid.datagrid({
		url : manageForwardUrl + "/clazzs/selectClazzsDatagrid.action",
		toolbar : "#clazzs_dg_tb",
		//queryParams:users_to_goods_tab_context.getContent(),
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
	});
}