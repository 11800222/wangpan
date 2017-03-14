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
<script type="text/javascript" src="/jquery/jquery.js"></script>
</head>

<body>
	<%
		String mode = request.getParameter("mode");
		if (mode != null && mode.equals("login")) {
		    Login log = ForLogin.login;
			log.waitlogin();
			log.login();
			log.keepOnRequesting();  
            log.getsign_time_cid();
            log.getUserId();
		} else {
			ForLogin.login = new Login();
			ForLogin.login.getQRcode();
			out.println("<img src=\"" + basePath + "/images/QR.png\">");
		}
	%>
	<input type="button" id="login" value="login">
	<div id="loginstatus"></div>
	<SCRIPT LANGUAGE="JavaScript">
		$(document).ready(function() {
			$("#login").click(function() {
				htmlobj = $.ajax({
					url : "/WebTest/getqr.jsp?mode=login",
				    success: function(data){success();}
				});
				$("#loginstatus").html("loading");
			});
		});
		function success(){
			$("#loginstatus").html("success")
		}
	</script>
</body>
</html>
