package com.jfspecial.modules.admin.aboutweb;


import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.util.ImageModel;
import com.jfspecial.component.util.ImageUtils;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.system.user.SysUser;
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
@ControllerBind(controllerKey = "/admin/setting_contact")
public class WebInfoController extends BaseProjectController {

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
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能提交文章！");
			renderJson(json.toJSONString());
			return;
		}

		//上传图片
		TbSite site = getBackSite();
		String temUrl=FileUploadUtils.getUploadTmpPath(site);//获取临时存储路径
		UploadFile uploadImage = getFile("model.weichat",temUrl, FileUploadUtils.UPLOAD_MAX,"utf-8");
		//获取路径参数
		SysAboutus model = getModel(SysAboutus.class);
		if (uploadImage != null) {
			model.setWechat(temUrl+"\\"+uploadImage.getFileName());//设置文件名
		}else{
			System.out.println("上传图片为空");
		}

		//获取前台页面的值
		Integer pid = getParaToInt();//获取路径中的id值
		//Integer pid =1;
		String wechat = getPara("wechat");
		model.setId("1");



		//设置修改人和修改时间
		Integer userid= getSessionUser().getUserid();
		String now = getNow();
		//aboutus.put("update_id", userid);
		//aboutus.put("update_time", now);
		model.setUpdateid(String.valueOf(userid));
		model.setUpdatedate(now);

		//修改
		boolean is=false;
		if(pid != null && pid > 0) {//更新
			is=model.update();
			if(is){
				System.out.println("修改成功");//测试
				json.put("status", 1);// 成功
			}else {
				System.out.println("修改失败");//测试
				json.put("msg","数据库错误");// 失败
			}
		}else{//新增
			model.remove("id");
			is=model.save();
			if(is){
				System.out.println("新增成功");//测试
				json.put("status", 1);// 成功
			}else {
				System.out.println("新增失败");//测试
				json.put("msg","数据库错误");// 失败
			}
		}
		//保存设置,返回给前台
		setAttr("model", model);
		//增加msg的值,报错误原因

		redirect("/admin/setting_contact");//12.4修改
	}
}


