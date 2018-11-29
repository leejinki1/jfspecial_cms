package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.modules.admin.sale.model.TbSale;
import com.jfspecial.modules.admin.tags.TbTags;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.modules.front.service.FrontCacheService;
import com.jfspecial.util.extend.HtmlUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@ControllerBind(controllerKey = "/front/sale_tags")
public class SaleTagsController extends BaseProjectController {

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
		//tagName = HtmlUtils.delHTMLTag(tagName);
		// 更新tag
		//tagName = HtmlUtils.delSpecialCode(tagName);

		setAttr("tagName", tagName);

		// 数据列表,只查询展示的和类型为11,12的
		//Page<TbSale> articles = TbSale.dao.paginate(getPaginator(), "select *  from tb_sale t " //
				//		+ " where t.status = 1 "
			//	+ " and name = '%'"+ tagName+"'%'");
				//	+ " and name = ?  "//
			//	+ " order by t.update_time desc ", "%" + tagName + "%" );
		Page<TbSale> articles = TbSale.dao.paginate(getPaginator(), " select t.*", //
				" from tb_sale t " //
						+ " where (t.name like ? or t.content like ?) " //
						+ " and t.status = 1   " // 查询状态为显示，类型是预览和正常的文章
						+ " order by t.update_time desc ", "%" + tagName + "%", "%" + tagName + "%");

		setAttr("page", articles);

		renderAuto(path + "sale_search.html");
	}


}
