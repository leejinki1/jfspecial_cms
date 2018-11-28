package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.projectapp.model.TbProjectApp;
import com.jfspecial.modules.admin.projectapp.model.TbProjectAppAlbum;
import com.jfspecial.modules.admin.sale.model.TbSale;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.modules.front.service.FrontProjectAppService;
import com.jfspecial.modules.front.service.FrontSaleService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateProjectAppService extends BaseService {

	private final static FrontProjectAppService service = new FrontProjectAppService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbProjectAppAlbum> albums() {
		return service.getAlbumList();
	}

	public TbProjectAppAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbProjectApp> projectapps(int pageNo, int pageSize) {
		return service.getProjectApp(new Paginator(pageNo, pageSize));
	}

	public Page<TbProjectApp> projectapps(int pageNo, int pageSize, int albumId) {
		return service.getProjectApp(new Paginator(pageNo, pageSize), albumId);
	}

	public Page<TbProjectApp> alter_projectapps(int pageNo, int pageSize) {
		return service.alterProjectApp(new Paginator(pageNo, pageSize));
	}

	public Page<TbProjectApp> alter_projectapps(int pageNo, int pageSize, int albumId) {
		return service.alterProjectApp(new Paginator(pageNo, pageSize), albumId);
	}
	public TbProjectApp image(Integer iamgeId) {
		return service.getProjectApp(iamgeId);
	}

	public Page<TbProjectApp> recommendImages(int pageNo, int pageSize) {
		return service.getRecommendImages(new Paginator(pageNo, pageSize));
	}
}