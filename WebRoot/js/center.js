/**
 * index界面的js
 */

/**
 * 变量申明
 */
var tabs;
$(function(){
	
	initCenterVar();
	
	loadCenterUI();
	
});

/**
 * 初始化变量
 */
function initCenterVar(){
	tabs = $("#tabs");
}
/**
 * 加载界面
 */
function loadCenterUI(){
	tabs.tabs({    
	    border:false,    
	    onSelect:function(title){    
	        /*alert(title+' is selected'); */   
	    }    
	});  

}
/**
 * 添加选项卡
 * @param title	标题
 * @param url	地址
 */
function addTab(title, url){
	if (tabs.tabs('exists', title)){
		tabs.tabs('select', title);
	} else {
		/*var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';*/
		tabs.tabs('add',{
			title:title,
			href:url,
			closable:true
		});
	}
}

