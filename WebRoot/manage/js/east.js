/**
 * index界面的js
 */

/**
 * 变量申明
 */
var onlineAdmins_datagrid;
$(function(){
	
	initEastVar();
	
	loadEastUI();
	
});

/**
 * 初始化变量
 */
function initEastVar(){
	onlineAdmins_datagrid = $("#onlineAdmins_datagrid");
	
	
}
/**
 * 加载界面
 */
function loadEastUI(){
	

}

/**
 * 刷新在綫
 * @param data
 */
function refreshOnlineAdminDG(data){
	onlineAdmins_datagrid.datagrid({    
		data:data,
		onLoadSuccess:function(data){
			responseHandler.handleSuccess(data, function() {

			}, function() {

			});
		    	
	    },
	    onLoadError:function(){
	    	responseHandler.handleFailure();
	    },
		hideColumn:[[
			{
				field:'loginAdmin',
				title:'id'/*,
				width:270,
				sortable : true*/
			},
			{
				field:'addrs',
				title:'地址',
				sortable : true
			}
			,
			{
				field:'addrs',
				title:'地址',
				sortable : true
			}
			,
			{
				field:'addrs',
				title:'地址',
				sortable : true
			}
		             ]],
		columns:[[ 
		    {
				field:'adminName',
				title:'名稱'
			},
			{
				field:'time',
				title:'登陸時間'
			},
			{
				field:'ip',
				title:'ip'
			},
			{
				field:'addrs',
				title:'地址',
				sortable : true
			}
			    
	    ]]    
	});  
}
