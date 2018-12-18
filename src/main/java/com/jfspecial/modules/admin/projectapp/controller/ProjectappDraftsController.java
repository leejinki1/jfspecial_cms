package com.jfspecial.modules.admin.projectapp.controller;

import com.jfinal.aop.Before;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.ImageModel;
import com.jfspecial.component.util.ImageUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenter;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenterTags;
import com.jfspecial.modules.admin.newscenter.service.NewsCenterAlbumService;
import com.jfspecial.modules.admin.projectapp.model.TbProjectApp;
import com.jfspecial.modules.admin.projectapp.model.TbProjectAppAlbum;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.StrUtils;

import java.io.File;
import java.util.List;

/**
 * 项目申报/草稿
 * zr  2018.11.27
 */
@ControllerBind(controllerKey = "/admin/projectapp_drafts")
public class ProjectappDraftsController extends BaseProjectController {

	private static final String path = "/pages/admin/projectapp/projectapp_";

	//显示保存的草稿
	public void index() {
		//判断user是否登录,
		SysUser user = (SysUser) getSessionUser();
		//Integer id = getParaToInt();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}
		//判断权限//只能显示自己创建的草稿
		String sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.image_net_url,t.album_name   " +
				"from tb_projectapp t where is_draft = 1 and create_id=" +user.getUserid()+
				" order by sort,id desc";



		List<TbProjectApp> lists = TbProjectApp.dao.find(sql);
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

		TbProjectApp model = TbProjectApp.dao.findById(getParaToInt());
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
		TbProjectApp.dao.deleteById(id);
		redirect("/admin/projectapp_drafts");
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
		TbProjectApp model = TbProjectApp.dao.findById(getParaToInt());
		setAttr("model", model);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//传参,下拉列表
		String sql = "select * from tb_projectapp_album t where  status = 1 order by sort,id desc";
		List<TbProjectAppAlbum> albums = TbProjectAppAlbum.dao.find(sql);
		setAttr("albums", albums);

		render(path+"edit.html");//转到编辑页
	}
}
