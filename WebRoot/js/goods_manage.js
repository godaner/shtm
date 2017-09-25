/**
 * goods_manage界面的js
 */

/**
 * 变量申明
 */
var goods_datagrid;
var goodsSearchForm;
//管理员属性表格
//var editGoodPG;
//管理员信息编辑弹窗
var editGoodDialog;
//编辑表单
var editGoodForm;
//新增管理员表单
var insertGoodForm;
//新增管理员的弹窗
var insertGoodDialog;
//保存当前编辑的管理员的信息
var currtEditDatagridRow;
//地址
var province;
var city;
var county;
$(function(){
	
	initGoodsManageVar();
	
	loadGoodsManageUI();
	
});

/**
 * 初始化变量
 */
function initGoodsManageVar(){
	goods_datagrid = $("#goods_datagrid");
	goodsSearchForm = $("#goodsSearchForm");
	editGoodPG = $("#editGoodPG");
	editGoodDialog = $("#editGoodDialog");
	editGoodForm = $("#editGoodForm");

	insertGoodDialog = $("#insertGoodDialog");
	insertGoodForm = $("#insertGoodForm");
	
	
	province = $("#province");
	city = $("#city");
	county = $("#county");
	
	
	
	
}
/**
 * 加载界面
 */
function loadGoodsManageUI(){
	
	
	//新增good信息窗口
	insertGoodDialog.dialog({   
	    title:"新增管理员",
		resizable : true,
		modal : true,
		closed : true,
		borer:false
	});
	//修改good信息窗口
	editGoodDialog.dialog({   
	    title:"属性编辑",
		resizable : true,
		modal : true,
		closed : true,
		borer:false
	});
	
	
	/*id	1
	username	123
	password	4297f44b13955235245b2497399d7a93
	salt	123
	status	1
	createtime	1505441889000
	creator	null
	theme	bootstrap
	email	null*/
	
	
	//加载goods的datagrid
	goods_datagrid.datagrid({    
	    url:getWebProjectName()+"/goods/selectGoodsDatagrid.action",
	    toolbar:"#goods_dg_tb",
	    pagination:true,
	    striped:true,
	    fitColumns:true,
	    fit: true,
	    pageList: [5 , 10, 20, 30, 40, 50],
	    singleSelect:true,
		checkOnSelect:true,
		selectOncheck:true,
	    sortName : 'createtime',
	    sortOrder : 'desc', //降序
		hideColumn:[[
			{
				field:'buyer',
				title:'购买者id'
			},
			{
				field:'owner',
				title:'发布者id'
			}
		             ]],
		columns:[[ 
			{
				field:'id',
				title:'id'/*,
				width:270,
				sortable : true*/
			},
    		{
    			field:'status',
    			title:'状态',
	    		sortable : true,
				formatter: function(value,row,index){
					
					return statusCode2String(value);
				}
    		},
	        {
    			field:'title',
    			title:'名称',
	    		sortable : true
    		},
    		{
    			field:'description',
    			title:'描述',
	    		sortable : true
    		},
    		{
    			field:'sprice',
    			title:'原价',
	    		sortable : true
    		},
    		{
    			field:'price',
    			title:'转让价格',
	    		sortable : true
    		},
    		{
    			field:'condition',
    			title:'新旧度',
	    		sortable : true
    		},
    		{
    			field:'regionDetail',
    			title:'地区',
	    		sortable : true
    		},    
	        {
    			field:'createtime',
    			title:'创建时间',
	    		sortable : true,
	    		formatter: function(value,row,index){
	    			if(!isEmpty(value)){
	    				return new Date(value).format("yyyy-MM-dd HH:mm:ss")
	    			}
	    			return value;
	    		}
    		},    
	        {
    			field:'ownerName',
    			title:'发布者',
	    		sortable : true
    		},    
	        {
    			field:'buyerName',
    			title:'购买者',
	    		sortable : true
    		},    
	        {
    			field:'browsenumber',
    			title:'浏览次数',
	    		sortable : true
    		},    
	        {
    			field:'lastupdatetime',
    			title:'最后一次被用户更新时间',
    			formatter: function(value,row,index){
	    			if(!isEmpty(value)){
	    				return new Date(value).format("yyyy-MM-dd HH:mm:ss")
	    			}
	    			return value;
	    		},
	    		sortable : true
    		},    
	        {
    			field:'buytime',
    			title:'被购买时间',
	    		sortable : true
    		},    
	        {
    			field:'finishtime',
    			title:'交易正常完成时间',
	    		sortable : true
    		}
			    
	    ]]    
	});  


}


/**
 * 删除管理员
 */
function deleteGood(){
	
}


/**
 * 编辑goods,弹出编辑框
 */
function editGood(){
	//获取datagrid当前选择行
	var row = goods_datagrid.datagrid('getSelected');
	if(isEmpty(row)){
		showMsg("请先选择行");
		return ;
	}
	
	//保存当前操作的管理员的行
	currtEditDatagridRow = row;
	

	/**
	 * 清空旧的文本记录
	 */
	editGoodForm.clearEasyuiForm();
	
	
	
	
	/**
	 * 打开信息编辑
	 */
	editGoodDialog.dialog('open');
	
	/**
	 * 修改格式
	 */
	//格式化日期
	row.createtime = timeFormatter.formatTime(row.createtime);
	row.lastupdatetime = timeFormatter.formatTime(row.lastupdatetime);
	row.buytime = timeFormatter.formatTime(row.buytime);
	row.finishtime = timeFormatter.formatTime(row.finishtime);
	
	//获取省-市-县
	var regions = row.regionDetail.split("-");
	row.province = regions[0];
	row.city = regions[1];
	row.county = regions[2];
	
	//状态文字匹配
	row.status = statusCode2String(row.status);
	
	/**
	 * 注入对象的name与form的id对应的表单对象
	 */
	editGoodForm.writeEasyuiForm(row);
	

	/**
	 * 设置地区选择框
	 */
	/*data: [{
		label: 'java',
		value: 'Java'
	},{
		label: 'perl',
		value: 'Perl'
	},{
		label: 'ruby',
		value: 'Ruby'
	}]*/
	

	var pid = "1";
	var url = '/region/selectRegionByPid.action';
	
	ajax.sendSync(url+"?pid="+pid, function(data){
		
		setRegionsToCombox(province,data);
		
		/*//加载市
		province.combobox({ 
			valueField: 'value',
			textField: 'text',
		    onSelect:function(record){
		    	c(record);
		    },
		    data:data
		}); */
		
		province.combobox("select",row.province);
		
	});
	
	pid = province.combobox("getValue");
	c(pid);
	/*
	ajax.sendSync(url+"?pid="+pid, function(data){
			
		setRegionsToCombox(city,data);
		
		city.combobox({ 
			valueField: 'value',
			textField: 'text',
		    onSelect:function(record){
		    	c(record);
		    }
		});  
		
		
	});*/
	/*ajax.sendSync(url+"?pid=1", function(data){
		
		setRegionsToCombox(province,data);
		
		province.combobox({ 
		    onSelect:function(record){
		    	ajax.sendSync(url+"?pid="+record.text, function(data){
		    		setRegionsToCombox(city,data);
		    	});
		    }
		});  
		
		
	});*/
	
	
}
/*
function updateCity(pid){
	ajax.sendSync(url+"?pid=1", function(data){
		
		setRegionsToCombox(province,data);
		
		province.combobox({ 
		    onSelect:function(record){
		    	ajax.sendSync(url+"?pid="+record.text, function(data){
		    		setRegionsToCombox(city,data);
		    	});
		    }
		});  
		
		
	});
}*/

/**
 * 设置参数到指定的combox
 */
function setRegionsToCombox(combox,data){
	var regions = data.childs;
	var data = [];
	for ( var i in regions) {
		var region = regions[i];
		data.push({"text":region.name,"value":region.id});
	}
	combox.combobox({ 
		valueField: 'value',
		textField: 'text',
	    data:data,
	    onChange:function(newValue,oldValue){
	    	c(newValue);
	    	c(oldValue);
	    },
	    onSelect:function(r){
	    	c(r);
	    }
	}); 
}

/**
 * 提交管理员修改后的信息
 */
function submitGoodEdit(){

	//提示信息
	pro.show("正在修改");
	
	editGoodForm.form('submit', {    
	    url:getWebProjectName()+"/goods/updateGood.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!editGoodForm.form('validate')){
	    		pro.close();
	    		return false;
	    	}
	    	//关闭信息编辑
	    	editGoodDialog.dialog('close');
	    },    
	    success:function(data){ 
//	    	c(data);
	    	data = JSON.parse(data);
	    	
	    	pro.close();
			//提示信息
			showMsg(data.msg);

			if(data.result == 1){
				//更新成功
				
				//刷新表格
				goods_datagrid.datagrid("reload");
				
			}else{
				//失敗
				//打開信息编辑
				editGoodDialog.dialog('open');
			}
	    } ,
	    onLoadError:function(){
	    	//失敗
			//提示信息
			showMsg(data.msg);
	    	//关闭进度条
	    	pro.close();
			//打開信息编辑
			editGoodDialog.dialog('open');
	    }
	});  

	
	

}
/**
 * 重置用戶信息
 */
function resetGood(){
	
	editGood();
	
	showMsg("重置成功");
}

/**
 * 删除管理员
 */
function deleteGood(){
	//确认删除?
	confirm("确认删除"+currtEditDatagridRow.username+"?",function(r){
		if(r){
			//进度条
			
			
			var id = currtEditDatagridRow.id;
			
			
			var url = "/goods/deleteGood.action?id="+id;
			ajax.send(url, function(data){
				//显示信息
				showMsg(data.msg);
				
				//关闭信息编辑
				editGoodDialog.dialog('close');
				
				//刷新表格
				goods_datagrid.datagrid("reload");
				
			}, function(){
				
			});
		}
	});
	
	
	
}

/**
 * 按条件搜索goods
 */
function searchGoods(){
	//form转js对象
	var searchConditions = goodsSearchForm.serializeObject();
	
	goods_datagrid.datagrid('load',searchConditions);
}

function clearGoodSearch(){
	//清空条件
	goodsSearchForm.clearEasyuiForm();
	//重新加载数据
	goods_datagrid.datagrid('load',{});
}


/********************************添加管理员****************************/

/**
 * 新增goods,弹出新增框
 */
function insertGood(){

	
	insertGoodDialog.dialog('open');
	
	
}


/**
 * 新增管理员
 */
function submitNewGood(){

	
	insertGoodForm.form('submit', {    
	    url:getWebProjectName()+"/goods/insertGood.action",    
	    ajax:true,
	    iframe:false,
	    onSubmit: function(){   
	    	if(!insertGoodForm.form('validate')){
	    		
	    		return false;
	    	}
	    	//关闭信息编辑
	    	insertGoodDialog.dialog('close');
	    },    
	    success:function(data){ 
//	    	c(data);
	    	data = JSON.parse(data);
	    	
	    	pro.close();
			//提示信息
			showMsg(data.msg);

			if(data.result == 1){
				//更新成功
				
				//刷新表格
				goods_datagrid.datagrid("reload");
				
			}else{
				//失敗
				//打開信息编辑
				insertGoodDialog.dialog('open');
			}
	    } ,
	    onLoadError:function(){
	    	//失敗
			//提示信息
			showMsg(data.msg);
	    	//关闭进度条
	    	pro.close();
			//打開信息编辑
	    	insertGoodDialog.dialog('open');
	    }
	});  

	
	

}

/**
 * 清空旧的文本记录
 */
function clearInsertGoodForm(){
	//清空旧的文本记录
	insertGoodForm.clearEasyuiForm();
	
}

/**
 * 转化状态码为状态字符
 */
function statusCode2String(statusCode){
	
	/*状态:2为已发货，
	 * 1为购买了且待发货，,
	 * 0为创建且待购买,
	 * -1为买家收货后交易正常结束，
	 * -2为卖家取消了出售本商品，
	 * -3是用户取消购买本商品，
	 * -4管理员取消发布的商品,
	 * -5为管理员删除了商品*/
	var s = statusCode;
	switch (statusCode) {
		case 1:
			s = "购买了且待发货";
			break;
		case 2:
			s = "购买了且待发货";
			break;
		case 0:
			s = "创建待购买";
			break;
		case -1:
			s = "买家收货后交易正常结束";
			break;
		case -2:
			s = "卖家取消了出售本商品";
			break;
		case -3:
			s = "用户取消购买本商品";
			break;
		case -4:
			s = "管理员取消发布的商品";
			break;
		case -5:
			s = "管理员删除了商品";
			break;
		default:
			break;
	}
	
	return s;
}
