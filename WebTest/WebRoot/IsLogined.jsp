<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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

</head>

<body>

	<%
     if(Login.isonline)
    out.print(GetResponse.lixian());

	%>
</body>
</html>
