<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品</title>
</head>
<body>
	<!-- 显示错误信息 -->
	<c:forEach items="${allErrors}" var="e">
		<label style="color: red;">${e.defaultMessage}</label>
	</c:forEach>
	<form enctype="multipart/form-data" method="post" action="<c:url value='/items/updateAItem.action'></c:url>">
	<input type="hidden" name="itemsCustom.id" value="${itemsCustom.id}"/>
	名称：<input name="itemsCustom.name" value="${itemsCustom.name}"/>
	描述：<input name="itemsCustom.description" value="${itemsCustom.description}"/>
	价格：<input name="itemsCustom.price" value="${itemsCustom.price}"/>
	数量：<input name="itemsCustom.quantity" value="${itemsCustom.quantity}"/>
	<br/>
	<img style="width:100px;height:100px;" src="/pic/${itemsCustom.id}.png"/>
	<input name="item_pic" type="file"/>
	<br/>
	<br/>
	<input type="submit"/>
	</form>
</body>
</html>