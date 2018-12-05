package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.maker.model.TbMaker;
import com.jfspecial.modules.admin.maker.model.TbMakerAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontMakerService extends BaseService {

	private final static String cacheName = "FrontMakerService";

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
	public List<TbMakerAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_maker_album t where  status = 1 order by sort,id desc";
		return TbMakerAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbMakerAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_maker_album t where id = ? ";
		return TbMakerAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbMaker> getMaker(Paginator paginator) {
		String key = ("maker_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbMaker> makers = TbMaker.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_maker " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return makers;
	}
	public Page<TbMaker> alterMaker(Paginator paginator) {
		String key = ("maker_" + System.currentTimeMillis());
		Page<TbMaker> makers = TbMaker.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.* " //
				, " from tb_maker t,(select @i:=0)s  " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return makers;
	}
	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbMaker> getMaker(Paginator paginator, int albumId) {
		String key = ("maker_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbMaker> makers = TbMaker.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_maker " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return makers;
	}
	public Page<TbMaker> alterMaker(Paginator paginator, int albumId) {
		String key = ("maker_" + System.currentTimeMillis());
		Page<TbMaker> makers = TbMaker.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.*  " //
				, " from tb_maker t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return makers;
	}
	public Page<TbMaker> timeMaker(Paginator paginator, int albumId) {
		String key = ("maker_" + System.currentTimeMillis());
		//String wheresql;
		//if (albumId==1) {
		//	wheresql=
		Page<TbMaker> makers = TbMaker.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.*  " //
				, " from tb_maker t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return makers;
	}
	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbMaker getMaker(Integer imageId) {
		String key = "image_" + imageId;
		String sql = "select * from tb_maker t where id = ? ";
		return TbMaker.dao.findFirstCache(cacheName, key, sql, imageId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbMaker> getRecommendImages(Paginator paginator) {
		String key = ("recommendImage_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbMaker> articles = TbMaker.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_maker  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by sort,create_time desc");
		return articles;
	}
}
