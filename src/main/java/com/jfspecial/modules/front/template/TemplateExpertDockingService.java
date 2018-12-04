package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.expert.model.TbExpertDocking;
import com.jfspecial.modules.admin.expert.model.TbExpertDockingAlbum;
import com.jfspecial.modules.front.service.FrontExpertDockingService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateExpertDockingService extends BaseService {

	private final static FrontExpertDockingService service = new FrontExpertDockingService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbExpertDockingAlbum> albums() {
		return service.getAlbumList();
	}

	public TbExpertDockingAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbExpertDocking> expertdockings(int pageNo, int pageSize) {
		return service.getExpertDocking(new Paginator(pageNo, pageSize));
	}

	public Page<TbExpertDocking> expertdockings(int pageNo, int pageSize, int albumId) {
		return service.getExpertDocking(new Paginator(pageNo, pageSize), albumId);
	}

	public Page<TbExpertDocking> alter_expertdockings(int pageNo, int pageSize) {
		return service.alterExpertDocking(new Paginator(pageNo, pageSize));
	}

	public Page<TbExpertDocking> alter_expertdockings(int pageNo, int pageSize, int albumId) {
		return service.alterExpertDocking(new Paginator(pageNo, pageSize), albumId);
	}

	public TbExpertDocking image(Integer iamgeId) {
		return service.getExpertDocking(iamgeId);
	}

	public Page<TbExpertDocking> recommendImages(int pageNo, int pageSize) {
		return service.getRecommendImages(new Paginator(pageNo, pageSize));
	}
}