package com.jfspecial.modules.admin.newscenter.service;

import com.jfspecial.modules.admin.newscenter.model.TbNewsCenterAlbum;

import java.util.List;

public class NewsCenterAlbumService {

	/**
	 * 查询政策分类
	 * 
	 * @param albumId
	 * @return
	 */
	public TbNewsCenterAlbum getAlbum(Integer albumId) {
		return TbNewsCenterAlbum.dao.findById(albumId);
	}
	
	/**
	 * 政策分类复选框
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbNewsCenterAlbum> list = TbNewsCenterAlbum.dao
				.find(" select id,name from tb_newscenter_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbNewsCenterAlbum album : list) {
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
