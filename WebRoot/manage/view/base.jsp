<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.shtm.util.ProjectUtil" %>
<%@ page import="com.shtm.util.Static" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 项目名url -->
<c:set var="baseUrl" value="${pageContext.request.contextPath}"></c:set>
<!-- 本模塊静态资源請求url -->
<c:set var="manageStaticSrcUrl" value="${pageContext.request.contextPath}/manage"></c:set>
<!-- 本模塊請求url -->
<c:set var="manageForwardUrl" value="${pageContext.request.contextPath}"></c:set>
<!-- 本模塊webscoket請求url -->

<c:set var="wsAddr" value="<%=ProjectUtil.getValue(Static.CONFIG.FIELD_WS_ADDR)%>"></c:set>
<c:set var="manageWebSocketUrl" value="${wsAddr}${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" charset="utf-8">
	/* 项目名url */
	var baseUrl = '${baseUrl}';
	/*本模塊静态资源請求url*/
	var manageStaticSrcUrl = '${manageStaticSrcUrl}';
	/*本模塊請求url*/
	var manageForwardUrl = '${manageForwardUrl}';
	/*本模塊webscoket請求url*/
	var manageWebSocketUrl = '${manageWebSocketUrl}';

</script>

