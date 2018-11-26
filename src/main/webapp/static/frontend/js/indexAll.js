document.write("jquery-1.11.1.min.js");
document.write("easing.js");
document.write("bootstrap.js");

$(function(){
	var Array=["#58C56F","#FF5D8F","#338AD1","#FFC400"];
	var len=$(".homepage_centger ul").length;
	var j=0;
	for (var i=0;i<len;i++) {
		var ul=$(".homepage_centger ul").eq(i).children(".hc-top,.hc-foot").css("background",Array[j]);
		j++;
		if(j==4){
			j=0;
		}
	}
			
})