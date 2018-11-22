package com.jfspecial.modules.admin.aboutus;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;


@ModelBind(table = "tb_webinfo")
public class SysAboutus extends BaseProjectModel<SysAboutus> {
    private static final long serialVersionUID = 1L;
    public static final SysAboutus dao = new SysAboutus();

    private String ID="id"; //网站的id,默认为1
    private String LOGO="logo";//网站的logo地址
    private String INTRODUCTION="introduction";//网站的介绍内容
    private String DEPARTMENT="department";//网站机构名称
    private String ADDRESS="address";//机构地址
    private String TEL="tel";//联系电话
    private String FAX="fax";//传真
    private String WECHAT="wechat";//微信公众号
    private String EMAIL="email";//电子邮件
    private String UPDATEID="updateid";//修改人的id
    private String UPDATEDATE ="updatedate";//修改日期

    public SysAboutus setId(String value) {
        set(ID, value);
        return this;
    }

    public String getId() {
        return get(ID);
    }

    public SysAboutus setLogo(String value){
        set(LOGO,value);
        return this;
    }

    public String getLogo(){
        return get(LOGO);
    }

    public SysAboutus setIntroduction(String value){
        set(INTRODUCTION,value);
        return this;
    }
    public String getIntroduction(){
        return get(INTRODUCTION);
    }
    public SysAboutus setDepartment(String value){
        set(DEPARTMENT,value);
        return this;
    }
    public String getDepartment(){
        return get(DEPARTMENT);
    }
    public SysAboutus setAddress(String value){
        set(ADDRESS,value);
        return this;
    }
    public String getAddress(){
        return get(ADDRESS);
    }
    public SysAboutus setTel(String value){
        set(TEL,value);
        return this;
    }
    public String getTel(){
        return get(TEL);
    }
    public SysAboutus setFax(String value){
        set(FAX,value);
        return this;
    }
    public String getFax(){
        return get(FAX);
    }
    public SysAboutus setWechat(String value){
        set(WECHAT,value);
        return this;
    }
    public String getWechat(){
        return get(WECHAT);
    }
    public SysAboutus setEmail(String value){
        set(EMAIL,value);
        return this;
    }
    public String getEmail(){
        return get(EMAIL);
    }

    @Override
    public String toString() {
        return "SysAboutus{" +
                "ID='" + ID + '\'' +
                ", LOGO='" + LOGO + '\'' +
                ", INTRODUCTION='" + INTRODUCTION + '\'' +
                ", DEPARTMENT='" + DEPARTMENT + '\'' +
                ", ADDRESS='" + ADDRESS + '\'' +
                ", TEL='" + TEL + '\'' +
                ", FAX='" + FAX + '\'' +
                ", WECHAT='" + WECHAT + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", UPDATEID='" + UPDATEID + '\'' +
                ", UPDATEDATE='" + UPDATEDATE + '\'' +
                '}';
    }

    public SysAboutus setUpdateid(String value){
        set(UPDATEID,value);
        return this;
    }
    public String getUpdateid(){
        return get(UPDATEID);
    }

    public SysAboutus setUpdatedate(String value){
        set(UPDATEDATE,value);
        return this;
    }
    public String getUpdatedate(){
        return get(UPDATEDATE);
    }

}
