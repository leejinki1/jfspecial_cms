package com.jfspecial.modules.admin.sale;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 特卖区发布信息
 * @author ljk 2018.11.28
 */
@ControllerBind(controllerKey = "/admin/sale_drafts")
public class SaleDraftsController extends BaseController {

	private static final String path = "/pages/admin/sale/sale_drafts";

	//显示保存的草稿
	public void index() {
		String sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.album_name,t.price,t.num,t.unit,t.property " +
				"from tb_sale t where  status = 1 and is_draft = 1 order by sort,id desc";
		List<TbSale> lists = TbSale.dao.find(sql);
		setAttr("lists", lists);
		render(path+".html");
	}

	/**
	 * del  article
	 *	删除
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
		redirect("/admin/sale_drafts");
	}

}


