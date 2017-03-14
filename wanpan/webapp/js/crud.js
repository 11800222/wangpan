function Gotodir(chosn){
	$("ul").empty();
	$.ajax({
		type: "get", 
		async:true, 
		url: "http://112.74.94.156:8080/wangpan/file_FileList.action",
		data: {pid: chosn.attr("cid")},
		dataType: "json", 
		success: function(data) {
			ListFiles(data);
			}
		}
	); 
	
}

function Returndir(){
	$(".file-path > *").eq(-1).remove();
	$(".file-path > *").eq(-1).remove();
/*	Gotodir($(".file-path > *").eq(-1).attr("cid"));*/
}
function ListFiles(data) {
	$.each(data.data, function(idx, item) {
		ListOneFile(item);
	});
}
function ListOneFile(item) {
	var i_attr = {
		style : "cursor:pointer;",
		data_move : "file",
		cid : item.cid,
		title: item.name,
		pc : item.pickcode,
		class : "file-thumb tb-"+item.type
	}
	if(item.isDone !='yes') i_attr.class = "file-thumb tb-folder-offline";
	if(item.type=='folder') i_attr.data_move='ico';
	if(item.isDone=='not') i_attr.data_move='temp';
		
	var i = $('<i>', i_attr);

	var file_name = $('<div>', {
		class : "file-name"
	});
	$(file_name).append($('<a>', {
		href : "javascript: ",
		title : item.name
	}).html(item.name));

	var file_info = $('<div>', {
		class : "file-info",
		id : "uncle"
	});
	
	if(item.isDone=='yes') $(file_info).html('已完成');
	else $(file_info).html('正在上传');
		
	var file_opt = $('<div>', {
		class : "file-opt",
	});
	var down = $('<a>', {
		href : "javascript:;",
		menu : "download_dir_one",
		title : "下载"
	});
	$(down).append($('<i>', {
		class : "icon ico-download",
		menu : "download_dir_one"
	}));
	$(down).append($('<span>', {}).html('下载'));

	var delet = $('<a>', {
		href : "javascript: ",
		menu : "delete",
		title : "删除"
	});

	$(delet).append($('<i>', {
		class : "icon ico-more",
		menu : "delete"
	}))
	$(delet).append($('<span>', {}).html('删除'))

	$(file_opt).append(down);
	$(file_opt).append(delet);

	var li = $('<li>', {
		rel : "item"
	});
	$(li).append(i);
	$(li).append(file_name);
	$(li).append(file_info);
	$(li).append(file_opt);
	$("ul").append(li);

}