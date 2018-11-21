package com.jfspecial.modules.admin.sale.service;

import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;

import java.util.List;

public class SaleAlbumService {

	/**
	 * 查询category
	 * 
	 * 2018年11月10日 上午1:13:09 ljk
	 * @param albumId
	 * @return
	 */
	public TbSaleAlbum getAlbum(Integer albumId) {
		return TbSaleAlbum.dao.findById(albumId);
	}
	
	/**
	 * 查询category复选框
	 *
	 * 2018年11月10日 上午1:13:09 ljk
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbSaleAlbum> list = TbSaleAlbum.dao
				.find(" select id,name from tb_sale_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbSaleAlbum album : list) {
				sb.append("<option value=\"");
				sb.append(album.getId());
				sb.append("\" ");
				sb.append(album.getId() == selected ? "selected" : "");
				sb.append(">");
				sb.append(album.getName());
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
}
