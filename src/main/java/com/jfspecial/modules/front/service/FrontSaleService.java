package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.sale.model.TbSale;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontSaleService extends BaseService {

	private final static String cacheName = "FrontSaleService";

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
	public List<TbSaleAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_sale_album t where  status = 1 order by sort,id desc";
		return TbSaleAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbSaleAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_sale_album t where id = ? ";
		return TbSaleAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbSale> getSale(Paginator paginator) {
		String key = ("article_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbSale> sales = TbSale.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_sale " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by sort,create_time desc");
		return sales;
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbSale> getSale(Paginator paginator, int albumId) {
		String key = ("article_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbSale> sales = TbSale.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_sale " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by sort,create_time desc", albumId);
		return sales;
	}

	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbSale getSale(Integer imageId) {
		String key = "image_" + imageId;
		String sql = "select * from tb_sale t where id = ? ";
		return TbSale.dao.findFirstCache(cacheName, key, sql, imageId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbSale> getRecommendSales(Paginator paginator) {
		String key = ("recommendImage_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbSale> articles = TbSale.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_sale  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by sort,create_time desc");
		return articles;
	}
}
