package com.jfspecial.modules.admin.assistance;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.system.menu.MenuSvc;
import com.jfspecial.system.menu.SysMenu;
import com.jfspecial.system.role.RoleSvc;
import com.jfspecial.system.role.SysRole;
import com.jfspecial.util.StrUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 帮扶企业/帮扶信息
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/assistance_info")
public class AssistanceInfoController extends BaseController {

	private static final String path = "/pages/admin/assistance/assistance_info";

	public void index() {
		render(path+".html");//先反回主页,待补充
	}

}


