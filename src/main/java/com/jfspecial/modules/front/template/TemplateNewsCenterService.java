package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenter;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenterAlbum;
import com.jfspecial.modules.admin.sale.model.TbSale;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.modules.front.service.FrontNewsCenterService;
import com.jfspecial.modules.front.service.FrontSaleService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateNewsCenterService extends BaseService {

	private final static FrontNewsCenterService service = new FrontNewsCenterService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbNewsCenterAlbum> albums() {
		return service.getAlbumList();
	}

	public TbNewsCenterAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbNewsCenter> newscenters(int pageNo, int pageSize) {
		return service.getNewsCenter(new Paginator(pageNo, pageSize));
	}

	public Page<TbNewsCenter> newscenters(int pageNo, int pageSize, int albumId) {
		return service.getNewsCenter(new Paginator(pageNo, pageSize), albumId);
	}

	public TbNewsCenter image(Integer iamgeId) {
		return service.getNewsCenter(iamgeId);
	}

	public Page<TbNewsCenter> recommendSales(int pageNo, int pageSize) {
		return service.getRecommendImages(new Paginator(pageNo, pageSize));
	}
}