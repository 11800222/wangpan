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
<title>网络硬盘</title>
<link href="/wangpan/css/file.css" rel="stylesheet" type="text/css">
<link href="/wangpan/css/file_type.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="/wangpan/css/fakeLoader.css" type="text/css">
<link href="/wangpan/css/dialog_box.css" rel="stylesheet"
	type="text/css">
  <link rel="stylesheet" href="/wangpan/css/introjs.css"/>
  
  <script type="text/javascript" src="/wangpan/js/spin.js"></script>
 <script type="text/javascript" src="/wangpan/js/intro.js"></script>
<script type="text/javascript" src="/jquery/jquery.js"></script> 
<script type="text/javascript" src="/jquery/jquery.min.js"></script> 
<script type="text/javascript" src="/wangpan/js/box.js"></script>
<script type="text/javascript" src="/wangpan/js/crud.js"></script>
<script type="text/javascript" src="/wangpan/js/path.js"></script>
<script type="text/javascript" src="/wangpan/js/util.js"></script>
<script type="text/javascript">
	$(document).ready(function() { 
		
		 
});
	 
</script>
<script type="text/javascript">
var myurl="http://127.0.0.1:8082/wangpan/";  //TODO 本地调试需修改
var newuser=-1;
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
				 $.get(myurl+"file_getDownlink.action?link="    
						 +chosn.attr("pc"),function(data,status){
				        window.open(data, '_blank');
				 });
				  
			 }
        });
		$("#js_data_list").delegate('[data_move="delete"]', "click", function(e) {
			var chosn=$(this);
			if (confirm("确定删除该文件吗？")==true){ 
				 $.get(myurl+"file_DelFile.action?file.cid="    
						 +chosn.attr("cid"),function(data,status){
				       alert(data);
				       setTimeout(function(){
				       		refrash();
						   }, 1000);
				 });
			}
		});
		$(".file-path").delegate('[menu="open"]', "click", function(e) {
			var chosn=$(this);
			clickpath(chosn);
		});
		//请求获得用户名
		$.get(
	    myurl+"User_userInsession.action",   
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
		if(newuser>0){
			--newuser;
		$("#js_offline_new_add").val("magnet:?xt=urn:btih:F3795220D5508E161CCF83F5116699BCA43D416A");
		introJs().exit();
		introJs().setOptions({   
		 	showButtons:false,
            steps: [ 
                { 
                    element: '#js_offline_new_add',
                    intro: '已帮您输入一个下载链接，请点击开始下载',
                    position: 'right'
                },   
            ] 
        }).start(); 
		
		}
	}
	function hidelinkdialog() {
		/* alert($("#js_offline_new_add").val()); */
		$("#dialog").hide();
	}
	function Download() {
		var link =myurl+"file_NewFile.action?link="+$("#js_offline_new_add").val();   
		 $.get(link,function(data,status){ alert(data+"。  请等待一段时间后，点击左上角按钮刷新文件目录"); });
		 $("#dialog").val("输入下载连接");
		$("#dialog").hide(); 
		   refrash();
		 if(newuser>0){
				--newuser;
			 introJs().exit();
				introJs().setOptions({   
				 	showButtons:false,
		            steps: [ 
		                { 
		                    element: '#js_upload_btn',
		                    intro: '点击这个按钮，刷新文件目录',
		                    position: 'right'
		                },   
		            ] 
		        }).start(); 
			
		 }
	}
	function refrash(){
		var folder=$(".file-path > *").eq(1);
		var opts = {
				  lines: 10, // The number of lines to draw
				  length: 20, // The length of each line
				  width: 20, // The line thickness
				  radius: 10, // The radius of the inner circle
				  corners: 1, // Corner roundness (0..1)
				  rotate: 0, // The rotation offset
				  color: '#FFF', // #rgb or #rrggbb
				  speed: 1, // Rounds per second
				  trail: 60, // Afterglow percentage
				  shadow: true, // Whether to render a shadow
				  hwaccel: false, // Whether to use hardware acceleration
				  className: 'spinner', // The CSS class to assign to the spinner
				  zIndex: 2e9, // The z-index (defaults to 2000000000)
				  top: '180px', // Top position relative to parent in px
				  left: 'auto' // Left position relative to parent in px
				};
				var target = document.getElementById('wrap1');
				var spinner = new Spinner(opts).spin(target);
				setTimeout(function(){
					spinner.stop();
					clickpath(folder);
				}, 3000); 
		//http://www.codeceo.com/demo/jquery-loading-spin-js/index.html
	}
	function guide() {
		--newuser;
		if(newuser>0){
		 introJs().setOptions({
		  	showButtons:false,
		            steps: [
		                {
		                    element: '#js_invisible_line', 
		                    intro: '点击这个按钮添加文件~~', 
		                    position: 'right'
		                },  
		            ] 
		        }).start(); 
		}
		};
	 $.backstretch("images/backgrounds/2.jpg");
	
	
</script>

</head>
<body>
<div id="fakeLoader"></div>

	<div class="wrap" id="wrap1">
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
										<a href="javascript:;" onclick="downloadButton()"
											title="下载"> <i class="icon ico-download"
											onclick="downloadButton()"></i> <span>下载</span>
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
		style="z-index: 1000000002; width: 600px; position: fixed; top: 100px; left: 400px;">
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
					<a href="javascript:;" id ="dl" class="button" onclick="Download()" data-btn="start">开始下载</a><a
						href="javascript:;" class="btn-link" onclick="hidelinkdialog()">取消</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>