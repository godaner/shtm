<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<!-- 图片上传dialog -->
<div id="goods_upload_imgs_dialog" class="easyui-dialog"  data-options="closed:true"  style="padding:10px 10px;display:none;width: 400px;">
	<form id="goods_upload_imgs_form" enctype="multipart/form-data" method="post">
		
		<!-- <input type="file" id="file" name="file" style="width:100%"> -->
		<input style="width:100%;" id="file" name="file" class="easyui-filebox input"
			data-options="required:true,accept:'image/jpeg,image/gif,image/jpg',prompt:'请选择图片',buttonText: '选择图片', buttonAlign: 'right' "
			/>
		<!-- 修改按钮 -->
		<a style="width:100%;" id="submitGoodsImgsBtn" style="color: green;"
			href="javascript:submitGoodsImgs();" class="easyui-linkbutton"
			data-options="">上传</a>

	</form>
</div>
<!-- 图片上传dialog -->