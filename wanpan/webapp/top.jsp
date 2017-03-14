<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<script src="/jquery/jquery.js"></script>
 <style>
        a:hover {
            text-decoration: none;
            padding: 20px 0 0 1px

        }
    </style>
</head>

<BODY>
<nav class="navbar navbar-default" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div >
        <span class="navbar-brand"  >
        <a href="javascript:;" onclick="alert('1.点击添加文件，输入资源下载链接后点击下载;\n2.等待服务器上传完成;\n3.点击要下载的资源进行下载（一般能达到满速）')"/>
            <i class="glyphicon glyphicon-home" ></i>&nbsp;&nbsp;<b>如何使用</b>
        </span>
        </div>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
       <%--  <ul class="nav navbar-nav">
            <s:iterator value="funcList" status="status" id="bean">
                <li style="position: relative;left:5px">
                    <a id="funca<s:property value="id"/>"
                       name="funca" ${funcid == bean.id ?"class='active'":"" }
                       onclick="selectFunc('funca<s:property value="id"/>')"
                       href="admin_left.do?funcid=<s:property value="id"/>" target="left">
                        <s:property value="name"/>
                    </a>
                </li>
            </s:iterator>
        </ul> --%>

        <ul class="nav navbar-nav navbar-right">
            <li style="position: relative;left:5px"><a h ><i class="glyphicon glyphicon-user">
                 </i></a></li>
            <li style="position: relative;left:5px"><a class="text" >关于本站</a>
            </li>
            <li style="position: relative;left:5px"><a class="text" >个人设置</a>
            </li>
            <li style="position: relative;left:5px"><a class="text" >更改密码</a></li>
            <li style="position: relative;left:5px"><a class="text"  target="_top">退出</a>
    </div>
    </li>
    </ul>
    </div>
    </div>
</nav>
<script>
    $("li").hover(
            function () {
                $(this).animate({left: "+=5"}, 400);
            }, function () {
                $(this).animate({left: "-=5"}, 400);
            }
    );
</script>
</BODY>
</html>
