package com.jfspecial.modules.admin.newscenter.model;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_newscenter_album")
public class TbNewsCenterAlbum extends BaseProjectModel<TbNewsCenterAlbum> {

	private static final long serialVersionUID = 1L;
	public static final TbNewsCenterAlbum dao = new TbNewsCenterAlbum();

	// columns START
	private String ID = "id"; // 目录id
	private String PARENT_ID = "parent_id"; // 父ID
	private String NAME = "name"; // 相册名称
	private String REMARK = "remark"; // 描述
	private String SORT = "sort"; // 排序
	private String STATUS = "status"; // 状态//radio/2,隐藏,1,显示
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 更新人
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbNewsCenterAlbum setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public TbNewsCenterAlbum setParentId(Integer value) {
		set(PARENT_ID, value);
		return this;
	}

	public Integer getParentId() {
		return get(PARENT_ID);
	}

	public TbNewsCenterAlbum setName(String value) {
		set(NAME, value);
		return this;
	}

	public String getName() {
		return get(NAME);
	}

	public TbNewsCenterAlbum setRemark(String value) {
		set(REMARK, value);
		return this;
	}

	public String getRemark() {
		return get(REMARK);
	}

	public TbNewsCenterAlbum setSort(Integer value) {
		set(SORT, value);
		return this;
	}

	public Integer getSort() {
		return get(SORT);
	}

	public TbNewsCenterAlbum setStatus(Integer value) {
		set(STATUS, value);
		return this;
	}

	public Integer getStatus() {
		return get(STATUS);
	}

	public TbNewsCenterAlbum setUpdateTime(String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbNewsCenterAlbum setUpdateId(Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbNewsCenterAlbum setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbNewsCenterAlbum setCreateId(Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public Integer getCreateId() {
		return get(CREATE_ID);
	}

	@Override
	public String toString() {
		String log = "";
		log += "[id:" + getId() + "]";
		log += "[parentId:" + getParentId() + "]";
		log += "[name:" + getName() + "]";
		log += "[remark:" + getRemark() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}