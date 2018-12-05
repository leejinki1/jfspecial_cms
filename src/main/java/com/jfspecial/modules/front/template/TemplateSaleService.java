package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.sale.TbSale;
import com.jfspecial.modules.admin.sale.model.TbSaleAlbum;
import com.jfspecial.modules.front.service.FrontSaleService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateSaleService extends BaseService {

	private final static FrontSaleService service = new FrontSaleService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbSaleAlbum> albums() {
		return service.getAlbumList();
	}

	public TbSaleAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbSale> sales(int pageNo, int pageSize) {
		return service.getSale(new Paginator(pageNo, pageSize));
	}

	public Page<TbSale> sales(int pageNo, int pageSize, int albumId) {
		return service.getSale(new Paginator(pageNo, pageSize), albumId);
	}

	public Page<TbSale> alter_sales(int pageNo, int pageSize) {
		return service.alterSale(new Paginator(pageNo, pageSize));
	}

	public Page<TbSale> alter_sales(int pageNo, int pageSize, int albumId) {
		return service.alterSale(new Paginator(pageNo, pageSize), albumId);
	}

	public TbSale image(Integer iamgeId) {
		return service.getSale(iamgeId);
	}

	public Page<TbSale> recommendSales(int pageNo, int pageSize) {
		return service.getRecommendSales(new Paginator(pageNo, pageSize));
	}
}