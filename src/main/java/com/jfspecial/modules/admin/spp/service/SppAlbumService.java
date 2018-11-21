package com.jfspecial.modules.admin.spp.service;

import com.jfspecial.modules.admin.spp.model.TbSppAlbum;

import java.util.List;

public class SppAlbumService {

	/**
	 * 查询政策分类
	 * 
	 * @param albumId
	 * @return
	 */
	public TbSppAlbum getAlbum(Integer albumId) {
		return TbSppAlbum.dao.findById(albumId);
	}
	
	/**
	 * 政策分类复选框
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbSppAlbum> list = TbSppAlbum.dao
				.find(" select id,name from tb_spp_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbSppAlbum album : list) {
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
