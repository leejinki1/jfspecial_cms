/*这些是特派员的函数*/
//特派员列表
function tepaiyuan_c1(){
	//如已经load将不再、load页面、如果有后台操作则else进行后台操作
	
	if($(".centerBox-right-c1 button:last").text()!=$(".tepaiyuan_c1").text()){
		$(".centerBox-right-c2").empty();
		$(".centerBox-right-c2").load("static/frontend/html/special/test1.html",function(){
			$(".centerBox-right-c1 button:last").text($(".tepaiyuan_c1").text());//连动
		});
	}else{}
}
//特派员介绍
function tepaiyuan_c2(){
	//如已经load将不再、load页面、如果有后台操作则else进行后台操作
	
	if($(".centerBox-right-c1 button:last").text()!=$(".tepaiyuan_c2").text()){
		$(".centerBox-right-c2").empty();
		$(".centerBox-right-c2").load("static/frontend/html/special/test2.html",function(){
			$(".centerBox-right-c1 button:last").text($(".tepaiyuan_c2").text());//连动
		});
		
	}else{}
	

}
//专家对接
function tepaiyuan_c3(){
	//如已经load将不再、load页面、如果有后台操作则else进行后台操作
	if($(".centerBox-right-c1 button:last").text()!=$(".tepaiyuan_c3").text()){
		$(".centerBox-right-c2").empty();
		$(".centerBox-right-c2").load("static/frontend/html/special/test3.html",function(){
			$(".centerBox-right-c1 button:last").text($(".tepaiyuan_c3").text());//连动
		});
	}else{}	
}
//解答列表
function tepaiyuan_c4(){
	//如已经load将不再、load页面、如果有后台操作则else进行后台操作
	if($(".centerBox-right-c1 button:last").text()!=$(".tepaiyuan_c4").text()){
		$(".centerBox-right-c2").empty();
		$(".centerBox-right-c2").load("static/frontend/html/special/test4.html",function(){
			$(".centerBox-right-c1 button:last").text($(".tepaiyuan_c4").text());//连动
		});
	}else{}	
}
$(document).on("click",".centerBox-left-c1 .btn",function(){
	/*来变颜色*/
	$(".centerBox-left-c1 .btn").css("border","0px");
	$(this).css("border","2px solid");
})

$(document).on("click",".centerBox-right-c2 td a",function(){
	$(".centerBox-right-c2,.centerBox-left-c2").empty();
	
	//$(".centerBox-right-c2").load("static/frontend/html/special/test4_child.html");
	//$(".centerBox-left-c2").load("static/frontend/html/special/tepaiyuan_left_2.html");
	
//	$(".centerBox-right-c1 p").append("<img src='static/frontend/images/rightImg.png'/>"+
//	"<span>正文</span>");
})

/*这些是特卖区的函数*/

$(function(){
		$(".tou").load("static/frontend/html/header1.html");
        	$(".wei").load("static/frontend/html/footer.html");
	//默认第一个--特派员--
	/*特派员的初始化*/
	$(".centerBox-left-c1").load("static/frontend/html/special/tepaiyuan_left_1.html");
	//$(".centerBox-right-c1").load("static/frontend/html/special/tepaiyuan_right_1.html");
	//$(".centerBox-right-c2").load("static/frontend/html/special/test1.html");
	
	
	
})
