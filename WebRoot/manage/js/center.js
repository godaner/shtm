/**
 * index界面的js
 */

/**
 * 变量申明
 */
var tabs;
var center_index_panel;
//当前显示的tab的title
var selectTabTitle;
$(function(){
	
	initCenterVar();
	
	loadCenterUI();
	
});

/**
 * 初始化变量
 */
function initCenterVar(){
	tabs = $("#tabs");
	center_index_panel = $("#center_index_panel");
}
/**
 * 加载界面
 */
function loadCenterUI(){
	/**
	 * 初始化tabs
	 */
	tabs.tabs({    
	    border:false,  
	    tools: [{
            iconCls: 'icon-mini-refresh',
            handler: function () {
                refreshTab(selectTabTitle);//刷新当前指定tab
            }
        }],
	    onSelect:function(title){    
	        selectTabTitle = title;
	    }    
	});  
	
	/**
	 * 加载中部首页内容
	 */
	center_index_panel.panel({
		fit : true,
		border : false,
		href : manageStaticSrcUrl + '/view/center_index.jsp',
		onLoad : function() {
			
		}
	});

}
/**
 * 刷新指定title的tab
 * @param title
 */
function refreshTab(title) {
    var tab = tabs.tabs('getSelected');  // 获取选择的面板
    var url = tab.panel('options').href;//获取当前tab的url
    tab.panel('refresh', url);

}
/**
 * 用户传递tab间的参数
 */
var tabParams;
/**
 * 添加选项卡
 * @param title	标题
 * @param url	地址
 * @param params	tab间传递的参数
 */
function addTab(title, url,params){
	
	if (tabs.tabs('exists', title)){
		tabs.tabs('select', title);
	} else {
		//设置参数
		tabParams = params;
//		c(tabParams);
		/*var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';*/
		tabs.tabs('add',{
			title:title,
			href:url,
			closable:true,
		});
		
	}
	
}


