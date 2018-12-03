package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.special.TbSpecial;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.cache.CacheManager;

public class FrontSpecialService extends BaseService {

	private final static String cacheName = "FrontSpecialService";

	/**
	 * 更新缓存,清空
	 * 
	 ** 2018年11月18日 上午3:43:39 ljk
	 */
	public void clearCache() {
		 CacheManager.get(cacheName).clear();
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbSpecial> getSpecial(Paginator paginator) {
		String key = ("special_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbSpecial> specials = TbSpecial.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from sys_user " //
						+ " where usertype = 10 " // 查询状态为显示
						+ " order by create_time desc");
		return specials;
	}
	public Page<TbSpecial> alterSpecial(Paginator paginator) {
		String key = ("special_" + System.currentTimeMillis());
		Page<TbSpecial> specials = TbSpecial.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from sys_user " //
						+ " where usertype = 10 " // 查询状态为显示
						+ " order by create_time desc");
		return specials;
	}
	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbSpecial> getSpecial(Paginator paginator, int userId) {
		String key = ("special_" + userId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbSpecial> specials = TbSpecial.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from sys_user " //
						+ " where userid = ? " // 查询状态为显示
						+ " order by create_time desc", userId);
		return specials;
	}

	public Page<TbSpecial> alterSpecial(Paginator paginator, int userId) {
		String key = ("special_" + System.currentTimeMillis());
		Page<TbSpecial> specials = TbSpecial.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from sys_user " //
						+ " where userid = ? " // 查询状态为显示
						+ " order by create_time desc", userId);
		return specials;
	}
	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbSpecial getSpecial(Integer userId) {
		String key = "special_" + userId;
		String sql = "select * from sys_user t where id = ? ";
		return TbSpecial.dao.findFirstCache(cacheName, key, sql, userId);
	}

}
