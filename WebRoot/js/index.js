/**
 * index界面的js
 */

/**
 * 变量申明
 */
//北部面板
var north_panel;
//西部面板
var west_panel;
//东部面板
var east_panel;
//登录弹窗
var login_dialog;
$(function(){
	
	initVar();
	
	loadUI();
	
});

/**
 * 初始化变量
 */
function initVar(){
	//北部面板
	north_panel = $("#north_panel");
	//西部面板
	west_panel = $("#west_panel");
	//东部面板
	east_panel = $("#east_panel");
	//登录弹窗
	login_dialog = $("#login_dialog");
}
/**
 * 加载界面
 */
function loadUI(){
	/**
	 * 加载北部
	 */
	north_panel.panel({ 
		fit:true,
	    href:getWebProjectName()+'/view/north.jsp',    
	    onLoad:function(){    
	    }    
	});  

	/**
	 * 加载西部
	 */
	west_panel.panel({ 
		fit:true,
	    href:getWebProjectName()+'/view/west.jsp',    
	    onLoad:function(){    
	    }    
	});  
	/**
	 * 加载东部
	 */
	east_panel.panel({ 
		fit:true,
	    href:getWebProjectName()+'/view/east.jsp',    
	    onLoad:function(){    
	    }    
	});  
	
	/**
	 * 登录弹窗
	 */
	login_dialog.dialog({    
	    title: '登录',    
	    width: 320,    
	    height: 280,    
	    closed: false, 
	    closable:false,
	    cache: false,    
	    modal: true, 
	    href:getWebProjectName()+"/view/login.jsp"
	});    
}

