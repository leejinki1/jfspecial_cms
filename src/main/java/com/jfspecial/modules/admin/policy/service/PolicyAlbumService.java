package com.jfspecial.modules.admin.policy.service;

import com.jfspecial.modules.admin.policy.model.TbPolicyAlbum;

import java.util.List;

public class PolicyAlbumService {

	/**
	 * 查询政策分类
	 * 
	 * @param albumId
	 * @return
	 */
	public TbPolicyAlbum getAlbum(Integer albumId) {
		return TbPolicyAlbum.dao.findById(albumId);
	}
	
	/**
	 * 政策分类复选框
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbPolicyAlbum> list = TbPolicyAlbum.dao
				.find(" select id,name from tb_policy_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbPolicyAlbum album : list) {
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
