<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>

<div id="confirm_adminPW_dialog" class="easyui-dialog" data-options="closed:true"  title="确认您的密码" style="display: none;width:300px;overflow:hidden;"   
        data-options="resizable:true,modal:true,closed:true">   
	<div style="margin: 20px 10%;width:80%;">
		<input id="pw" class="easyui-textbox" data-options="required:true,prompt:'请输入您的密码'" style="width:100%">
	</div>
	<div style="margin: 20px 0px;text-align:center;width:100%;height:20px;">
		<a id="confirm_adminPW_btn" href="javascript:confirmAdminPW();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">检验</a>   
	</div>
</div>  
<script type="text/javascript">
	var confirmAdminPWBtn = $("#confirm_adminPW_dialog #confirm_adminPW_btn");
	var confirm_adminPW_dialog = $("#confirm_adminPW_dialog");
	var pw =  $("#confirm_adminPW_dialog #pw");
	var confirmCallFun = undefined;
	
	//confirmAdminPWDialog对象
	var confirmAdminPWDialog = {
			/*callfun为确认密码后的回调函数*/
			open:function(callfun){
				pw.textbox('setText',"");
				confirmCallFun = callfun;
				confirm_adminPW_dialog.dialog('open');
			},
			close:function(){
				confirm_adminPW_dialog.dialog('close');
			}
	};
	/* 确认密码 */
	function confirmAdminPW(){
		var pwv = pw.textbox('getText');
		//验证密码
		ajax.send(manageForwardUrl+"/admins/checkOnlineAdminPW.action?password="+pwv, function(data){
			confirmCallFun(true);
		}, function(data){
			confirmCallFun(false);
		}, function(){
			
		});
	}
	
</script>

