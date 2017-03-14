<%@ page pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta name="referrer" content="never">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>116</title>
<link href="\wangpan\css\file.css" rel="stylesheet" type="text/css">
<link href="\wangpan\css\file_type.css" rel="stylesheet"
	type="text/css">

<link href="\wangpan\css\dialog_box.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="/jquery/jquery.js"></script>
<script type="text/javascript" src="/wangpan/js/box.js"></script>
<script type="text/javascript" src="/wangpan/js/crud.js"></script>
<script type="text/javascript" src="/wangpan/js/path.js"></script>
<script type="text/javascript" src="/wangpan/js/util.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		/* 	addone(); */
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#dialog").hide();
		$("#js_data_list").delegate('[data_move="ico"]', "click", function(e) {
			var chosn=$(this);
			Gotodir(chosn);
			addpath(chosn);
		});
		$("#js_data_list").delegate('[data_move="file"]', "click", function(e) {
			var chosn=$(this);
			 if (confirm("确定要下载吗？")==true){ 
				 $.get("http://112.74.94.156:8080/wangpan/file_getDownlink.action?link="
						 +chosn.attr("pc"),function(data,status){
				        window.open(data, '_blank');
				 });
			 }
        });
		$("#js_data_list").delegate('[data_move="temp"]', "click", function(e) {
			alert("等待上传完成");
		});
		$(".file-path").delegate('[menu="open"]', "click", function(e) {
			var chosn=$(this);
			clickpath(chosn);
		});
		//请求获得用户名
		$.get(
	    "http://112.74.94.156:8080/wangpan/User_userInsession.action",
		function(data){
	    	var folder=$(".file-path > *").eq(1);
	    	folder.attr("cid",data);
	    	folder.attr("title",'你的所有文件');
	    	folder.html('你的所有文件');
	    	clickpath(folder);
		});
	});
	function showlinkdialog() {
		$("#dialog").show();
	}
	function hidelinkdialog() {
		/* alert($("#js_offline_new_add").val()); */
		$("#dialog").hide();
	}
	function Download() {
		var link ="http://112.74.94.156:8080/wangpan/file_NewFile.action?link="+$("#js_offline_new_add").val();
		 $.get(link,function(data,status){ alert(data); });
		$("#dialog").hide();
		var folder=$(".file-path > *").eq(1);
		clickpath(folder);
	}
	function refrash(){
		var folder=$(".file-path > *").eq(1);
		clickpath(folder);
	}
	 $.backstretch("images/backgrounds/2.jpg");
</script>

</head>
<body >


	<div class="wrap" >
		<div class="top-panel" id="js_top_panel_box">
			 
			<div class="panel-file-upload" id="js_upload_btn" style=""
				is_bind="1" title="刷新" onclick ="refrash();">
				<h3 class="pfu-action">添加</h3>
				</div>
			 
	<!-- 		<div class="panel-file-upload" id="js_upload_btn" style=""
				is_bind="1">
				<h3 class="pfu-action">添加</h3>
				<div class="pfu-menu">
					<a href="javascript:;" menu="upload"><s class="pfum-upload"></s><span>上传文件</span></a>
					<a href="javascript:;" menu="add_dir"><s class="pfum-newdir"></s><span>新建文件夹</span></a>
					<a href="javascript:;" onclick="showlinkdialog()"><s class="pfum-offline"></s><span>离线任务</span></a>
				</div>
			</div> -->
			
			<div class="top-user-behavior"></div>
			<div class="invisible-mode-exit" id="js_invisible_line"
				style="display: block;">
				<a href="javascript:;" onclick="showlinkdialog()"><i></i><span>添加文件</span></a>
			</div>
			
			<div class="list-operate" id="js_operate_box" style="display:none;"></div>
		</div>

		<div class="container">
			<div class="main-top-box" id="js_top_bar_box">
				<div class="file-path" rel="page_local">
					<i>»</i><a href="javascript: " menu="open" aid="1" cid="" title="">  </a> 
				</div>
			</div>

			<div id="js_cantain_box" class="page-center">
				<div class="page-container" id="js_data_list_outer">
					<div style="min-height:100%;_height:100%;cursor:default;"
						id="js_data_list" rel="list">
						<div class="list-cell" ind="0">
							<ul class="list-thumb">
								<li rel="item"><i style="cursor:pointer;" data_move="ico"  cid="11800222" title="20150728"
									class="file-thumb tb-folder"></i>
									<div class="file-name">
										<a href="javascript:;" data_move="ico">20150728-</a>
									</div>
									<div class="file-info" id="uncle">已添加</div>
									<div class="file-opt">
										<a href="javascript:;" menu="download_dir_one"
											title="下载"> <i class="icon ico-download"
											menu="download_dir_one"></i> <span>下载</span>
										</a> <a href="javascript:;" menu="delete" title="删除"><i
											class="icon ico-more" menu="delete">ico-more</i><span>删除</span></a>
									</div></li>

							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="page-footer" id="js_pagination_box"></div>
			<div style="display:none;" id="js_no_file_box"></div>
		</div>
	</div>
	<div class="dialog-box dialog-mini offline-box window-current"
		id="dialog"
		style="z-index: 1000000002; width: 600px; position: fixed; top: 0px; left: 500px;">
		<div class="dialog-header" rel="title_box" ws_property="1">
			<h3 rel="base_title">输入下载链接</h3>
		</div>
		<div class="dialog-handle">
			<a href="javascript:;" class="close" btn="close">关闭</a>
		</div>
		<div rel="base_content">
			<div class="offline-container">
				<div class="offline-link" style="padding: 10px;">
					<table>
						<tbody>
							<tr>
								<td style="padding:0;"><textarea
										style="width:540px;height:150px; padding: 12px 9px;color: #333;border: 1px solid;border-color: #CECECF;background: white;box-shadow: inset 1px 1px 2px rgba(0,0,0,0.1);line-height:24px;font-size: 14px;"
										id="js_offline_new_add" class="text"></textarea></td>
							</tr>	
						</tbody>
					</table>
					<div
						style="line-height: 24px;color: #999;height: 24px;vertical-align: mdiddle;padding-left: 10px;">输入HTTP、HTTPS、FTP、磁力链或电驴链接 </div>
				</div>
			</div>
			<div class="dialog-bottom">
				<div class="con">
					<a href="javascript:;" class="button" onclick="Download()" data-btn="start">开始下载</a><a
						href="javascript:;" class="btn-link" onclick="hidelinkdialog()">取消</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>