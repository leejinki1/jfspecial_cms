package com.jfspecial.modules.admin.sale;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.admin.article.ArticleService;
import com.jfspecial.modules.admin.article.TbArticle;
import com.jfspecial.modules.admin.comment.CommentService;
import com.jfspecial.modules.admin.folder.FolderService;
import com.jfspecial.modules.admin.folder.TbFolder;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.modules.admin.tags.TbTags;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.util.StrUtils;

import java.util.List;

/**
 * 文章管理管理
 *
 * @author ljk 2018-11-11
 */
@ControllerBind(controllerKey = "/admin/sale1")
public class Sale1Controller extends BaseProjectController {

	private static final String path = "/pages/admin/sale/sale_";

	public void index() {
		list();
	}

	public void list() {
		TbSale model = getModelByAttr(TbSale.class);

		SQLUtils sql = new SQLUtils(" from tb_sale t " //
				+ " where 1 = 1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("title", model.getStr("title"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.create_time desc ");
		} else {
			sql.append(" order by t.").append(orderBy);
		}

		Page<TbSale> page = TbSale.dao.paginate(getPaginator(), "select t.* ", //
				sql.toString().toString());

		// 查询下拉框

		setAttr("page", page);
		setAttr("attr", model);

		render(path + "list.html");
	}

	public void publish() {
		// 获取页面信息,设置目录传入
		TbSale attr = getModel(TbSale.class);
		setAttr("model", attr);

		// 查询下拉框

		render(path + "add.html");
	}

	public void add() {
		// 获取页面信息,设置目录传入
		TbSale attr = getModel(TbSale.class);
		setAttr("model", attr);

		// 查询下拉框
		render(path + "add.html");
	}

	public void view() {
		TbSale model = TbSale.dao.findById(getParaToInt());

		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		Integer id = getParaToInt();
		// 删除文章
		TbSale model = new TbSale();
		Integer userid = getSessionUser().getUserid();
		String now = getNow();
		//model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(id);

		list();
	}

	public void edit() {
		TbSale model = TbSale.dao.findById(getParaToInt());
		setAttr("model", model);

		// 查询下拉框

		render(path + "edit.html");
	}

	public void save() {
		TbSite site = getBackSite();
		UploadFile uploadImage = getFile("model.image_url", FileUploadUtils.getUploadTmpPath(site), FileUploadUtils.UPLOAD_MAX);

		UploadFile uploadFile = getFile("model.file_url", FileUploadUtils.getUploadTmpPath(site), FileUploadUtils.UPLOAD_MAX);

		Integer pid = getParaToInt();
		TbSale model = getModel(TbSale.class);

		// 图片附件
		if (uploadImage != null) {
			String fileUrl = uploadHandler(site, uploadImage.getFile(), "article_image");
			model.set("image_url", fileUrl);
		}

		// 文件附件
		if (uploadFile != null) {
			String oldFileName = uploadFile.getFileName();
			String fileUrl = uploadHandler(site, uploadFile.getFile(), "article_file");
			model.set("file_url", fileUrl);
			model.set("file_name", oldFileName); // 原文件名
		} else {
			// 删除标记
			Integer file_flag = getParaToInt("file_url_flag");
			if (file_flag != null && file_flag == 1) {
				model.set("file_url", "");
				model.set("file_name", "");
			}
		}

		Integer userid = getSessionUser().getUserid();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			if (JFSpecialUtils.ARTICLE_APPROVE) {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_UPDATE);
			} else {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS);
			}
			model.update();
		} else { // 新增
			model.remove("id");
			if (JFSpecialUtils.ARTICLE_APPROVE) {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_UPDATE);
			} else {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS);
			}
			model.put("create_id", userid);
			model.put("create_time", now);
			model.save();
		}

		renderMessage("保存成功");
	}

	public void edit_content() {
		TbSale model = TbSale.dao.findById(getParaToInt());
		setAttr("model", model);

		String tags = new SaleService().getTags(model);
		setAttr("tags", tags);

		super.render(path + "edit_content.html");
	}

	public void edit_content_ue() {
		TbSale model = TbSale.dao.findById(getParaToInt());
		setAttr("model", model);

		// 设置标签
		String tags = new SaleService().getTags(model);
		setAttr("tags", tags);

		super.render(path + "edit_content_ue.html");
	}

	public void edit_content_textarea() {
		TbSale model = TbSale.dao.findById(getParaToInt());
		setAttr("model", model);

		// 设置标签
		String tags = new SaleService().getTags(model);
		setAttr("tags", tags);

		super.render(path + "edit_content_textarea.html");
	}

	public void view_content() {
		// 根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));
		// 数据列表
		TbSale article = TbSale.dao.findById(getParaToInt());
		if (article != null) {
			setAttr("item", article);
		}

		renderAuto(path + "view_content.html");

	}

	public void save_content() {
		TbSale model = getModel(TbSale.class);
		if (JFSpecialUtils.ARTICLE_APPROVE) {
			model.set("approve_status", SaleConstant.APPROVE_STATUS_UPDATE);
		} else {
			model.set("approve_status", SaleConstant.APPROVE_STATUS_PASS);
		}
		model.update();

		// 保存tags
		Db.update(" delete from tb_tags where article_id = ?", model.getInt("id"));
		String tags = getPara("tags");
		if (StrUtils.isNotEmpty(tags)) {
			String[] tagsArr = tags.split(",");
			for (int i = 0; i < tagsArr.length; i++) {
				String tagname = tagsArr[i];
				// 最多5个
				if (i >= 5) {
					break;
				}
				if (StrUtils.isEmpty(tagname)) {
					continue;
				}
				TbTags tbTags = new TbTags();
				tbTags.put("tagname", tagname);
				tbTags.put("article_id", model.getInt("id"));
				tbTags.put("create_id", getSessionUser().getUserid());
				tbTags.put("create_time", getNow());
				tbTags.save();
			}
		}

		renderMessage("保存成功");
	}

	public void list_approve() {
		TbSale model = getModelByAttr(TbSale.class);

		SQLUtils sql = new SQLUtils(" from tb_article t " //
				+ " left join tb_folder f on f.id = t.folder_id " //
				+ " where approve_status in ( " //
				+ ArticleConstant.APPROVE_STATUS_INIT + "," + ArticleConstant.APPROVE_STATUS_UPDATE + " ) ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("title", model.getStr("title"));
			sql.whereEquals("folder_id", model.getInt("folder_id"));
			sql.whereEquals("status", model.getInt("status"));

		}

		// 站点设置
		int siteId = getSessionUser().getBackSiteId();
		sql.append(" and site_id = " + siteId);

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.folder_id,t.sort,t.create_time desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<TbArticle> page = TbArticle.dao.paginate(getPaginator(), "select t.*,f.name as folderName ", //
				sql.toString().toString());

		// 查询下拉框
		setAttr("selectFolder", selectFolder(model.getInt("folder_id")));

		setAttr("page", page);
		setAttr("attr", model);

		List<TbFolder> folders = new FolderService().getFolders(getSessionUser().getBackSiteId());
		setAttr("folders", folders);

		render(path + "list_approve.html");
	}

	public void save_approve() {
		TbSale model = TbSale.dao.findById(getParaToInt());
		int approveStatus = getParaToInt("approve_status");
		Integer userid = getSessionUser().getUserid();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.set("approve_status", approveStatus);
		model.update();

		// renderMessage("审核成功","javascript:window.top.location.href = 'admin/article/list_approve';");
		redirect("/admin/sale/list_approve");
	}

	public void tocopy() {
		TbSale model = TbSale.dao.findById(getParaToInt());

		// 查询下拉框
		setAttr("selectFolder", selectFolder(0));

		setAttr("model", model);
		render(path + "copy.html");
	}

	public void copy() {
		int id = getParaToInt();
		Integer userid = getSessionUser().getUserid();
		String folders = getPara("folders");
		// 复制
		new SaleService().copy(id, userid, folders);

		renderMessage("复制完成");
	}

}
