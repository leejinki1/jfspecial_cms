package com.jfspecial.modules.admin.aboutweb;

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
    private String BANNER1="banner1";//banner1主页轮播图1链接
    private String BANNER2="banner2";//banner2主页轮播图2链接
    private String BANNER3="banner3";//banner3主页轮播图3链接
    private String BANNERTITLE1="bannertitle1";//banner1主页轮播图1主题
    private String BANNERTITLE2="bannertitle2";//banner2主页轮播图2主题
    private String BANNERTITLE3="bannertitle3";//banner3主页轮播图3主题
    private String BANNERTITLECONTENT1="bannertitlecontent1";//banner1主页轮播图1主题内容
    private String BANNERTITLECONTENT2="bannertitlecontent2";//banner2主页轮播图2主题内容
    private String BANNERTITLECONTENT3="bannertitlecontent3";//banner3主页轮播图3主题内容

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
                ", BANNER1='" + BANNER1 + '\'' +
                ", BANNER2='" + BANNER2 + '\'' +
                ", BANNER3='" + BANNER3 + '\'' +
                ", BANNERTITLE1='" + BANNERTITLE1 + '\'' +
                ", BANNERTITLE2='" + BANNERTITLE2 + '\'' +
                ", BANNERTITLE3='" + BANNERTITLE3 + '\'' +
                ", BANNERTITLECONTENT1='" + BANNERTITLECONTENT1 + '\'' +
                ", BANNERTITLECONTENT2='" + BANNERTITLECONTENT2 + '\'' +
                ", BANNERTITLECONTENT3='" + BANNERTITLECONTENT3 + '\'' +
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

    /*banner图*/
    public SysAboutus setBanner1(String value){
        set(BANNER1,value);
        return this;
    }
    public String getBanner1(){
        return get(BANNER1);
    }
    public SysAboutus setBanner2(String value){
        set(BANNER2,value);
        return this;
    }
    public String getBanner2(){
        return get(BANNER2);
    }
    public SysAboutus setBanner3(String value){
        set(BANNER3,value);
        return this;
    }
    public String getBanner3(){
        return get(BANNER3);
    }

    /*标题*/
    public SysAboutus setBannerTitle1(String value){
        set(BANNERTITLE1,value);
        return this;
    }
    public String getBannerTitle1(){
        return get(BANNERTITLE1);
    }
    public SysAboutus setBannerTitle2(String value){
        set(BANNERTITLE2,value);
        return this;
    }
    public String getBannerTitle2(){
        return get(BANNERTITLE2);
    }
    public SysAboutus setBannerTitle3(String value){
        set(BANNERTITLE3,value);
        return this;
    }
    public String getBannerTitle3(){
        return get(BANNERTITLE3);
    }

    /*主题内容*/

    public SysAboutus setBannerTitleContent1(String value){
        set(BANNERTITLECONTENT1,value);
        return this;
    }
    public String getBannerTitleContent1(){
        return get(BANNERTITLECONTENT1);
    }
    public SysAboutus setBannerTitleContent2(String value){
        set(BANNERTITLECONTENT2,value);
        return this;
    }
    public String getBannerTitleContent2(){
        return get(BANNERTITLECONTENT2);
    }
    public SysAboutus setBannerTitleContent3(String value){
        set(BANNERTITLECONTENT3,value);
        return this;
    }
    public String getBannerTitleContent3(){
        return get(BANNERTITLECONTENT3);
    }


}
