package com.jfspecial.component.controller;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.ActionMap;
import com.jfinal.kit.PathKit;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.site.TbSite;

@ControllerBind(controllerKey = "ueditor")
public class Ueditor extends BaseProjectController {

	public void index() {
		String out = new ActionEnter(getRequest(), PathKit.getWebRootPath()).exec();

		// 路径处理
		TbSite site = getSessionSite().getModel();
		int userid = getSessionUser() == null ? 0 : getSessionUser().getUserid();
		// 上传类型
		String actionType = getPara("action");
		int actionCode = ActionMap.getType(actionType);
		String contextPath = getRequest().getContextPath();
		// 文件处理
		String handlerOut = new UeditorService().uploadHandle(actionCode, out, contextPath, site, userid);

		renderText(handlerOut);
	}
}
