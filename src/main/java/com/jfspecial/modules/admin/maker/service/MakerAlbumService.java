package com.jfspecial.modules.admin.maker.service;

import com.jfspecial.modules.admin.maker.model.TbMakerAlbum;

import java.util.List;

public class MakerAlbumService {

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbMakerAlbum getAlbum(Integer albumId) {
		return TbMakerAlbum.dao.findById(albumId);
	}
	
	/**
	 * 相册复选框
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbMakerAlbum> list = TbMakerAlbum.dao
				.find(" select id,name from tb_maker_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbMakerAlbum album : list) {
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
