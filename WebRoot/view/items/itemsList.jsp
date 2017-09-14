<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
</head>
<body>
	
	当前用户：${user.username}
	<a href="<c:url value='/users/logout.action'></c:url>">注销</a>
	<br/>
	
	<form action="<c:url value='/items/selectItems.action'></c:url>">
		搜索商品名：<input name="itemsCustom.name"/>
		<input type="submit" value="搜索"/>
	</form>
	
	<form action="<c:url value='/items/deleteItems.action'></c:url>">
		<c:forEach items="${items}" var="i">
			<input name="deleteIds" type="checkbox" value="${i.id}"/>
			名称：${i.name},描述：${i.description},价格：${i.price},数量：${i.quantity}
			<a href="<c:url value='/items/editAItem.action?id=${i.id}'></c:url>">修改</a>
			<br/>
		</c:forEach>
		<input type="submit" value="批量删除"/>
	</form>
	<br/>
	
</body>
</html>