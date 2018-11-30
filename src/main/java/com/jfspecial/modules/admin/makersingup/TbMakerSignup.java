package com.jfspecial.modules.admin.makersingup;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_maker_signup")
public class TbMakerSignup extends BaseProjectModel<TbMakerSignup> {

	private static final long serialVersionUID = 1L;
	public static final TbMakerSignup dao = new TbMakerSignup();

	// columns START
	private String ID = "id"; // 主键
	private String MAKER_ID = "maker_id"; // 相册ID
	private String REALNAME = "realname"; // 相册名称
	private String CONTACT = "contact"; // 图片名称
	private String SEX = "sex"; // 链接地址
	private String EMAIL = "email"; // CDN地址
	private String STATUS = "status"; // 状态//radio/2,隐藏,1,显示
	private String REMARK = "remark"; // 备注
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbMakerSignup setMakerId(String value) {
		set(MAKER_ID, value);
		return this;
	}

	public String getMakerID() {
		return get(MAKER_ID);
	}

	public TbMakerSignup setRealName(String value) {
		set(REALNAME, value);
		return this;
	}

	public String getRealName() {
		return get(REALNAME);
	}
	public TbMakerSignup setContact(String value) {
		set(CONTACT, value);
		return this;
	}

	public String getContact() {
		return get(CONTACT);
	}


	public TbMakerSignup setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public TbMakerSignup setStatus(Integer value) {
		set(STATUS, value);
		return this;
	}

	public Integer getStatus() {
		return get(STATUS);
	}

	public TbMakerSignup setSex(Integer value) {
		set(SEX, value);
		return this;
	}

	public Integer getSex() {
		return get(SEX);
	}

	public TbMakerSignup setEmail(String value) {
		set(EMAIL, value);
		return this;
	}

	public String getEmail() {
		return get(EMAIL);
	}

	public TbMakerSignup setRemark(String value) {
		set(REMARK, value);
		return this;
	}

	public String getRemark() {
		return get(REMARK);
	}

	public TbMakerSignup setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbMakerSignup setCreateId(Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public Integer getCreateId() {
		return get(CREATE_ID);
	}

	@Override
	public String toString() {
		return "TbMaker{" +
				"ID='" + ID + '\'' +
				", realNAME='" + REALNAME + '\'' +
				", STATUS='" + STATUS + '\'' +
				", REMARK='" + REMARK + '\'' +
				", CREATE_TIME='" + CREATE_TIME + '\'' +
				", CREATE_ID='" + CREATE_ID + '\'' +
				'}';
	}
}