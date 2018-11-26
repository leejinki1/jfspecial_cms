package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;

@ControllerBind(controllerKey = "/front/special")
public class SpecialInfoController extends BaseProjectController {

	public static final String path = "/home/";

	@Before(FrontInterceptor.class)
	public void index() {
		String albumId = getPara();
		setSessionAttr("albumId", Integer.parseInt(albumId.toString()));
		//setAttr("albumId", albumId);
		//forwardAction("policy");
		redirect("/special.html");
		// renderAuto("/policy.html");
	}


}
