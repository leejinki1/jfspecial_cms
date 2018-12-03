

$(function(){
		$(".tou").load("static/frontend/html/header.html");
    	$(".wei").load("static/frontend/html/footer.html");

    	$(".centerBox ul").empty();
        		for (var i=0;i<100;i++) {
        			$(".centerBox ul").append("<li><a href='#'>"+(i+1)+"、这是一个链接外链的项目申请链接</a></li>");
        		}
})