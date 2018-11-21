package com.jfspecial.modules.front.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.ImageCode;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.advicefeedback.TbAdviceFeedback;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.system.user.UserCache;
import com.jfspecial.util.StrUtils;

/**
 * 意见反馈
 *
 * 2018年11月18日 上午3:43:39 ljk
 */
@ControllerBind(controllerKey = "/front/advice")
public class AdviceController extends BaseProjectController {

	/**
	 * 个人信息保存
	 */
	public void save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		// 获取验证码
		String imageCode = getSessionAttr(ImageCode.class.getName());
		String checkCode = this.getPara("imageCode");

		if (StrUtils.isEmpty(imageCode) || !imageCode.equalsIgnoreCase(checkCode)) {
			json.put("msg", "验证码错误！");
			renderJson(json.toJSONString());
			return;
		}

		SysUser user = (SysUser) getSessionUser();
		if (user==null) {
			json.put("msg", "请先登录再填写意见反馈！");
			renderJson(json.toJSONString());
			return;
		}

		TbAdviceFeedback model = getModel(TbAdviceFeedback.class);

		int userid = user.getInt("userid");
		String now = getNow();
		model.setUsername(user.getUserName());
		model.setUserid(userid);
		model.set("create_id", userid);
		model.set("create_time", now);
		model.save();
		UserCache.init(); // 设置缓存
		SysUser newUser = SysUser.dao.findById(userid);
		setSessionUser(newUser); // 设置session
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

}
