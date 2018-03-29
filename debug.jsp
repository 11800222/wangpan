<%@page import="java.util.concurrent.Callable"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="util.*"%>
<%@ page import="Core.*"%>
<%@ page import="org.hibernate.criterion.*" %>
<%@ page import="model.*"%>
<%@ page import="org.apache.log4j.Logger"%>

String path = request.getContextPath(); String basePath =
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 

<script type="text/javascript" src="/jquery/jquery.min.js"></script>
 
	
</head>

<body>
<div class="form-bottom"> <form role="form" action="User_login.action" method="post" class="login-form" onsubmit="return submitForm()" > <div class="form-group"> <label class="sr-only" for="form-username">帐号</label> <input type="text" name="user.username" placeholder="Username..." class="form-username form-control" id="form-username"> </div> <div class="form-group"> <label class="sr-only" for="form-password">密码</label> <input type="password" name="user.password" placeholder="Password..." class="form-password form-control" id="form-password"> </div> <div class="row"> <div class="col-xs-6 col-sm-6 col-md-6"> <input type="text" name="ValidateCode" id="ValidateCode" class="form-control input-lg" placeholder="验证码"> </div> <div class="col-xs-3 col-sm-3 col-md-3"> <img src="ValidateCode.jsp" onclick="this.src='ValidateCode.jsp?date='+new Date();" title="点击重新获取验证码" /> </div> <div class="col-xs-3 col-sm-3 col-md-3"> <div class="btn btn-lg btn-warning btn-block"> <input id="RememberMe" type="checkbox" name="RememberMe" onclick="RememberMeClick()"><label for="RememberMe"> 记住账号</label> </div> </div> </div> <hr class="colorgraph"> <div class="row"> <div class="col-xs-6 col-sm-6 col-md-6"> <input type="submit" class="btn btn-lg btn-success btn-block" value="登陆" > </div> <div class="col-xs-6 col-sm-6 col-md-6"> <a href="register.jsp" class="btn btn-lg btn-primary btn-block">注册</a> </div> </div> </form> </div>
</body>
</html>
