package com.jfspecial.modules.admin.infostat;

import com.jfinal.plugin.activerecord.Db;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;

/**
 * 用户信息统计
 * 
 * @author ljk 2018-12-11
 */
@ControllerBind(controllerKey = "/admin/infostat")
public class InfoStatController extends BaseProjectController {

	private static final String path = "/pages/admin/infostat/";

	public void index() {
		//特派员 300
		long  specials=Db.queryLong("select count(*)  from tb_article where folder_id='300'");
//特卖区 301
		long  sales=Db.queryLong("select count(*)  from tb_sale where 1=1 ");
//新闻中心 302
     	long newscenters=Db.queryLong("select count(*)  from tb_newscenter where 1=1");
//技术需求 303
		//var trds=temp.articlePage(1,10,303);
		long trds=Db.queryLong("select count(*)  from tb_trd where 1=1");
//创客活动 304
		long makers=Db.queryLong("select count(*)  from tb_maker where 1=1");
//政策法规 305
		long policys=Db.queryLong("select count(*)  from tb_policy where 1=1");
//科普板块 306
		long spps=Db.queryLong("select count(*)  from tb_spp where 1=1");
//项目申报 307
		long projectapps=Db.queryLong("select count(*)  from tb_projectapp where 1=1");
//帮扶企业 308
		long addoils=Db.queryLong("select count(*)  from tb_addoil where 1=1");
		
		setAttr("specials", specials);
		setAttr("sales", sales);
		setAttr("newscenters", newscenters);
		setAttr("trds", trds);
		setAttr("makers", makers);
		setAttr("policys", policys);
		setAttr("spps", spps);
		setAttr("projectapps", projectapps);
		setAttr("addoils", addoils);

		render(path + "infostat.html");
	}
}
