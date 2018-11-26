
$(document).on("click",".centerBox-left-c1 .btn",function(){
	/*来变颜色*/
	$(".centerBox-left-c1 .btn").css("border","0px");
	$(this).css("border","2px solid");
})
$(document).on("click",".centerBox-right-c2 td a",function(){
	$(".centerBox-right-c2").empty();
	$(".centerBox-left-c2").empty();
	$(".centerBox-right-c2").load("static/frontend/html/policy/zhengcefagui_type1_child.html");
	//$(".centerBox-left-c2").load("static/frontend/html/policy/zhengcefagui_left_2_2.html");
	
	$(".centerBox-right-c1 p").append("<img src='../../../static/frontend/images/rightImg.png'/>"+
	"<span>正文</span>");
})

$(function(){
	$(".tou").load("../../../static/frontend/html/header_article.html");
	$(".wei").load("../../../static/frontend/html/footer.html");

	
})
