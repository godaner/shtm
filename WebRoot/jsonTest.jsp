<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsonTest</title>
</head>
<body>
	<input type="button" onclick="return requestJson();" value="请求json"/>
	<input type="button" onclick="return requestKeyValue();" value="请求key/value"/>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	/*请求json返回json*/
	function requestJson(){
		url = "${pageContext.request.contextPath}/requestJson.action";
		$.ajax({
			type:"post",
			url:url,
			/*重点*/
			contentType:"application/json;charset=utf-8",
			/*重点*/
			dataType:"json",
			data:'{"name":"手机","price":"99.9"}',
			success:function(data){
				console.info(data);
			}
		});
		
		return true;
	}

	/*请求key/value返回json*/
	function requestKeyValue(){
		url = "${pageContext.request.contextPath}/requestKeyValue.action";
		$.ajax({
			type:"post",
			url:url,
			/*重点*/
			/*重点*/
			dataType:"json",
			data:'name=手机&price=999',
			success:function(data){
				console.info(data);
			}
		});
		
		return true;
	}
	
</script>
</html>