package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.trd.model.TbTrd;
import com.jfspecial.modules.admin.trd.model.TbTrdAlbum;
import com.jfspecial.modules.front.service.FrontTrdService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateTrdService extends BaseService {

	private final static FrontTrdService service = new FrontTrdService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbTrdAlbum> albums() {
		return service.getAlbumList();
	}

	public TbTrdAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbTrd> trds(int pageNo, int pageSize) {
		return service.getTrd(new Paginator(pageNo, pageSize));
	}

	public Page<TbTrd> trds(int pageNo, int pageSize, int albumId) {
		return service.getTrd(new Paginator(pageNo, pageSize), albumId);
	}

	public Page<TbTrd> alter_trds(int pageNo, int pageSize) {
		return service.alterTrd(new Paginator(pageNo, pageSize));
	}

	public Page<TbTrd> alter_trds(int pageNo, int pageSize, int albumId) {
		return service.alterTrd(new Paginator(pageNo, pageSize), albumId);
	}
	public TbTrd image(Integer iamgeId) {
		return service.getTrd(iamgeId);
	}

	public Page<TbTrd> recommendImages(int pageNo, int pageSize) {
		return service.getRecommendImages(new Paginator(pageNo, pageSize));
	}
}