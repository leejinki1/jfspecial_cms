package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.video.model.TbVideo;
import com.jfspecial.modules.admin.video.model.TbVideoAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontVideoService extends BaseService {

	private final static String cacheName = "FrontVideoService";

	/**
	 * 更新缓存,清空
	 *
	 * 2018年11月18日 上午3:43:39 ljk
	 */
	public void clearCache() {
		CacheManager.get(cacheName).clear();
	}

	/**
	 * 返回相册列表
	 *
	 * @return
	 */
	public List<TbVideoAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_video_album t where  status = 1 order by sort,id desc";
		return TbVideoAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 *
	 * @param albumId
	 * @return
	 */
	public TbVideoAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_video_album t where id = ? ";
		return TbVideoAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbVideo> getVideo(Paginator paginator) {
		String key = ("article_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbVideo> videos = TbVideo.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_video " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by sort,create_time desc");
		return videos;
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbVideo> getVideo(Paginator paginator, int albumId) {
		String key = ("article_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbVideo> videos = TbVideo.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_video " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by sort,create_time desc", albumId);
		return videos;
	}

	/**
	 * 查询图片
	 *
	 * @param videoId
	 * @return
	 */
	public TbVideo getVideo(Integer videoId) {
		String key = "video_" + videoId;
		String sql = "select * from tb_video t where id = ? ";
		return TbVideo.dao.findFirstCache(cacheName, key, sql, videoId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbVideo> getRecommendVideos(Paginator paginator) {
		String key = ("recommendVideo_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbVideo> articles = TbVideo.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_video  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by sort,create_time desc");
		return articles;
	}
}
