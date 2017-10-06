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
 * 刷新在綫管理员的datagrid
 * @param data
 */
function refreshOnlineAdminDG(data){
	onlineAdmins_datagrid.datagrid({    
		title:"共 "+data.length+" 人在线",
		toolbar: [{
			iconCls: 'icon-remove',
			text:"踢出",
			handler: function(){
				kickOutAdmin();
			}
		}]
		,
		data:data,
		hideColumn:[[
			{
				field:'loginAdmin',
				title:'id'/*,
				width:270,
				sortable : true*/
			},
			{
				field:'province'
			}
			,
			{
				field:'city'
			}
			
		]],
		columns:[[ 
		    {
				field:'adminName',
				title:'名稱'
			},
			
			
			{
				field:'addrs',
				title:'地址',
				formatter: function(value,row,index){
					var province = row.province;
					var city = row.city;
					return province+"-"+city;
				}
			}
			,
			{
				field:'ip',
				title:'ip'
			},
			{
				field:'time',
				title:'登陸時間',
				formatter: function(value,row,index){
					return timeFormatter.formatTime(value);
				}
			}
			    
	    ]]    
	});  
}

/**
 * 踢出管理员
 */
var currtDatagridOnlineRow;
function kickOutAdmin(){
	//获取datagrid当前选择行
	var row = onlineAdmins_datagrid.datagrid('getSelected');
	if(isEmpty(row)){
		showMsg("请先选择行");
		return ;
	}
	
	var adminId = row.loginAdmin;
	var url = manageForwardUrl+"/admins/kickOutAdmin.action?id="+adminId;
	ajax.send(url, function(data){
		
	}, function(){
		
	}, function(){
		
	});
	
}