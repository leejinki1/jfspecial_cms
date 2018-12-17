package com.jfspecial.modules.admin.projectapp.controller;

import com.jfinal.aop.Before;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.projectapp.model.TbProjectApp;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 项目申报/历史
 * zr  2018.11.27
 */
@ControllerBind(controllerKey = "/admin/projectapp_person")
public class ProjectappPersonController extends BaseProjectController {

	private static final String path = "/pages/admin/projectapp/projectapp_";

	@Before(FrontInterceptor.class)
	public void index() {
		//判断user是否登录,
		SysUser user = (SysUser) getSessionUser();
		//Integer id = getParaToInt();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		//判断权限//不用判断权限,只显示自己发布的

		String sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.image_net_url,t.album_name,t.approve_status  " +
				"from tb_projectapp t " +
				"where  status = 1 and approve_status>0 and create_id="+user.getUserid();

		//待审核:审核状态=1初始+++
		List<TbProjectApp> lists = TbProjectApp.dao.find(sql);
		setAttr("lists", lists);
		render(path+"person.html");
		return;
	}


}
