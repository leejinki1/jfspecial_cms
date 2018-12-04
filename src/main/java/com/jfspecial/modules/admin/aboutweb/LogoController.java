package com.jfspecial.modules.admin.aboutweb;


import com.jfinal.upload.UploadFile;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.system.file.util.FileUploadUtils;

import java.io.File;

/**
 * 网站配置/logo更换/logo
 * 
 * @author ZR2018.11.20
 */
@ControllerBind(controllerKey = "/admin/setting_logo")
public class LogoController extends BaseProjectController {

	private static final String path = "/pages/admin/aboutweb/setting_";

	public void index() {

		//System.out.println("测试1:index++++++++++++"+getPara(0));//测试方法1
		show();
	}

	public void show(){
		//System.out.println("测试2:show");//测试方法1
		SysAboutus model = SysAboutus.dao.findById("1");//根据id查出网站的信息,放在model对象中
		//System.out.println("测试2.1:"+model);
		setAttr("model",model);
		render(path + "logo.html");

	}

	//参数:model.logo
	public void save(){
		SysAboutus model = getModel(SysAboutus.class);
		TbSite site = getBackSite();//站点信息//[id:10][name:特派员][template:special][templateMobile:special][domainPc:special.demo.com][domainMobile:special.demo.com][domainOthers:null][siteTitle:科技特派员创业扶贫联盟平台][siteFolderId:1][siteArticleId:1][dbUrl:null][dbUser:null][dbPwd:null][dbDriver:null][sort:10][status:1][updateTime:2018-11-14 00:50:57][updateId:1][createTime:2018-11-14 00:50:57][createId:1]
		//System.out.println("---12.4 site是什么:"+site);
		String temUrl=FileUploadUtils.getUploadTmpPath(site);//获取临时存储路径
		//上传图片
		//public UploadFile getFile(String parameterName//参数名称, String saveDirectory//保存路径//默认是tomacat下upload下的文件夹, Integer maxPostSize//最大传输值, String encoding//编码,可用可不用)
		UploadFile uploadImage = getFile("model.logo",temUrl, FileUploadUtils.UPLOAD_MAX,"utf-8");
		//long currentTime=System.currentTimeMillis();//12.4 获取当前时间戳//打算改文件名的,没有可改的地方

		if (uploadImage != null) {
			//File file=new File(temUrl+"\\"+uploadImage.getFileName());
			//System.out.println("12.4 ---"+temUrl+"\\"+uploadImage.getFileName());
			//String fileUrl = uploadHandler(site,file,"image");
			model.setLogo(temUrl+"\\"+uploadImage.getFileName());//设置文件名
			//model.setLogo(fileUrl);//设置文件名
		}else{
			System.out.println("上传图片为空");
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

		//修改
		model.update();

		//发送给页面
		System.out.println("测试:"+model);//测试
		setAttr("model", model);
		renderMessage("保存成功");

		//render( "/pages/admin/aboutweb/setting_logo.html");//12.4修改
		redirect("/admin/setting_logo");//12.4修改
	}
}


