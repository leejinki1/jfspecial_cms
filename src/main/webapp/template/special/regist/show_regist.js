
function loadPicimageCode() {
		document.getElementById("picimageCode").src = '../front/image_code?ran=' + Math.random();
}

function oper_save(){
	var username = $('[name="model.username"]').val();
	var realname = $('[name="model.realname"]').val();
	if(username==''){
		alert('手机号不能为空！');
		return;
	}
	
	if(realname==''){
		alert('姓名不能为空！');
		return;
	}
	
	if(realname.length < 2){
		alert('姓名长度必须大于2！');
		return;
	}
	
	if(realname.length > 10){
		alert('姓名长度必须小于11！');
		return;
	}
	
	var pwd = $('[name="password"]').val();
	var pwd2 = $('[name="password2"]').val();
	if(pwd==''){
		alert('密码不能为空！');
		return;
	}
	if(pwd2==''){
		alert('确认密码不能为空！');
		return;
	}
	if(pwd.length < 6 ){
		alert('密码长度必须大于等于6');
		return;
	}
	if(pwd.length > 20 ){
		alert('密码长度必须小于等于20');
		return;
	}
	
	if(pwd != pwd2){
		alert('两次密码不一致！');
		return;
	}

	var imageCode = $('[name="imageCode"]').val();
	if(imageCode==''){
		alert('验证码不能为空！');
		return;
	}
	if(imageCode.length != 4){
		alert('验证码输入错误！');
		return;
	}
	
	jQuery.ajax({
		type:'POST',
		url: '../front/regist/save',
		data:$("form").serialize(),
		success:function(data){
			if(data.status==1){
				alert('保存成功');
				var prePage = $('[name="pre_page"]').val();
				if (prePage=='') {
					prePage = "/";
				}
				window.top.location.href = prePage;
			} else {
				loadPicimageCode();
				$('[name="imageCode"]').val('');
				$('[name="password"]').val('');
				$('[name="password2"]').val('');
				alert('保存失败：'+data.msg);
			}
		},
		error:function(html){
			var flag = (typeof console != 'undefined');
			if(flag) console.log("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
			alert("服务器忙，提交数据失败，请联系管理员！");
		}
	});
}