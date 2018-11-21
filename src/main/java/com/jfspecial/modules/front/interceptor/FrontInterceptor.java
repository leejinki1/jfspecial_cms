package com.jfspecial.modules.front.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.modules.admin.folder.FolderService;
import com.jfspecial.modules.admin.folder.TbFolder;
import com.jfspecial.modules.admin.friendlylink.FriendlylinkCache;
import com.jfspecial.modules.admin.friendlylink.TbFriendlylink;
import com.jfspecial.modules.admin.site.SessionSite;
import com.jfspecial.modules.admin.site.SiteConstant;
import com.jfspecial.modules.front.service.FrontCacheService;

import java.util.List;
import java.util.Map;

/**
 * 用户认证拦截器
 * 
 * @author ljk 2018-11-14
 */
public class FrontInterceptor implements Interceptor {

	public void intercept(Invocation ai) {

		Controller controller = ai.getController();
		// 栏目，缓存
		SessionSite site = null;
		if (controller instanceof BaseProjectController) {
			site = ((BaseProjectController) controller).getSessionSite();
		} else {
			site = controller.getSessionAttr(SiteConstant.getSessionSite());
		}
		Map<String, List<TbFolder>> folders = new FolderService().getFolderMenus(site.getSiteId());
		controller.setAttr("folders", folders);

		// 推荐文章列表，缓存
		Page<TbArticle> recommendArticles = new FrontCacheService().getRecommendArticle(new Paginator(1, 8),
				site.getSiteId());
		controller.setAttr("recommendArticles", recommendArticles);

		// 友情链接，缓存
		List<TbFriendlylink> friendlylinkList = FriendlylinkCache.getFriendlylinkList(site.getSiteId());
		controller.setAttr("friendlylinkList", friendlylinkList);

		// 底部关于，缓存
		List<TbFriendlylink> aboutList = FriendlylinkCache.getAboutList(site.getSiteId());
		controller.setAttr("aboutList", aboutList);

		ai.invoke();
	}
}
