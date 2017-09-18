/**
 * users_manage界面的js
 */

/**
 * 变量申明
 */
var users_datagrid;
$(function(){
	
	initUsersManageVar();
	
	loadUsersManageUI();
	
});

/**
 * 初始化变量
 */
function initUsersManageVar(){
	users_datagrid = $("#users_datagrid");
}
/**
 * 加载界面
 */
function loadUsersManageUI(){
	
	//加载users的datagrid
	users_datagrid.datagrid({    
	    url:getWebProjectName()+"/users/selectUsersDatagrid.action",    
	    columns:[[      
	    	{field:'headimg',title:'头像'},  
	        {field:'id',title:'id'},    
	        {field:'username',title:'名称'},    
	        {field:'email',title:'邮箱'},    
	        {field:'password',title:'密码'},    
	        {field:'salt',title:'盐'},    
	        {field:'sex',title:'性别'},   
	        {field:'birthday',title:'生日'},    
	        {field:'description',title:'介绍'},     
	        {field:'score',title:'积分'},     
	        {field:'registtime',title:'注册时间'},     
	        {field:'status',title:'状态'}      
	    ]]    
	});  


}

