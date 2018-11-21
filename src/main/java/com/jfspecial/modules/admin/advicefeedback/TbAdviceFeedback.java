package com.jfspecial.modules.admin.advicefeedback;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_advice_feedback")
public class TbAdviceFeedback extends BaseProjectModel<TbAdviceFeedback> {

	private static final long serialVersionUID = 1L;
	public static final TbAdviceFeedback dao = new TbAdviceFeedback();

	// columns START
	private String ID = "id"; // 主键
	private String USERID = "userid"; // 用户ID
	private String USERNAME = "username"; // 用户名
	private String QQ = "qq"; // qq
	private String EMAIL = "email"; // email
	private String TELPHONE = "telphone"; // 手机号
	private String CONTENT = "content"; // 意见反馈内容
	private String REMARK = "remark"; // 备注
	private String IS_READ = "is_read"; // 是否已读
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbAdviceFeedback setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public TbAdviceFeedback setUserid(Integer value) {
		set(USERID, value);
		return this;
	}

	public Integer getUserid() {
		return get(USERID);
	}

	public TbAdviceFeedback setUsername(String value) {
		set(USERNAME, value);
		return this;
	}

	public String getUsername() {
		return get(USERNAME);
	}

	public TbAdviceFeedback setQq(String value) {
		set(QQ, value);
		return this;
	}

	public String getQq() {
		return get(QQ);
	}

	public TbAdviceFeedback setEmail(String value) {
		set(EMAIL, value);
		return this;
	}

	public String getEmail() {
		return get(EMAIL);
	}

	public TbAdviceFeedback setTelphone(String value) {
		set(TELPHONE, value);
		return this;
	}

	public String getTelphone() {
		return get(TELPHONE);
	}

	public TbAdviceFeedback setContent(String value) {
		set(CONTENT, value);
		return this;
	}

	public String getContent() {
		return get(CONTENT);
	}

	public TbAdviceFeedback setRemark(String value) {
		set(REMARK, value);
		return this;
	}

	public String getRemark() {
		return get(REMARK);
	}

	public TbAdviceFeedback setIsRead(Integer value) {
		set(IS_READ, value);
		return this;
	}

	public Integer getIsRead() {
		return get(IS_READ);
	}

	public TbAdviceFeedback setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbAdviceFeedback setCreateId(Integer value) {
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
		log += "[userid:" + getUserid() + "]";
		log += "[username:" + getUsername() + "]";
		log += "[qq:" + getQq() + "]";
		log += "[email:" + getEmail() + "]";
		log += "[telphone:" + getTelphone() + "]";
		log += "[content:" + getContent() + "]";
		log += "[remark:" + getRemark() + "]";
		log += "[isRead:" + getIsRead() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}