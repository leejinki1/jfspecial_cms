package com.jfspecial.modules.front.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.ImageCode;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.system.user.UserCache;
import com.jfspecial.util.StrUtils;

@ControllerBind(controllerKey = "/front/regist")
public class RegistController extends BaseProjectController {

	public static final String path = "/regist/";

	/**
	 * 注册
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		// 目录列表
		setAttr("folders_selected", "regist");

		String prePage = getPara("pre_page");
		if (StrUtils.isEmpty(prePage)) {
			prePage = getPrePage();
		}
		setAttr("pre_page", prePage);

		SysUser user = (SysUser) getSessionUser();
		// 如果已经登录了~您就别注册啦
		if (user != null) {
			redirect(prePage);
		} else {
			renderAuto(path + "register.html");
		}
	}

	/**
	 * 注册信息保存
	 */
	public void save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getModel(SysUser.class);
		String password = getPara("password");
		String password2 = getPara("password2");
		String key = user.getStr("username");
		String rolename = user.getStr("rolename");

		// 获取验证码
		String imageCode = getSessionAttr(ImageCode.class.getName());
		String checkCode = this.getPara("imageCode");

		if (StrUtils.isEmpty(imageCode) || !imageCode.equalsIgnoreCase(checkCode)) {
			json.put("msg", "验证码错误！");
			renderJson(json.toJSONString());
			return;
		}

		if (StrUtils.isEmpty(key) ) {
			json.put("msg", "手机号格式错误！");
			renderJson(json.toJSONString());
			return;
		}

		// 前台都验证了~没必要都进行逐一提示，错误的都是跳过了js验证，不怀好意的人
		String realname = user.getStr("realname");
		if (user.getInt("userid") != null || StrUtils.isEmpty(realname) //
				|| realname.length() < 3 || realname.length() > 20 // 名称长度限制
				|| StrUtils.isEmpty(password) || StrUtils.isEmpty(password2) //
				|| password.length() < 6 || password.length() > 20 // 密码长度限制
				|| !password.equals(password2)) {
			json.put("msg", "提交数据错误！");
			renderJson(json.toJSONString());
			return;
		}

		SysUser newUser = SysUser.dao.findFirstByWhere("where username = ? ", key);
		if (newUser != null) {
			json.put("msg", "手机号已存在，请重新输入");
			renderJson(json.toJSONString());
			return;
		}

		user.set("username", key);
		user.set("rolename", rolename);
		user.set("password", JFSpecialUtils.passwordEncrypt(password));
		user.set("usertype", JFSpecialUtils.USER_TYPE_FRONT);
		user.set("departid", JFSpecialUtils.DEPART_REGIST_ID);
		user.set("state", 2); // 需要认证

		String sql="select max(userid) userid from sys_user";
		SysUser model=SysUser.dao.findFirst(sql);
		Integer id=model.getUserid()+1;//获取原来数据库中最大id,加1,即是新的id
		user.set("id",id);

		// 站点设置
		TbSite site = getSessionSite().getModel();
		user.set("back_site_id", 0);
		user.set("create_site_id", site.getId());
		
		user.set("create_time", getNow());
		user.set("create_id", 1);
		user.save();
		
		UserCache.init(); // 设置缓存
		setSessionUser(user); // 设置session
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}
}
