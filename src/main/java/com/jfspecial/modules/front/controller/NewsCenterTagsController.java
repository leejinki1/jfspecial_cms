package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.trd.model.TbTrd;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@ControllerBind(controllerKey = "/front/newscenter_tags")
public class NewsCenterTagsController extends BaseProjectController {

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

		Page<TbTrd> articles = TbTrd.dao.paginate(getPaginator(), " select t.*", //
				" from tb_newscenter t " //
						+ " where (t.name like ? or t.content like ?) " //
						+ " and t.status = 1   " // 查询状态为显示，类型是预览和正常的文章
						+ " order by t.update_time desc ", "%" + tagName + "%", "%" + tagName + "%");

		setAttr("page", articles);

		renderAuto(path + "newscenter_search.html");
	}


}
