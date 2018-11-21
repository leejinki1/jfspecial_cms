package com.jfspecial.modules.admin.site;

import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class SiteService extends BaseService {

	/**
	 * 缓存
	 */
	private final static String cacheName = "SiteService";

	public void clearCache() {
		CacheManager.get(cacheName).clear();
	}

	public int getDefaultId() {
		return getDefaultSite().getId();
	}
	
	public TbSite getDefaultSite() {
		return TbSite.dao.findFirstCache(cacheName, "getDefaultSite" //
				, "select * from tb_site where site_defalut = ?", SiteConstant.SITE_DEFAULT_YES);
	}

	public List<TbSite> getSiteList() {
		return TbSite.dao.findCache(cacheName, "getSiteList", "select * from tb_site where status =  "
				+ JFSpecialUtils.STATUS_SHOW);
	}

	public TbSite getSite(int siteId) {
		return TbSite.dao.findFirstCache(cacheName, "getSite_" + siteId //
		, "select * from tb_site where id = ? ", siteId);
	}

}
