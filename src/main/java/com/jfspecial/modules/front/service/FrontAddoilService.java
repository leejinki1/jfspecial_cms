package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.addoil.model.TbAddOil;
import com.jfspecial.modules.admin.addoil.model.TbAddOilAlbum;
import com.jfspecial.modules.admin.sale.model.TbSale;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontAddoilService extends BaseService {

	private final static String cacheName = "FrontAddoilService";

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
	public List<TbAddOilAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_addoil_album t where  status = 1 order by sort,id desc";
		return TbAddOilAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbAddOilAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_addoil_album t where id = ? ";
		return TbAddOilAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbAddOil> getAddOil(Paginator paginator) {
		String key = ("addoil_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbAddOil> addoils = TbAddOil.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.* " //
				, " from tb_addoil t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return addoils;
	}
	public Page<TbAddOil> alterAddOil(Paginator paginator) {
		String key = ("addoil_" + System.currentTimeMillis());
		Page<TbAddOil> addoils = TbAddOil.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.* " //
				, " from tb_addoil t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return addoils;
	}
	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbAddOil> getAddOil(Paginator paginator, int albumId) {
		String key = ("addoil_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbAddOil> addoils = TbAddOil.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_addoil " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return addoils;
	}
	public Page<TbAddOil> alterAddOil(Paginator paginator, int albumId) {
		String key = ("addoil_"  + System.currentTimeMillis());
		Page<TbAddOil> addoils = TbAddOil.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.*  " //
				, " from tb_addoil t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return addoils;
	}

	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbAddOil getAddOil(Integer imageId) {
		String key = "image_" + imageId;
		String sql = "select * from tb_addoil t where id = ? ";
		return TbAddOil.dao.findFirstCache(cacheName, key, sql, imageId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbAddOil> getRecommendImages(Paginator paginator) {
		String key = ("recommendImage_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbAddOil> articles = TbAddOil.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_addoil  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by update_time desc");
		return articles;
	}
}
