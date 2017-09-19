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
	    	//添加选项卡到index.jsp的tabs
	    	var url = node.attributes.url;
	    	var title = node.text;
	    	if(url == null || url == undefined || url==""){
	    		return ;
	    	}
	    	//加上项目名
	    	url = getWebProjectName()+url;
	    	//添加选项卡
			addTab(title,url);
		},    
	    loadFilter: function(data){    
	        if (data.datagrid){    
	            return data.datagrid;    
	        }   
	    },
	    onLoadSuccess:function(node, data){
	    	//设置url到attributes
	    	for (var i = 0; i < data.length; i++) {
				var d = data[i];
		    	var n = menutree.tree('find',d.id);
		    	
		    	menutree.tree('update',{
		    		target: n.target,
		    		attributes:{
		                 "url":d.url
		             }
		    	});
				
			}
	    },
	    onDblClick:function(node){
	    	menutree.tree("expandAll",node.target);
	    	
	    }
	    


	});  



}

