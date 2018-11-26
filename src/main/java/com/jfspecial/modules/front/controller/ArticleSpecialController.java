package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.modules.admin.folder.FolderService;
import com.jfspecial.modules.admin.special.TbSpecial;
import com.jfspecial.modules.admin.sysuser.TbSysUser;
import com.jfspecial.modules.admin.tags.TbTags;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.modules.front.service.FrontCacheService;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.StrUtils;

/**
 * 文章管理
 * 
 * @author ljk 2018-11-14
 */
@ControllerBind(controllerKey = "/front/special/info")
public class ArticleSpecialController extends BaseProjectController {

	public static final String path = "/article/";

	/**
	 * 查看文章
	 * 
	 * @see 不用缓存便于实时更新，访问量大再优化
	 * 
	 *     * 2018年11月18日 上午3:43:39 ljk
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		// 数据列表
		int userId = getParaToInt();

		// 文章
		// TbArticle article = new FrontCacheService().getArticle(articleId);
		SysUser special = SysUser.dao.findFirst(
				"select * from sys_user where userid = ? ",userId);

		// 新增链接跳转
		if (special != null) {
			String jumpUrl = special.getStr("jump_url");
			if (StrUtils.isNotEmpty(jumpUrl)) { // jump url
				redirect(jumpUrl);
				return;
			}
		}

		if (special != null) {

			setAttr("item", special);

		}

		renderAuto(path + "special_info.html");

	}

}
