package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenter;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenterAlbum;
import com.jfspecial.modules.admin.policy.model.TbPolicy;
import com.jfspecial.modules.admin.spp.model.TbSpp;
import com.jfspecial.modules.admin.spp.model.TbSppAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontNewsCenterService extends BaseService {

	private final static String cacheName = "FrontNewsCenterService";

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
	public List<TbNewsCenterAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_newscenter_album t where  status = 1 order by sort,id desc";
		return TbNewsCenterAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbNewsCenterAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_newscenter_album t where id = ? ";
		return TbNewsCenterAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbNewsCenter> getNewsCenter(Paginator paginator) {
		String key = ("newscenter_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbNewsCenter> newscenters = TbNewsCenter.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_newscenter " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return newscenters;
	}
	public Page<TbNewsCenter> alterNewsCenter(Paginator paginator) {
		String key = ("newscenter_" + System.currentTimeMillis());
		Page<TbNewsCenter> newscenters = TbNewsCenter.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.*  " //
				, " from tb_newscenter t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return newscenters;
	}
	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbNewsCenter> getNewsCenter(Paginator paginator, int albumId) {
		String key = ("newscenter_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbNewsCenter> newscenters = TbNewsCenter.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.*  " //
				, " from tb_newscenter t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return newscenters;
	}
	public Page<TbNewsCenter> alterNewsCenter(Paginator paginator, int albumId) {
		String key = ("newscenter_" + System.currentTimeMillis());
		Page<TbNewsCenter> newscenters = TbNewsCenter.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.*  " //
				, " from tb_newscenter t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return newscenters;
	}
	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbNewsCenter getNewsCenter(Integer imageId) {
		String key = "image_" + imageId;
		String sql = "select * from tb_newscenter t where id = ? ";
		return TbNewsCenter.dao.findFirstCache(cacheName, key, sql, imageId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbNewsCenter> getRecommendImages(Paginator paginator) {
		String key = ("recommendImage_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbNewsCenter> articles = TbNewsCenter.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_newscenter  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by sort,create_time desc");
		return articles;
	}
}
