
$(document).on("click",".centerBox-left-c1 .btn",function(){
	/*来变颜色*/
	$(".centerBox-left-c1 .btn").css("border","0px");
	$(this).css("border","2px solid");
})

$(document).on("click",".centerBox-right-c2 .ok a",function(){
	$(".centerBox-right-c2,.centerBox-left-c2").empty();
	
	$(".centerBox-right-c2").load("static/frontend/html/maker/chuangkehuodong_test1_child.html");
	$(".centerBox-left-c2").load("static/frontend/html/maker/chuangkehuodong_left_2_2.html");
	
	$(".centerBox-right-c1 p").append("<img src='static/frontend/images/rightImg.png'/>"+
	"<button class='btn btn-warning btn-xs' disabled='disabled'>正文</button>");
})
//我要报名
$(document).on("click",".baoming",function(){
	$(".centerBox-right-c2,.centerBox-left-c2").empty();
	$(".centerBox-right-c2").load("static/frontend/html/maker/chuangkehuodong_test1_child_baoming.html");
})

function chuangkehuodong_c1(){
	

	if($(".centerBox-right-c1 p button:last").text()!=$(".chuangkehuodong_c1").text()){
		$(".centerBox-right-c2").empty();
		$(".centerBox-right-c2").load("static/frontend/html/maker/chuangkehuodong_test1.html",function(){
			
			$(".centerBox-right-c1 p button:last").text($(".chuangkehuodong_c1").text());//连动
			
		});

	}else{}
	
}
//往期测试
$(document).on("click","#wangqiTest1 td a",function(){
	$(".centerBox-right-c2,.centerBox-left-c2").empty();
	$(".centerBox-right-c2").load("static/frontend/html/maker/wangqiTest1_child.html");
	$(".centerBox-left-c2").load("static/frontend/html/maker/chuangkehuodong_left_2_2.html");
})

function chuangkehuodong_c2(){
	if($(".centerBox-right-c1 p button:last").text()!=$(".chuangkehuodong_c2").text()){
		$(".centerBox-right-c2").empty();
		$(".centerBox-right-c2").load("static/frontend/html/maker/wangqiTest1.html",function(){
			$(".centerBox-right-c1 p button:last").text($(".chuangkehuodong_c2").text());//连动
			
		});
		

	}else{}
}


$(function(){
	$(".tou").load("static/frontend/html/header.html");
    	$(".wei").load("static/frontend/html/footer.html");
	//默认第一个--特派员--
	/*特派员的初始化*/
	$(".centerBox-left-c1").load("static/frontend/html/maker/chuangkehuodong_left_1.html");
	
	$(".centerBox-right-c1").load("static/frontend/html/maker/chuangkehuodong_right_1.html");
	$(".centerBox-right-c2").load("static/frontend/html/maker/chuangkehuodong_test1.html");
	/*$(".centerBox-right-c1").load("tepaiyuan_right_1.html");
	$(".centerBox-right-c2").load("test1.html");*/
	
	
	
})
