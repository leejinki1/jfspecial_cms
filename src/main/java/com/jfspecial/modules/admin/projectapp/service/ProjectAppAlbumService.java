package com.jfspecial.modules.admin.projectapp.service;

import com.jfspecial.modules.admin.projectapp.model.TbProjectAppAlbum;

import java.util.List;

public class ProjectAppAlbumService {

	/**
	 * 查询政策分类
	 * 
	 * @param albumId
	 * @return
	 */
	public TbProjectAppAlbum getAlbum(Integer albumId) {
		return TbProjectAppAlbum.dao.findById(albumId);
	}
	
	/**
	 * 政策分类复选框
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbProjectAppAlbum> list = TbProjectAppAlbum.dao
				.find(" select id,name from tb_policy_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbProjectAppAlbum album : list) {
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
