package com.jfspecial.modules.admin.image.model;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_image")
public class TbImage extends BaseProjectModel<TbImage> {

	private static final long serialVersionUID = 1L;
	public static final TbImage dao = new TbImage();

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

	public TbImage setId(Integer value) {
		set(ID, value);
		return this;
	}

	public Integer getId() {
		return get(ID);
	}

	public TbImage setAlbumId(Integer value) {
		set(ALBUM_ID, value);
		return this;
	}

	public Integer getAlbumId() {
		return get(ALBUM_ID);
	}

	public TbImage setAlbumName(String value) {
		set(ALBUM_NAME, value);
		return this;
	}

	public String getAlbumName() {
		return get(ALBUM_NAME);
	}

	public TbImage setName(String value) {
		set(NAME, value);
		return this;
	}

	public String getName() {
		return get(NAME);
	}

	public TbImage setLinkurl(String value) {
		set(LINKURL, value);
		return this;
	}

	public String getLinkurl() {
		return get(LINKURL);
	}

	public TbImage setCdnurl(String value) {
		set(CDNURL, value);
		return this;
	}

	public String getCdnurl() {
		return get(CDNURL);
	}

	public TbImage setImageUrl(String value) {
		set(IMAGE_URL, value);
		return this;
	}

	public String getImageUrl() {
		return get(IMAGE_URL);
	}

	public TbImage setImageNetUrl(String value) {
		set(IMAGE_NET_URL, value);
		return this;
	}

	public String getImageNetUrl() {
		return get(IMAGE_NET_URL);
	}

	public TbImage setExt(String value) {
		set(EXT, value);
		return this;
	}

	public String getExt() {
		return get(EXT);
	}

	public TbImage setWidth(String value) {
		set(WIDTH, value);
		return this;
	}

	public String getWidth() {
		return get(WIDTH);
	}

	public TbImage setHeight(String value) {
		set(HEIGHT, value);
		return this;
	}

	public String getHeight() {
		return get(HEIGHT);
	}

	public TbImage setStatus(Integer value) {
		set(STATUS, value);
		return this;
	}

	public Integer getStatus() {
		return get(STATUS);
	}

	public TbImage setIsComment(Integer value) {
		set(IS_COMMENT, value);
		return this;
	}

	public Integer getIsComment() {
		return get(IS_COMMENT);
	}

	public TbImage setIsRecommend(Integer value) {
		set(IS_RECOMMEND, value);
		return this;
	}

	public Integer getIsRecommend() {
		return get(IS_RECOMMEND);
	}

	public TbImage setSort(Integer value) {
		set(SORT, value);
		return this;
	}

	public Integer getSort() {
		return get(SORT);
	}

	public TbImage setRemark(String value) {
		set(REMARK, value);
		return this;
	}

	public String getRemark() {
		return get(REMARK);
	}

	public TbImage setPublishTime(String value) {
		set(PUBLISH_TIME, value);
		return this;
	}

	public String getPublishTime() {
		return get(PUBLISH_TIME);
	}

	public TbImage setPublishUser(String value) {
		set(PUBLISH_USER, value);
		return this;
	}

	public String getPublishUser() {
		return get(PUBLISH_USER);
	}

	public TbImage setUpdateTime(String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbImage setUpdateId(Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbImage setCreateTime(String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbImage setCreateId(Integer value) {
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
		log += "[albumId:" + getAlbumId() + "]";
		log += "[albumName:" + getAlbumName() + "]";
		log += "[name:" + getName() + "]";
		log += "[linkurl:" + getLinkurl() + "]";
		log += "[cdnurl:" + getCdnurl() + "]";
		log += "[imageUrl:" + getImageUrl() + "]";
		log += "[imageNetUrl:" + getImageNetUrl() + "]";
		log += "[ext:" + getExt() + "]";
		log += "[width:" + getWidth() + "]";
		log += "[height:" + getHeight() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[isComment:" + getIsComment() + "]";
		log += "[isRecommend:" + getIsRecommend() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[remark:" + getRemark() + "]";
		log += "[publishTime:" + getPublishTime() + "]";
		log += "[publishUser:" + getPublishUser() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}