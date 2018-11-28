package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.policy.model.TbPolicy;
import com.jfspecial.modules.admin.policy.model.TbPolicyAlbum;
import com.jfspecial.modules.admin.trd.model.TbTrd;
import com.jfspecial.modules.admin.trd.model.TbTrdAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontTrdService extends BaseService {

	private final static String cacheName = "FrontTrdService";

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
	public List<TbTrdAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_trd_album t where  status = 1 order by sort,id desc";
		return TbTrdAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbTrdAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_trd_album t where id = ? ";
		return TbTrdAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbTrd> getTrd(Paginator paginator) {
		String key = ("article_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbTrd> trds = TbTrd.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_trd " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return trds;
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbTrd> getTrd(Paginator paginator, int albumId) {
		String key = ("article_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbTrd> policys = TbTrd.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_trd " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return policys;
	}

	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbTrd getTrd(Integer imageId) {
		String key = "image_" + imageId;
		String sql = "select * from tb_trd t where id = ? ";
		return TbTrd.dao.findFirstCache(cacheName, key, sql, imageId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbTrd> getRecommendImages(Paginator paginator) {
		String key = ("recommendImage_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbTrd> articles = TbTrd.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_trd where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by update_time desc");
		return articles;
	}
}
