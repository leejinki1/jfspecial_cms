package com.jfspecial.modules.admin.maker.controller;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.maker.model.TbMaker;
import com.jfspecial.modules.admin.maker.model.TbMakerAlbum;
import com.jfspecial.util.StrUtils;

import java.util.List;

/**
 * 目录
 *
 */
@ControllerBind(controllerKey = "/admin/makeralbum")
public class MakeralbumController extends BaseProjectController {

	private static final String path = "/pages/admin/makeralbum/makeralbum_";

	public void list() {
		TbMakerAlbum model = getModelByAttr(TbMakerAlbum.class);

		SQLUtils sql = new SQLUtils(" from tb_maker_album t "
				+ " left join tb_maker_album f  on f.id = t.parent_id  where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("name", model.getStr("name"));
			sql.whereEquals("status", model.getInt("status"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.sort,t.id desc");
		} else {
			sql.append(" order by t.").append(orderBy);
		}
				
		Page<TbMakerAlbum> page = TbMakerAlbum.dao.paginate(getPaginator(), "select t.*,f.name as parentName ", //
				sql.toString().toString());

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		setAttr("selectParentFolder", selectParentFolder(0, 0));
		
		render(path + "add.html");
	}

	public void view() {
		TbMakerAlbum model = TbMakerAlbum.dao.findById(getParaToInt());
		
		TbMakerAlbum album = TbMakerAlbum.dao.findById(model.getParentId());
		model.put("parentName", album != null ? album.getName() : null);
		
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		// 日志添加
		TbMakerAlbum model = new TbMakerAlbum();
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
		TbMaker imag = TbMaker.dao.findFirstByWhere(" where album_id = ? ", id);
		if (imag != null) {
			renderMessage("相册下存在图片，不能删除");
			return;
		}
		
		// 日志添加
		TbMakerAlbum model = new TbMakerAlbum();
		Integer userid = getSessionUser().getUserid();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(id);
				
		renderMessage("删除成功");
	}

	public void edit() {
		TbMakerAlbum model = TbMakerAlbum.dao.findById(getParaToInt());
		setAttr("model", model);
		
		// 下拉框
		setAttr("selectParentFolder", selectParentFolder(model.getParentId(), model.getId()));
				
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbMakerAlbum model = getModel(TbMakerAlbum.class);
		
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
		renderMessage("保存成功");
	}
	
	/**
	 * 目录复选框
	 * 
	 * @return
	 */
	private String selectParentFolder(Integer selected, Integer id) {
		List<TbMakerAlbum> list = TbMakerAlbum.dao.find(" select id,name from tb_maker_album " //
				+ " where id != ? order by sort,create_time desc ", id);
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbMakerAlbum album : list) {
				sb.append("<option value=\"");
				sb.append(album.getInt("id"));
				sb.append("\" ");
				sb.append(album.getInt("id") == selected ? "selected" : "");
				sb.append(">");
				sb.append(album.getStr("name"));
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
}
