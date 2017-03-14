<%@page import="login.Login"%>
<%@ page import="login.*"%>
 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	if (!Login.isonline) {
		out.write("not online");
	}
	int i=2;
%>

<body>
	<form action="/WebTest/servlet/Rp" method="get">
		<input name="url" type="text"><BR> <input type="submit"
			value="请求">
	</form>

</body>
</html>
