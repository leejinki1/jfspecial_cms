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
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.StrUtils;

import java.io.File;
import java.util.List;

/**
 * 项目申报/历史
 * zr  2018.11.27
 */
@ControllerBind(controllerKey = "/admin/projectapp_history")
public class ProjectappHistoryController extends BaseProjectController {

	private static final String path = "/pages/admin/projectapp/projectapp_history";

	@Before(FrontInterceptor.class)
	public void index() {
		//判断user是否登录,
		SysUser user = (SysUser) getSessionUser();
		//Integer id = getParaToInt();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		//判断权限
		String sql;
		Integer usertype = user.getInt("usertype");
		if(usertype>0&&usertype<10){
			//管理员
			sql = "select t.id,t.name,t.publish_user, t.update_time,t.content,t.image_url,t.image_net_url,t.album_name  " +
					"from tb_projectapp t " +
					"where t.status = 1  and approve_status = 10 order by t.sort,t.id desc";
		}else{
			//非管理员,只能看到自己的权限//理论上进不来
			sql = "select t.id,t.name,t.publish_user, t.update_time,t.content,t.image_url,t.image_net_url,t.album_name  " +
					"from tb_projectapp t " +
					"where t.status = 1  and approve_status = 10 and create_id=" +user.getUserid()+
					" order by t.sort,t.id desc";
		}

		//历史===已发布(approve.status=pass)===不在草稿(is_draft=0)
		List<TbProjectApp> lists = TbProjectApp.dao.find(sql);
		setAttr("lists", lists);
		render(path+".html");//先反回主页,待补充
	}

	/**
	 * del  article
	 *	删除
	 *	未完成功能:1.判断权限,是否能删
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
		redirect("/admin/projectapp_history");
	}


}
