/**
 * index界面的js
 */

/**
 * 变量申明
 */
var menutree;
$(function(){
	
	initWestVar();
	
	loadWestUI();
	
});

/**
 * 初始化变量
 */
function initWestVar(){
	menutree = $("#menutree");
}
/**
 * 加载界面
 */
function loadWestUI(){
	//加载树
	menutree.tree({    
	    url:getWebProjectName()+'/permissions/selectPermissionsByPid.action',
	    onClick: function(node){
			alert(node.text);  // 在用户点击的时候提示
		},    
	    loadFilter: function(data){    
	        if (data.datagrid){    
	            return data.datagrid;    
	        }   
	    }


	});  



}

