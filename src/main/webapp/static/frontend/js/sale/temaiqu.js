
$(document).on("click",".centerBox-left-c1 .btn",function(){
	/*来变颜色*/
	$(".centerBox-left-c1 .btn").css("border","0px");
	$(this).css("border","2px solid");
})
$(document).on("click",".centerBox-right-c2 p a",function(){
	$(".centerBox-right-c2,.centerBox-left-c2").empty();
	$(".centerBox-right-c2").load("static/frontend/html/sale/temaiqu_right_2_child.html");
	$(".centerBox-left-c2").load("static/frontend/html/sale/temaiqu_left_2_2.html");
	$(".centerBox-right-c1 p").append("<img src='static/frontend/images/rightImg.png'/>"+
	"<button class='btn btn-warning btn-xs' disabled='disabled'>正文</button>");
})
$(function(){
	$(".tou").load("static/frontend/html/header.html");
	$(".wei").load("static/frontend/html/footer.html");
	/*特卖区的初始化*/
	//$(".centerBox-left-c1").load("static/frontend/html/sale/temaiqu_left_1.html");
	$(".centerBox-left-c2").load("static/frontend/html/sale/temaiqu_left_2.html");
	
	$(".centerBox-right-c1").load("static/frontend/html/sale/temaiqu_right_1.html");
	//$(".centerBox-right-c2").load("static/frontend/html/sale/temaiqu_right_2.html");
})
  