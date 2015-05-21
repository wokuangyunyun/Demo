<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="WEB-INF/resource/struts-logic.tld" prefix="logic"%>
<%@taglib uri="WEB-INF/resource/struts-bean.tld" prefix="bean"%>
<%@taglib uri="WEB-INF/resource/struts-html.tld" prefix="html"%>
<%@taglib uri="WEB-INF/resource/struts-nested.tld" prefix="nested"%>
<%@taglib uri="WEB-INF/bing.tld" prefix="bing"%>

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

<title>My JSP 'login_1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	function languages(lan) {
		//var cookiesPath = "/";//路径
		//var cookiesDomain = ".xxx.com";//设置有效的url
		document.cookie = "xxx_language=" + lan;//path=" + cookiesPath+ ";domain=" + cookiesDomain;
		//将有关信息写到cookie中
		location.reload();//刷新本页面进行国际化切换
	}
</script>
</head>

<body>
	<ul id="ul_lans">
		<li><a href="javascript:void(0);" onclick="languages('zh_CN')">中文(简体)</a></li>
		<li><a href="javascript:void(0);" onclick="languages('en_US')">English</a></li>
	</ul>
	<bing:hello name="hello"></bing:hello>
	<form action="login.do">
		<bean:message key="username" />
		：<input type="text" name="username"><html:errors property="userName"/><br/>
		<bean:message key="password" />
		：<input type="password" name="password"> <input type="submit"
			value="登录"><html:errors property="password"/><br/>
	</form>

</body>
</html>
