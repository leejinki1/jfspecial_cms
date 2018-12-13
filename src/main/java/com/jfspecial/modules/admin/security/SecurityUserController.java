package com.jfspecial.modules.admin.security;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.BaseController;
import com.jfspecial.jfinal.component.annotation.ControllerBind;
import com.jfspecial.jfinal.component.db.SQLUtils;
import com.jfspecial.modules.CommonController;
import com.jfspecial.modules.admin.aboutweb.SysAboutus;
import com.jfspecial.system.department.DepartmentSvc;
import com.jfspecial.system.file.util.FileUploadUtils;
import com.jfspecial.system.role.SysRole;
import com.jfspecial.system.user.SysUser;
import com.jfspecial.system.user.UserCache;
import com.jfspecial.system.user.UserSvc;
import com.jfspecial.util.StrUtils;
import com.jfspecial.util.encrypt.Md5Utils;

import java.util.List;

/**
 * 安全设置/用户设置/单个页面
 * @author ZR 2018.11.23
 */
@ControllerBind(controllerKey = "/admin/security_user")
public class SecurityUserController extends BaseController {

	private static final String path = "/pages/admin/security/security_user_";

	public void index() {
		list();
	}

	/**
	 * 	显示全部用户(有权限限制)
	 */
	public void list() {
		SysUser model = getModelByAttr(SysUser.class);

		SQLUtils sql = new SQLUtils(" from sys_user t " //
				+ " left join sys_department d on d.id = t.departid " //
				+ " where 1 = 1 and userid != 1 ");

		if (model.getAttrValues().length != 0) {
			sql.whereLike("username", model.getStr("username"));
			sql.whereLike("realname", model.getStr("realname"));
			sql.whereEquals("usertype", model.getInt("usertype"));
			sql.whereEquals("departid", model.getInt("departid"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by userid desc");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<SysUser> page = SysUser.dao.paginate(getPaginator(), "select t.*,d.name as departname ", sql.toString()
				.toString());
		// 下拉框
		setAttr("departSelect", new DepartmentSvc().selectDepart(model.getInt("departid")));

		//查询全部//有权限限制(只能查出比自己权限小的)
		Integer userType= getSessionUser().getInt("usertype");
		String sql1 = "select * " +
				"from sys_user t " +
				" order by userid desc";
		List<SysUser> list=SysUser.dao.find(sql1);

		setAttr("page", page);
		setAttr("attr", model);
		setAttr("list",list);
		render(path + "list.html");
	}



	/**
	 * 跳转到授权页面
	 *
	 * 2015年4月28日 下午12:00:05 flyfox 369191470@qq.com
	 */
	public void auth() {
		int userid = getParaToInt();
		List<SysRole> list = SysRole.dao.findByWhere(" where status=1 order by sort ");

		String roleids = new UserSvc().getRoleids(userid);
		setAttr("userid", userid);
		setAttr("roleids", roleids);
		// 根结点
		setAttr("list", list);
		render(path + "auth.html");
	}

	/**
	 * 保存角色信息
	 *
	 * 2015年4月28日 下午3:18:33 flyfox 369191470@qq.com
	 */
	public void auth_save() {
		int userid = getParaToInt("userid");
		String roleids = getPara("roleids");

		new UserSvc().saveAuth(userid, roleids, getSessionUser().getUserid());
		renderMessage("保存成功");
	}

	/**
	 * 跳转到新增页面
	 *
	*/
	public void toSaveUser(){
		/*判断是否登陆或登陆过期*/
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		/*判断权限(userType  1或9  为管理员,可以新增)*/
		Integer userType =  user.getInt("usertype");
		//System.out.println("12.7----安全/userC/toCreateUser----"+userType);
		String msg;
		if(!(userType==1||userType==9)){
			msg="没有权限";
			setAttr("msg",msg);
			render(path+"list.html");
			return;

		}

		String sql = "select * from sys_role t where  status = 1 order by sort,id desc";
		List<SysRole> albums = SysRole.dao.find(sql);
		setAttr("albums", albums);

		/*从前台获取数据*//*保存返回前台的参数*/
		Integer pid = getParaToInt();//获取路径中的参数
		SecurityUser model = SecurityUser.dao.findById(pid);//根据id查出model的值
		if(model!=null){//解密
			String password = model.getPassword();
			System.out.println("12.12------password" +password);
			model.setPassword(JFSpecialUtils.passwordDecrypt(password));
			System.out.println("12.12------解密后的password:"+JFSpecialUtils.passwordDecrypt(password));
		}


		if (pid != null && pid > 0) { // 更新
			setAttr("model",model);
			render(path + "edit.html");
			return;
		}else{//新增
			render(path + "edit.html");
			return;
		}

	}

	/**
	 * 保存新增或修改用户的结果
	 *
	 */
	public void saveUser() {
		/*判断是否登陆或登陆过期*/
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		/*从前台获取提交数据*/
		SecurityUser model = getModel(SecurityUser.class);
		Integer pid = getParaToInt();//获取路径中的参数

		if(model!=null){//加密密码
			System.out.println("12.11---------------model"+model);
			String password = model.getPassword();
			model.setPassword(JFSpecialUtils.passwordEncrypt(password));
		}

		/*提交*/
		boolean is;
		if (pid != null && pid > 0) {//修改
			is=model.update();
		}else{//新增
			String sql="select max(userid) userid from sys_user";
			SysUser model1=SysUser.dao.findFirst(sql);
			//System.out.println("12.10-----"+model1);
			Integer id=model1.getUserid()+1;//获取原来数据库中最大id,加1,即是新的id
			//System.out.println("12.10------"+id);
			model.set("id",id);
			//System.out.println("12.10----"+model);
			is=model.save();
		}


		/*判断提交结果*/
		String msg="";
		if(is){
			msg="保存成功";
			System.out.println("保存成功");
		}else{
			msg="保存失败";
			System.out.println("保存失败");
		}

		/*保存反回参数*/
		setAttr("msg",msg);
		redirect("/admin/security_user");//跳转回
	}

	/**
	 * 修改密码
	 *
	 */
	public void resetPassword() {
		/*ajax提交返回值*/
		JSONObject json = new JSONObject();
		json.put("msg", "失败");// 失败

		/*判断是否登陆或登陆过期*/
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		/*从前台获取提交数据*/

		Integer pid = getParaToInt();//获取路径中的参数
		String password = getPara("password");
		//String sql="select * from sys_user where userid="+pid;
		SysUser model = SysUser.dao.findFirstByWhere("where userid = ? ", pid);
		model.set("password", JFSpecialUtils.passwordEncrypt(password));
		//model.put("realname","1234567890");
		//System.out.println("12.10---model:"+model);

		if (StrUtils.isEmpty(password)|| password.length() < 6 || password.length() > 20 // 密码长度限制
			) {
			json.put("msg", "提交数据错误！");
			renderJson(json.toJSONString());
			return;
		}

		/*提交*/
		boolean is;
		is=model.update();

		/*判断提交结果*/
		if(is){
			json.put("msg", "ok");// 成功
			System.out.println("保存成功");
		}else{
			System.out.println("保存失败");
		}

		/*保存反回参数*/
		renderJson(json.toJSONString());
	}
}


