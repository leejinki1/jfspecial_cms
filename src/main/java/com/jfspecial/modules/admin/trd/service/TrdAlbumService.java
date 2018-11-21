package com.jfspecial.modules.admin.trd.service;

import com.jfspecial.modules.admin.trd.model.TbTrdAlbum;

import java.util.List;

public class TrdAlbumService {

	/**
	 * 查询政策分类
	 * 
	 * @param albumId
	 * @return
	 */
	public TbTrdAlbum getAlbum(Integer albumId) {
		return TbTrdAlbum.dao.findById(albumId);
	}
	
	/**
	 * 政策分类复选框
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbTrdAlbum> list = TbTrdAlbum.dao
				.find(" select id,name from tb_trd_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbTrdAlbum album : list) {
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
