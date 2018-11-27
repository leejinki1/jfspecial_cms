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
@ControllerBind(controllerKey = "/admin/banner")
public class BannerController extends BaseController {

	private static final String path = "/pages/admin/aboutweb/setting_";

	public void index() {

		show();
	}

	public void show(){
		//System.out.println("测试2:show");//测试方法1
		SysAboutus model = SysAboutus.dao.findById("1");//根据id查出网站的信息,放在model对象中
		//System.out.println("测试2.1:"+model);
		setAttr("model",model);
		render(path + "banner.html");

	}
	public void save1(){
		//renderText("声明此方法是一个action");
		SysAboutus model = getModel(SysAboutus.class);


		//上传图片
		//TbSite site = getBackSite();
		//public UploadFile getFile(String parameterName//参数名称, String saveDirectory//保存路径//默认是tomacat下upload下的文件夹, Integer maxPostSize//最大传输值, String encoding//编码,可用可不用)
		UploadFile uploadImage1=null;//声明上传文件
		//UploadFile uploadImage2=null;//声明上传文件
		//UploadFile uploadImage3=null;//声明上传文件
		try{
			uploadImage1 = getFile("model.banner1","web", FileUploadUtils.UPLOAD_MAX,"utf-8");
			//uploadImage2 = getFile("model.banner2","web", FileUploadUtils.UPLOAD_MAX,"utf-8");
			//uploadImage3 = getFile("model.banner3","web", FileUploadUtils.UPLOAD_MAX,"utf-8");
		}catch(Exception exception){
			System.out.println("路径错误");
		}


		//修改参数
		model.setId("1");
		model.setBanner1("\\web\\"+uploadImage1.getFileName());
		model.setBannerTitle1(getPara("model.bannertitle1"));
		model.setBannerTitleContent1(getPara("model.bannertitlecontent1"));
		//model.setBanner2("\\web\\"+uploadImage2.getFileName());
		//model.setBanner3("\\web\\"+uploadImage3.getFileName());
		//System.out.println("测试:"+uploadImage.getUploadPath()+"\\"+uploadImage.getFileName());



		//修改人员信息
		Integer userid= getSessionUser().getUserid();
		String now = getNow();
		//model.put("update_id", userid);
		//model.put("update_time", now);
		model.setUpdateid(String.valueOf(userid));
		model.setUpdatedate(now);
		model.put("update_name","name");//测试:put能否传到前端

		//修改
		model.update();

		//发送给页面
		System.out.println("测试:"+model);//测试
		setAttr("model", model);
		renderMessage("保存成功");


		render(path + "banner.html");
	}


	//参数:model.
	public void save2(){
		//renderText("声明此方法是一个action");
		SysAboutus model = getModel(SysAboutus.class);


		//上传图片
		//TbSite site = getBackSite();
		//public UploadFile getFile(String parameterName//参数名称, String saveDirectory//保存路径//默认是tomacat下upload下的文件夹, Integer maxPostSize//最大传输值, String encoding//编码,可用可不用)
		//UploadFile uploadImage1=null;//声明上传文件
		UploadFile uploadImage2=null;//声明上传文件
	//	UploadFile uploadImage3=null;//声明上传文件
		try{
			//uploadImage1 = getFile("model.banner1","web", FileUploadUtils.UPLOAD_MAX,"utf-8");
			uploadImage2 = getFile("model.banner2","web", FileUploadUtils.UPLOAD_MAX,"utf-8");
			//uploadImage3 = getFile("model.banner3","web", FileUploadUtils.UPLOAD_MAX,"utf-8");
		}catch(Exception exception){
			System.out.println("路径错误");
		}


		//修改参数
		model.setId("1");
		//model.setBanner1("\\web\\"+uploadImage1.getFileName());
		model.setBanner2("\\web\\"+uploadImage2.getFileName());

		model.setBannerTitle2(getPara("model.bannertitle2"));
		model.setBannerTitleContent2(getPara("model.bannertitlecontent2"));
		//model.setBanner3("\\web\\"+uploadImage3.getFileName());
		//System.out.println("测试:"+uploadImage.getUploadPath()+"\\"+uploadImage.getFileName());



		//修改人员信息
		Integer userid= getSessionUser().getUserid();
		String now = getNow();
		//model.put("update_id", userid);
		//model.put("update_time", now);
		model.setUpdateid(String.valueOf(userid));
		model.setUpdatedate(now);
		model.put("update_name","name");//测试:put能否传到前端

		//修改
		model.update();

		//发送给页面
		System.out.println("测试:"+model);//测试
		setAttr("model", model);
		renderMessage("保存成功");


		render(path + "banner.html");
	}


	//参数:model.
	public void save3(){
		//renderText("声明此方法是一个action");
		SysAboutus model = getModel(SysAboutus.class);


		//上传图片
		//TbSite site = getBackSite();
		//public UploadFile getFile(String parameterName//参数名称, String saveDirectory//保存路径//默认是tomacat下upload下的文件夹, Integer maxPostSize//最大传输值, String encoding//编码,可用可不用)
		//UploadFile uploadImage1=null;//声明上传文件
		//UploadFile uploadImage2=null;//声明上传文件
		UploadFile uploadImage3=null;//声明上传文件
		try{
			//uploadImage1 = getFile("model.banner1","web", FileUploadUtils.UPLOAD_MAX,"utf-8");
			//uploadImage2 = getFile("model.banner2","web", FileUploadUtils.UPLOAD_MAX,"utf-8");
			uploadImage3 = getFile("model.banner3","web", FileUploadUtils.UPLOAD_MAX,"utf-8");
		}catch(Exception exception){
			System.out.println("路径错误");
		}


		//修改参数
		model.setId("1");
		//model.setBanner1("\\web\\"+uploadImage1.getFileName());
		//model.setBanner2("\\web\\"+uploadImage2.getFileName());
		model.setBanner3("\\web\\"+uploadImage3.getFileName());

		model.setBannerTitle3(getPara("model.bannertitle3"));
		model.setBannerTitleContent3(getPara("model.bannertitlecontent3"));
		//System.out.println("测试:"+uploadImage.getUploadPath()+"\\"+uploadImage.getFileName());



		//修改人员信息
		Integer userid= getSessionUser().getUserid();
		String now = getNow();
		//model.put("update_id", userid);
		//model.put("update_time", now);
		model.setUpdateid(String.valueOf(userid));
		model.setUpdatedate(now);
		model.put("update_name","name");//测试:put能否传到前端

		//修改
		model.update();

		//发送给页面
		System.out.println("测试:"+model);//测试
		setAttr("model", model);
		renderMessage("保存成功");


		render(path + "banner.html");
	}
}


