package com.jfspecial.system.config;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_config")
public class SysConfig extends BaseProjectModel<SysConfig> {

	private static final long serialVersionUID = 1L;
	public static final SysConfig dao = new SysConfig();

	// columns START
	private String ID = "id"; // 主键
	private String NAME = "name"; // 名称
	private String KEY = "key"; // 键
	private String VALUE = "value"; // 值
	private String CODE = "code"; // 编码
	private String TYPE = "type"; // 类型
	private String SORT = "sort"; // 排序号
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 更新人
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public SysConfig setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public SysConfig setName(String value) {
		set(NAME, value);
		return this;
	}

	public String getName() {
		return get(NAME);
	}

	public SysConfig setKey(String value) {
		set(KEY, value);
		return this;
	}

	public String getKey() {
		return get(KEY);
	}

	public SysConfig setValue(String value) {
		set(VALUE, value);
		return this;
	}

	public String getValue() {
		return get(VALUE);
	}

	public SysConfig setCode(String value) {
		set(CODE, value);
		return this;
	}

	public String getCode() {
		return get(CODE);
	}

	public SysConfig setType(Integer value) {
		set(TYPE, value);
		return this;
	}

	public Integer getType() {
		return get(TYPE);
	}

	public SysConfig setSort(Integer value) {
		set(SORT, value);
		return this;
	}

	public Integer getSort() {
		return get(SORT);
	}

	public SysConfig setUpdateTime(String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public SysConfig setUpdateId(Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public SysConfig setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public SysConfig setCreateId(Integer value) {
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
		log += "[name:" + getName() + "]";
		log += "[key:" + getKey() + "]";
		log += "[value:" + getValue() + "]";
		log += "[code:" + getCode() + "]";
		log += "[type:" + getType() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}