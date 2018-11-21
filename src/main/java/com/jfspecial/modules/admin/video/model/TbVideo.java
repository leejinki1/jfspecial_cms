package com.jfspecial.modules.admin.video.model;

import com.jfspecial.component.base.BaseProjectModel;
import com.jfspecial.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_video")
public class TbVideo extends BaseProjectModel<TbVideo> {

	private static final long serialVersionUID = 1L;
	public static final TbVideo dao = new TbVideo();

    //columns START
    private String ID = "id";  // 主键
    private String ALBUM_ID = "album_id";  // 专辑ID
    private String ALBUM_NAME = "album_name";  // 专辑名称
    private String NAME = "name";  // 图片名称
    private String VIDEO_URL = "video_url";  // 点播视频路径
    private String VIDEO_NET_URL = "video_net_url";  // 网络视频路径
    private String THUMBNAIL = "thumbnail";  // 缩略图
    private String EXT = "ext";  // 扩展名
    private String RESOLUTION = "resolution";  // 分辨率
    private String STATUS = "status";  // 状态//ra dio/2,隐藏,1,显示
    private String IS_COMMENT = "is_comment";  // 是否评论//radio/2,否,1,是
    private String IS_RECOMMEND = "is_recommend";  // 是否推荐：2 否 1 是
    private String SORT = "sort";  // 排序
    private String REMARK = "remark";  // 备注
    private String PUBLISH_TIME = "publish_time";  // 发布时间
    private String PUBLISH_USER = "publish_user";  // 发布者
    private String UPDATE_TIME = "update_time";  // 更新时间
    private String UPDATE_ID = "update_id";  // 更新者
    private String CREATE_TIME = "create_time";  // 创建时间
    private String CREATE_ID = "create_id";  // 创建者
    
    public TbVideo setId(Integer value) {
        set(ID, value);
        return this;
    }

	public Integer getId() {
		return get(ID);
	}
    public TbVideo setAlbumId(Integer value) {
        set(ALBUM_ID, value);
        return this;
    }

	public Integer getAlbumId() {
		return get(ALBUM_ID);
	}
    public TbVideo setAlbumName(String value) {
        set(ALBUM_NAME, value);
        return this;
    }

	public String getAlbumName() {
		return get(ALBUM_NAME);
	}
    public TbVideo setName(String value) {
        set(NAME, value);
        return this;
    }

	public String getName() {
		return get(NAME);
	}
    public TbVideo setVideoUrl(String value) {
        set(VIDEO_URL, value);
        return this;
    }

	public String getVideoUrl() {
		return get(VIDEO_URL);
	}
    public TbVideo setVideoNetUrl(String value) {
        set(VIDEO_NET_URL, value);
        return this;
    }

	public String getVideoNetUrl() {
		return get(VIDEO_NET_URL);
	}
    public TbVideo setThumbnail(String value) {
        set(THUMBNAIL, value);
        return this;
    }

	public String getThumbnail() {
		return get(THUMBNAIL);
	}
    public TbVideo setExt(String value) {
        set(EXT, value);
        return this;
    }

	public String getExt() {
		return get(EXT);
	}
    public TbVideo setResolution(String value) {
        set(RESOLUTION, value);
        return this;
    }

	public String getResolution() {
		return get(RESOLUTION);
	}
    public TbVideo setStatus(Integer value) {
        set(STATUS, value);
        return this;
    }

	public Integer getStatus() {
		return get(STATUS);
	}
    public TbVideo setIsComment(Integer value) {
        set(IS_COMMENT, value);
        return this;
    }

	public Integer getIsComment() {
		return get(IS_COMMENT);
	}
    public TbVideo setIsRecommend(Integer value) {
        set(IS_RECOMMEND, value);
        return this;
    }

	public Integer getIsRecommend() {
		return get(IS_RECOMMEND);
	}
    public TbVideo setSort(Integer value) {
        set(SORT, value);
        return this;
    }

	public Integer getSort() {
		return get(SORT);
	}
    public TbVideo setRemark(String value) {
        set(REMARK, value);
        return this;
    }

	public String getRemark() {
		return get(REMARK);
	}
    public TbVideo setPublishTime(String value) {
        set(PUBLISH_TIME, value);
        return this;
    }

	public String getPublishTime() {
		return get(PUBLISH_TIME);
	}
    public TbVideo setPublishUser(String value) {
        set(PUBLISH_USER, value);
        return this;
    }

	public String getPublishUser() {
		return get(PUBLISH_USER);
	}
    public TbVideo setUpdateTime(String value) {
        set(UPDATE_TIME, value);
        return this;
    }

	public String getUpdateTime() {
		return get(UPDATE_TIME);
	}
    public TbVideo setUpdateId(Integer value) {
        set(UPDATE_ID, value);
        return this;
    }

	public Integer getUpdateId() {
		return get(UPDATE_ID);
	}
    public TbVideo setCreateTime(String value) {
        set(CREATE_TIME, value);
        return this;
    }

	public String getCreateTime() {
		return get(CREATE_TIME);
	}
    public TbVideo setCreateId(Integer value) {
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
		log += "[videoUrl:" + getVideoUrl() + "]";
		log += "[videoNetUrl:" + getVideoNetUrl() + "]";
		log += "[thumbnail:" + getThumbnail() + "]";
		log += "[ext:" + getExt() + "]";
		log += "[resolution:" + getResolution() + "]";
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