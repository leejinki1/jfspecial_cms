package com.jfspecial.modules.admin.maker.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.ImageModel;
import com.jfspecial.component.util.ImageUtils;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.admin.maker.model.TbMaker;
import com.jfspecial.modules.admin.maker.model.TbMakerAlbum;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.DateUtils;
import com.jfspecial.util.StrUtils;
import com.jfspecial.util.extend.HtmlUtils;

import java.io.File;
import java.util.List;

/**
 * 创客/发布
 * zr  2018.11.27
 */
@ControllerBind(controllerKey = "/admin/maker_publish")
public class MakerPublishController extends BaseProjectController {

	private static final String path = "/pages/admin/maker/maker_publish";

	/**
	 * 跳转到编辑博文页面
	 *
	 * 2018年11月17日 下午9:53:04 ljk
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		setAttr("user", user);


		// 不是自己的文章也想修改,总有不怀好意的人哦
		//	if (model.getCreateId() != user.getUserid()) {
		//		System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
		//		redirect(CommonController.firstPage);
		//		return;
		//	}

		TbMaker model = TbMaker.dao.findById(getParaToInt());
		setAttr("model", model);

		String sql = "select * from tb_maker_album t where  status = 1 order by sort,id desc";
		List<TbMakerAlbum> albums = TbMakerAlbum.dao.find(sql);
		setAttr("albums", albums);

		render(path+".html");//
	}


	/**
	 * publish article
	 *
	 * 2018年11月27日 下午10:12:18 ljk
	 */
	public void publishArticle() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能提交文章！");
			renderJson(json.toJSONString());
			return;
		}

		Integer pid = getParaToInt();
		TbMaker model = getModel(TbMaker.class);
		// 验证题目，内容
		String content = model.getContent();
		String title = model.getName();

		// 删除侵入脚本
		content = JFSpecialUtils.delScriptTag(content);
		title = HtmlUtils.delHTMLTag(title);

		// 这里没有必要提示太精准~因为前台有验证~绕过的都不是好人哦
		if (content == null || HtmlUtils.delHTMLTag(content).length() > 2000 //
				|| title == null || title.length() > 200 //
		) {
			json.put("msg", "文章信息错误，请输入正确数据！");
			renderJson(json.toJSONString());
			return;
		}

		model.setUpdateTime(getNow());
		if (pid != null && pid > 0) { // 更新
			// 管理员或者自己才能修改
			//	if (! model.getCreateId().intValue() != user.getUserid().intValue()) {
			//	if (!isAdmin(user) //
			//	&& model.getCreateId().intValue() != user.getUserid().intValue()) {
			//	json.put("msg", "你没有权限修改文章！");
			//	renderJson(json.toJSONString());
			//	return;
			//	}
			model.update();
		} else { // 新增
			model.remove("id");
			if (model.getAlbumId() == null || model.getAlbumId() <= 0) {
				//model.getAlbumId(JFSpecialUtils.MENU_BLOG); // 博文目录
			}
			model.setStatus(1); // 显示
			//model.setType(11);
			model.setIsComment(1); // 能评论
			model.setIsRecommend(2);// 不推荐
			model.setSort(20); // 排序
			model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS); // 需要审核改为update
			model.setPublishTime(DateUtils.getNow("yyyy-MM-dd")); // 发布时间
			model.setPublishUser(user.getUserName()); // 发布人
			model.setCreateId(getSessionUser().getUserid());
			model.setUpdateId(getSessionUser().getUserid());
			model.setCreateTime(getNow());
			model.save();
		}

		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}


}
