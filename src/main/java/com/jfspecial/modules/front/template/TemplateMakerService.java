package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.maker.model.TbMaker;
import com.jfspecial.modules.admin.maker.model.TbMakerAlbum;
import com.jfspecial.modules.front.service.FrontMakerService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateMakerService extends BaseService {

	private final static FrontMakerService service = new FrontMakerService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbMakerAlbum> albums() {
		return service.getAlbumList();
	}

	public TbMakerAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbMaker> makers(int pageNo, int pageSize) {
		return service.getMaker(new Paginator(pageNo, pageSize));
	}

	public Page<TbMaker> makers(int pageNo, int pageSize, int albumId) {
		return service.getMaker(new Paginator(pageNo, pageSize), albumId);
	}

	public Page<TbMaker> alter_makers(int pageNo, int pageSize) {
		return service.alterMaker(new Paginator(pageNo, pageSize));
	}

	public Page<TbMaker> alter_makers(int pageNo, int pageSize, int albumId) {
		return service.alterMaker(new Paginator(pageNo, pageSize), albumId);
	}
	public Page<TbMaker> time_makers(int pageNo, int pageSize, int albumId) {
		return service.timeMaker(new Paginator(pageNo, pageSize), albumId);
	}
	public TbMaker image(Integer iamgeId) {
		return service.getMaker(iamgeId);
	}

	public Page<TbMaker> recommendImages(int pageNo, int pageSize) {
		return service.getRecommendImages(new Paginator(pageNo, pageSize));
	}
}