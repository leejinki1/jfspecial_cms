package com.jfspecial.modules.front.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.policy.model.TbPolicy;
import com.jfspecial.modules.admin.policy.model.TbPolicyAlbum;
import com.jfspecial.util.cache.CacheManager;

import java.util.List;

public class FrontPolicyService extends BaseService {

	private final static String cacheName = "FrontPolicyService";

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
	public List<TbPolicyAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_policy_album t where  status = 1 order by sort,id desc";
		return TbPolicyAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbPolicyAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_policy_album t where id = ? ";
		return TbPolicyAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @return
	 */
	public Page<TbPolicy> getPolicy(Paginator paginator) {
		String key = ("article_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbPolicy> policys = TbPolicy.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_policy " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by sort,create_time desc");
		return policys;
	}

	/**
	 * 查询图片
	 *
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbPolicy> getPolicy(Paginator paginator, int albumId) {
		String key = ("article_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbPolicy> policys = TbPolicy.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_policy " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by sort,create_time desc", albumId);
		return policys;
	}

	/**
	 * 查询图片
	 *
	 * @param imageId
	 * @return
	 */
	public TbPolicy getPolicy(Integer imageId) {
		String key = "image_" + imageId;
		String sql = "select * from tb_policy t where id = ? ";
		return TbPolicy.dao.findFirstCache(cacheName, key, sql, imageId);
	}

	/**
	 * 查询推荐图片
	 *
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbPolicy> getRecommendImages(Paginator paginator) {
		String key = ("recommendImage_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbPolicy> articles = TbPolicy.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_policy  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by sort,create_time desc");
		return articles;
	}
}
