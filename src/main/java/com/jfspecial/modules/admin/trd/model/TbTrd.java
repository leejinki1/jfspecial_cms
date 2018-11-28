package com.jfspecial.modules.admin.trd.model;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;
import com.jfspecial.modules.admin.maker.model.TbMaker;

@ModelBind(table = "tb_trd")
public class TbTrd extends BaseProjectModel<TbTrd> {

	private static final long serialVersionUID = 1L;
	public static final TbTrd dao = new TbTrd();

	// columns START
	private String ID = "id"; // 主键
	private String ALBUM_ID = "album_id"; // 相册ID
	private String ALBUM_NAME = "album_name"; // 相册名称
	private String NAME = "name"; // 图片名称
	private String LINKURL = "linkurl"; // 链接地址
	private String CDNURL = "cdnurl"; // CDN地址
	private String IMAGE_URL = "image_url"; // 图片路径
	private String IMAGE_NET_URL = "image_net_url"; // 网络图片路径
	private String EXT = "ext"; // 扩展名
	private String WIDTH = "width"; // 宽
	private String HEIGHT = "height"; // 高
	private String STATUS = "status"; // 状态//radio/2,隐藏,1,显示
	private String IS_COMMENT = "is_comment"; // 是否评论//radio/2,否,1,是
	private String IS_RECOMMEND = "is_recommend"; // 是否推荐：2 否 1 是
	private String SORT = "sort"; // 排序
	private String REMARK = "remark"; // 备注
	private String PUBLISH_TIME = "publish_time"; // 发布时间
	private String PUBLISH_USER = "publish_user"; // 发布者
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 创建者
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者
	private String CONTENT="content";//内容;2018.11.27zr添加
	private String IS_DRAFTS = "is_drafts"; // 是否保存到草稿箱:0 否 1 是

	public TbTrd setIsDrafts(String value) {
		set(IS_DRAFTS, value);
		return this;
	}

	public String getIsDrafts() {
		return get(IS_DRAFTS);
	}

	public TbTrd setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public TbTrd setAlbumId(Integer value) {
		set(ALBUM_ID, value);
		return this;
	}

	public Integer getAlbumId() {
		return get(ALBUM_ID);
	}

	public TbTrd setAlbumName(String value) {
		set(ALBUM_NAME, value);
		return this;
	}

	public String getAlbumName() {
		return get(ALBUM_NAME);
	}

	public TbTrd setName(String value) {
		set(NAME, value);
		return this;
	}

	public String getName() {
		return get(NAME);
	}

	public TbTrd setLinkurl(String value) {
		set(LINKURL, value);
		return this;
	}

	public String getLinkurl() {
		return get(LINKURL);
	}

	public TbTrd setCdnurl(String value) {
		set(CDNURL, value);
		return this;
	}

	public String getCdnurl() {
		return get(CDNURL);
	}

	public TbTrd setImageUrl(String value) {
		set(IMAGE_URL, value);
		return this;
	}

	public String getImageUrl() {
		return get(IMAGE_URL);
	}

	public TbTrd setImageNetUrl(String value) {
		set(IMAGE_NET_URL, value);
		return this;
	}

	public String getImageNetUrl() {
		return get(IMAGE_NET_URL);
	}

	public TbTrd setExt(String value) {
		set(EXT, value);
		return this;
	}

	public String getExt() {
		return get(EXT);
	}

	public TbTrd setWidth(String value) {
		set(WIDTH, value);
		return this;
	}

	public String getWidth() {
		return get(WIDTH);
	}

	public TbTrd setHeight(String value) {
		set(HEIGHT, value);
		return this;
	}

	public String getHeight() {
		return get(HEIGHT);
	}

	public TbTrd setStatus(Integer value) {
		set(STATUS, value);
		return this;
	}

	public Integer getStatus() {
		return get(STATUS);
	}

	public TbTrd setIsComment(Integer value) {
		set(IS_COMMENT, value);
		return this;
	}

	public Integer getIsComment() {
		return get(IS_COMMENT);
	}

	public TbTrd setIsRecommend(Integer value) {
		set(IS_RECOMMEND, value);
		return this;
	}

	public Integer getIsRecommend() {
		return get(IS_RECOMMEND);
	}

	public TbTrd setSort(Integer value) {
		set(SORT, value);
		return this;
	}

	public Integer getSort() {
		return get(SORT);
	}

	public TbTrd setRemark(String value) {
		set(REMARK, value);
		return this;
	}

	public String getRemark() {
		return get(REMARK);
	}

	public TbTrd setPublishTime(String value) {
		set(PUBLISH_TIME, value);
		return this;
	}

	public String getPublishTime() {
		return get(PUBLISH_TIME);
	}

	public TbTrd setPublishUser(String value) {
		set(PUBLISH_USER, value);
		return this;
	}

	public String getPublishUser() {
		return get(PUBLISH_USER);
	}

	public TbTrd setUpdateTime(String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbTrd setUpdateId(Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbTrd setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbTrd setCreateId(Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public Integer getCreateId() {
		return get(CREATE_ID);
	}

	/*2018.11.27 添加	*/
	public TbTrd setContent(String value) {
		set(CONTENT, value);
		return this;
	}

	public String getContent() {
		return get(CONTENT);
	}

	@Override
	public String toString() {
		return "TbTrd{" +
				"ID='" + ID + '\'' +
				", ALBUM_ID='" + ALBUM_ID + '\'' +
				", ALBUM_NAME='" + ALBUM_NAME + '\'' +
				", NAME='" + NAME + '\'' +
				", LINKURL='" + LINKURL + '\'' +
				", CDNURL='" + CDNURL + '\'' +
				", IMAGE_URL='" + IMAGE_URL + '\'' +
				", IMAGE_NET_URL='" + IMAGE_NET_URL + '\'' +
				", EXT='" + EXT + '\'' +
				", WIDTH='" + WIDTH + '\'' +
				", HEIGHT='" + HEIGHT + '\'' +
				", STATUS='" + STATUS + '\'' +
				", IS_COMMENT='" + IS_COMMENT + '\'' +
				", IS_RECOMMEND='" + IS_RECOMMEND + '\'' +
				", SORT='" + SORT + '\'' +
				", REMARK='" + REMARK + '\'' +
				", PUBLISH_TIME='" + PUBLISH_TIME + '\'' +
				", PUBLISH_USER='" + PUBLISH_USER + '\'' +
				", UPDATE_TIME='" + UPDATE_TIME + '\'' +
				", UPDATE_ID='" + UPDATE_ID + '\'' +
				", CREATE_TIME='" + CREATE_TIME + '\'' +
				", CREATE_ID='" + CREATE_ID + '\'' +
				", CONTENT='" + CONTENT + '\'' +
				'}';
	}
}