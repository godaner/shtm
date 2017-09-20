<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/center.css">


<div id="tabs" class="easyui-tabs"
	data-options="closable:true,fit:true,
	tools:[{    
        iconCls:'icon-mini-refresh',    
        handler:function(){ 
        	//刷新
        	$('#tabs').datagrid('reload');   
        }    
    }]    
	">
	<!-- tabs的直接子元素只能使用简单div -->
	<div data-options="fit:true" title="主页">
		<!-- 预留内容加载区域 -->
		<div  id="center_index_panel" style="border:none;" data-options="border:false" class="easyui-panel" title="">
		
		</div>
	</div>

</div>

