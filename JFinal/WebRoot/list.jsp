<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<a href="student/add">添加</a>
	<table border="1">
		<tr>
			<td>姓名</td>
			<td>年龄</td>
			<td>班级</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${students}" var="student" varStatus="status">  
		<tr>
			<td>${student.name}</td>
			<td>${student.age}</td>
			<td>${student.getClasses().name}</td>
			<td><a href="student/delete/${student.id}">删除</a> <a
				href="student/get/${student.id}">修改</a></td>
		</tr>
		</c:forEach>
		
	</table>
</body>
</html>
