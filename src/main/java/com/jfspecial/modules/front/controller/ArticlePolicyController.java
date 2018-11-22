package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.modules.admin.folder.FolderService;
import com.jfspecial.modules.admin.policy.model.TbPolicy;
import com.jfspecial.modules.admin.tags.TbTags;
import com.jfspecial.modules.admin.trd.model.TbTrd;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.modules.front.service.FrontCacheService;
import com.jfspecial.util.StrUtils;

/**
 * 文章管理
 * 
 * @author ljk 2018-11-14
 */
@ControllerBind(controllerKey = "/front/policy/article")
public class ArticlePolicyController extends BaseProjectController {

	public static final String path = "/article/";

	/**
	 * 查看文章
	 * 
	 * @see 不用缓存便于实时更新，访问量大再优化
	 * 
	 *     * 2018年11月18日 上午3:43:39 ljk
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		// 数据列表
		int articleId = getParaToInt();

		// 文章
		// TbArticle article = new FrontCacheService().getArticle(articleId);
		TbPolicy article = TbPolicy.dao.findById(articleId);

		// 新增链接跳转
		if (article != null) {
			String jumpUrl = article.getStr("jump_url");
			if (StrUtils.isNotEmpty(jumpUrl)) { // jump url
				redirect(jumpUrl);
				return;
			}
		}

		if (article != null) {
			// 更新浏览量
			String key = getSessionAttr(JFSpecialUtils.USER_KEY);
			if (key != null) {
			//	ArticleCountCache.addCountView(article, key);
				// 缓存访问量和评论数
			//	new FrontCacheService().addArticleCount(article);
			}

			setAttr("item", article);

			// seo：title优化
			setAttr(JFSpecialUtils.TITLE_ATTR, article.getName() + " - " + getAttr(JFSpecialUtils.TITLE_ATTR));

			// 标签


			// 评论

		}

		// 活动目录
		setAttr("folders_selected", 302);

		// 同类文章
		setAttr("folder", new FolderService().getFolder(302));

		Page<TbArticle> articles;
		if (302 == JFSpecialUtils.MENU_BLOG) { // 博客会经常被删除，缓存不靠谱
			articles = new FrontCacheService().getArticleByNoCache(new Paginator(1, 10), 302);
		} else {
			articles = new FrontCacheService().getArticle(new Paginator(1, 10), 302);
		}
		setAttr("articles", articles);

		renderAuto(path + "sale_article.html");

	}

	/**
	 * 查看文章某用户发布文章
	 *
	 */
	@Before(FrontInterceptor.class)
	public void recommend() {
		// 活动目录,哪个都不是
		setAttr("folders_selected", 0);

		// 推荐文章列表，缓存
		Page<TbArticle> pages = new FrontCacheService().getRecommendArticle(getPaginator() //
				, getSessionSite().getSiteId());
		setAttr("page", pages);

		// 显示50个标签
		Page<TbTags> taglist = new FrontCacheService().getTags(new Paginator(1, 50), getSessionSite().getSiteId());
		setAttr("taglist", taglist.getList());

		// seo：title优化
		setAttr(JFSpecialUtils.TITLE_ATTR, "推荐文章 - " + getAttr(JFSpecialUtils.TITLE_ATTR));

		renderAuto(path + "show_recommend.html");
	}

}
