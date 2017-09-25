<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 用户属性编辑弹窗 start -->
<div id="editGoodDialog" style="display: none;width:450px;">
	<form id="editGoodForm" enctype="multipart/form-data" method="post">
		<table id="editGoodTable">
			<!-- <tr>
				<td>
					选择头像
				</td>
				<td>
					<input type="file" id="file" name="file" style="width:100%">
					<input id="file" name="file" class="easyui-filebox input" data-options="accept:'image/jpeg,image/gif,image/jpg',prompt:'请选择图片',buttonText: '选择图片', buttonAlign: 'right' "  style="width:100%"> 
				</td>
			</tr> -->
		
			<tr>
				<td>
					<label>主键</label> 
				</td>
				<td>
					<input id="id" name="id" class="easyui-textbox input" data-options="required:true,prompt:'请选择图片',editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>标题</label> 
				</td>
				<td>
					<input id="title" name="title" class="easyui-textbox input" data-options="required:true,prompt:'标题'" style="width:100%"> 
				</td>
			</tr>
			
			
			<tr>
				<td>
					<label>描述</label> 
				</td>
				<td>
					<input id="description" name="description" class="easyui-textbox input" data-options="required:true,prompt:'描述',multiline:true" style="width:100%;height:100px;"> 
				</td>
			</tr>
			
			<tr>
				<td>
					<label>原价</label> 
				</td>
				<td>
					<input id="sprice" name="sprice" class="easyui-numberbox input" data-options="min:0.1,precision:2,required:true,prompt:'原价'" style="width:100%;"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>转让价格</label> 
				</td>
				<td>
					<input id="price" name="price" class="easyui-numberbox input" data-options="min:0.1,precision:2,required:true,prompt:'原价'" style="width:100%;"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>新旧度</label> 
				</td>
				
				<td>
				
					<select id="condition" name="condition" class="easyui-combobox input" style="width:100%;" data-options="editable:false">   
					    <option value="1">1</option> 
					    <option value="2">2</option> 
					    <option value="3">3</option> 
					    <option value="4">4</option> 
					    <option value="5">5</option> 
					    <option value="6">6</option> 
					    <option value="7">7</option> 
					    <option value="8">8</option> 
					    <option value="9">9</option> 
					    <option value="10">10</option> 
					</select>
				
				</td>
			</tr>
			<tr>
				<td>
					<label>地区</label> 
				</td>
				
				<td>
				
					<select id="province" name="province" class="easyui-combobox " style="width:100%;" data-options="required:true,editable:false">   
					</select>
					<br/>
					<br/>
					
					<select id="city" name="city" class="easyui-combobox " style="width:100%;" data-options="required:true,editable:false">   
					     
					    
					</select>
					<br/>
					<br/>
					<select id="county" name="county" class="easyui-combobox " style="width:100%;" data-options="required:true,editable:false">   
					     
					    
					</select>
					<input type="hidden" id="region" name="region" value="">
					
				</td>
			</tr>
			<tr>
				<td>
					<label>状态</label> 
				</td>
				
				<td>
					<input id="status" class="easyui-textbox input" data-options="required:true,editable:false" style="width:100%">
				
				</td>
			</tr>
			<tr>
				<td>
					<label>創建时间</label> 
				</td>
				<td>
					<input id="createtime" name="createtime" class="easyui-textbox input" data-options="required:true,editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>发布者</label> 
				</td>
				<td>
					<input id="owner" name="owner" class="easyui-textbox input" data-options="required:true,editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>购买者</label> 
				</td>
				<td>
					<input id="buyer" name="buyer" class="easyui-textbox input" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>浏览次数</label> 
				</td>
				<td>
					<input id="browsenumber" name="browsenumber" class="easyui-textbox input" data-options="required:true,editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>最近一次更新时间</label> 
				</td>
				<td>
					<input id="lastupdatetime" name="lastupdatetime" class="easyui-textbox input" data-options="required:true,editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>被购买时间</label> 
				</td>
				<td>
					<input id="buytime" name="buytime" class="easyui-textbox input" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>交易完成时间</label> 
				</td>
				<td>
					<input id="finishtime" name="finishtime" class="easyui-textbox input" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 編輯按钮 -->
					<a id="submitEditBtn" style="color:green;" href="javascript:submitGoodEdit();" class="easyui-linkbutton" data-options="">同步</a>
					<!-- 取消发布按钮 -->
					<a id="cancelBtn" style="color:red;" href="javascript:cancelGood();" class="easyui-linkbutton" data-options="">取消发布</a>
					<!-- 删除按钮 -->
					<a id="deleteBtn" style="color:red;" href="javascript:deleteGood();" class="easyui-linkbutton" data-options="">删除</a>
					<!-- 重置按钮 -->
					<a style="color:red;" href="javascript:resetGood();" class="easyui-linkbutton" data-options="">重置</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 用户属性编辑弹窗 end -->
