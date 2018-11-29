package com.jfspecial.modules.admin.addoil.controller;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.addoil.model.TbAddOil;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 帮扶企业/待发布信息
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/addoil_drafts")
public class AddoilDraftsController extends BaseController {

	private static final String path = "/pages/admin/addoil/addoil_drafts";

	//显示保存的草稿
	public void index() {
		String sql = "select t.id,t.name,t.publish_user, t.update_time from tb_addoil t where  status = 1 and is_draft = 1 order by sort,id desc";
		//草稿:在草稿中1
		List<TbAddOil> lists = TbAddOil.dao.find(sql);
		setAttr("lists", lists);
		render(path+".html");
	}

	/**
	 * del article
	 *删除
	 * 2018年11月27日 下午9:53:04 ljk
	 */
	@Before(FrontInterceptor.class)
	public void  delArticle() {
		SysUser user = (SysUser) getSessionUser();
		Integer id = getParaToInt();
		if (user == null || id == null) {
			redirect(CommonController.firstPage);
			return;
		}

		TbAddOil model = TbAddOil.dao.findById(getParaToInt());
		setAttr("model", model);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		// 删除评论~
		//new CommentService().deleteComment(id);
		// 删除文章
		TbAddOil.dao.deleteById(id);
		redirect("/admin/addoil_drafts");
	}

}


