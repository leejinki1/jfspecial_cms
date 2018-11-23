package com.jfspecial.modules.front.template;

import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.modules.admin.special.TbSpecial;
import com.jfspecial.modules.admin.spp.model.TbSpp;
import com.jfspecial.modules.admin.spp.model.TbSppAlbum;
import com.jfspecial.modules.front.service.FrontSpecialService;
import com.jfspecial.modules.front.service.FrontSppService;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.extend.RandomStrUtils;

import java.util.List;

/**
 * 模板方法接口
 * 
 * 2018年11月18日 下午6:05:54 ljk
 */
public class TemplateSpecialService extends BaseService {

	private final static FrontSpecialService service = new FrontSpecialService();

	public Page<TbSpecial> specials(int pageNo, int pageSize) {
		return service.getSpecial(new Paginator(pageNo, pageSize));
	}

	public Page<TbSpecial> specials(int pageNo, int pageSize, int albumId) {
		return service.getSpecial(new Paginator(pageNo, pageSize), albumId);
	}

	public TbSpecial special(Integer userId) {
		return service.getSpecial(userId);
	}

}