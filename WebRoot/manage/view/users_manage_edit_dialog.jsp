<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 用户属性编辑弹窗 start -->
<div id="editUserDialog" class="easyui-dialog"  data-options="closed:true"  style="display: none;width:450px;">
	<form id="editUserForm" enctype="multipart/form-data" method="post">
		<table id="editUserTable">
			<tr>
				<td>
					修改头像
				</td>
				<td>
					<!-- <input type="file" id="file" name="file" style="width:100%"> -->
					<input id="file" name="file" class="easyui-filebox input" data-options="accept:'image/jpeg,image/gif,image/jpg',prompt:'默认为原图片',buttonText: '选择图片', buttonAlign: 'right' "  style="width:100%"> 
				</td>
			</tr>
		
			<tr>
				<td>
					<label>主键</label> 
				</td>
				<td>
					<input id="id" name="id" class="easyui-textbox input" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>名称</label> 
				</td>
				<td>
					<input id="username" name="username" class="easyui-textbox input" data-options="required:true" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>邮箱</label> 
				</td>
				<td>
					<input id="email" name="email" class="easyui-textbox input" data-options="required:true,validType:'email'" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>密码</label> 
				</td>
				<td>
					<input id="password" name="password" class="easyui-textbox input" data-options="prompt:'默认为原密码'" style="width:100%"> 
				</td>
			</tr>
			<!-- <tr>
				<td>
					<label>盐</label> 
				</td>
				<td>
					<input id="salt" name="salt" class="easyui-textbox" data-options="" style="width:100%"> 
				</td>
			</tr> -->
			<tr>
				<td>
					<label>性别</label> 
				</td>
				<td>
					<!-- <input id="sex" name="sex" class="easyui-combobox" style="width:100%"
    				data-options="valueField:'id',textField:'text',data:[
    					{    
						    'id':1,    
						    'text':'男'   
						},{    
						    'id':0,    
						    'text':'女'   
						},{    
						    'id':-1,    
						    'text':'未设置',    
						}]  " />   -->
					
					<select id="sex" name="sex" class="easyui-combobox input" data-options="required:true,editable:false" style="width:100%">   
					    <option value="-1">未设置</option>   
					    <option value="1">男</option>   
					    <option value="0">女</option>   
					</select>  
					
				</td>
			</tr>
			<tr>
				<td>
					<label>生日</label> 
				</td>
				<td>
					<input id="birthday" name="birthday" class="easyui-datebox input" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>介绍</label> 
				</td>
				<td>
					<input id="description" name="description" class="easyui-textbox input" data-options="multiline:true" style="width:100%;height:80px;"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>积分</label> 
				</td>
				<td>
					<input id="score" name="score" class="easyui-numberbox input" data-options="required:true,min:0,max:999999999,precision:0" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>余额</label> 
				</td>
				<td>
					<input id="money" name="money" class="easyui-numberbox input" data-options="required:true,min:0,max:999999999,precision:2" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>注册时间</label> 
				</td>
				<td>
					<input id="registtime" name="registtime" class="easyui-textbox input" data-options="required:true,editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>状态</label> 
				</td>
				<td>
					<select id="status" name="status" class="easyui-combobox input" data-options="required:true,editable:false" style="width:100%">   
					    <option value="1">激活</option>   
					    <option value="0">冻结</option>   
					</select>  
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 修改按钮 -->
					<a id="submitEditBtn" style="color:green;" href="javascript:submitUserEdit();" class="easyui-linkbutton" data-options="">同步</a>
					<!-- 删除按钮 -->
					<a id="deleteBtn" style="color:red;" href="javascript:deleteUser();" class="easyui-linkbutton" data-options="">删除</a>
					<!-- 重置按钮 -->
					<a id="deleteBtn" href="javascript:resetUser();" class="easyui-linkbutton" data-options="">重置</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 用户属性编辑弹窗 end -->
