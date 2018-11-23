package com.jfspecial.modules.admin.aboutweb;


import com.jfinal.upload.UploadFile;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.system.file.util.FileUploadUtils;

/**
 * 网站配置
 * 
 * @author ZR2018.11.20
 */
@ControllerBind(controllerKey = "/admin/logo")
public class LogoController extends BaseController {

	private static final String path = "/pages/admin/aboutweb/wangzhanshezhi_";

	public void index() {

		//System.out.println("测试1:index++++++++++++"+getPara(0));//测试方法1
		show();
	}

	public void show(){
		//System.out.println("测试2:show");//测试方法1
		SysAboutus model = SysAboutus.dao.findById("1");//根据id查出网站的信息,放在model对象中
		//System.out.println("测试2.1:"+model);
		setAttr("model",model);
		render(path + "c1.html");

	}

	//参数:model.logo
	public void save(){
		//renderText("声明此方法是一个action");
		SysAboutus model = getModel(SysAboutus.class);


		//上传图片
		//TbSite site = getBackSite();
		//public UploadFile getFile(String parameterName//参数名称, String saveDirectory//保存路径//默认是tomacat下upload下的文件夹, Integer maxPostSize//最大传输值, String encoding//编码,可用可不用)
		UploadFile uploadImage=null;//声明上传文件
		try{
			uploadImage = getFile("model.logo","logo", FileUploadUtils.UPLOAD_MAX,"utf-8");
		}catch(Exception exception){
			System.out.println("路径错误");
		}


		//修改参数
		model.setId("1");
		model.setLogo("\\logo\\"+uploadImage.getFileName());
		//System.out.println("测试:"+uploadImage.getUploadPath()+"\\"+uploadImage.getFileName());



		//修改人员信息
		Integer userid= getSessionUser().getUserid();
		String now = getNow();
		//model.put("update_id", userid);
		//model.put("update_time", now);
		model.setUpdateid(String.valueOf(userid));
		model.setUpdatedate(now);

		//修改
		model.update();

		//发送给页面
		System.out.println("测试:"+model);//测试
		setAttr("model", model);
		renderMessage("保存成功");
		show();
	}
}


