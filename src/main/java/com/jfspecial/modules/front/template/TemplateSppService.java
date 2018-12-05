package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.spp.model.TbSpp;
import com.jfspecial.modules.admin.spp.model.TbSppAlbum;
import com.jfspecial.modules.front.service.FrontSppService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateSppService extends BaseService {

	private final static FrontSppService service = new FrontSppService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbSppAlbum> albums() {
		return service.getAlbumList();
	}

	public TbSppAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbSpp> spps(int pageNo, int pageSize) {
		return service.getSpp(new Paginator(pageNo, pageSize));
	}

	public Page<TbSpp> spps(int pageNo, int pageSize, int albumId) {
		return service.getSpp(new Paginator(pageNo, pageSize), albumId);
	}

	public Page<TbSpp> alter_spps(int pageNo, int pageSize) {
		return service.alterSpp(new Paginator(pageNo, pageSize));
	}

	public Page<TbSpp> alter_spps(int pageNo, int pageSize, int albumId) {
		return service.alterSpp(new Paginator(pageNo, pageSize), albumId);
	}

	public TbSpp image(Integer iamgeId) {
		return service.getSpp(iamgeId);
	}

	public Page<TbSpp> recommendImages(int pageNo, int pageSize) {
		return service.getRecommendImages(new Paginator(pageNo, pageSize));
	}
}