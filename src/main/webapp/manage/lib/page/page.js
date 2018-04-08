
/*
 * 第一个参数pageInfoObj对象，第二个参数是点第几页的函数名，函数里面有一个点第几页的值
 */
function page( pageInfoObj, funName){

	if(pageInfoObj == null){
        var pageSize = 0;//每页显示的数目
        var lines = 0;	//总条数
        var pages = 1;	//总页数
        var page = 1;//当前页码
	}else{
        var pageSize = pageInfoObj.pageSize;//每页显示的数目
        var lines = pageInfoObj.total;	//总条数
        var pages = pageInfoObj.pages;	//总页数
        var page = pageInfoObj.pageNum;//当前页码
	}

	 var st="";
	 if(pages>0){
	 	st+="<ul class='totalbtn pagination' id='totalnum' style='float:left; margin:0px 10px 0px 0px'>"+
		"<li class='disabled'>"+
		"<a href='#'><i class='ace-icon fa' title='总数'>共有数据："+lines+"条</i></a></li></ul>";
	 	if(page>0){
	 			st+="<ul class='pagination' id='listnum' style='float:left; margin:0px 10px 0px 0px'>";
			 	if(page == 1){
			 		st+="<li class='disabled' ><a>"+
								"<i class=\"Hui-iconfont\" title=\"上一页\">&#xe67d;</i></a></li>";
			 	}else{
			 		st+="<li style='cursor:pointer;'><a onclick='"+funName+"("+(page-1)+") '>"+
					"<i class=\"Hui-iconfont\" title=\"上一页\">&#xe67d;</i></a></li>";
			 	}
			 if(pages<=5){
			 	for (var i=1; i <=pages; i++) {
						if (i==page) {
							st+="<li class='active' style='cursor:pointer;'><a class='pageNumBtn' onclick='"+funName+"("+(i)+") '>"+i+"</a></li>";
						} else{
							st+="<li><a class='pageNumBtn' style='cursor:pointer;' onclick='"+funName+"("+(i)+") '>"+i+"</a></li>";
						};
				 };
			 }
			 else if(pages>5){
			 	if (page<3) {
			 		for (var i=1; i <=5; i++) {
						if (i==page) {
							st+="<li style='cursor:pointer;' class='active'><a class='pageNumBtn' onclick='"+funName+"("+(i)+") '>"+i+"</a></li>";
						} else{
							st+="<li><a class='pageNumBtn' style='cursor:pointer;' onclick='"+funName+"("+(i)+") '>"+i+"</a></li>";
						};
				 };
			 	} else{
			 		var i = 0;
			 		if(page<=pages-2){
			 			for (i=page-2; i <=(page+2); i++) {
							if (i==page) {
								st+="<li style='cursor:pointer;' class='active'><a class='pageNumBtn' onclick='"+funName+"("+(i)+") '>"+i+"</a></li>";
							} else{
								st+="<li><a class='pageNumBtn' style='cursor:pointer;' onclick='"+funName+"("+(i)+") '>"+i+"</a></li>";
							};
				 		};
			 		}else if(page>pages-2){
			 			for (i=pages-4; i <=(pages); i++) {
							if (i==page) {
								st+="<li style='cursor:pointer;' class='active'><a class='pageNumBtn' onclick='"+funName+"("+(i)+") '>"+i+"</a></li>";
							} else{
								st+="<li><a class='pageNumBtn' style='cursor:pointer;' onclick='"+funName+"("+(i)+") '>"+i+"</a></li>";
							};
				 		};
			 		}
			 	};
			 }

			 	if(page!=pages){
				 	st+="<li><a style='cursor:pointer;' onclick='"+funName+"("+(page+1)+") '><i class=\"Hui-iconfont\" title=\"下一页\">&#xe63d;</i></a></li>";
				 }else{
					 st+="<li class='disabled'><a><i class=\"Hui-iconfont\" title=\"下一页\">&#xe63d;</i></a></li>";
				 }
			 	st+="</ul>";
		 }else{
		 	st="";
		 }
	 }
	return st;
}
