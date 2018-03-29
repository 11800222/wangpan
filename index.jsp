<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<HTML>
<HEAD>
<TITLE>网络硬盘</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
 <link rel="stylesheet" href="/wangpan/css/fakeLoader.css" type="text/css">
 
<script src="/jquery/jquery.min.js"></script>
   
  <script type="text/javascript" src="/wangpan/js/fakeLoader.min.js"></script>
<script type="text/javascript">
	$.backstretch("images/backgrounds/2.jpg");
</script>
 
</HEAD>

<frameset id="mainframes" border="false" framespacing="0">


	<frameset id="contentframes" framespacing="0" border="false"
		rows="55,*" frameborder="0" scrolling="yes">

		<frame name="top" scrolling="no" src="top.jsp" border="false"
			framespacing="0">
		<frameset id="bottomframes" framespacing="0" border="false"
			cols="100,*,100" frameborder="0" scrolling="yes"
			nload="this.contentWindow.document.body.style.backgroundImage='url(images/backgrounds/2.jpg)'">
			<frame name="septor" src="blank.html">
			<frame  id="separator" name="separator" src="mybox.jsp" scrolling="no"
				frameborder="1 " />
			<frame name="sepstor" src="blank.html">
		</frameset>
	</frameset>


</frameset>

</HTML>

