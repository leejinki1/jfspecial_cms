//特派员介绍
function tepaiyuan_c1(){
	//如已经load将不再、load页面、如果有后台操作则else进行后台操作
	if($(".centerBox-right-c1 button:last").text()!=$(".tepaiyuan_c1").text()){
		$(".centerBox-right-c2").empty();
		$(".centerBox-right-c2").load("test1.html",function(){
			$(".centerBox-right-c1 button:last").text($(".tepaiyuan_c1").text());//连动
		});
		
	}else{}
	

}
//专家对接
function tepaiyuan_c2(){
	//如已经load将不再、load页面、如果有后台操作则else进行后台操作
	if($(".centerBox-right-c1 button:last").text()!=$(".tepaiyuan_c2").text()){
		$(".centerBox-right-c2").empty();
		$(".centerBox-right-c2").load("test2.html",function(){
			$(".centerBox-right-c1 button:last").text($(".tepaiyuan_c2").text());//连动
		});
	}else{}	
}
//解答列表
//专家对接
function tepaiyuan_c3(){
	//如已经load将不再、load页面、如果有后台操作则else进行后台操作
	if($(".centerBox-right-c1 button:last").text()!=$(".tepaiyuan_c3").text()){
		$(".centerBox-right-c2").empty();
		$(".centerBox-right-c2").load("test3.html",function(){
			$(".centerBox-right-c1 button:last").text($(".tepaiyuan_c3").text());//连动
		});
	}else{}	
}

$(function(){
	//默认第一个--特派员--
	
	$(".centerBox-right-c2").load("test1.html",function(){
			$(".centerBox-right-c1 button:last").text($(".tepaiyuan_c1").text());//连动
	});
	

})
