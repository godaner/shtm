/**
 * index界面的js
 */

/**
 * 变量申明
 */
var meutree;
$(function(){
	
	initVar();
	
	loadUI();
	
});

/**
 * 初始化变量
 */
function initVar(){
	meutree = $("#meutree");
}
/**
 * 加载界面
 */
function loadUI(){
	//加载树
	meutree.tree({    
	    url:getWebProjectName()+'/js/menutree.json'   
	});  
	//树节点触发事件
	meutree.tree({
		onClick: function(node){
			alert(node.text);  // 在用户点击的时候提示
		}
	});



}

