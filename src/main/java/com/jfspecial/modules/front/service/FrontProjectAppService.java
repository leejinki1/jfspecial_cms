package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.projectapp.model.TbProjectApp;
import com.jfspecial.modules.admin.projectapp.model.TbProjectAppAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontProjectAppService extends BaseService {

	private final static String cacheName = "FrontProjectAppService";

	/**
	 * 更新缓存,清空
	 * 
	 ** 2018年11月18日 上午3:43:39 ljk
	 */
	public void clearCache() {
		 CacheManager.get(cacheName).clear();
	}

	/**
	 * 返回相册列表
	 * 
	 ** 2018年11月18日 上午3:43:39 ljk
	 * 
	 * @return
	 */
	public List<TbProjectAppAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_projectapp_album t where  status = 1 order by sort,id desc";
		return TbProjectAppAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbProjectAppAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_projectapp_album t where id = ? ";
		return TbProjectAppAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbProjectApp> getProjectApp(Paginator paginator) {
		String key = ("projectapp_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbProjectApp> projectapps = TbProjectApp.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_projectapp " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return projectapps;
	}
	public Page<TbProjectApp> alterProjectApp(Paginator paginator) {
		String key = ("projectapp_" + System.currentTimeMillis());
		Page<TbProjectApp> projectapps = TbProjectApp.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_projectapp " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return projectapps;
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbProjectApp> getProjectApp(Paginator paginator, int albumId) {
		String key = ("projectapp_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbProjectApp> projectapps = TbProjectApp.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_projectapp " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return projectapps;
	}
	public Page<TbProjectApp> alterProjectApp(Paginator paginator, int albumId) {
		String key = ("projectapp_" + System.currentTimeMillis());
		Page<TbProjectApp> projectapps = TbProjectApp.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_projectapp " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return projectapps;
	}
	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbProjectApp getProjectApp(Integer imageId) {
		String key = "image_" + imageId;
		String sql = "select * from tb_projectapp t where id = ? ";
		return TbProjectApp.dao.findFirstCache(cacheName, key, sql, imageId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbProjectApp> getRecommendImages(Paginator paginator) {
		String key = ("recommendImage_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbProjectApp> articles = TbProjectApp.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_projectapp  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by sort,create_time desc");
		return articles;
	}
}
