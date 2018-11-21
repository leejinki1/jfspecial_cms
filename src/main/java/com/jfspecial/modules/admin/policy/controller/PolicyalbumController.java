package com.jfspecial.modules.admin.policy.controller;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.admin.policy.model.TbPolicy;
import com.jfspecial.modules.admin.policy.model.TbPolicyAlbum;
import com.jfspecial.util.StrUtils;

import java.util.List;

/**
 * 目录
 *
 */
@ControllerBind(controllerKey = "/admin/policyalbum")
public class PolicyalbumController extends BaseProjectController {

	private static final String path = "/pages/admin/policyalbum/policyalbum_";

	public void list() {
		TbPolicyAlbum model = getModelByAttr(TbPolicyAlbum.class);

		SQLUtils sql = new SQLUtils(" from tb_policy_album t "
				+ " left join tb_policy_album f  on f.id = t.parent_id  where 1=1 ");
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
				
		Page<TbPolicyAlbum> page = TbPolicyAlbum.dao.paginate(getPaginator(), "select t.*,f.name as parentName ", //
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
		TbPolicyAlbum model = TbPolicyAlbum.dao.findById(getParaToInt());
		
		TbPolicyAlbum album = TbPolicyAlbum.dao.findById(model.getParentId());
		model.put("parentName", album != null ? album.getName() : null);
		
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		// 日志添加
		TbPolicyAlbum model = new TbPolicyAlbum();
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
		TbPolicy imag = TbPolicy.dao.findFirstByWhere(" where album_id = ? ", id);
		if (imag != null) {
			renderMessage("相册下存在图片，不能删除");
			return;
		}
		
		// 日志添加
		TbPolicyAlbum model = new TbPolicyAlbum();
		Integer userid = getSessionUser().getUserid();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(id);
				
		renderMessage("删除成功");
	}

	public void edit() {
		TbPolicyAlbum model = TbPolicyAlbum.dao.findById(getParaToInt());
		setAttr("model", model);
		
		// 下拉框
		setAttr("selectParentFolder", selectParentFolder(model.getParentId(), model.getId()));
				
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbPolicyAlbum model = getModel(TbPolicyAlbum.class);
		
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
		List<TbPolicyAlbum> list = TbPolicyAlbum.dao.find(" select id,name from tb_policy_album " //
				+ " where id != ? order by sort,create_time desc ", id);
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbPolicyAlbum album : list) {
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
