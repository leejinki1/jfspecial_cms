package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;

import com.jfspecial.modules.admin.policy.model.TbPolicy;
import com.jfspecial.modules.admin.policy.model.TbPolicyAlbum;
import com.jfspecial.modules.front.service.FrontPolicyService;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplatePolicyService extends BaseService {

	private final static FrontPolicyService service = new FrontPolicyService();

	public String randomAlbumId() {
		return RandomStrUtils.randomAlphanumeric(5);
	}

	public List<TbPolicyAlbum> albums() {
		return service.getAlbumList();
	}

	public TbPolicyAlbum album(Integer albumId) {
		return service.getAlbum(albumId);
	}

	public Page<TbPolicy> policys(int pageNo, int pageSize) {
		return service.getPolicy(new Paginator(pageNo, pageSize));
	}

	public Page<TbPolicy> policys(int pageNo, int pageSize, int albumId) {
		return service.getPolicy(new Paginator(pageNo, pageSize), albumId);
	}

	public Page<TbPolicy> alter_policys(int pageNo, int pageSize) {
		return service.alterPolicy(new Paginator(pageNo, pageSize));
	}

	public Page<TbPolicy> alter_policys(int pageNo, int pageSize, int albumId) {
		return service.alterPolicy(new Paginator(pageNo, pageSize), albumId);
	}

	public TbPolicy policy(Integer iamgeId) {
		return service.getPolicy(iamgeId);
	}

	public Page<TbPolicy> recommendImages(int pageNo, int pageSize) {
		return service.getRecommendImages(new Paginator(pageNo, pageSize));
	}
}