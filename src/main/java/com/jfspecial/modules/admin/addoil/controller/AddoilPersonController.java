package com.jfspecial.modules.admin.addoil.controller;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.addoil.model.TbAddOil;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 帮扶企业/我的帮扶
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/addoil_person")
public class AddoilPersonController extends BaseController {

	private static final String path = "/pages/admin/addoil/addoil_";

	//显示待审核
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
				"from tb_addoil t " +
				"where  status = 1 and approve_status>0 and create_id="+user.getUserid();

			//待审核:审核状态=1初始+++
			List<TbAddOil> lists = TbAddOil.dao.find(sql);
			setAttr("lists", lists);
			render(path+"person.html");
			return;
	}



}


