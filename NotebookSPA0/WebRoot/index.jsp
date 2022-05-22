<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<link rel="shortcut icon" href="favicon.ico" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <h1><a href="index_step1.html" target="blank">index step1 页面设计</a></h1>
    <h1><a href="index_step2.html" target="blank">index step2 API开发</a></h1>
    <h1><a href="index_step3.html" target="blank">index step3 整合</a></h1>
  </body>
</html>
