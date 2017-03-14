<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="service.*" %>
<%@ page import="util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <body>
   <%
   
   FilesService filesService=(FilesService)util.BeanFactory.getbean("FilesService");
   filesService.CompleteFilesByID("31c893ef409809410bf3e6749f271edea77d6b7c", "998318718335479566");
   
   
   
   %>
  </body>
</html>
