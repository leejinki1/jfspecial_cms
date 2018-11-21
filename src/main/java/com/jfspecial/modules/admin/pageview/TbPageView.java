package com.jfspecial.modules.admin.pageview;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_pageview")
public class TbPageView extends BaseProjectModel<TbPageView> {

	private static final long serialVersionUID = 1L;

	public static final TbPageView dao = new TbPageView();
	// columns START
	private String ID = "id"; // 主键
	private String IP = "ip"; // IP地址
	private String USERID = "userid"; // 用户ID
	private String CREATE_DAY = "create_day"; // 创建时间到天
	private String CREATE_TIME = "create_time"; // 创建时间

	public TbPageView setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public TbPageView setIp(String value) {
		set(IP, value);
		return this;
	}

	public String getIp() {
		return get(IP);
	}

	public TbPageView setUserid(Integer value) {
		set(USERID, value);
		return this;
	}

	public Integer getUserid() {
		return get(USERID);
	}

	public TbPageView setCreateDay(String value) {
		set(CREATE_DAY, value);
		return this;
	}

	public String getCreateDay() {
		return get(CREATE_DAY);
	}

	public TbPageView setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}
}