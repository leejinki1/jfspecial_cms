package com.jfspecial.modules.admin.application;

import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;

/**
 * 项目申报/信息
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/application_info")
public class ApplicationInfoController extends BaseController {

	private static final String path = "/pages/admin/application/application_info";

	public void index() {
		render(path+".html");//先反回主页,待补充
	}

}


