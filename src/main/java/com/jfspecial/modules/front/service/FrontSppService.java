package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.sale.model.TbSale;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.modules.admin.spp.model.TbSpp;
import com.jfspecial.modules.admin.spp.model.TbSppAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontSppService extends BaseService {

	private final static String cacheName = "FrontSppService";

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
	public List<TbSppAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_spp_album t where  status = 1 order by sort,id desc";
		return TbSppAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbSppAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_sale_album t where id = ? ";
		return TbSppAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbSpp> getSpp(Paginator paginator) {
		String key = ("spp_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbSpp> spps = TbSpp.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_spp " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return spps;
	}
	public Page<TbSpp> alterSpp(Paginator paginator) {
		String key = ("spp_" + System.currentTimeMillis());
		Page<TbSpp> spps = TbSpp.dao.paginateCache(cacheName, key, paginator, "select  (@i:=@i+1)pm,t.*  " //
				, " from tb_spp  t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return spps;
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbSpp> getSpp(Paginator paginator, int albumId) {
		String key = ("spp_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbSpp> spps = TbSpp.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_spp " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return spps;
	}
	public Page<TbSpp> alterSpp(Paginator paginator, int albumId) {
		String key = ("spp_" + System.currentTimeMillis());
		Page<TbSpp> spps = TbSpp.dao.paginateCache(cacheName, key, paginator, "select  (@i:=@i+1)pm,t.*  " //
				, " from tb_spp  t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return spps;
	}
	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbSpp getSpp(Integer imageId) {
		String key = "image_" + imageId;
		String sql = "select * from tb_spp t where id = ? ";
		return TbSpp.dao.findFirstCache(cacheName, key, sql, imageId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbSpp> getRecommendImages(Paginator paginator) {
		String key = ("recommendImage_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbSpp> articles = TbSpp.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_spp  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by sort,create_time desc");
		return articles;
	}
}
