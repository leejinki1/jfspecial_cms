package com.jfspecial.modules.admin.security;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_user")
public class SecurityUser extends BaseProjectModel<SecurityUser> {
    private static final long serialVersionUID = 1L;
    public static final SecurityUser dao = new SecurityUser();

    // columns START
    private String ID="id";//id
    private String USERID = "userid"; // id
    private String USERNAME = "username"; // 名称
    private String USERTYPE = "usertype"; // 模板名称
    private String REALNAME = "realname"; //

    public SecurityUser setId(Integer value) {
        set(ID, value);
        return this;
    }

    public Integer getId() {
        return get(ID);
    }

    public SecurityUser setUserId(Integer value) {
        set(USERID, value);
        return this;
    }

    public Integer getUserId() {
        return get(USERID);
    }

    public SecurityUser setUserName(String value) {
        set(USERNAME, value);
        return this;
    }

    public String getUserName() {
        return get(USERNAME);
    }

    public SecurityUser setUserType(Integer value) {
        set(USERTYPE, value);
        return this;
    }

    public Integer getUserType() {
        return get(USERTYPE);
    }

    public SecurityUser setRealName(String value) {
        set(REALNAME, value);
        return this;
    }

    public String getRealName() {
        return get(REALNAME);
    }


    @Override
    public String toString() {
        return "SecurityUser{" +
                "USERID='" + getUserId() + '\'' +
                ", USERNAME='" + getUserName() + '\'' +
                ", ID='" + getId() + '\'' +
                ", USERTYPE='" + getUserType() + '\'' +
                ", REALNAME='" + getRealName() + '\'' +
                '}';
    }
}
