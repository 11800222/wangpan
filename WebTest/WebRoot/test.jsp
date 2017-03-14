<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="login.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<body>
	<%
		GetResponse.httpclient = Login.httpclient;
		System.out.println(GetResponse.openfile("0", "99"));
		/*   System.out.println(Tool.lixian()); */
		/* System.out.println(Tool.addTaskLink("ed2k://|file|%E8%AE%AD%E7%BB%83%E6%97%A5.Training.Day.S01E04.720p.HDTV.X264-%E5%8F%8C%E8%AF%AD%E5%AD%97%E5%B9%95%E7%B2%BE%E6%A0%A1%E7%89%88-%E6%B7%B1%E5%BD%B1%E5%AD%97%E5%B9%95%E7%BB%84.mp4|548354501|00050a3ee95a1c6f933250f254ddf151|h=lkzz7qxdluq7xxa2xsprbspedsqwaffp|/"));
		 */
	%>
</body>
</html>
