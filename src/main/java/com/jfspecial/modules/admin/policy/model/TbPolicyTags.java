package com.jfspecial.modules.admin.policy.model;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_policy_tags")
public class TbPolicyTags extends BaseProjectModel<TbPolicyTags> {

	private static final long serialVersionUID = 1L;
	public static final TbPolicyTags dao = new TbPolicyTags();

	// columns START
	private String ID = "id"; // id
	private String IMAGE_ID = "image_id"; // 图片ID
	private String TAGNAME = "tagname"; // 标签内容
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbPolicyTags setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public TbPolicyTags setImageId(Integer value) {
		set(IMAGE_ID, value);
		return this;
	}

	public Integer getImageId() {
		return get(IMAGE_ID);
	}

	public TbPolicyTags setTagname(String value) {
		set(TAGNAME, value);
		return this;
	}

	public String getTagname() {
		return get(TAGNAME);
	}

	public TbPolicyTags setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbPolicyTags setCreateId(Integer value) {
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
		log += "[imageId:" + getImageId() + "]";
		log += "[tagname:" + getTagname() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}