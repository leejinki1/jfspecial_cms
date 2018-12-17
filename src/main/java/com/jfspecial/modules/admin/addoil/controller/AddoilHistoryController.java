package com.jfspecial.modules.admin.addoil.controller;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.addoil.model.TbAddOil;
import com.jfspecial.modules.admin.addoil.model.TbAddOilAlbum;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 帮扶企业/历史帮扶
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/addoil_history")
public class AddoilHistoryController extends BaseController {

	private static final String path = "/pages/admin/addoil/addoil_";

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
					"from tb_addoil t " +
					"where approve_status = 10 order by t.sort,t.id desc";
		}else{
			//非管理员,只能看到自己的权限//理论上进不来
			sql = "select t.id,t.name,t.publish_user, t.update_time,t.content,t.image_url,t.image_net_url,t.album_name,t.status  " +
					"from tb_addoil t " +
					"where and approve_status = 10 and create_id=" +user.getUserid()+
					" order by t.sort,t.id desc";
		}

		//历史:发布状态未10;
		List<TbAddOil> lists = TbAddOil.dao.find(sql);
		setAttr("lists", lists);
		render(path+"history.html");//先反回主页,待补充
	}

	/**
	 * del assistance article
	 *
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

		TbAddOil model = TbAddOil.dao.findById(getParaToInt());
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
		TbAddOil.dao.deleteById(id);
		redirect("/admin/addoil_history");
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
		TbAddOil model = TbAddOil.dao.findById(getParaToInt());
		setAttr("model", model);
		// 只有管理员可以修改//自己都不能修改
		if (!(user.getUserid()==1||user.getUserid()==9)) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//传参,下拉列表
		String sql = "select * from tb_addoil_album t where  status = 1 order by sort,id desc";
		List<TbAddOilAlbum> albums = TbAddOilAlbum.dao.find(sql);
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
		TbAddOil model = TbAddOil.dao.findById(getParaToInt());
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

		redirect("/admin/addoil_history");

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
		TbAddOil model = TbAddOil.dao.findById(getParaToInt());
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

		redirect("/admin/addoil_history");

	}
}


