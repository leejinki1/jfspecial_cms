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
 * 特卖区发布信息/历史
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/sale_history")
public class SaleHistoryController extends BaseController {

	private static final String path = "/pages/admin/sale/sale_";

	@Before(FrontInterceptor.class)
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
		if(usertype>0&&usertype<10){
			//管理员
			sql = "select t.id,t.name,t.publish_user, t.update_time,t.content,t.image_url,t.image_net_url,t.album_name,t.status  " +
					"from tb_sale t " +
					"where approve_status = 10 order by t.sort,t.id desc";
		}else{
			//非管理员,只能看到自己的权限//理论上进不来
			sql = "select t.id,t.name,t.publish_user, t.update_time,t.content,t.image_url,t.image_net_url,t.album_name,t.status  " +
					"from tb_sale t " +
					"where approve_status = 10 and create_id=" +user.getUserid()+
					" order by t.sort,t.id desc";
		}

		//历史===已发布(approve.status=pass)===不在草稿(is_draft=0)
		List<TbSale> lists = TbSale.dao.find(sql);
		setAttr("lists", lists);
		render(path+"history.html");//先反回主页,待补充
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

	/**
	 * 修改历史文章
	 *
	 * 2018年11月27日 下午9:53:04 ljk
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
		// 只有管理员可以修改//自己都不能修改
		if (!(user.getUserid()==1||user.getUserid()==9)) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//传参,下拉列表
		String sql = "select * from tb_addoil_album t where  status = 1 order by sort,id desc";
		List<TbSaleAlbum> albums = TbSaleAlbum.dao.find(sql);
		setAttr("albums", albums);

		render(path+"edit.html");//转到编辑页
	}

	/**
	 * 在网站显示
	 *
	 * 2018年11月27日 下午9:53:04 ljk
	 */
	@Before(FrontInterceptor.class)
	public void  showArticle() {
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
		// 只有管理员可以修改//自己都不能修改
		if (!(user.getUserid()==1||user.getUserid()==9)) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//修改属性
		model.setStatus(1);
		model.setUpdateId(getSessionUser().getUserid());//修改人
		model.setUpdateTime(getNow());//修改时间
		model.update();

		redirect("/admin/sale_history");

	}

	/**
	 * 在网站显示
	 *
	 * 2018年11月27日 下午9:53:04 ljk
	 */
	@Before(FrontInterceptor.class)
	public void  hideArticle() {
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
		// 只有管理员可以修改//自己都不能修改
		if (!(user.getUserid()==1||user.getUserid()==9)) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//修改属性
		model.setStatus(0);
		model.setUpdateId(getSessionUser().getUserid());//修改人
		model.setUpdateTime(getNow());//修改时间
		model.update();

		redirect("/admin/sale_history");

	}
}


