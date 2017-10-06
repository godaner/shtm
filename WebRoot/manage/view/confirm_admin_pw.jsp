<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>

<div id="confirmAdminPWDialog" class="easyui-dialog" title="确认您的密码" style="width:300px;overflow:hidden;"   
        data-options="resizable:true,modal:true,closed:true">   
	<div style="margin: 20px 10%;width:80%;">
		<input id="pw" class="easyui-textbox" data-options="required:true,prompt:'请输入您的密码'" style="width:100%">
	</div>
	<div style="margin: 20px 0px;text-align:center;width:100%;height:20px;">
		<a id="confirmAdminPWBtn" href="javascript:confirmAdminPW();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">检验</a>   
	</div>
</div>  
<script type="text/javascript">
	var confirmAdminPWBtn = $("#confirmAdminPWBtn");
	var confirmAdminPWDialog = $("#confirmAdminPWDialog");
	var pw =  $("#confirmAdminPWDialog #pw");
	var confirmCallFun = undefined;
	
	//confirmAdminPWDiaog对象
	var confirmAdminPWDiaog = {
			/*callfun为确认密码后的回调函数*/
			open:function(callfun){
				confirmCallFun = callfun;
				confirmAdminPWDialog.dialog('open');
			},
			close:function(){
				confirmAdminPWDialog.dialog('close');
			}
	};
	/* 确认密码 */
	function confirmAdminPW(){
		var pwv = pw.textbox('getText');
		c(pwv);
		//验证密码
		ajax.send(manageForwardUrl+"/admins/checkOnlineAdminPW.action?password="+pwv, function(data){
			confirmCallFun(true);
		}, function(data){
			confirmCallFun(false);
		}, function(){
			
		});
	}
	
</script>

