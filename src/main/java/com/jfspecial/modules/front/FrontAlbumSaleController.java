package com.jfspecial.modules.front;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.admin.sale.model.TbSale;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.modules.front.service.FrontSaleService;
import com.jfspecial.util.NumberUtils;

@ControllerBind(controllerKey = "/album/sale")
public class FrontAlbumSaleController extends BaseProjectController {

	public static final String path = "/album/";

	/**
	 * 图片专辑
	 * 
	 * 2018年11月18日 上午3:43:39 ljk
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		String albumIdStr = getPara();
		albumIdStr = albumIdStr.substring(5);
		int albumId = NumberUtils.parseInt(albumIdStr);
		// 活动目录
		setAttr("album_selected", albumId);

		TbSaleAlbum album = new FrontSaleService().getAlbum(albumId);
		setAttr("album", album);

		setAttr("paginator", getPaginator());

		// seo：title优化
		String albumName = (album == null ? "" : album.getName() + " - ");
		setAttr(JFSpecialUtils.TITLE_ATTR, albumName + getAttr(JFSpecialUtils.TITLE_ATTR));

		renderAuto(path + "common_album.html");
	}

	/**
	 * 图片
	 *
	 * 2018年11月18日 上午3:43:39 ljk
	 */
	@Before(FrontInterceptor.class)
	public void img() {
		int imageId = getParaToInt();
		// 活动目录
		setAttr("imageId", imageId);

		TbSale sale = new FrontSaleService().getSale(imageId);
		setAttr("sale", sale);

		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_sale_tags where image_id = ? order by id", sale.getId()).getStr("tags");
		setAttr("tags", tags);
				
		setAttr("paginator", getPaginator());

		// seo：title优化
		String saleName = (sale == null ? "" : sale.getName() + " - ");
		setAttr(JFSpecialUtils.TITLE_ATTR, saleName + getAttr(JFSpecialUtils.TITLE_ATTR));

		renderAuto(path + "common_image.html");
	}
}
