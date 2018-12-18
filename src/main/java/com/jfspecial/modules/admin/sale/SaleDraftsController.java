package com.jfspecial.modules.admin.sale;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 特卖区发布信息
 * @author ljk 2018.11.28
 */
@ControllerBind(controllerKey = "/admin/sale_drafts")
public class SaleDraftsController extends BaseController {

	private static final String path = "/pages/admin/sale/sale_";

	//显示保存的草稿
	public void index() {
		//判断user是否登录,
		SysUser user = (SysUser) getSessionUser();
		//Integer id = getParaToInt();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}
		//判断权限//只能显示自己创建的草稿
		String sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.image_net_url,t.album_name   " +
				"from tb_sale t where is_draft = 1 and create_id=" +user.getUserid()+
				" order by sort,id desc";



		List<TbSale> lists = TbSale.dao.find(sql);
		setAttr("lists", lists);
		render(path+"drafts.html");
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

	/**
	 *	edit article
	 *	修改
	 * 	2018.12.5 zr
	 */
	@Before(FrontInterceptor.class)
	public void  editArticle() {

		//判断登陆和是否将id传过来
		SysUser user = (SysUser) getSessionUser();
		Integer id = getParaToInt();
		//id不存在或用户名不存在,返回
		if (user == null || id == null) {
			redirect(CommonController.firstPage);
			return;
		}

		//判断权限,非本人不能删除
		TbSale model = TbSale.dao.findById(getParaToInt());
		setAttr("model", model);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//传参,下拉列表
		String sql = "select * from tb_sale_album t where  status = 1 order by sort,id desc";
		List<TbSaleAlbum> albums = TbSaleAlbum.dao.find(sql);
		setAttr("albums", albums);

		render(path+"edit.html");//转到编辑页
	}

}


