package com.jfspecial.modules.admin.sale.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.sale.TbSale;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.StrUtils;

import java.util.List;

/**
 * 目录
 *
 */
@ControllerBind(controllerKey = "/admin/salealbum")
public class SalealbumController extends BaseProjectController {

	private static final String path = "/pages/admin/salealbum/salealbum_";

	/**
	 * 2018.11.30 ZR 新增分类
	 */
	public void addAlbum() {
		//System.out.println("-------------------------------进入addAlum");
		//ajax返回参数设置
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		//验证登陆--------------------------------判断用户权限
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能提交文章！");
			renderJson(json.toJSONString());
			return;
		}

		//获取参数
		Integer pid = getParaToInt();
		TbSaleAlbum model = new TbSaleAlbum();
		String sql1="select max(id) id from tb_sale_album t";
		model=TbSaleAlbum.dao.findFirst(sql1);
		Integer id=model.getId()+1;//获取原来数据库中最大id,加1,即是新的id
		String name=getPara("albumname");

		model.setName(name);
		//System.out.println("--------------------------name-------------------------------------------------"+model);

		//设置修改人和修改时间
		Integer userid = getSessionUser().getUserid();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);


		//保存数据库//只有新增,没有修改
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			//model.remove("id");
			model.setId(id);//为了和页面中追加的id统一
			model.put("create_id", userid);
			model.put("create_time", now);
			model.save();
		}

		/*ajax返回成功信号*/
		json.put("status", 1);// 成功
		//modal增加的代码
		json.put("modal","<tr id=\"modal"+id+"\">"+
				"<td>"+name+"</td>\n" +
				"<td><a onclick=\"oper_delAlbum("+id+");return false\">删除</a></td>\n" +
				"</tr>");
		//option增加的代码
		json.put("option","<option id=\"option"+id+"\" " +
				"value=\""+id+"\" ${"+id+"==model.album_id!0?'selected':''} >"+name+"</option>");
		renderJson(json.toJSONString());
	}


	/**
	 * 2018.11.30 ZR 删除分类
	 */
	public void delAlbum() {
		//ajax返回参数设置
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		//验证登陆--------------------------------判断用户权限
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能提交文章！");
			renderJson(json.toJSONString());
			return;
		}

		//获取参数
		Integer pid = getParaToInt();//从路径中获取参数
		//TbAddOilAlbum model = getModel(TbAddOilAlbum.class);//从前台获取参数
		TbSaleAlbum model = TbSaleAlbum.dao.findById(pid);//从数据库获取信息

		//判断分类下是否已有文章或内容
		String sql="select * from tb_sale t where album_id="+pid;
		List<TbSale> makers=TbSale.dao.find(sql);
		if(makers.isEmpty()){
			//保存数据库
			model.delete();
			/*ajax返回成功信号*/
			json.put("status", 1);// 成功
		}else{
			json.put("msg","该分类下有文章,请先删除文章再来删除分类!");
		}

		renderJson(json.toJSONString());
	}
}
