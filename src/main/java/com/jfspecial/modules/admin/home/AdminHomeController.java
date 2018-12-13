package com.jfspecial.modules.admin.home;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.Paginator;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.pageview.TbPageView;
import com.jfspecial.system.user.SysUser;

@ControllerBind(controllerKey = "/admin/home")
public class AdminHomeController extends BaseProjectController {

	private static final String path = "/pages/admin/home/";

	public void index() {
		System.out.println("12.12---------入方法");
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}
		setAttr("nowUser", user);

		System.out.println("12.12---------入方法");
		/*
		// 最新文件
		Page<TbArticle> articlePage = TbArticle.dao.paginate(new Paginator(1, 10), "select t.*,f.name as folderName " //
				, " from tb_article t left join tb_folder f on f.id = t.folder_id " //
						+ " where t.status = 1 and t.type in (11,12) " // 查询状态为显示，类型是预览和正常的文章
						+ "  and f.site_id="+getBackSite().getId()
						+ " order by t.update_time desc,t.id desc");
		setAttr("articles", articlePage.getList());

		// 特卖区
		Page<TbSale> salePage = TbSale.dao.paginate(new Paginator(1, 10), "select t.* " //
				, " from tb_sale t " //
						+ " where t.is_audit = 1 " // 查询状态为显示，类型是预览和正常的文章
						+ " order by t.update_time desc,t.id desc");
		setAttr("sales", salePage.getList());

		// 最新评论
		Page<TbComment> commentPage = TbComment.dao.paginate(new Paginator(1, 10), "select t.*,a.title articleName ", //
				" from tb_comment t " //
						+ " left join tb_article a on a.id = t.article_id where 1=1 order by t.id desc  ");
		setAttr("comments", commentPage.getList());
*/
		// 最新用户
		Page<SysUser> userPage = SysUser.dao.paginate(new Paginator(1, 10), "select t.*,d.name as departname ", //
				" from sys_user t left join sys_department d on d.id = t.departid " //
						+ " where 1 = 1 and userid != 1 order by userid desc ");
		setAttr("users", userPage.getList());

		
		// 最新访问用户
		Page<TbPageView> pageViewPage = TbPageView.dao.paginate(new Paginator(1, 10), "select t.*", //
				" from tb_pageview t order by id desc ");
		setAttr("pageViews", pageViewPage.getList());


		//特派员 300
		long  specials= Db.queryLong("select count(*)  from tb_sale where 1=1");
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
		//专家对接
		long experts=Db.queryLong("select count(*)  from tb_expert_docking where 1=1");


		//获取当前用户的权限

		setAttr("specials", specials);
		setAttr("sales", sales);
		setAttr("newscenters", newscenters);
		setAttr("trds", trds);
		setAttr("makers", makers);
		setAttr("policys", policys);
		setAttr("spps", spps);
		setAttr("projectapps", projectapps);
		setAttr("addoils", addoils);
		setAttr("experts", experts);

		System.out.println("12.12---------出方法");
		render(path + "home.html");
	}
}
