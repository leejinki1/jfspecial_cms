package com.jfspecial.modules.admin.addoil.controller;

import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;

/**
 * 帮扶企业/帮扶信息
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/addoil_approve")
public class AddoilApproveController extends BaseController {

	private static final String path = "/pages/admin/addoil/addoil_approve";

	public void index() {
		render(path+".html");//先反回主页,待补充
	}

}


