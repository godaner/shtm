<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${manageStaticSrcUrl}/css/users_charts.css">


	<!-- 性别圆饼图 -->
	<div class="users_charts" id="users_sex_0"></div>
	<div class="users_charts_split_line"></div>
	<!-- 用户月注册数折线图 -->
	<div class="users_charts" id="users_month_regist_number_0"></div>
	<div class="users_charts_split_line"></div>


 
<script type="text/javascript" src="${manageStaticSrcUrl}/plugin/echarts.common.min.js"></script>
<script type="text/javascript" src="${manageStaticSrcUrl}/js/users_charts.js"></script>