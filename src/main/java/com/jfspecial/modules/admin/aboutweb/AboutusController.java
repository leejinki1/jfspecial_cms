package com.jfspecial.modules.admin.aboutweb;


import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;

import javax.xml.ws.RequestWrapper;

/**
 * 网站配置
 * 
 * @author ZR2018.11.20
 */
@ControllerBind(controllerKey = "/admin/aboutweb")
public class AboutusController extends BaseController {

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
		render(path + "c2.html");

	}

	//这个方法没有试验是否有效,因为没有进来
	public void save(){
		//renderText("声明此方法是一个action");

		//获取前台页面的值
		//System.out.println("测试3:save++++++++"+getPara("model.introduction"));//测试方法1
		SysAboutus model = getModel(SysAboutus.class);
		//用set方法修改model的值
		model.setIntroduction(getPara("model.introduction"));
		model.setId("1");

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


