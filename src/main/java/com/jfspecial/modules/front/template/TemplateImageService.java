package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.image.model.TbImage;
import com.jfspecial.modules.admin.image.model.TbImageAlbum;
import com.jfspecial.modules.front.service.FrontImageService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateImageService extends BaseService {

	private final static FrontImageService service = new FrontImageService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbImageAlbum> albums() {
		return service.getAlbumList();
	}

	public TbImageAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbImage> images(int pageNo, int pageSize) {
		return service.getImage(new Paginator(pageNo, pageSize));
	}

	public Page<TbImage> images(int pageNo, int pageSize, int albumId) {
		return service.getImage(new Paginator(pageNo, pageSize), albumId);
	}

	public TbImage image(Integer iamgeId) {
		return service.getImage(iamgeId);
	}

	public Page<TbImage> recommendImages(int pageNo, int pageSize) {
		return service.getRecommendImages(new Paginator(pageNo, pageSize));
	}
}