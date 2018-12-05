package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.addoil.model.TbAddOil;
import com.jfspecial.modules.admin.addoil.model.TbAddOilAlbum;
import com.jfspecial.modules.front.service.FrontAddoilService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateAddoilService extends BaseService {

	private final static FrontAddoilService service = new FrontAddoilService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbAddOilAlbum> albums() {
		return service.getAlbumList();
	}

	public TbAddOilAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbAddOil> addoils(int pageNo, int pageSize) {
		return service.getAddOil(new Paginator(pageNo, pageSize));
	}

	public Page<TbAddOil> addoils(int pageNo, int pageSize, int albumId) {
		return service.getAddOil(new Paginator(pageNo, pageSize), albumId);
	}

	public Page<TbAddOil> alter_addoils(int pageNo, int pageSize) {
		return service.alterAddOil(new Paginator(pageNo, pageSize));
	}

	public Page<TbAddOil> alter_addoils(int pageNo, int pageSize, int albumId) {
		return service.alterAddOil(new Paginator(pageNo, pageSize), albumId);
	}

	public TbAddOil image(Integer iamgeId) {
		return service.getAddOil(iamgeId);
	}

	public Page<TbAddOil> recommendImages(int pageNo, int pageSize) {
		return service.getRecommendImages(new Paginator(pageNo, pageSize));
	}
}