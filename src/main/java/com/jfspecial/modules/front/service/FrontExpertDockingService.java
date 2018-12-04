package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.expert.model.TbExpertDocking;
import com.jfspecial.modules.admin.expert.model.TbExpertDockingAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontExpertDockingService extends BaseService {

	private final static String cacheName = "FrontExpertDockingService";

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
	public List<TbExpertDockingAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_expert_docking_album t where  status = 1 order by sort,id desc";
		return TbExpertDockingAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbExpertDockingAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_expert_docking_album t where id = ? ";
		return TbExpertDockingAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbExpertDocking> getExpertDocking(Paginator paginator) {
		String key = ("expertdocking_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbExpertDocking> expertdockings = TbExpertDocking.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.* " //
				, " from tb_expert_docking t,(select @i:=0)s " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by update_time desc");
		return expertdockings;
	}
	public Page<TbExpertDocking> alterExpertDocking(Paginator paginator) {
		String key = ("expertdocking_" + System.currentTimeMillis());
		Page<TbExpertDocking> expertdockings = TbExpertDocking.dao.paginateCache(cacheName, key, paginator, "select (@i:=@i+1)pm,t.* " //
				, " from tb_expert_docking t,(select @i:=0)s " //
						+ " where status = 1 and name is not null and name <>''" // 查询状态为显示
						+ " order by update_time desc");
		return expertdockings;
	}
	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbExpertDocking> getExpertDocking(Paginator paginator, int albumId) {
		String key = ("expertdocking_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbExpertDocking> expertdockings = TbExpertDocking.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_expert_docking " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return expertdockings;
	}
	public Page<TbExpertDocking> alterExpertDocking(Paginator paginator, int albumId) {
		String key = ("expertdocking_"  + System.currentTimeMillis());
		Page<TbExpertDocking> expertdockings = TbExpertDocking.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_expert_docking " //
						+ " where status = 1 and name is not null and name <>''" // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by update_time desc", albumId);
		return expertdockings;
	}

	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbExpertDocking getExpertDocking(Integer imageId) {
		String key = "image_" + imageId;
		String sql = "select * from tb_expert_docking t where id = ? ";
		return TbExpertDocking.dao.findFirstCache(cacheName, key, sql, imageId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbExpertDocking> getRecommendImages(Paginator paginator) {
		String key = ("recommendImage_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbExpertDocking> articles = TbExpertDocking.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_expert_docking  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by update_time desc");
		return articles;
	}
}
