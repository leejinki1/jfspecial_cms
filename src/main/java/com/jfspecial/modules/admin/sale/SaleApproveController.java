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

	private static final String path = "/pages/admin/sale/sale_";

	//显示待审核
	public void index() {
		//判断user是否登录,
		SysUser user = (SysUser) getSessionUser();
		//Integer id = getParaToInt();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		//判断权限
		String sql;
		Integer usertype = user.getInt("usertype");
		if(usertype>0&&usertype<=9){
			//1-9是管理员
			sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.image_net_url,t.album_name " +
					"from tb_sale t " +
					"where  status = 1 and approve_status = 1  and is_draft=0 order by sort,id desc";


			//待审核:审核状态=1初始+++
			List<TbSale> lists = TbSale.dao.find(sql);
			setAttr("lists", lists);
			render(path+"approve.html");
			return;
		}

		//其他的是没有权限的,提示一下
		//理论上不会进入,在页面控制一下

		setAttr("msg", "没有该权限,请联系系统管理员");
		redirect("/admin");
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

	/**
	 *  approve article
	 *  跳转到审核页面
	 *	2018.12.6  zr
	 */
	@Before(FrontInterceptor.class)
	public void  approveArticle() {
		SysUser user = (SysUser) getSessionUser();
		Integer pid = getParaToInt();

		if (user == null || pid == null) {
			redirect(CommonController.firstPage);
			return;
		}

		TbSale model = TbSale.dao.findById(pid);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//保存数据
		setAttr("model", model);
		//返回审核页面
		render(path+"approve_detail.html");

	}

}


