package com.jfspecial.modules.admin.sale;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_sale")
public class TbSale extends BaseProjectModel<TbSale> {

	private static final long serialVersionUID = 1L;
	public static final TbSale dao = new TbSale();

	// columns START
	private String ID = "id"; // id
	private String TITLE = "title"; // 文章名称
	private String NAME = "name"; // 文章名称
	private String CONTENT = "content"; // 文件内容
	private String IMAGE_URL = "image_url"; // 图片路径
	private String IMAGE_NET_URL = "image_net_url"; // 网络图片路径
	private String PRICE = "price";
	private String NUM = "num";
	private String UNIT = "unit";
	private String PROPERTY = "property";
	private String IS_AUDIT = "is_audit";
	private String IS_DRAFT = "is_draft";
	private String PUBLISH_TIME = "publish_time"; // 发布时间
	private String PUBLISH_USER = "publish_user"; // 发布者
	private String TYPE = "type"; // 类型
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者
	private String ALBUM_ID = "album_id"; // 相册ID
	private String ALBUM_NAME = "album_name"; // 相册名称
	private String STATUS = "status"; // 状态//radio/2,隐藏,1,显示
	private String IS_COMMENT = "is_comment"; // 是否评论//radio/2,否,1,是
	private String IS_RECOMMEND = "is_recommend"; // 是否推荐：2 否 1 是
	private String UPDATE_ID = "update_id"; // 创建者

	public TbSale setUpdateId(Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbSale setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public TbSale setStatus(Integer value) {
		set(STATUS, value);
		return this;
	}

	public Integer getStatus() {
		return get(STATUS);
	}

	public TbSale setIsComment(Integer value) {
		set(IS_COMMENT, value);
		return this;
	}

	public Integer getIsComment() {
		return get(IS_COMMENT);
	}

	public TbSale setIsRecommend(Integer value) {
		set(IS_RECOMMEND, value);
		return this;
	}

	public Integer getIsRecommend() {
		return get(IS_RECOMMEND);
	}

	public TbSale setAlbumId(Integer value) {
		set(ALBUM_ID, value);
		return this;
	}

	public Integer getAlbumId() {
		return get(ALBUM_ID);
	}

	public TbSale setAlbumName(String value) {
		set(ALBUM_NAME, value);
		return this;
	}

	public String getAlbumName() {
		return get(ALBUM_NAME);
	}

	public TbSale setName(String value) {
		set(NAME, value);
		return this;
	}

	public String getName() {
		return get(NAME);
	}

	public TbSale setProperty(String value) {
		set(PROPERTY, value);
		return this;
	}

	public String getProperty() {
		return get(PROPERTY);
	}

	public TbSale setTitle(String value) {
		set(TITLE, value);
		return this;
	}

	public String getTitle() {
		return get(TITLE);
	}
	public TbSale setPrice(Double value) {
		set(PRICE, value);
		return this;
	}

	public String getPrice() {
		return get(PRICE);
	}
	public TbSale setNum(Integer value) {
		set(NUM, value);
		return this;
	}

	public String getNum() {
		return get(NUM);
	}
	public TbSale setUnit(String value) {
		set(UNIT, value);
		return this;
	}

	public String getUnit() {
		return get(UNIT);
	}
	public TbSale setContent(String value) {
		set(CONTENT, value);
		return this;
	}

	public String getContent() {
		return get(CONTENT);
	}

	public TbSale setType(Integer value) {
		set(TYPE, value);
		return this;
	}
	public Integer getType() {
		return get(TYPE);
	}

	public TbSale setIsAudit(Integer value) {
		set(IS_AUDIT, value);
		return this;
	}

	public Integer getIsAudit() {
		return get(IS_AUDIT);
	}

	public TbSale setIsDraft(Integer value) {
		set(IS_DRAFT, value);
		return this;
	}
	public Integer getIsDraft() {
		return get(IS_DRAFT);
	}

	public TbSale setImageUrl(String value) {
		set(IMAGE_URL, value);
		return this;
	}

	public String getImageUrl() {
		return get(IMAGE_URL);
	}

	public TbSale setImageNetUrl(String value) {
		set(IMAGE_NET_URL, value);
		return this;
	}

	public String getImageNetUrl() {
		return get(IMAGE_NET_URL);
	}

	public TbSale setPublishTime(String value) {
		set(PUBLISH_TIME, value);
		return this;
	}

	public String getPublishTime() {
		return get(PUBLISH_TIME);
	}

	public TbSale setPublishUser(String value) {
		set(PUBLISH_USER, value);
		return this;
	}

	public String getPublishUser() {
		return get(PUBLISH_USER);
	}

	public TbSale setUpdateTime(String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbSale setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbSale setCreateId(Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public Integer getCreateId() {
		return get(CREATE_ID);
	}
}