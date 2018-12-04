package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.makersingup.TbMakerSignup;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.modules.front.service.FrontCacheService;
import com.jfspecial.util.DateUtils;
import com.jfspecial.util.StrUtils;

/**
 * 文章管理
 * 
 * @author ljk 2018-11-14
 */
@ControllerBind(controllerKey = "/front/maker/signup")
public class SignupMakerController extends BaseProjectController {

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
		int articleId = getParaToInt();

		setAttr("articleId", articleId);

		renderAuto(path + "maker_signup.html");
	}

	public void save() {
		Integer makerid = getParaToInt();
		TbMakerSignup model = getModel(TbMakerSignup.class);
		//model.remove("id");
		String realname = getPara("model.realname");
		String contact = getPara("model.contact");
		String sex = getPara("model.sex");
		String email = getPara("model.email");
		String remark = getPara("model.remark");
		model.setStatus(1); // 显示
		model.setMakerId(makerid.toString());
		model.setContact(contact);
		model.setEmail(email);
		model.setRemark(remark);
		model.setRealName(realname);
		//model.setCreateId(getSessionUser().getUserid());
		model.setCreateTime(getNow());
		model.save();

		redirect( "/maker.html");

	}

}
