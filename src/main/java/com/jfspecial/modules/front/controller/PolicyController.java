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

@ControllerBind(controllerKey = "/front/policy")
public class PolicyController extends BaseProjectController {

	public static final String path = "/home/";

	@Before(FrontInterceptor.class)
	public void index() {
		String albumId = getPara();
		setSessionAttr("albumId", Integer.parseInt(albumId.toString()));
		//setAttr("albumId", albumId);
		//forwardAction("policy");
		redirect("/policy.html");
	//	renderAuto("/policy.html");
	}


}
