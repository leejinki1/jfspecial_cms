package com.jfspecial.modules;

import com.jfinal.aop.Before;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.ImageCode;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.aboutweb.SysAboutus;
import com.jfspecial.modules.admin.folder.FolderService;
import com.jfspecial.modules.admin.folder.TbFolder;
import com.jfspecial.modules.admin.site.SessionSite;
import com.jfspecial.modules.front.Home;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.dict.DictCache;
import com.jfspecial.system.log.SysLog;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.system.user.UserCache;
import com.jfspecial.util.Config;
import com.jfspecial.util.NumberUtils;
import com.jfspecial.util.StrUtils;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "/")
public class CommonController extends BaseProjectController {

	public static final String loginPage = "/login.html";
	public static final String firstPage = "/home";

	/**
	 * 首页，菜单
	 * 
	 * 2018年11月25日 下午11:00:28 ljk
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		// new FrontService().menu(this);
		int folderRoot = TbFolder.ROOT;
		SessionSite site = getSessionSite();
		Integer siteFolderId = site.getModel().getSiteFolderId();
		if (siteFolderId != null && siteFolderId > 0) {
			folderRoot = siteFolderId;
		}

		String folderStr = getPara();
		Integer folderId = folderRoot;

		if (folderStr != null) {
			if (NumberUtils.parseInt(folderStr) > 0) {
				folderId = NumberUtils.parseInt(folderStr);
			} else {
				folderId = NumberUtils.parseInt(FolderService.getMenu(folderStr, site.getSiteId()));
			}
		}

		if (folderId == null || folderId <= 0) {
			folderId = folderRoot;
		}
		// 活动目录
		setAttr("folders_selected", folderId);

		TbFolder folder = new FolderService().getFolder(folderId);
		setAttr("folder", folder);

		setAttr("paginator", getPaginator());

		// seo：title优化
		String folderName = (folder == null ? "" : folder.getStr("name") + " - ");
		setAttr(JFSpecialUtils.TITLE_ATTR, folderName + getAttr(JFSpecialUtils.TITLE_ATTR));

		// 栏目跳转规则
		String jumpUrl = folder.getJumpUrl();
		String path = folder.getPath();
		String urlKey = folder.getKey();
		if (StrUtils.isNotEmpty(jumpUrl)) {
			redirectAuto(jumpUrl);
		} else if (StrUtils.isNotEmpty(path)) {
			renderAuto(path);
		} else {
			renderAuto(Home.PATH + urlKey + ".html");
		}

		//增加logo--------------定版后改放到缓存处
		String sql="select * from tb_webinfo where id = 1";
		SysAboutus webinfo=SysAboutus.dao.findFirst(sql);
		setAttr("webinfo",webinfo);
	}

	/**
	 * 登录
	 *
	 */
	@Before(FrontInterceptor.class)
	public void login() {
		// 获取验证码
		String imageCode = getSessionAttr(ImageCode.class.getName());
		String checkCode = this.getPara("imageCode");

		if (StrUtils.isEmpty(imageCode) || !imageCode.equalsIgnoreCase(checkCode)) {
			setAttr("msg", "验证码错误！");
			renderAuto(loginPage);
			return;
		}

		// 初始化数据字典Map
		String username = getPara("username");
		String password = getPara("password");

		// 新加入，判断是否有上一个页面
		String prePage = getPara("pre_page");
		String toPage = StrUtils.isEmpty(prePage) || prePage.indexOf("login") >= 0 //
				|| prePage.indexOf("trans") >= 0 ? firstPage : prePage;
		setAttr("pre_page", prePage); // 如果密码错误还需要用到

		if (StrUtils.isEmpty(username)) {
			setAttr("msg", "用户名不能为空");
			renderAuto(loginPage);
			return;
		} else if (StrUtils.isEmpty(password)) {
			setAttr("msg", "密码不能为空");
			renderAuto(loginPage);
			return;
		}
		String encryptPassword = JFSpecialUtils.passwordEncrypt(password); // 加密
		SysUser user = SysUser.dao.findFirstByWhere(" where username = ? and password = ? " //
				+ " and usertype != " + JFSpecialUtils.USER_TYPE_THIRD // 第三方的只能通过oauth登录
				, username, encryptPassword);
		
		if (user == null || user.getInt("userid") <= 0) {
			setAttr("msg", "认证失败，请您重新输入。");
			renderAuto(loginPage);
			return;
		} else {
			setSessionUser(user);
		}

		// 添加日志
		user.put("update_id", user.getUserid());
		user.put("update_time", getNow());
		saveLog(user, SysLog.SYSTEM_LOGIN);

		redirect(toPage);
	}

	/**
	 * 登出
	 */
	@Before(FrontInterceptor.class)
	public void logout() {
		SysUser user = (SysUser) getSessionUser();
		if (user != null) {
			// 添加日志
			user.put("update_id", user.getUserid());
			user.put("update_time", getNow());
			saveLog(user, SysLog.SYSTEM_LOGOUT);
			// 删除session
			removeSessionUser();
		}

		setAttr("msg", "您已退出");
		renderAuto(loginPage);
	}

	public void update_cache() {
		DictCache.init();
		UserCache.init();
		renderHtml("1");
	}

	public void trans() {
		String redirectPath = getPara();
		if (StrUtils.isEmpty(redirectPath)) {
			redirectPath = Config.getStr("PAGES.TRANS");
		} else if (redirectPath.equals("auth")) {
			redirectPath = "/pages/error/trans_no_auth.html";
		}
		render(redirectPath);
	}
}
