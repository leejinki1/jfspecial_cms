/**
 * author 2018-2025 ljk
 */
package com.jfspecial.jfinal.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfspecial.util.StrUtils;

public class SessionUser<M extends Model<M>> extends BaseModel<M> {

	private static final long serialVersionUID = 1L;

	public Integer getUserid() {
		return getInt("userid") == null ? -1 : getInt("userid");
	}
	
	public String getUserName() {
		if (StrUtils.isNotEmpty(getStr("realname"))) {
			return getStr("realname");	
		}
		return getStr("username");
	}

	public String getRoleName() {
		if (StrUtils.isNotEmpty(getStr("rolename"))) {
			return getStr("rolename");
		}
		return getStr("rolename");
	}
	
	public Integer getBackSiteId() {
		return getInt("back_site_id");
	}

}
