package com.jfspecial.modules.admin.spp.controller;

import com.jfinal.aop.Before;
import com.jfspecial.component.base.BaseProjectController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.article.ArticleConstant;
import com.jfspecial.modules.admin.spp.model.TbSpp;
import com.jfspecial.modules.front.interceptor.FrontInterceptor;
import com.jfspecial.system.user.SysUser;

import java.util.List;

/**
 * 科普/审核
 * zr  2018.11.27
 */
@ControllerBind(controllerKey = "/admin/spp_approve")
public class SppApproveController extends BaseProjectController {

	private static final String path = "/pages/admin/spp/spp_";

	//显示待审核
	public void index() {
        //判断user是否登录,
        SysUser user = (SysUser) getSessionUser();
        //Integer id = getParaToInt();
        if (user == null) {
            redirect(CommonController.firstPage);
            return;
        }

        //判断权限
        String sql;
        Integer usertype = user.getInt("usertype");
        if(usertype>0&&usertype<=9){
            //1-9是管理员
            sql = "select t.id,t.name,t.publish_user, t.update_time ,t.content,t.image_url,t.image_net_url,t.album_name " +
                    "from tb_spp t " +
                    "where approve_status = 1  and is_draft=0 order by sort,id desc";


            //待审核:审核状态=1初始+++
            List<TbSpp> lists = TbSpp.dao.find(sql);
            setAttr("lists", lists);
            render(path+"approve.html");
            return;
        }

        //其他的是没有权限的,提示一下
        //理论上不会进入,在页面控制一下

        setAttr("msg", "没有该权限,请联系系统管理员");
        redirect("/admin");
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

        TbSpp model = TbSpp.dao.findById(getParaToInt());
        Integer usertype = user.getInt("usertype");
        if(usertype>0&&usertype<10){
            //1-9是系统管理员
            //修改审核状态
            model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS); // 需要审核改为update;通过审核为pass

            //保存到数据库
            model.update();
            //返回审核页面
            redirect("/admin/spp_approve");
        }

        // 没有权限的一般也进不来
        System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
        //返回审核页面
        redirect("/admin.html");


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

        TbSpp model = TbSpp.dao.findById(getParaToInt());
        Integer usertype = user.getInt("usertype");
        if(usertype>0&&usertype<10) {
            //修改审核状态
            model.set("approve_status", ArticleConstant.APPROVE_STATUS_REJECT); // 需要审核改为update;通过审核为pass;不通过为reject

            //保存到数据库
            model.update();

            //返回审核页面
            redirect("/admin/spp_approve");
            return;
        }
        //其他的是没有权限的,提示一下
        //理论上不会进入,在页面控制一下

        setAttr("msg", "没有该权限,请联系系统管理员");
        redirect("/admin");


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
        Integer usertype = user.getInt("usertype");
        if (user == null || pid == null) {
            redirect(CommonController.firstPage);
            return;
        }

        TbSpp model = TbSpp.dao.findById(pid);
        //判断权限
        if((usertype>0&&usertype<10)||(model.getCreateId()== user.getUserid())){
            //是管理员//或是作者自己
            //保存数据
            setAttr("model", model);
            //返回审核页面
            render(path+"approve_detail.html");
        }else{
            // 不是自己的文章也想修改,总有不怀好意的人哦
            System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
            redirect(CommonController.firstPage);
            return;
        }

    }
}
