package com.jfspecial.modules.admin.newscenter.controller;

import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenter;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenterAlbum;
import com.jfspecial.modules.admin.newscenter.service.NewsCenterAlbumService;
import com.jfspecial.util.StrUtils;

import java.util.List;

/**
 * 图片
 *
 */
@ControllerBind(controllerKey = "/admin/newscentershow")
public class NewsCenterShowController extends BaseController {

	private static final String path = "/pages/admin/newscentershow/newscentershow_";

	public void list() {
		TbNewsCenter model = getModelByAttr(TbNewsCenter.class);

		SQLUtils sql = new SQLUtils(" from tb_newscenter_album t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereEquals("album_id", model.getAlbumId());
			sql.whereLike("name", model.getStr("name"));
			sql.whereEquals("status", model.getInt("status"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by sort,id desc");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		String sqlSelect = "select t.*,(select ifnull(im.image_net_url,im.image_url) " //
				+ " from tb_newscenter im where im.album_id = t.id order by sort,id desc limit 1 ) as imageUrl ";
		List<TbNewsCenterAlbum> list = TbNewsCenterAlbum.dao.find(sqlSelect + sql.toString());

		setAttr("list", list);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void edit() {
		int albumId = getParaToInt();
		TbNewsCenter model = getModelByAttr(TbNewsCenter.class);
		model.setAlbumId(albumId);

		SQLUtils sql = new SQLUtils(" from tb_newscenter t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("name", model.getStr("name"));
			sql.whereEquals("status", model.getInt("status"));
		}
		sql.whereEquals("album_id", model.getAlbumId());

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by sort,id desc");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		List<TbNewsCenter> list = TbNewsCenter.dao.find("select t.* " + sql.toString());

		// 下拉框
		setAttr("album", new NewsCenterAlbumService().getAlbum(model.getAlbumId()));
		
		setAttr("list", list);
		setAttr("attr", model);
		render(path + "edit.html");
	}

}
