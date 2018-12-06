package com.jfspecial.modules.admin.maker.controller;

import com.jfinal.aop.Before;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.component.util.ImageModel;
import com.jfspecial.component.util.ImageUtils;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.admin.maker.model.TbMaker;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenter;
import com.jfspecial.modules.admin.newscenter.model.TbNewsCenterTags;
import com.jfspecial.modules.admin.newscenter.service.NewsCenterAlbumService;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.util.StrUtils;

import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * 创客/审核
 * zr  2018.11.27
 */
@ControllerBind(controllerKey = "/admin/maker_approve")
public class MakerApproveController extends BaseProjectController {

	private static final String path = "/pages/admin/maker/maker_approve";

	//显示待审核
	public void index() {
		String sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.image_net_url,t.album_name " +
                "from tb_maker t where  status = 1 and approve_status = 1   and is_draft=0 order by sort,id desc";
        //待审核:审核状态=1初始
		List<TbMaker> lists = TbMaker.dao.find(sql);
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

        TbMaker model = TbMaker.dao.findById(getParaToInt());
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
        redirect("/admin/maker_approve");
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

        TbMaker model = TbMaker.dao.findById(getParaToInt());
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
        redirect("/admin/maker_approve");
    }

    /**
     *  approve article
     *  跳转到审核页面
     *	2018.12.6  zr
     */
    @Before(FrontInterceptor.class)
    public void  approveArticle() {
        SysUser user = (SysUser) getSessionUser();
        Integer pid = getParaToInt();

        if (user == null || pid == null) {
            redirect(CommonController.firstPage);
            return;
        }

        TbMaker model = TbMaker.dao.findById(pid);
        // 不是自己的文章也想修改,总有不怀好意的人哦
        if (model.getCreateId() != user.getUserid()) {
            System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
            redirect(CommonController.firstPage);
            return;
        }

        //保存数据
        setAttr("model", model);
        //返回审核页面
        render(path+"_detail.html");

    }

}
