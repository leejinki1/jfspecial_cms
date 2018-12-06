package com.jfspecial.modules.admin.aboutweb;

import com.jfinal.upload.UploadFile;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.system.file.util.FileUploadUtils;

/**
 * 网站配置/banner图更换/banner
 * 
 * @author ZR2018.11.23
 */
@ControllerBind(controllerKey = "/admin/setting_banner")
public class BannerController extends BaseController {

	private static final String path = "/pages/admin/aboutweb/setting_";
	private static String msg="";
	public void index() {

		show();
	}

	public void show(){
		//System.out.println("测试2:show");//测试方法1
		SysAboutus model = SysAboutus.dao.findById("1");//根据id查出网站的信息,放在model对象中
		//System.out.println("测试2.1:"+model);
		setAttr("model",model);
		setAttr("msg",msg);
		msg="";
		render(path + "banner.html");

	}
	public void save1(){
		System.out.println("12.6----进入banner/save1");
		//上传图片
		UploadFile uploadImage1 = getFile("model.banner1","banner", FileUploadUtils.UPLOAD_MAX,"utf-8");//声明上传文件
		SysAboutus model = getModel(SysAboutus.class);
		try{
			model.setBanner1("\\upload\\banner\\"+uploadImage1.getFileName());//设置文件名
		}catch(Exception exception){
			System.out.println("没有上传图片banner1");
		}

		//修改参数
		model.setId("1");

		//修改人员信息
		Integer userid= getSessionUser().getUserid();
		String now = getNow();
		//model.put("update_id", userid);
		//model.put("update_time", now);
		model.setUpdateid(String.valueOf(userid));
		model.setUpdatedate(now);
		model.put("update_name","name");//测试:put能否传到前端

		//修改
		boolean is = model.update();

		//发送给页面
		if(is){
			//setAttr("msg", "保存成功");
			msg="banner1保存成功";
			//System.out.println("保存成功banner1");
		}else{
			setAttr("msg", "保存失败");
			msg="banner1保存失败";
			//System.out.println("失败banner1");
		}

		//发送给页面
		setAttr("model", model);
		renderMessage("保存成功");


		redirect("/admin/setting_banner");//12.4修改
	}


	//参数:model.
	public void save2(){
		System.out.println("12.6----进入banner/save1");
		//上传图片
		UploadFile uploadImage2 = getFile("model.banner2","banner", FileUploadUtils.UPLOAD_MAX,"utf-8");
		SysAboutus model = getModel(SysAboutus.class);
		try{
			model.setBanner2("\\upload\\banner\\"+uploadImage2.getFileName());
		}catch(Exception exception){
			System.out.println("没有上传图片banner2");
		}


		//修改参数
		model.setId("1");



		//修改人员信息
		Integer userid= getSessionUser().getUserid();
		String now = getNow();
		//model.put("update_id", userid);
		//model.put("update_time", now);
		model.setUpdateid(String.valueOf(userid));
		model.setUpdatedate(now);
		model.put("update_name","name");//测试:put能否传到前端

		//修改
		boolean is = model.update();

		//发送给页面
		if(is){
			//setAttr("msg", "保存成功");
			msg="banner2保存成功";
			//System.out.println("保存成功banner2");
		}else{
			setAttr("msg", "保存失败");
			msg="banner2保存失败";
			//System.out.println("失败banner2");
		}

		//发送给页面
		setAttr("model", model);
		renderMessage("保存成功");


		//render(path + "banner.html");
		redirect("/admin/setting_banner");//12.4修改
	}


	//参数:model.
	public void save3(){
		System.out.println("12.6----进入banner/save1");

		//上传图片
		//TbSite site = getBackSite();
		//public UploadFile getFile(String parameterName//参数名称, String saveDirectory//保存路径//默认是tomacat下upload下的文件夹, Integer maxPostSize//最大传输值, String encoding//编码,可用可不用)
		UploadFile uploadImage3 = getFile("model.banner3","banner", FileUploadUtils.UPLOAD_MAX,"utf-8");
		SysAboutus model = getModel(SysAboutus.class);
		try{
			model.setBanner3("\\upload\\banner\\"+uploadImage3.getFileName());
		}catch(Exception exception){
			System.out.println("没有上传图片banner3");
		}


		//修改参数
		model.setId("1");



		//修改人员信息
		Integer userid= getSessionUser().getUserid();
		String now = getNow();
		//model.put("update_id", userid);
		//model.put("update_time", now);
		model.setUpdateid(String.valueOf(userid));
		model.setUpdatedate(now);
		model.put("update_name","name");//测试:put能否传到前端

		//修改
		boolean is = model.update();

		//发送给页面
		if(is){
			//setAttr("msg", "保存成功");
			msg="banner3保存成功";
			//System.out.println("保存成功banner3");
		}else{
			setAttr("msg", "保存失败");
			msg="banner3保存失败";
			//System.out.println("失败banner3");
		}
		setAttr("model", model);
		renderMessage("保存成功");


		//render(path + "banner.html");
		redirect("/admin/setting_banner");//12.4修改
	}
}


