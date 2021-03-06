package com.jfspecial.modules.admin;

import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.ImageCode;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.system.log.SysLog;
import com.jfspecial.system.menu.SysMenu;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.Config;
import com.jfspecial.util.StrUtils;
import com.jfspecial.util.encrypt.Md5Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * adminController
 */
@ControllerBind(controllerKey = "/admin")
public class AdminController extends BaseProjectController {

	public static final String loginPage = "/pages/admin/login.html";
	public static final String homePage = "/admin/home";

	public void index() {
		if (getSessionUser() != null) {
			// 如果session存在，不再验证
			redirect(homePage);
		} else {

			render(loginPage);
		}

	}

	/**
	 * 登录
	 *
	 */
	public void login() {
		// 获取验证码
		String imageCode = getSessionAttr(ImageCode.class.getName());
		String checkCode = this.getPara("imageCode");

		if (StrUtils.isEmpty(imageCode) || !imageCode.equalsIgnoreCase(checkCode)) {
			setAttr("msg", "验证码错误！");
			render(loginPage);
			return;
		}

		// 初始化数据字典Map
		String username = getPara("username");
		String password = getPara("password");//用户输入的密码//可能是真实的密码.可能是保存的md5密码

		if (StrUtils.isEmpty(username)) {
			setAttr("msg", "用户名不能为空");
			render(loginPage);
			return;
		} else if (StrUtils.isEmpty(password)) {
			setAttr("msg", "密码不能为空");
			render(loginPage);
			return;
		}


		SysUser user = SysUser.dao.findFirstByWhere(" where username = ? " //
				+ " and usertype in ( " + JFSpecialUtils.USER_TYPE_ADMIN +" ,9,10,11,12,13,20,21)",
				username);
		if (user == null || user.getInt("userid") <= 0) {
			setAttr("msg", "账号错误，请您重新输入。");
			render(loginPage);
			return;
		}

		//将数据库中的密码解密,再md5加密
		String dbPassword = user.get("password");


		// 前台md5加密

		String md5Password = "";
		String decryptPassword="";
		String userPassword="";
		String md5Password1="";
		try {
			userPassword = user.get("password");
			decryptPassword = JFSpecialUtils.passwordDecrypt(userPassword);//解密后的后台密码
			md5Password = new Md5Utils().getMD5(decryptPassword).toLowerCase();//重新加密的后台密码
			md5Password1=new Md5Utils().getMD5(md5Password).toLowerCase();//二次加密的后台密码
			//0c909a141f1f2c0a1cb602b0b2d7d050

		} catch (Exception e) {
			log.error("认证异常", e);
			setAttr("msg", "认证异常，请您重新输入。");
			render(loginPage);
			return;
		}


		if (!(md5Password.equals(password)||md5Password1.equals(password))) {
			setAttr("msg", "密码错误，请您重新输入。");
			render(loginPage);
			return;
		}


		if (!(user.getInt("usertype") == 1 ||
				user.getInt("usertype") == 9|| user.getInt("usertype") == 10||
				user.getInt("usertype") == 11|| user.getInt("usertype") == 12||
				user.getInt("usertype") == 13|| user.getInt("usertype") == 20||
				user.getInt("usertype") == 21)) {
			setAttr("msg", "您没有登录权限，请您重新输入。");
			render(loginPage);
			return;
		}

		//
		setSessionUser(user);

		// 第一个页面跳转(判断是否有权限登录该页面，关键词nomenu不知道从什么地方走)
		String tmpMainPage = setFirstPage();
		/*System.out.println("zr---admincontroller"+tmpMainPage);
		if (tmpMainPage == null) {
			setAttr("msg", "没有权限，请联系管理员。");
			render(loginPage);
			return;
		}*/

		// 添加日志
		user.put("update_id", user.getUserid());
		user.put("update_time", getNow());
		saveLog(user, SysLog.SYSTEM_LOGIN);

		/*if(tmpMainPage!=null) {
			System.out.println("zr12.14------adminC" + tmpMainPage);
			redirect("../" + tmpMainPage);
			return;
		}*/

		redirectAuto("../../"+homePage);

	}

	/**
	 * 获取第一个跳转页面
	 * @param
	 * @return
	 */
	protected String setFirstPage() {
		Map<Integer, List<SysMenu>> map = getSessionAttr("menu");
		if (map == null || map.size() <= 0) {
			return null;
		}

		String tmpMainPage = "";
		List<String> menuList = new ArrayList<String>();

		List<SysMenu> list = map.get(0);
		for (SysMenu menu : list) {
			if (StrUtils.isNotEmpty(menu.getStr("url"))) {
				menuList.add("/" + menu.getStr("url"));
			}
			List<SysMenu> list2 = map.get(menu.getInt("id"));
			if (list2 == null || list2.size() < 0) {
				continue;
			}

			for (SysMenu menu2 : list2) {
				if (StrUtils.isNotEmpty(menu2.getStr("url"))) {
					menuList.add("/" + menu2.getStr("url"));
				}
			}

		}

		if (menuList.size() <= 0) {
			return null;
		}

		if (menuList.contains(homePage)) {
			tmpMainPage = homePage;
		} else {
			tmpMainPage = menuList.get(0);
		}

		if (!tmpMainPage.startsWith("/")) {
			tmpMainPage = "/" + tmpMainPage;
		}
		return tmpMainPage;
	}
	
	/**
	 * 登出
	 */
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
		render(loginPage);
	}

	public void trans() {
		String redirectPath = getPara();
		System.out.println("12.13-----"+redirectPath);
		if (StrUtils.isEmpty(redirectPath)) {
			redirectPath = Config.getStr("PAGES.TRANS");
		} else if (redirectPath.equals("auth")) {
			redirectPath = "/pages/error/trans_no_auth.html";
		}
		render(redirectPath);
	}
}
