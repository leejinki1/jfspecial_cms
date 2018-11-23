package com.jfspecial.modules.admin.assistance;

import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;

/**
 * 帮扶企业/帮扶信息
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/assistance_waitconfirm")
public class AssistanceWaitconfirmController extends BaseController {

	private static final String path = "/pages/admin/assistance/assistance_waitconfirm";

	public void index() {
		render(path+".html");//先反回主页,待补充
	}

}


