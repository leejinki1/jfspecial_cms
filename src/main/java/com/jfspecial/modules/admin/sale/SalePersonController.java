package com.jfspecial.modules.admin.sale;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.sale.TbSale;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 特卖区发布信息/历史
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/sale_person")
public class SalePersonController extends BaseController {

	private static final String path = "/pages/admin/sale/sale_";

	@Before(FrontInterceptor.class)
	public void index() {
		//判断user是否登录,
		SysUser user = (SysUser) getSessionUser();
		//Integer id = getParaToInt();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		//判断权限//不用判断权限,只显示自己发布的

		String sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.image_net_url,t.album_name,t.approve_status  " +
				"from tb_sale t " +
				"where approve_status>0 and create_id="+user.getUserid();

		//待审核:审核状态=1初始+++
		List<TbSale> lists = TbSale.dao.find(sql);
		setAttr("lists", lists);
		render(path+"person.html");
		return;
	}
}


