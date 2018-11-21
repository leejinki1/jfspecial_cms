package com.jfspecial.modules.admin.sale;

import com.jfinal.plugin.activerecord.Db;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.util.DateUtils;
import com.jfspecial.util.NumberUtils;

public class SaleService {

	/**
	 * 获取tag标签
	 *
	 * @param model
	 * @return
	 */
	public String getTags(TbSale model) {
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_tags where article_id = ? order by id", model.getInt("id")).getStr("tags");
		return tags;
	}

	/**
	 * 复制文章
	 *
	 * @param id
	 * @param userid
	 * @param folders
	 */
	public void copy(int id, Integer userid, String folders) {
		TbSale model = TbSale.dao.findById(id);
		for (String folderStr : folders.split(",")) {
			String now = DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
			model.remove("id");
		//	model.setFolderId(NumberUtils.parseInt(folderStr));
			if (JFSpecialUtils.ARTICLE_APPROVE) {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_UPDATE);
			} else {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS);
			}
			model.set("create_id", userid);
			model.set("create_time", now);
			if (model.get("sort") == null)
				model.put("sort", 10);
			model.save();
		}
	}
}
