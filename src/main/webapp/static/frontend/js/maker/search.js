
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
	$(".tou").load("../../static/frontend/html/header_article.html");
    $(".wei").load("../../static/frontend/html/footer.html");

})
