function addpath(chosn){
	/*添加下一目录名到路径*/
	$(".file-path").append("<i>»</i>");
	var a=$('<a>',{
		href : "javascript: ",
		menu: "open",
		aid :"1",
		cid :chosn.attr("cid"),
		title: chosn.attr("title")
	}).html(chosn.attr("title"));
	$(".file-path").append(a);
}
function clickpath(chosn){
	Gotodir(chosn);
	$(".file-path > *").each(
	function(index){
          if(index > chosn.index())
          this.remove();
		
	}
	)
	
}