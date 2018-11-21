package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.modules.admin.foldernotice.TbFolderNotice;
import com.jfspecial.modules.admin.folderrollpicture.TbFolderRollPicture;
import com.jfspecial.modules.admin.policy.model.TbPolicy;
import com.jfspecial.modules.admin.sale.TbSale;
import com.jfspecial.modules.admin.tags.TbTags;
import com.jfspecial.modules.front.articlelike.ArticleLikeCache;
import com.jfspecial.modules.front.service.FrontCacheService;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateService extends BaseService {

	private final static FrontCacheService service = new FrontCacheService();

	private final static ArticleLikeCache articleLikeservice = new ArticleLikeCache();

	/**
	 * 获取浏览数
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param articleId
	 * @return
	 */
	public static int countView(int articleId) {
		TbArticle article = service.getArticleCount(articleId);
		return article == null ? 0 : article.getCountView();
	}

	/**
	 * 获取评论数
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param articleId
	 * @return
	 */
	public static int countComment(int articleId) {
		TbArticle article = service.getArticleCount(articleId);
		return article == null ? 0 : article.getCountComment();
	}

	/**
	 * 获取浏览数
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param articleId
	 * @return
	 */
	public static boolean isLike(int userId, int articleId) {
		return articleLikeservice.isLike(userId, articleId);
	}

	/**
	 * 获取标签信息
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param paginator
	 * @param folderId
	 *            目录
	 * @return
	 */
	public List<TbTags> tagsListByArticle(int articleId) {
		return service.getTagsByArticle(articleId);
	}

	/**
	 * 获取标签信息
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param paginator
	 * @param folderId
	 *            目录
	 * @return
	 */
	public Page<TbTags> tagsPageByFolder(int pageNo, int pageSize, int folderId) {
		return service.getTagsByFolder(new Paginator(pageNo, pageSize), folderId);
	}

	/**
	 * 获取标签信息
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param paginator
	 * @return
	 */
	public Page<TbTags> tagsPage(int pageNo, int pageSize, int siteId) {
		return service.getTags(new Paginator(pageNo, pageSize), siteId);
	}

	/**
	 * 查询文章，展示的和类型为11,12的推荐文件,前10个
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbArticle> articlePageRecommend(int pageNo, int pageSize, int siteId) {
		return service.getRecommendArticle(new Paginator(pageNo, pageSize), siteId);
	}

	/**
	 * 返回最新文章
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param paginator
	 * @return
	 */
	public Page<TbArticle> articlePageTop(int pageNo, int pageSize, int siteId) {
		return service.getNewArticle(new Paginator(pageNo, pageSize), siteId);
	}

	/**
	 * 返回文章列表
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> articlePageSite(int pageNo, int pageSize, int siteId) {
		return service.getArticleBySiteId(new Paginator(pageNo, pageSize), siteId);
	}
	
	/**
	 * 按照特定排序，返回文章列表
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * @param pageNo
	 * @param pageSize
	 * @param siteId
	 * @param orderType
	 * @return
	 */
	public Page<TbArticle> articleOrder(int pageNo, int pageSize, int siteId, int orderType) {
		return service.getArticleByOrder(new Paginator(pageNo, pageSize), siteId, orderType);
	}

	/**
	 * 返回文章列表
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 * eg:food(1,10,3)
	 */
	public Page<TbArticle> articlePage(int pageNo, int pageSize, int folderId) {
		return service.getArticle(new Paginator(pageNo, pageSize), folderId);
	}

	/**
	 * 返回文章列表不走缓存
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> articlePageNoCache(int pageNo, int pageSize, int folderId) {
		return service.getArticleByNoCache(new Paginator(pageNo, pageSize), folderId);
	}

	/**
	 * 返回对应文章
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public TbArticle article(int articleId) {
		return service.getArticle(articleId);
	}


	/**
	 * eg:food(1,10,3)
	 */
	public Page<TbSale> salePage(int pageNo, int pageSize, int folderId ) {
		return service.getSale(new Paginator(pageNo, pageSize), folderId);
	}
	public TbSale sale(int articleId) {
		return service.getSale(articleId);
	}

	public Page<TbPolicy> policyPage(int pageNo, int pageSize, int folderId ) {
		return service.getPolicy(new Paginator(pageNo, pageSize), folderId);
	}
	public TbPolicy policy(int articleId) {
		return service.getPolicy(articleId);
	}

	/**
	 * 获取栏目滚动图片
	 * 2018年11月18日 下午6:05:54 ljk
	 * @param folderId
	 * @return
	 */
	public List<TbFolderRollPicture> rollPicture(int folderId) {
		return service.getRollPicture(folderId);
	}

	/**
	 * 获取公告信息
	 *
	 * 2018年11月18日 下午6:05:54 ljk
	 * @param folderId
	 * @return
	 */
	public List<TbFolderNotice> notice(int folderId) {
		return service.getNotice(folderId);
	}

}