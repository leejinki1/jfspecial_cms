package com.jfspecial.modules.front.controller;

import com.jfinal.aop.Before;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.expert.model.TbExpertDocking;
import com.jfspecial.modules.admin.makersingup.TbMakerSignup;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.StrUtils;

import java.util.List;

/**
 * 文章管理
 * 
 * @author ljk 2018-11-14
 */
@ControllerBind(controllerKey = "/front/special/expert_docking")
public class SpecialExpertDockingController extends BaseProjectController {

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
		//SysUser special = SysUser.dao.findFirst(
			//	"select userid,username from sys_user where userid = ? ",userId);
		List<SysUser> specials = SysUser.dao.find(
				"select userid,realname from sys_user where usertype=10");

		if (specials != null) {
			setAttr("specials", specials);
		}
		setAttr("userId", userId);
		renderAuto(path + "expert_docking.html");

	}

	public void save() {
		//Integer makerid = getParaToInt();
		TbExpertDocking model = getModel(TbExpertDocking.class);
		//model.remove("id");
		Integer special_id = getParaToInt("model.special_id");
		String your_name = getPara("model.your_name");
		String your_contact = getPara("model.your_contact");
		String title = getPara("model.name");
		String content = getPara("model.content");
		model.setStatus(1); // 显示
		model.setSpecialId(special_id);
		model.setYourName(your_name);
		model.setYourContact(your_contact);
		model.setName(content);
		model.setContent(title);
		model.setCreateId(getSessionUser().getUserid());
		model.setCreateTime(getNow());
		model.setUpdateId(getSessionUser().getUserid());
		model.setUpdateTime(getNow());
		model.save();

		redirect( "/answer_list.html");
	//	renderAuto(path + "answer_list.html");
	}
}
