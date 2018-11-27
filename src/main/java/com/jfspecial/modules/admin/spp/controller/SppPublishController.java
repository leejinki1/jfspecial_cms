package com.jfspecial.modules.admin.spp.controller;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.ImageModel;
import com.jfspecial.component.util.ImageUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenter;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenterTags;
import com.jfspecial.modules.admin.newscenter.service.NewsCenterAlbumService;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.util.StrUtils;

import java.io.File;

/**
 * 科普/发布
 * zr  2018.11.27
 */
@ControllerBind(controllerKey = "/admin/spp_publish")
public class SppPublishController extends BaseProjectController {

	private static final String path = "/pages/admin/spp/spp_publish";

	public void index(){
		list();
	}

	//
	public void list() {
		TbNewsCenter model = getModelByAttr(TbNewsCenter.class);

		SQLUtils sql = new SQLUtils(" from tb_maker t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereEquals("album_id", model.getAlbumId());
			sql.whereLike("name", model.getStr("name"));
			sql.whereEquals("status", model.getInt("status"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by sort,id desc");
		} else {
			sql.append(" order by ").append(orderBy);
		}
		
		Page<TbNewsCenter> page = TbNewsCenter.dao.paginate(getPaginator(), "select t.* ", //
				sql.toString().toString());

		// 下拉框
		setAttr("selectAlbum", new NewsCenterAlbumService().selectAlbum(model.getAlbumId()));
				
		setAttr("page", page);
		setAttr("attr", model);
		render(path + ".html");
	}

	public void add() {
		// 获取页面信息,设置目录传入
		TbNewsCenter attr = getModel(TbNewsCenter.class);
		setAttr("model", attr);

		// 查询下拉框
		setAttr("selectAlbum", new NewsCenterAlbumService().selectAlbum(attr.getAlbumId()));
		
		render(path + "add.html");
	}

	public void view() {
		TbNewsCenter model = TbNewsCenter.dao.findById(getParaToInt());
		setAttr("model", model);
		
		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_newscenter_tags where image_id = ? order by id", model.getInt("id")).getStr("tags");
		setAttr("tags", tags);
		
		render(path + "view.html");
	}

	public void delete() {
		// 日志添加
		TbNewsCenter model = new TbNewsCenter();
		Integer userid = getSessionUser().getUserid();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(getParaToInt());
				
		list();
	}
	
	/**
	 * Iframe删除
	 */
	public void del() {
		int id = getParaToInt();
		// 日志添加
		TbNewsCenter model = new TbNewsCenter();
		Integer userid = getSessionUser().getUserid();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(id);
				
		renderMessage("删除成功");
	}

	public void edit() {
		TbNewsCenter model = TbNewsCenter.dao.findById(getParaToInt());
		setAttr("model", model);
		
		// 查询下拉框
		setAttr("selectAlbum", new NewsCenterAlbumService().selectAlbum(model.getAlbumId()));
				
		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_newscenter_tags where image_id = ? order by id", model.getInt("id")).getStr("tags");
		setAttr("tags", tags);
				
		render(path + "edit.html");
	}

	public void save() {
		TbSite site = getBackSite();
		UploadFile uploadImage = getFile("model.image_url", FileUploadUtils.getUploadTmpPath(site), FileUploadUtils.UPLOAD_MAX);
		
		Integer pid = getParaToInt();
		TbNewsCenter model = getModel(TbNewsCenter.class);
		
		// 图片附件
		if (uploadImage != null) {
			String fileUrl = uploadHandler(site, uploadImage.getFile(), "image");
			model.set("image_url", fileUrl);
		}
		
		// 设置图片信息
		if (StrUtils.isNotEmpty(model.getImageNetUrl())) {
			ImageModel imageModel = ImageUtils.getIamge(model.getImageNetUrl());
			model.setExt(imageModel.getExt());
			model.setWidth(imageModel.getWidth() + "");
			model.setHeight(imageModel.getHeight() + "");
			
			model.setLinkurl(model.getImageNetUrl());
		} else if (StrUtils.isNotEmpty(model.getImageUrl())) {
			ImageModel imageModel = ImageUtils
					.getIamge(PathKit.getWebRootPath() + File.separator + model.getImageUrl());
			model.setExt(imageModel.getExt());
			model.setWidth(imageModel.getWidth() + "");
			model.setHeight(imageModel.getHeight() + "");
			
			String linkUrl = getAttr("BASE_PATH") + model.getImageUrl();
			model.setLinkurl(linkUrl.replace("\\", "/"));
		}
		
		Integer userid = getSessionUser().getUserid();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			model.update();
			
		} else { // 新增
			model.remove("id");
			model.put("create_id", userid);
			model.put("create_time", now);
			model.save();
		}
		
		
		// 保存tags
		if (pid != null && pid > 0) { 
			Db.update(" delete from tb_newscenter_tags where image_id = ?", model.getInt("id"));
		}
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
				TbNewsCenterTags tag = new TbNewsCenterTags();
				tag.put("tagname", tagname);
				tag.put("image_id", model.getInt("id"));
				tag.put("create_id", getSessionUser().getUserid());
				tag.put("create_time", getNow());
				tag.save();

			}
		}
		
		renderMessage("保存成功");
	}


}
