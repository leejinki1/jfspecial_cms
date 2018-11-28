package com.jfspecial.modules.admin.sale;

import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;

/**
 * 特卖区发布信息
 * @author ljk 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/sale_approve")
public class SaleApproveController extends BaseController {

	private static final String path = "/pages/admin/sale/";

	public void index() {
		render(path+"sale_approve.html");//先反回主页,待补充
	}

}


