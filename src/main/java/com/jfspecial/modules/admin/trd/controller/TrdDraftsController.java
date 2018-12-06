package com.jfspecial.modules.admin.trd.controller;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.trd.model.TbTrd;
import com.jfspecial.modules.admin.trd.model.TbTrdAlbum;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 技术需求/草稿箱
 * @author ZR 2018.11.27
 */
@ControllerBind(controllerKey = "/admin/trd_drafts")
public class TrdDraftsController extends BaseController {

	private static final String path = "/pages/admin/trd/trd_";

	//显示保存的草稿
	public void index() {
		String sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.album_name " +
				"from tb_trd t where  status = 1 and is_draft = 1 order by sort,id desc";
		List<TbTrd> lists = TbTrd.dao.find(sql);
		setAttr("lists", lists);
		render(path+"drafts.html");
	}

	/**
	 * del  article
	 *	删除
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

		TbTrd model = TbTrd.dao.findById(getParaToInt());
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
		TbTrd.dao.deleteById(id);
		redirect("/admin/trd_drafts");
	}

	/**
	 *	edit article
	 *	修改
	 * 	2018.12.5 zr
	 */
	@Before(FrontInterceptor.class)
	public void  editArticle() {

		//判断登陆和是否将id传过来
		SysUser user = (SysUser) getSessionUser();
		Integer id = getParaToInt();
		//id不存在或用户名不存在,返回
		if (user == null || id == null) {
			redirect(CommonController.firstPage);
			return;
		}

		//判断权限,非本人不能删除
		TbTrd model = TbTrd.dao.findById(getParaToInt());
		setAttr("model", model);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//传参,下拉列表
		String sql = "select * from tb_trd_album t where  status = 1 order by sort,id desc";
		List<TbTrdAlbum> albums = TbTrdAlbum.dao.find(sql);
		setAttr("albums", albums);

		render(path+"edit.html");//转到编辑页
	}
}


