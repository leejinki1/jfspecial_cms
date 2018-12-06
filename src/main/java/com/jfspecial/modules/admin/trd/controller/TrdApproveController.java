package com.jfspecial.modules.admin.trd.controller;

import com.jfinal.aop.Before;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.admin.maker.model.TbMaker;
import com.jfspecial.modules.admin.trd.model.TbTrd;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 技术需求/待审核
 * @author ZR 2018.11.27
 */
@ControllerBind(controllerKey = "/admin/trd_approve")
public class TrdApproveController extends BaseController {

	private static final String path = "/pages/admin/trd/trd_approve";

	//显示待审核
	public void index() {
		String sql = "select t.id,t.name,t.publish_user, t.update_time,t.content,t.image_url,t.album_name " +
                " from tb_trd t where  status = 1 and approve_status = 1 and  is_draft=0  order by sort,id desc";
        //待审核:审核状态=1初始+++不在草稿箱
		List<TbTrd> lists = TbTrd.dao.find(sql);
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

        TbTrd model = TbTrd.dao.findById(getParaToInt());
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
        redirect("/admin/trd_approve");
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

        TbTrd model = TbTrd.dao.findById(getParaToInt());
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
        redirect("/admin/trd_approve");
    }
}


