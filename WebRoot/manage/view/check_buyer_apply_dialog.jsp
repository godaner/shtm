<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 用户退款申请查看弹窗 start -->
<div id="checkBuyerApplyDialog" class="easyui-dialog" data-options="closed:true"  style="display: none;width:70%;height:70%;">
	<table style="width:100%;height:100%;">
		<tr>
			<td colspan="2" align="center">
			
				<img id="sellerRefuseBillImg" style="border:1px solid rgba(0.5,222,222,222); width:80%;" src=""/>
				
			</td>
		</tr>
		<tr>
			
			
			<td align="center">
				<!-- 删除按钮 -->
				<a id="agreeApplyBtn" style="color:green;" href="javascript:updateGoodsStatus('-9');" class="easyui-linkbutton" data-options="">同意退款</a>
			</td>
			<td align="center">
				<a id="refuseApplyBtn" style="color:red;" href="javascript:updateGoodsStatus('-1');" class="easyui-linkbutton" data-options="">拒绝退款</a>
			
			</td>
		</tr>
	</table>
</div>
<!-- 用户退款申请查看弹窗 end -->
