<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 新建用户弹窗 start -->
<div id="insertUserDialog" style="display: none;width:450px;">
	<form id="insertUserForm" enctype="multipart/form-data" method="post">
		<table id="insertUserTable">
			<tr>
				<td>
					选择头像
				</td>
				<td>
					<!-- <input type="file" id="file" name="file" style="width:100%"> -->
					<input id="file" name="file" class="easyui-filebox input" data-options="accept:'image/jpeg,image/gif,image/jpg',prompt:'请选择图片',buttonText: '选择图片', buttonAlign: 'right' "  style="width:100%"> 
				</td>
			</tr>
		
			<!-- <tr>
				<td>
					<label>主键</label> 
				</td>
				<td>
					<input id="id" name="id" class="easyui-textbox input" data-options="prompt:'请选择图片',editable:false" style="width:100%"> 
				</td>
			</tr> -->
			<tr>
				<td>
					<label>名称</label> 
				</td>
				<td>
					<input id="username" name="username" class="easyui-textbox input" data-options="prompt:'请输入用户名',required:true" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>邮箱</label> 
				</td>
				<td>
					<input id="email" name="email" class="easyui-textbox input" data-options="prompt:'请输入邮箱',required:true,validType:'email'" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>密码</label> 
				</td>
				<td>
					<input id="password" name="password" class="easyui-textbox input" data-options="required:true,prompt:'请输入密码'" style="width:100%"> 
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
					    <option  selected="selected" value="-1">未设置</option>   
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
					<input id="birthday" name="birthday" class="easyui-datebox input" data-options="prompt:'请选择生日',editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>介绍</label> 
				</td>
				<td>
					<input id="description" name="description" class="easyui-textbox input" data-options="prompt:'请输入介绍',multiline:true" style="width:100%;height:80px;"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>积分</label> 
				</td>
				<td>
					<input id="score" name="score" class="easyui-numberbox input" data-options="prompt:'请输入积分',required:true,min:0,precision:0" style="width:100%"> 
				</td>
			</tr>
			<!-- <tr>
				<td>
					<label>注册时间</label> 
				</td>
				<td>
					<input id="registtime" name="registtime" class="easyui-textbox input" data-options="required:true,editable:false" style="width:100%"> 
				</td>
			</tr> -->
			<tr>
				<td>
					<label>状态</label> 
				</td>
				<td>
					<select id="status" name="status" class="easyui-combobox input" data-options="required:true,editable:false" style="width:100%">   
					    <option value="1">激活</option>   
					    <option selected="selected" value="0">冻结</option>   
					</select>  
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 新增按钮 -->
					<a id="submitInsertBtn" style="color:green;" href="javascript:submitNewUser();" class="easyui-linkbutton" data-options="">新增</a>
					<!-- 清空按钮 -->
					<a style="color:red;" href="javascript:clearInsertUserForm();" class="easyui-linkbutton" data-options="">清空</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 新建用户弹窗 end -->
