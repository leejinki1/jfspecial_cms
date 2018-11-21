package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.modules.front.service.FrontCacheService;

/**
 * 关于我们
 * 
 ** 2018年11月18日 上午3:43:39 ljk
 */
@ControllerBind(controllerKey = "/front/about")
public class AboutController extends BaseProjectController {

	public static final String path = "/about/";

	/**
	 * 关于我们
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		Integer articleId = getParaToInt();

		setAttr("folders_selected", JFSpecialUtils.MENU_ABOUT);
		
		Page<TbArticle> pages = new FrontCacheService().getArticle(new Paginator(1, 100), JFSpecialUtils.MENU_ABOUT);
		setAttr("pages", pages);

		TbArticle article = null;
		if (articleId == null || articleId <= 0) {
			article = pages.getList().get(0);
		} else {
			article = new FrontCacheService().getArticle(articleId);
		}
		setAttr("article", article);

		// seo：title优化
		setAttr(JFSpecialUtils.TITLE_ATTR, article.getTitle() + " - " + "关于我们 - " + getAttr(JFSpecialUtils.TITLE_ATTR));

		renderAuto(path + "show_about.html");
	}
}
