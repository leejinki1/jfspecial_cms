package com.jfspecial.modules.admin.addoil.service;

import com.jfspecial.modules.admin.addoil.model.TbAddOilAlbum;

import java.util.List;

public class AddOilAlbumService {

	/**
	 * 查询政策分类
	 * 
	 * @param albumId
	 * @return
	 */
	public TbAddOilAlbum getAlbum(Integer albumId) {
		return TbAddOilAlbum.dao.findById(albumId);
	}
	
	/**
	 * 政策分类复选框
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbAddOilAlbum> list = TbAddOilAlbum.dao
				.find(" select id,name from tb_addoil_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbAddOilAlbum album : list) {
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
