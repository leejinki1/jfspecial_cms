package com.jfspecial.modules.admin.sale;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 特卖区发布信息
 * @author ljk 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/sale_approve")
public class SaleApproveController extends BaseController {

	private static final String path = "/pages/admin/sale/sale_approve";

	//显示待审核
	public void index() {
		String sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.album_name,t.price,t.num,t.unit,t.property " +
				"from tb_sale t where  status = 1 and approve_status = 1  and is_draft=0 order by sort,id desc";
		//待审核:审核状态=1初始+++不在草稿箱
		List<TbSale> lists = TbSale.dao.find(sql);
		setAttr("lists", lists);
		render(path+".html");
	}

	/**
	 *  pass article
	 *  通过
	 * 2018年11月27日 下午9:53:04 ljk
	 */
	@Before(FrontInterceptor.class)
	public void  passArticle() {
		SysUser user = (SysUser) getSessionUser();
		Integer id = getParaToInt();
		if (user == null || id == null) {
			redirect(CommonController.firstPage);
			return;
		}

		TbSale model = TbSale.dao.findById(getParaToInt());
		setAttr("model", model);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//修改审核状态
		model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS); // 需要审核改为update;通过审核为pass

		//保存到数据库
		model.update();

		//返回审核页面
		redirect("/admin/sale_approve");
	}

	/**
	 *  reject article
	 *  不通过
	 * 2018年11月27日 下午9:53:04 ljk
	 */
	@Before(FrontInterceptor.class)
	public void  rejectArticle() {
		SysUser user = (SysUser) getSessionUser();
		Integer id = getParaToInt();
		if (user == null || id == null) {
			redirect(CommonController.firstPage);
			return;
		}

		TbSale model = TbSale.dao.findById(getParaToInt());
		setAttr("model", model);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//修改审核状态
		model.set("approve_status", ArticleConstant.APPROVE_STATUS_REJECT); // 需要审核改为update;通过审核为pass;不通过为reject

		//保存到数据库
		model.update();

		//返回审核页面
		redirect("/admin/sale_approve");
	}

}


