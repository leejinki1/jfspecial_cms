package com.jfspecial.modules.admin.addoil.controller;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.addoil.model.TbAddOil;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 帮扶企业/审核
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/addoil_approve")
public class AddoilApproveController extends BaseController {

	private static final String path = "/pages/admin/addoil/addoil_approve";

	//显示待审核
	public void index() {
		String sql = "select t.id,t.name,t.publish_user, t.update_time from tb_addoil t where  status = 1 and approve_status = 1  and is_draft=0 order by sort,id desc";
		//待审核:审核状态=1初始+++
		List<TbAddOil> lists = TbAddOil.dao.find(sql);
		setAttr("lists", lists);
		render(path+".html");
	}

	/**
	 *  pass article
	 *  通过
	 * 2018年11月27日 下午9:53:04 ljk
	 */
	@Before(FrontInterceptor.class)
	public void  passArticle() {
		SysUser user = (SysUser) getSessionUser();
		Integer id = getParaToInt();
		if (user == null || id == null) {
			redirect(CommonController.firstPage);
			return;
		}

		TbAddOil model = TbAddOil.dao.findById(getParaToInt());
		setAttr("model", model);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		//修改审核状态
        model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS); // 需要审核改为update;通过审核为pass

		//保存到数据库
        model.update();

        //返回审核页面
        redirect("/admin/addoil_approve");
	}

    /**
     *  reject article
     *  不通过
     * 2018年11月27日 下午9:53:04 ljk
     */
    @Before(FrontInterceptor.class)
    public void  rejectArticle() {
        SysUser user = (SysUser) getSessionUser();
        Integer id = getParaToInt();
        if (user == null || id == null) {
            redirect(CommonController.firstPage);
            return;
        }

        TbAddOil model = TbAddOil.dao.findById(getParaToInt());
        setAttr("model", model);
        // 不是自己的文章也想修改,总有不怀好意的人哦
        if (model.getCreateId() != user.getUserid()) {
            System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
            redirect(CommonController.firstPage);
            return;
        }

        //修改审核状态
        model.set("approve_status", ArticleConstant.APPROVE_STATUS_REJECT); // 需要审核改为update;通过审核为pass;不通过为reject

        //保存到数据库
        model.update();

        //返回审核页面
        redirect("/admin/addoil_approve");
    }
}


