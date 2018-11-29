package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.modules.admin.maker.model.TbMaker;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@ControllerBind(controllerKey = "/front/maker_tags")
public class MakerTagsController extends BaseProjectController {

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

		setAttr("tagName", tagName);

		Page<TbMaker> articles = TbMaker.dao.paginate(getPaginator(), " select t.*", //
				" from tb_maker t " //
						+ " where (t.name like ? or t.title like ?) " //
						+ " and t.status = 1   " // 查询状态为显示，类型是预览和正常的文章
						+ " order by t.update_time desc ", "%" + tagName + "%", "%" + tagName + "%");

		setAttr("page", articles);

		renderAuto(path + "maker_search.html");
	}


}
