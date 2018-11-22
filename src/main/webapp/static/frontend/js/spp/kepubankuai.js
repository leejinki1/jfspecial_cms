
$(document).on("click",".centerBox-left-c1 .btn",function(){
	/*来变颜色*/
	$(".centerBox-left-c1 .btn").css("border","0px");
	$(this).css("border","2px solid");
})
$(document).on("click",".centerBox-right-c2 td a",function(){
	$(".centerBox-right-c2").empty();
	$(".centerBox-left-c2").empty();
	$(".centerBox-right-c2").load("static/frontend/html/spp/kepubankuai_type1_child.html");
	$(".centerBox-left-c2").load("static/frontend/html/spp/kepubankuai_left_2_2.html");
	
	$(".centerBox-right-c1 p").append("<img src='static/frontend/images/rightImg.png'/>"+
	"<span>正文</span>");
})

$(function(){
	$(".tou").load("static/frontend/html/header.html");
    	$(".wei").load("static/frontend/html/footer.html");
	//默认第一个--特派员--
	/*特派员的初始化*/
	$(".centerBox-left-c1").load("static/frontend/html/spp/kepubankuai_left_1.html");
	
	$(".centerBox-right-c1").load("static/frontend/html/spp/kepubankuai_right_1.html");
//	$(".centerBox-right-c2").load("static/frontend/html/spp/kepubankuai_type1.html");
	/*$(".centerBox-right-c1").load("tepaiyuan_right_1.html");
	$(".centerBox-right-c2").load("test1.html");*/
	
	
	
})
