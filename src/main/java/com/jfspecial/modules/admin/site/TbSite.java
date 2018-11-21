package com.jfspecial.modules.admin.site;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_site")
public class TbSite extends BaseProjectModel<TbSite> {

	private static final long serialVersionUID = 1L;
	public static final TbSite dao = new TbSite();

	// columns START
	private String ID = "id"; // id
	private String NAME = "name"; // 名称
	private String TEMPLATE = "template"; // 模板名称
	private String TEMPLATE_MOBILE = "template_mobile"; //
	private String DOMAIN_PC = "domain_pc"; // pc端域名
	private String DOMAIN_MOBILE = "domain_mobile"; // 移动端域名
	private String DOMAIN_OTHERS = "domain_others"; // 其他域名
	private String SITE_TITLE = "site_title"; // 标题
	private String SITE_FOLDER_ID = "site_folder_id"; // 默认标题ID
	private String SITE_ARTICLE_ID = "site_article_id"; // 默认文章ID
	private String DB_URL = "db_url"; // 数据库
	private String DB_USER = "db_user"; // 数据库用户
	private String DB_PWD = "db_pwd"; // 数据库密码
	private String DB_DRIVER = "db_driver"; // 数据库驱动
	private String SORT = "sort"; // 序号
	private String STATUS = "status"; // 状态//radio/2,禁用,1,启用
	private String SITE_DEFALUT = "site_defalut"; // 默认站点：1,是,2,否
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 更新人
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbSite setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public TbSite setName(String value) {
		set(NAME, value);
		return this;
	}

	public String getName() {
		return get(NAME);
	}

	public TbSite setTemplate(String value) {
		set(TEMPLATE, value);
		return this;
	}

	public String getTemplate() {
		return get(TEMPLATE);
	}

	public TbSite setTemplateMobile(String value) {
		set(TEMPLATE_MOBILE, value);
		return this;
	}

	public String getTemplateMobile() {
		return get(TEMPLATE_MOBILE);
	}

	public TbSite setDomainPc(String value) {
		set(DOMAIN_PC, value);
		return this;
	}

	public String getDomainPc() {
		return get(DOMAIN_PC);
	}

	public TbSite setDomainMobile(String value) {
		set(DOMAIN_MOBILE, value);
		return this;
	}

	public String getDomainMobile() {
		return get(DOMAIN_MOBILE);
	}

	public TbSite setDomainOthers(String value) {
		set(DOMAIN_OTHERS, value);
		return this;
	}

	public String getDomainOthers() {
		return get(DOMAIN_OTHERS);
	}

	public TbSite setSiteTitle(String value) {
		set(SITE_TITLE, value);
		return this;
	}

	public String getSiteTitle() {
		return get(SITE_TITLE);
	}

	public TbSite setSiteFolderId(Integer value) {
		set(SITE_FOLDER_ID, value);
		return this;
	}

	public Integer getSiteFolderId() {
		return get(SITE_FOLDER_ID);
	}

	public TbSite setSiteArticleId(Integer value) {
		set(SITE_ARTICLE_ID, value);
		return this;
	}

	public Integer getSiteArticleId() {
		return get(SITE_ARTICLE_ID);
	}

	public TbSite setDbUrl(String value) {
		set(DB_URL, value);
		return this;
	}

	public String getDbUrl() {
		return get(DB_URL);
	}

	public TbSite setDbUser(String value) {
		set(DB_USER, value);
		return this;
	}

	public String getDbUser() {
		return get(DB_USER);
	}

	public TbSite setDbPwd(String value) {
		set(DB_PWD, value);
		return this;
	}

	public String getDbPwd() {
		return get(DB_PWD);
	}

	public TbSite setDbDriver(String value) {
		set(DB_DRIVER, value);
		return this;
	}

	public String getDbDriver() {
		return get(DB_DRIVER);
	}

	public TbSite setSort(Integer value) {
		set(SORT, value);
		return this;
	}

	public Integer getSort() {
		return get(SORT);
	}

	public TbSite setStatus(Integer value) {
		set(STATUS, value);
		return this;
	}

	public Integer getStatus() {
		return get(STATUS);
	}

	public TbSite setSiteDefalut(Integer value) {
		set(SITE_DEFALUT, value);
		return this;
	}

	public Integer getSiteDefalut() {
		return get(SITE_DEFALUT);
	}

	public TbSite setUpdateTime(String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbSite setUpdateId(Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbSite setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbSite setCreateId(Integer value) {
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
		log += "[template:" + getTemplate() + "]";
		log += "[templateMobile:" + getTemplateMobile() + "]";
		log += "[domainPc:" + getDomainPc() + "]";
		log += "[domainMobile:" + getDomainMobile() + "]";
		log += "[domainOthers:" + getDomainOthers() + "]";
		log += "[siteTitle:" + getSiteTitle() + "]";
		log += "[siteFolderId:" + getSiteFolderId() + "]";
		log += "[siteArticleId:" + getSiteArticleId() + "]";
		log += "[dbUrl:" + getDbUrl() + "]";
		log += "[dbUser:" + getDbUser() + "]";
		log += "[dbPwd:" + getDbPwd() + "]";
		log += "[dbDriver:" + getDbDriver() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}