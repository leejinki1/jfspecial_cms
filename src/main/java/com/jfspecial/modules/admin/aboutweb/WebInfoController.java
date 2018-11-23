package com.jfspecial.modules.admin.aboutweb;


import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.util.ImageModel;
import com.jfspecial.component.util.ImageUtils;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.util.StrUtils;import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.ImageModel;
import com.jfspecial.component.util.ImageUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.image.model.TbImage;
import com.jfspecial.modules.admin.image.model.TbImageTags;
import com.jfspecial.modules.admin.image.service.ImageAlbumService;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.util.StrUtils;


import java.io.File;

/**
 * 网站配置/联系方式/contact
 * 
 * @author ZR2018.11.20
 */
@ControllerBind(controllerKey = "/admin/hostcontact")
public class WebInfoController extends BaseController {

	private static final String path = "/pages/admin/aboutweb/setting_";
	
	public void index() {
		//System.out.println("测试1:index");//测试方法1
		show();
	}

	public void show(){
		//System.out.println("测试2:show");//测试方法1
		SysAboutus model = SysAboutus.dao.findById("1");//根据id查出网站的信息,放在model对象中
		//System.out.println("测试2.1:"+model);
		setAttr("model",model);
		render(path + "contact.html");

	}

	//这个方法没有试验是否有效,因为没有进来
	public void save(){

		//renderText("声明此方法是一个action");

		//上传图片
		//TbSite site = getBackSite();
		//public UploadFile getFile(String parameterName//参数名称, String saveDirectory//保存路径//默认是tomacat下upload下的文件夹, Integer maxPostSize//最大传输值, String encoding//编码,可用可不用)
		//UploadFile uploadImage = getFile("model.image_url", FileUploadUtils.getUploadTmpPath(site), FileUploadUtils.UPLOAD_MAX);
		UploadFile uploadImage=null;
		try{
			uploadImage = getFile("model.wechat","logo",FileUploadUtils.UPLOAD_MAX,"utf-8");
		}catch(Exception exception){
			System.out.println("路径错误");
		}


		//获取前台页面的值
		//System.out.println("测试3:save++++++++"+getPara("model.introduction"));//测试方法1
		SysAboutus model = getModel(SysAboutus.class);
		//用set方法修改model的值
		model.setId("1");
		model.setDepartment(getPara("model.department"));
		model.setAddress(getPara("model.address"));
		model.setTel(getPara("model.tel"));
		model.setFax(getPara("model.fax"));
		model.setEmail(getPara("model.email"));
		model.setWechat(uploadImage.getUploadPath()+"\\"+uploadImage.getFileName());
		//System.out.println("测试:"+uploadImage.getUploadPath()+uploadImage.getFileName());



		//设置修改人和修改时间
		Integer userid= getSessionUser().getUserid();
		String now = getNow();
		//aboutus.put("update_id", userid);
		//aboutus.put("update_time", now);
		model.setUpdateid(String.valueOf(userid));
		model.setUpdatedate(now);

		//修改
		//System.out.println("测试:"+model);//测试
		boolean a=model.update();
		if(a){
			System.out.println("成功");//测试
		}else {
			System.out.println("失败");//测试
		}
		//System.out.println("测试:"+model);//测试

		//保存设置,返回给前台
		setAttr("model", model);
		renderMessage("保存成功");
		show();
	}
}


