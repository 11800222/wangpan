<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>

<meta charset="utf-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit"> 
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
<meta HTTP-EQUIV="Expires" CONTENT="0"> 

<title>网络硬盘</title>

<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="/wangpan/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/wangpan/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/wangpan/css/form-elements.css">
<link rel="stylesheet" href="/wangpan/css/style.css">

<link rel="shortcut icon"href="http://112.74.94.156/wangpan/images/folder.ico"> 


<link rel="shortcut icon" href="/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="ico/apple-touch-icon-57-precomposed.png">

</head>
<html>  
<body  >

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
		
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>网络硬盘</strong> 
						</h1>
						<div class="description">
							<!-- <p>
								This is a free responsive login form made with Bootstrap.
								Download it on <a href="http://azmind.com"><strong>AZMIND</strong></a>,
								customize and use it as you like!
							</p> -->
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>登录</h3>
								<p> </p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"></i>
							</div>
						</div>

						<div class="form-bottom">
							<form role="form" action="User_login.action" method="post"
								class="login-form" onsubmit="return submitForm()" >
								<div class="form-group">
									<label class="sr-only" for="form-username">帐号</label> <input
										type="text" name="user.username" placeholder="Username..."
										class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">密码</label> <input
										type="password" name="user.password" placeholder="Password..."
										class="form-password form-control" id="form-password">
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<input type="text" name="ValidateCode" id="ValidateCode"
											class="form-control input-lg" placeholder="验证码">
									</div>
									<div class="col-xs-3 col-sm-3 col-md-3">
										<img src="ValidateCode.jsp"
											onclick="this.src='ValidateCode.jsp?date='+new Date();"
											title="点击重新获取验证码" />
									</div>
									<div class="col-xs-3 col-sm-3 col-md-3">
										<div class="btn btn-lg btn-warning btn-block">
											<input id="RememberMe" type="checkbox" name="RememberMe"
												onclick="RememberMeClick()"><label for="RememberMe">
												记住账号</label>
										</div>
									</div>
								</div>
								<hr class="colorgraph">
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<input type="submit" class="btn btn-lg btn-success btn-block"
											value="登陆" >
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<a href="register.jsp"
											class="btn btn-lg btn-primary btn-block">注册</a>
									</div>
								</div>
							</form>

						</div>
					</div>
				</div>

				<!--     <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 social-login">
                        	<h3>...or login with:</h3>
                        	<div class="social-login-buttons">
	                        	<a class="btn btn-link-2" href="#">
	                        		<i class="fa fa-facebook"></i> Facebook
	                        	</a>
	                        	<a class="btn btn-link-2" href="#">
	                        		<i class="fa fa-twitter"></i> Twitter
	                        	</a>
	                        	<a class="btn btn-link-2" href="#">
	                        		<i class="fa fa-google-plus"></i> Google Plus
	                        	</a>
                        	</div>
                        </div>
                    </div> -->
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/jquery.backstretch.min.js"></script>
	<script charset="utf-8" src="js/login.js" ></script>
    <script charset="utf-8" src="js/cookie.js" ></script>
	<!--[if lt IE 10]>
            <script src="/js/placeholder.js"></script>
        <![endif]-->

</body>

</html>