package com.jfspecial.modules.admin.special;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_user")
public class TbSpecial extends BaseProjectModel<TbSpecial> {

	private static final long serialVersionUID = 1L;
	public static final TbSpecial dao = new TbSpecial();

	// columns START
	private String USERID = "userid"; // id
	private String USERNAME = "username"; // 名称
	private String USERTYPE = "usertype"; // 模板名称
	private String REALNAME = "realname"; //
	private String TEL = "tel"; // pc端域名
	private String EMAIL = "email"; // 移动端域名
	private String ADDRESS = "address"; // 其他域名
	private String STATUS = "status"; // 状态//radio/2,禁用,1,启用
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者
	private String QQ = "qq"; // 移动端域名
	private String AGE = "age"; // 其他域名
	private String BIRTHDAY = "birthday"; // 移动端域名
	private String SEX = "sex"; // 其他域名

	public TbSpecial setQq(String value) {
		set(QQ, value);
		return this;
	}

	public String getQq() {
		return get(QQ);
	}
	public TbSpecial setAge(String value) {
		set(AGE, value);
		return this;
	}

	public String getAge() {
		return get(AGE);
	}
	public TbSpecial setBirthday(String value) {
		set(BIRTHDAY, value);
		return this;
	}

	public String getBirthday() {
		return get(BIRTHDAY);
	}

	public TbSpecial setSex(String value) {
		set(SEX, value);
		return this;
	}

	public String getSex() {
		return get(SEX);
	}

	public TbSpecial setUserID(String value) {
		set(USERID, value);
		return this;
	}

	public String getUserID() {
		return get(USERID);
	}

	public TbSpecial setUserName(String value) {
		set(USERNAME, value);
		return this;
	}

	public String getUserName() {
		return get(USERNAME);
	}

	public TbSpecial setUserType(String value) {
		set(USERTYPE, value);
		return this;
	}

	public String getUSERTYPE() {
		return get(USERTYPE);
	}

	public TbSpecial setRealName(String value) {
		set(REALNAME, value);
		return this;
	}
	public String getRealName() {
		return get(REALNAME);
	}

	public TbSpecial setTel(String value) {
		set(TEL, value);
		return this;
	}

	public String getTel() {
		return get(TEL);
	}

	public TbSpecial setEmail(String value) {
		set(EMAIL, value);
		return this;
	}

	public String getEmail() {
		return get(EMAIL);
	}

	public TbSpecial setAddress(String value) {
		set(ADDRESS, value);
		return this;
	}

	public String getAddress() {
		return get(ADDRESS);
	}

	public TbSpecial setStatus(Integer value) {
		set(STATUS, value);
		return this;
	}

	public Integer getStatus() {
		return get(STATUS);
	}

	public TbSpecial setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbSpecial setCreateId(Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public Integer getCreateId() {
		return get(CREATE_ID);
	}

	@Override
	public String toString() {
		String log = "";
		log += "[userid:" + getUserID() + "]";
		log += "[name:" + getUserName() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}