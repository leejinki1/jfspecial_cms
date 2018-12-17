package com.jfspecial.modules.admin.newscenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenterAlbum;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenter;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.DateUtils;
import com.jfspecial.util.extend.HtmlUtils;

import java.util.List;

/**
 * 新闻中心/发布
 * zr  2018.11.27
 */
@ControllerBind(controllerKey = "/admin/newscenter_publish")
public class NewsCenterPublishController extends BaseProjectController {

	private static final String path = "/pages/admin/newscenter/newscenter_publish";

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

		TbNewsCenter model = TbNewsCenter.dao.findById(getParaToInt());
		setAttr("model", model);

		String sql = "select * from tb_newscenter_album t where  status = 1 order by sort,id desc";
		List<TbNewsCenterAlbum> albums = TbNewsCenterAlbum.dao.find(sql);
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


		//上传图片
		TbSite site = getBackSite();
		String temUrl=FileUploadUtils.getUploadTmpPath(site);//获取临时存储路径
		UploadFile uploadImage = getFile("model.image_url","newscenter", FileUploadUtils.UPLOAD_MAX,"utf-8");
		//获取路径参数
		TbNewsCenter model = getModel(TbNewsCenter.class);
		if (uploadImage != null) {
			model.setImageUrl("\\upload\\newscenter\\"+uploadImage.getFileName());//设置文件名
		}else{
			System.out.println("上传图片为空");
		}
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
//获取其他参数:
		Integer pid = getParaToInt();
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
			model.set("approve_status", ArticleConstant.APPROVE_STATUS_UPDATE); // 待审核==update;
			model.setPublishTime(DateUtils.getNow("yyyy-MM-dd")); // 发布时间//没有时分秒
			model.setPublishUser(user.getUserName()); // 发布人
			model.setUpdateId(getSessionUser().getUserid());//修改人
			model.setUpdateTime(getNow());//修改时间
			model.setIsDraft("0");//0=发布;1=草稿箱  可能是从草稿箱过来的
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
			model.set("approve_status", ArticleConstant.APPROVE_STATUS_UPDATE); // 需要审核改为update
			model.setPublishTime(DateUtils.getNow("yyyy-MM-dd")); // 发布时间//没有时分秒
			model.setPublishUser(user.getUserName()); // 发布人
			model.setCreateId(getSessionUser().getUserid());//创建人
			model.setCreateTime(getNow());//创建时间
			model.setUpdateId(getSessionUser().getUserid());//修改人
			model.setUpdateTime(getNow());//修改时间
			model.setIsDraft("0");//0==发布;1==草稿箱


			model.save();
		}

		json.put("status", 1);// 成功
		// renderJson(json.toJSONString());
		redirect("/admin/newscenter_person");//12.4修改   后期:跳转到待审核页面
	}



	/**
	 * 保存草稿
	 *
	 * 2018年11月27日 下午10:12:18 ljk
	 */
	public void saveToDrafts() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能提交文章！");
			renderJson(json.toJSONString());
			return;
		}


		//上传图片
		TbSite site = getBackSite();
		String temUrl=FileUploadUtils.getUploadTmpPath(site);//获取临时存储路径
		UploadFile uploadImage = getFile("model.image_url","newscenter", FileUploadUtils.UPLOAD_MAX,"utf-8");
		//获取路径参数
		TbNewsCenter model = getModel(TbNewsCenter.class);
		if (uploadImage != null) {
			model.setImageUrl("\\upload\\newscenter\\"+uploadImage.getFileName());//设置文件名
		}else{
			System.out.println("上传图片为空");
		}
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
//获取其他参数:
		Integer pid = getParaToInt();
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
			model.set("approve_status", ArticleConstant.APPROVE_STATUS_INIT); // 草稿即为未发布
			model.setUpdateId(getSessionUser().getUserid());//修改人
			model.setUpdateTime(getNow());//修改时间
			model.setIsDraft("1");//0=发布;1=草稿箱
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
			model.setIsDraft("1");//0=发布;1=草稿箱
			//model.setPublishTime(DateUtils.getNow("yyyy-MM-dd")); // 发布时间
			//model.setPublishUser(user.getUserName()); // 发布人
			model.set("approve_status", ArticleConstant.APPROVE_STATUS_INIT); // 草稿即为未发布
			model.setCreateId(getSessionUser().getUserid());//创建人
			model.setCreateTime(getNow());//创建时间
			model.setUpdateId(getSessionUser().getUserid());//修改人
			model.setUpdateTime(getNow());//修改时间



			model.save();
		}

		json.put("status", 1);// 成功
		//renderJson(json.toJSONString());
		redirect("/admin/newscenter_drafts");//12.4修改   后期:
	}

}
