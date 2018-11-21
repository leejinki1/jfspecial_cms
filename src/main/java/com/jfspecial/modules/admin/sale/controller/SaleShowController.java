package com.jfspecial.modules.admin.sale.controller;

import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.sale.model.TbSale;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.modules.admin.sale.service.SaleAlbumService;
import com.jfspecial.util.StrUtils;

import java.util.List;

/**
 * 图片
 * 
 * 2018年11月18日 上午11:23:24 ljk
 */
@ControllerBind(controllerKey = "/admin/saleshow")
public class SaleShowController extends BaseController {

	private static final String path = "/pages/admin/saleshow/saleshow_";

	public void list() {
		TbSale model = getModelByAttr(TbSale.class);

		SQLUtils sql = new SQLUtils(" from tb_sale_album t where 1=1 ");
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
				+ " from tb_sale im where im.album_id = t.id order by sort,id desc limit 1 ) as imageUrl ";
		List<TbSaleAlbum> list = TbSaleAlbum.dao.find(sqlSelect + sql.toString());

		setAttr("list", list);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void edit() {
		int albumId = getParaToInt();
		TbSale model = getModelByAttr(TbSale.class);
		model.setAlbumId(albumId);

		SQLUtils sql = new SQLUtils(" from tb_sale t where 1=1 ");
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

		List<TbSale> list = TbSale.dao.find("select t.* " + sql.toString());

		// 下拉框
		setAttr("album", new SaleAlbumService().getAlbum(model.getAlbumId()));
		
		setAttr("list", list);
		setAttr("attr", model);
		render(path + "edit.html");
	}

}
