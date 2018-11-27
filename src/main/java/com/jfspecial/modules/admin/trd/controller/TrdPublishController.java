package com.jfspecial.modules.admin.trd.controller;

import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;

/**
 * 技术需求/发布           需要返回下拉框的值
 * @author ZR 2018.11.27
 */
@ControllerBind(controllerKey = "/admin/trd_publish")
public class TrdPublishController extends BaseController {

	private static final String path = "/pages/admin/trd/trd_publish";

	public void index() {
		list();
	}

	public void list(){
		render(path+".html");//先反回主页,待补充
	}
}


