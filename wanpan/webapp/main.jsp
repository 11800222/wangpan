<%@page import="login.MyCallable"%>
<%@page import="login.ResponseUtil"%>

<%@page import="login.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 

  </head>
  <%
  GetResponse.addTaskLink("");
  %>
  <body>
    登录成功 <br>
  </body>
</html>
