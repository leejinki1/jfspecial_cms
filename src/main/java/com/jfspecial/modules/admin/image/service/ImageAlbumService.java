package com.jfspecial.modules.admin.image.service;

import com.jfspecial.modules.admin.image.model.TbImageAlbum;

import java.util.List;

public class ImageAlbumService {

	/**
	 * 查询相册
	 * 
	 * @param albumId
	 * @return
	 */
	public TbImageAlbum getAlbum(Integer albumId) {
		return TbImageAlbum.dao.findById(albumId);
	}
	
	/**
	 * 相册复选框
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbImageAlbum> list = TbImageAlbum.dao
				.find(" select id,name from tb_image_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbImageAlbum album : list) {
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
