package com.jfspecial.component.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.SessionUser;
import com.jfspecial.jfinal.component.util.Attr;
import com.jfspecial.modules.admin.site.SessionSite;
import com.jfspecial.modules.admin.site.SiteConstant;
import com.jfspecial.modules.admin.site.SiteService;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.system.config.ConfigCache;

/**
 * 公共属性拦截器
 * 
 * @author ljk
 */
public class CommonInterceptor implements Interceptor {

	private SiteService siteSvc = new SiteService();

	@SuppressWarnings("rawtypes")
	public void intercept(Invocation ai) {

		Controller controller = ai.getController();

		// 请求路径
		String tmpPath = ai.getActionKey();
		tmpPath = JFSpecialUtils.handlerPath(tmpPath);
		boolean isBack = JFSpecialUtils.isBack(tmpPath);

		if (isBack) { // 后台
			SessionUser user = controller.getSessionAttr(Attr.SESSION_NAME);
			if (user == null) {
				String title = ConfigCache.getValue("SYSTEM.TITLE");
				// 设置公共属性
				controller.setAttr(JFSpecialUtils.WEBSITE_TITLE, title);
				controller.setAttr(JFSpecialUtils.TITLE_ATTR, title);
				controller.setAttr(JFSpecialUtils.KEYWORDS_ATTR, title);
				controller.setAttr(JFSpecialUtils.DESCRIPTION_ATTR, title);
			} else {
				TbSite site = siteSvc.getSite(user.getBackSiteId());
				controller.setAttr(JFSpecialUtils.WEBSITE_TITLE, site.getSiteTitle());
				controller.setAttr(JFSpecialUtils.TITLE_ATTR, site.getSiteTitle());
				controller.setAttr(JFSpecialUtils.KEYWORDS_ATTR, site.getSiteTitle());
				controller.setAttr(JFSpecialUtils.DESCRIPTION_ATTR, site.getSiteTitle());
			}
		} else {
			SessionSite sessionSite = controller.getSessionAttr(SiteConstant.getSessionSite());
			if (sessionSite == null) {
				String title = ConfigCache.getValue("SYSTEM.TITLE");
				// 设置公共属性
				controller.setAttr(JFSpecialUtils.WEBSITE_TITLE, title);
				controller.setAttr(JFSpecialUtils.TITLE_ATTR, title);
				controller.setAttr(JFSpecialUtils.KEYWORDS_ATTR, title);
				controller.setAttr(JFSpecialUtils.DESCRIPTION_ATTR, title);
			} else if (sessionSite.getModel() != null) {
				TbSite site = sessionSite.getModel();
				controller.setAttr(JFSpecialUtils.WEBSITE_TITLE, site.getSiteTitle());
				controller.setAttr(JFSpecialUtils.TITLE_ATTR, site.getSiteTitle());
				controller.setAttr(JFSpecialUtils.KEYWORDS_ATTR, site.getSiteTitle());
				controller.setAttr(JFSpecialUtils.DESCRIPTION_ATTR, site.getSiteTitle());
			}
		}

		ai.invoke();
	}
}
