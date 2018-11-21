package com.jfspecial.modules.front.articlelike;

import com.alibaba.fastjson.JSONObject;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.system.user.SysUser;

/**
 * 文章喜欢
 * 
 * @author ljk 2018-11-16
 */
@ControllerBind(controllerKey = "/front/articlelike")
public class ArticleLikeController extends BaseProjectController {

	/**
	 * 喜欢
	 *
	 * 2018年11月18日 上午3:43:39 ljk
	 */
	public void yes() {
		Integer articleId = getParaToInt();

		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能操作！");
			renderJson(json.toJSONString());
			return;
		}

		if (articleId == null || articleId <= 0) {
			json.put("msg", "操作异常");
			renderJson(json.toJSONString());
			return;
		}

		// 不存在就创建
		TbArticleLike model = TbArticleLike.dao.findFirstByWhere(" where article_id = ? and create_id = ? " //
				, articleId, user.getUserid());
		if (model == null) {
			model = new TbArticleLike();
			model.setArticleId(articleId);
			model.setCreateId(getSessionUser().getUserid());
			model.setCreateTime(getNow());
			model.save();
			// 添加缓存
			new ArticleLikeCache().add(user.getUserid(), articleId);
		}

		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}

	/**
	 * 取消喜欢
	 ** 2018年11月18日 上午3:43:39 ljk
	 */
	public void no() {
		Integer articleId = getParaToInt();

		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能操作！");
			renderJson(json.toJSONString());
			return;
		}

		if (articleId == null || articleId <= 0) {
			json.put("msg", "操作异常");
			renderJson(json.toJSONString());
			return;
		}

		// 存在删除对象
		TbArticleLike model = TbArticleLike.dao.findFirstByWhere(" where article_id = ? and create_id = ? " //
				, articleId, user.getUserid());
		if (model != null) {
			model.delete();
			// 删除缓存
			new ArticleLikeCache().delete(user.getUserid(), articleId);
		}

		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}
}
