package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.modules.admin.tags.TbTags;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.modules.front.service.FrontCacheService;
import com.jfspecial.util.extend.HtmlUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@ControllerBind(controllerKey = "/front/tags")
public class TagsController extends BaseProjectController {

	public static final String path = "/tags/";

	@Before(FrontInterceptor.class)
	public void index() {
		String tagName = getPara();
		try {
			tagName = (tagName == null ? "" : tagName);
			tagName = URLDecoder.decode(tagName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 去除标签
		tagName = HtmlUtils.delHTMLTag(tagName);
		// 更新tag
		tagName = HtmlUtils.delSpecialCode(tagName);

		setAttr("tagName", tagName);

		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), " select a.*", //
				" from (select distinct t.*  from tb_article t " //
						+ " left join tb_folder tf on tf.id = t.folder_id " //
						+ " left join tb_tags tag on tag.article_id = t.id " //
						+ " where (tag.tagname like ? or t.title like ?) " //
						+ " and t.status = 1 and t.type in (11,12)  " // 查询状态为显示，类型是预览和正常的文章
						+ " and tf.site_id = " + getSessionSite().getSiteId() //
						+ " order by t.sort,t.create_time desc ) a", "%" + tagName + "%", "%" + tagName + "%");
		setAttr("page", articles);

		// 显示50个标签
		if (articles.getTotalRow() > 0) {
			Page<TbTags> taglist = new FrontCacheService().getTagsByFolder(new Paginator(1, 50), articles.getList()
					.get(0).getFolderId());
			setAttr("taglist", taglist.getList());
		} else {
			Page<TbTags> taglist = new FrontCacheService().getTags(new Paginator(1, 50), getSessionSite().getSiteId());
			setAttr("taglist", taglist.getList());
		}

		// seo：title优化
		setAttr(JFSpecialUtils.TITLE_ATTR, tagName + " - 搜索 - " + getAttr(JFSpecialUtils.TITLE_ATTR));

		renderAuto(path + "search.html");
	}


}
