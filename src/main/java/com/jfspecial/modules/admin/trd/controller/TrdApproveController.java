package com.jfspecial.modules.admin.trd.controller;

import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;

/**
 * 技术需求/待审核
 * @author ZR 2018.11.27
 */
@ControllerBind(controllerKey = "/admin/trd_approve")
public class TrdApproveController extends BaseController {

	private static final String path = "/pages/admin/trd/trd_approve";

	public void index() {
		list();
	}
	public void list(){
		render(path+".html");//先反回主页,待补充
	}
}


