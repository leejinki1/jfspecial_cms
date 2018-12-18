package com.jfspecial.modules.admin.policy.controller;

import com.jfinal.aop.Before;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.policy.model.TbPolicy;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 政策法规/历史
 * zr  2018.11.27
 */
@ControllerBind(controllerKey = "/admin/policy_person")
public class PolicyPersonController extends BaseProjectController {

	private static final String path = "/pages/admin/policy/policy_";

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
				"from tb_policy t " +
				"where approve_status>0 and create_id="+user.getUserid();

		//待审核:审核状态=1初始+++
		List<TbPolicy> lists = TbPolicy.dao.find(sql);
		setAttr("lists", lists);
		render(path+"person.html");
		return;
	}


}
