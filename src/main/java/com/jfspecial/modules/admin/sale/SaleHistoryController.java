package com.jfspecial.modules.admin.sale;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 特卖区发布信息/历史
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/sale_history")
public class SaleHistoryController extends BaseController {

	private static final String path = "/pages/admin/sale/sale_history";

	@Before(FrontInterceptor.class)
	public void index() {
		String sql = "select t.id,t.name,t.publish_user, t.update_time from tb_sale t where  status = 1 and approve_status=10 order by sort,id desc";
		//历史===已发布(approve.status=pass)===不在草稿(is_draft=0)
		List<TbSale> lists = TbSale.dao.find(sql);
		setAttr("lists", lists);
		render(path+".html");//先反回主页,待补充
	}

	/**
	 * del  article
	 *	删除
	 *	未完成功能:1.判断权限,是否能删
	 * 2018年11月27日 下午9:53:04 ljk
	 */
	@Before(FrontInterceptor.class)
	public void  delArticle() {
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

		// 删除评论~
		//new CommentService().deleteComment(id);
		// 删除文章
		TbSale.dao.deleteById(id);
		redirect("/admin/sale_history");
	}
}


