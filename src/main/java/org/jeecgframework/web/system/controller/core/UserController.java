package org.jeecgframework.web.system.controller.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alu.common.Constant;
import com.alu.entity.ActivityEntity;


/**
 * @ClassName: UserController
 * @Description: TODO(用户管理处理类)
 * @author 张代浩
 */
//@Scope("prototype")
@Controller
@RequestMapping("/userController")
public class UserController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserController.class);

	private UserService userService;
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	/**
	 * 菜单列表
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "menu")
	public void menu(HttpServletRequest request, HttpServletResponse response) {
		SetListSort sort = new SetListSort();
		TSUser u = ResourceUtil.getSessionUser();
		// 登陆者的权限
		Set<TSFunction> loginActionlist = new HashSet<TSFunction>();// 已有权限菜单
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", u.getId());
		for (TSRoleUser ru : rUsers) {
			TSRole role = ru.getTSRole();
			List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
			if (roleFunctionList.size() > 0) {
				for (TSRoleFunction roleFunction : roleFunctionList) {
					TSFunction function = (TSFunction) roleFunction.getTSFunction();
					loginActionlist.add(function);
				}
			}
		}
		List<TSFunction> bigActionlist = new ArrayList<TSFunction>();// 一级权限菜单
		List<TSFunction> smailActionlist = new ArrayList<TSFunction>();// 二级权限菜单
		if (loginActionlist.size() > 0) {
			for (TSFunction function : loginActionlist) {
				if (function.getFunctionLevel() == 0) {
					bigActionlist.add(function);
				} else if (function.getFunctionLevel() == 1) {
					smailActionlist.add(function);
				}
			}
		}
		// 菜单栏排序
		Collections.sort(bigActionlist, sort);
		Collections.sort(smailActionlist, sort);
		String logString = ListtoMenu.getMenu(bigActionlist, smailActionlist);
		// request.setAttribute("loginMenu",logString);
		try {
			response.getWriter().write(logString);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "user")
	public String user(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		return "system/user/userList";
	}

	/**
	 * 用户信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "userinfo")
	public String userinfo(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUser();
		request.setAttribute("user", user);
		return "system/user/userinfo";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(params = "changepassword")
	public String changepassword(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUser();
		request.setAttribute("user", user);
		return "system/user/changepassword";
	}
	@RequestMapping(params = "changeportrait")
	public String changeportrait(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUser();
		request.setAttribute("user", user);
		return "system/user/changeportrait";
	}
	/**
	 * 修改密码
	 *
	 * @return
	 */
	@RequestMapping(params = "saveportrait")
	@ResponseBody
	public AjaxJson saveportrait(HttpServletRequest request,String fileName) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUser();
		user.setPortrait(fileName);
		j.setMsg("修改成功");
		try {
			systemService.updateEntitie(user);
		} catch (Exception e) {
			j.setMsg("修改失败");
			e.printStackTrace();
		}
		return j;
	}


	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(params = "savenewpwd")
	@ResponseBody
	public AjaxJson savenewpwd(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUser();
		logger.info("["+IpUtil.getIpAddr(request)+"][修改密码] start");
		String password = oConvertUtils.getString(request.getParameter("password"));
		String newpassword = oConvertUtils.getString(request.getParameter("newpassword"));
		String pString = PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt());
		if (!pString.equals(user.getPassword())) {
			j.setMsg("原密码不正确");
			j.setSuccess(false);
		} else {
			try {
				user.setPassword(PasswordUtil.encrypt(user.getUserName(), newpassword, PasswordUtil.getStaticSalt()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			systemService.updateEntitie(user);
			j.setMsg("修改成功");
			logger.info("["+IpUtil.getIpAddr(request)+"][修改密码]修改成功 userId:"+user.getUserName());

		}
		return j;
	}
	

	/**
	 * 
	 * 跳转重置用户密码页面
	 * @author Chj
	 */
	
	@RequestMapping(params = "changepasswordforuser")
	public ModelAndView changepasswordforuser(TSUser user, HttpServletRequest req) {
		logger.info("["+IpUtil.getIpAddr(req)+"][跳转重置用户密码页面]["+user.getUserName()+"]");
		if (StringUtil.isNotEmpty(user.getId())) {
			user = systemService.getEntity(TSUser.class, user.getId());
			req.setAttribute("user", user);
			idandname(req, user);
			//System.out.println(user.getPassword()+"-----"+user.getRealName());
		}
		return new ModelAndView("system/user/adminchangepwd");
	}
	
	
	
	/**
	 * 重置密码
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "savenewpwdforuser")
	@ResponseBody
	public AjaxJson savenewpwdforuser(HttpServletRequest req) {
		logger.info("["+IpUtil.getIpAddr(req)+"][重置密码] start");
		String message = null;
		AjaxJson j = new AjaxJson();
		String id = oConvertUtils.getString(req.getParameter("id"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		
		if (StringUtil.isNotEmpty(id)) {
			TSUser users = systemService.getEntity(TSUser.class,id);
			if("admin".equals(users.getUserName()) && !"admin".equals(ResourceUtil.getSessionUser().getUserName())){
				message = "超级管理员[admin]，只有admin本人可操作，其他人无权限!";
				logger.info("["+IpUtil.getIpAddr(req)+"]"+message);
				j.setMsg(message);
				return j;
			}
			
			//System.out.println(users.getUserName());
			users.setPassword(PasswordUtil.encrypt(users.getUserName(), password, PasswordUtil.getStaticSalt()));
			users.setStatus(Globals.User_Normal);
			users.setActivitiSync(users.getActivitiSync());
			systemService.updateEntitie(users);	
			message = "用户: " + users.getUserName() + "密码重置成功";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			logger.info("["+IpUtil.getIpAddr(req)+"][重置密码]"+message);
		} 
		
		j.setMsg(message);

		return j;
	}
	/**
	 * 锁定账户
	
	 * 
	 * @author pu.chen
	 */
	@RequestMapping(params = "lock")
	@ResponseBody
	public AjaxJson lock(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String message = null;
		TSUser user = systemService.getEntity(TSUser.class, id);
		if("admin".equals(user.getUserName())){
			message = "超级管理员[admin]不可操作";
			j.setMsg(message);
			return j;
		}
		String lockValue=req.getParameter("lockvalue");

		user.setStatus(new Short(lockValue));
		try{
		userService.updateEntitie(user);
		if("0".equals(lockValue)){
			message = "用户：" + user.getUserName() + "锁定成功!";
		}else if("1".equals(lockValue)){
			message = "用户：" + user.getUserName() + "激活成功!";
		}
		systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		logger.info("["+IpUtil.getIpAddr(req)+"][锁定账户]"+message);
		}catch(Exception e){
			message = "操作失败!";
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 得到角色列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "role")
	@ResponseBody
	public List<ComboBox> role(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {
		String id = request.getParameter("id");
		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		List<TSRole> roles = new ArrayList<TSRole>();
		if (StringUtil.isNotEmpty(id)) {
			List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", id);
			if (roleUser.size() > 0) {
				for (TSRoleUser ru : roleUser) {
					roles.add(ru.getTSRole());
				}
			}
		}
		List<TSRole> roleList = systemService.getList(TSRole.class);
		comboBoxs = TagUtil.getComboBox(roleList, roles, comboBox);

		roleList.clear();
		roles.clear();

		return comboBoxs;
	}


	/**
	 * easyuiAJAX用户列表请求数据 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(TSUser user,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);

        Short[] userstate = new Short[]{Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden};
        cq.in("status", userstate);
        cq.eq("deleteFlag", 0);
        
        TSUser curUser = ResourceUtil.getSessionUser();
        
        if("sys_manage".equals(curUser.getUserRoleCode()) || "sch_manage".equals(curUser.getUserRoleCode())){
            cq.notEq("collegeId", " ");
            cq.isNull("departid");
        }else if("col_manage".equals(curUser.getUserRoleCode())){
        	cq.eq("departid", curUser.getCollegeId());
        }
        
        cq.add();
        this.systemService.getDataGridReturn(cq, true);

        List<TSUser> cfeList = new ArrayList<TSUser>();
        for (Object o : dataGrid.getResults()) {
            if (o instanceof TSUser) {
                TSUser cfe = (TSUser) o;
                if (cfe.getId() != null && !"".equals(cfe.getId())) {
                    List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", cfe.getId());
                    if (roleUser.size() > 0) {
                        String roleName = "";
                        for (TSRoleUser ru : roleUser) {
                            roleName += ru.getTSRole().getRoleName() + ",";
                        }
                        roleName = roleName.substring(0, roleName.length() - 1);
                        cfe.setUserKey(roleName);
                    }
                }
                cfeList.add(cfe);
            }
        }

        TagUtil.datagrid(response, dataGrid);
    }

	/**
	 * 用户删除选择对话框
	 * 
	 * @return
	 */
	@RequestMapping(params = "deleteDialog")
	public String deleteDialog(TSUser user,HttpServletRequest request) {
		request.setAttribute("user", user);
		return "system/user/user-delete";
	} 
	
	@RequestMapping(params = "delete")
	@ResponseBody
	public AjaxJson delete(TSUser user, @RequestParam String deleteType, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		
		if (deleteType.equals("delete")) {
			return this.del(user, req);
		}else if (deleteType.equals("deleteTrue")) {
			return this.trueDel(user, req);
		}else{
			
			j.setMsg("删除逻辑参数异常,请重试.");
			return j;
		}
	}

	
	/**
	 * 用户信息录入和更新
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSUser user, HttpServletRequest req) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if("admin".equals(user.getUserName())){
			message = "超级管理员[admin]不可删除";
			j.setMsg(message);
			return j;
		}
		user = systemService.getEntity(TSUser.class, user.getId());
//		List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (!user.getStatus().equals(Globals.User_ADMIN)) {

			user.setDeleteFlag(1);
			userService.updateEntitie(user);
			message = "用户：" + user.getUserName() + "删除成功";
			logger.info("["+IpUtil.getIpAddr(req)+"][逻辑删除用户]"+message);

			
/**
			if (roleUser.size()>0) {
				// 删除用户时先删除用户和角色关系表
				delRoleUser(user);

                systemService.executeSql("delete from t_s_user_org where user_id=?", user.getId()); // 删除 用户-机构 数据

                userService.delete(user);
				message = "用户：" + user.getUserName() + "删除成功";
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			} else {
				userService.delete(user);
				message = "用户：" + user.getUserName() + "删除成功";
			}
**/	
		} else {
			message = "超级管理员不可删除";
		}

		j.setMsg(message);
		return j;
	}
	
	/**
	 * 真实删除
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "trueDel")
	@ResponseBody
	public AjaxJson trueDel(TSUser user, HttpServletRequest req) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if("admin".equals(user.getUserName())){
			message = "超级管理员[admin]不可删除";
			j.setMsg(message);
			return j;
		}
		user = systemService.getEntity(TSUser.class, user.getId());

		/*List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (!user.getStatus().equals(Globals.User_ADMIN)) {
			if (roleUser.size()>0) {
				// 删除用户时先删除用户和角色关系表
				delRoleUser(user);
                systemService.executeSql("delete from t_s_user_org where user_id=?", user.getId()); // 删除 用户-机构 数据
                userService.delete(user);
				message = "用户：" + user.getUserName() + "删除成功";
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			} else {
				userService.delete(user);
				message = "用户：" + user.getUserName() + "删除成功";
			}
		} else {
			message = "超级管理员不可删除";
		}*/
		
		try {
			message = userService.trueDel(user);
			logger.info("["+IpUtil.getIpAddr(req)+"][真实删除用户]"+message);
		} catch (Exception e) {
			e.printStackTrace();
			message ="删除失败";
		}


		j.setMsg(message);
		return j;
	}

	/*public void delRoleUser(TSUser user) {
		// 同步删除用户角色关联表
		List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (roleUserList.size() >= 1) {
			for (TSRoleUser tRoleUser : roleUserList) {
				systemService.delete(tRoleUser);
			}
		}
	}*/
	/**
	 * 检查用户名
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkUser")
	@ResponseBody
	public ValidForm checkUser(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		String userName=oConvertUtils.getString(request.getParameter("param"));
		String code=oConvertUtils.getString(request.getParameter("code"));
		List<TSUser> roles=systemService.findByProperty(TSUser.class,"userName",userName);
		if(roles.size()>0&&!code.equals(userName))
		{
			v.setInfo("用户名已存在");
			v.setStatus("n");
		}
		return v;
	}

	/**
	 * 用户录入
	 * 
	 * @param user
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "saveUser")
	@ResponseBody
	public AjaxJson saveUser(HttpServletRequest req, TSUser user) {
		String message = null;
		AjaxJson j = new AjaxJson();
		// 得到用户的角色
		String roleid = oConvertUtils.getString(req.getParameter("roleid"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		
		TSUser curUser = ResourceUtil.getSessionUser();
		if("col_manage".equals(curUser.getUserRoleCode())){
			user.setCollegeId(curUser.getCollegeId());
			user.setCollegeName(curUser.getCollegeName());
			user.setDepartid(curUser.getCollegeId());
		}
		
		if (StringUtil.isNotEmpty(user.getId())) {
			TSUser users = systemService.getEntity(TSUser.class, user.getId());
			users.setEmail(user.getEmail());
			users.setOfficePhone(user.getOfficePhone());
			users.setMobilePhone(user.getMobilePhone());

            systemService.executeSql("delete from t_s_user_org where user_id=?", user.getId());

			users.setRealName(user.getRealName());
			users.setStatus(Globals.User_Normal);
			users.setActivitiSync(user.getActivitiSync());
			systemService.updateEntitie(users);
			List<TSRoleUser> ru = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			systemService.deleteAllEntitie(ru);
			message = "用户: " + users.getUserName() + "更新成功";
			if (StringUtil.isNotEmpty(roleid)) {
				saveRoleUser(users, roleid);
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			TSUser users = systemService.findUniqueByProperty(TSUser.class, "userName",user.getUserName());
			if (users != null) {
				message = "用户: " + users.getUserName() + "已经存在";
			} else {
				user.setPassword(PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt()));
				user.setStatus(Globals.User_Normal);
				user.setDeleteFlag(0);
				systemService.save(user);
				message = "用户: " + user.getUserName() + "添加成功";
				if (StringUtil.isNotEmpty(roleid)) {
					saveRoleUser(user, roleid);
				}
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}

		}
		j.setMsg(message);
		logger.info("["+IpUtil.getIpAddr(req)+"][添加编辑用户]"+message);
		return j;
	}

    

    protected void saveRoleUser(TSUser user, String roleidstr) {
		String[] roleids = roleidstr.split(",");
		for (int i = 0; i < roleids.length; i++) {
			TSRoleUser rUser = new TSRoleUser();
			TSRole role = systemService.getEntity(TSRole.class, roleids[i]);
			rUser.setTSRole(role);
			rUser.setTSUser(user);
			systemService.save(rUser);

		}
	}

	/**
	 * 用户选择角色跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "roles")
	public ModelAndView roles(HttpServletRequest request) {
		//--author：zhoujf-----start----date:20150531--------for: 编辑用户，选择角色,弹出的角色列表页面，默认没选中
		ModelAndView mv = new ModelAndView("system/user/users");
		String ids = oConvertUtils.getString(request.getParameter("ids"));
		mv.addObject("ids", ids);
		return mv;
	}

	/**
	 * 角色显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridRole")
	public void datagridRole(TSRole tsRole, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tsRole);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * easyuiAJAX请求数据： 用户选择角色列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSUser user, HttpServletRequest req) {


        List<String> orgIdList = new ArrayList<String>();
		if (StringUtil.isNotEmpty(user.getId())) {
			user = systemService.getEntity(TSUser.class, user.getId());
			
			req.setAttribute("user", user);
			idandname(req, user);
		}
        return new ModelAndView("system/user/user");
	}



	public void idandname(HttpServletRequest req, TSUser user) {
		List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		String roleId = "";
		String roleName = "";
		if (roleUsers.size() > 0) {
			for (TSRoleUser tRoleUser : roleUsers) {
				roleId += tRoleUser.getTSRole().getId() + ",";
				roleName += tRoleUser.getTSRole().getRoleName() + ",";
			}
		}
		req.setAttribute("id", roleId);
		req.setAttribute("roleName", roleName);

	}
	
	
	/**
	 * 根据部门和角色选择用户跳转页面
	 */
	@RequestMapping(params = "choose")
	public String choose(HttpServletRequest request) {
		List<TSRole> roles = systemService.loadAll(TSRole.class);
		request.setAttribute("roleList", roles);
		return "system/membership/checkuser";
	}

	/**
	 * 部门和角色选择用户的panel跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "chooseUser")
	public String chooseUser(HttpServletRequest request) {
		String departid = request.getParameter("departid");
		String roleid = request.getParameter("roleid");
		request.setAttribute("roleid", roleid);
		request.setAttribute("departid", departid);
		return "system/membership/userlist";
	}

	/**
	 * 部门和角色选择用户的用户显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridUser")
	public void datagridUser(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String departid = request.getParameter("departid");
		String roleid = request.getParameter("roleid");
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		if (departid.length() > 0) {
			cq.eq("TDepart.departid", oConvertUtils.getInt(departid, 0));
			cq.add();
		}
		String userid = "";
		if (roleid.length() > 0) {
			List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TRole.roleid", oConvertUtils.getInt(roleid, 0));
			if (roleUsers.size() > 0) {
				for (TSRoleUser tRoleUser : roleUsers) {
					userid += tRoleUser.getTSUser().getId() + ",";
				}
			}
			cq.in("userid", oConvertUtils.getInts(userid.split(",")));
			cq.add();
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 根据部门和角色选择用户跳转页面
	 */
	@RequestMapping(params = "roleDepart")
	public String roleDepart(HttpServletRequest request) {
		List<TSRole> roles = systemService.loadAll(TSRole.class);
		request.setAttribute("roleList", roles);
		return "system/membership/roledepart";
	}

	/**
	 * 部门和角色选择用户的panel跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "chooseDepart")
	public ModelAndView chooseDepart(HttpServletRequest request) {
		String nodeid = request.getParameter("nodeid");
		ModelAndView modelAndView = null;
		if (nodeid.equals("role")) {
			modelAndView = new ModelAndView("system/membership/users");
		} else {
			modelAndView = new ModelAndView("system/membership/departList");
		}
		return modelAndView;
	}

	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "index")
	public String index() {
		return "bootstrap/main";
	}

	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "main")
	public String main() {
		return "bootstrap/test";
	}

	/**
	 * 测试
	 * 
	 * @return
	 */
	@RequestMapping(params = "testpage")
	public String testpage(HttpServletRequest request) {
		return "test/test";
	}

	/**
	 * 设置签名跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		return new ModelAndView("system/user/usersign");
	}

	/**
	 * 用户录入
	 * 
	 * @param user
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "savesign", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson savesign(HttpServletRequest req) {
		String message = null;
		UploadFile uploadFile = new UploadFile(req);
		String id = uploadFile.get("id");
		TSUser user = systemService.getEntity(TSUser.class, id);
		uploadFile.setRealPath("signatureFile");
		uploadFile.setCusPath("signature");
		uploadFile.setByteField("signature");
		uploadFile.setBasePath("resources");
		uploadFile.setRename(false);
		uploadFile.setObject(user);
		AjaxJson j = new AjaxJson();
		message = user.getUserName() + "设置签名成功";
		systemService.uploadFile(uploadFile);
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);

		return j;
	}
	/**
	 * 测试组合查询功能
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "testSearch")
	public void testSearch(TSUser user, HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		if(user.getUserName()!=null){
			cq.like("userName", user.getUserName());
		}
		if(user.getRealName()!=null){
			cq.like("realName", user.getRealName());
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "changestyle")
	public String changeStyle(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUser();
		if(user==null){
			return "login/login";
		}
//		String indexStyle = "shortcut";
//		String cssTheme="";
//		Cookie[] cookies = request.getCookies();
//		for (Cookie cookie : cookies) {
//			if(cookie==null || StringUtils.isEmpty(cookie.getName())){
//				continue;
//			}
//			if(cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE")){
//				indexStyle = cookie.getValue();
//			}
//			if(cookie.getName().equalsIgnoreCase("JEECGCSSTHEME")){
//				cssTheme = cookie.getValue();
//			}
//		}
		SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
		request.setAttribute("indexStyle", sysThemesEnum.getStyle());
//		request.setAttribute("cssTheme", cssTheme);
		return "system/user/changestyle";
	}
	/**
	* @Title: saveStyle
	* @Description: 修改首页样式
	* @param request
	* @return AjaxJson    
	* @throws
	 */
	@RequestMapping(params = "savestyle")
	@ResponseBody
	public AjaxJson saveStyle(HttpServletRequest request,HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		j.setSuccess(Boolean.FALSE);
		TSUser user = ResourceUtil.getSessionUser();
		if(user!=null){
			String indexStyle = request.getParameter("indexStyle");
//			String cssTheme = request.getParameter("cssTheme");

//			if(StringUtils.isNotEmpty(cssTheme)){
//				Cookie cookie4css = new Cookie("JEECGCSSTHEME", cssTheme);
//				cookie4css.setMaxAge(3600*24*30);
//				response.addCookie(cookie4css);
//				logger.info("cssTheme:"+cssTheme);
//			}else if("ace".equals(indexStyle)){
//				Cookie cookie4css = new Cookie("JEECGCSSTHEME", "metro");
//				cookie4css.setMaxAge(3600*24*30);
//				response.addCookie(cookie4css);
//				logger.info("cssTheme:metro");

//			}else {
//				Cookie cookie4css = new Cookie("JEECGCSSTHEME", "");
//				cookie4css.setMaxAge(3600*24*30);
//				response.addCookie(cookie4css);
//				logger.info("cssTheme:default");
//			}

			
			if(StringUtils.isNotEmpty(indexStyle)){
				Cookie cookie = new Cookie("JEECGINDEXSTYLE", indexStyle);
				//设置cookie有效期为一个月
				cookie.setMaxAge(3600*24*30);
				response.addCookie(cookie);
				logger.debug(" ----- 首页样式: 在· ----- "+indexStyle);
				j.setSuccess(Boolean.TRUE);
				j.setMsg("样式修改成功，请刷新页面");
			}

			try {
				 ClientManager.getInstance().getClient().getFunctions().clear();
			} catch (Exception e) {
			}

		}else{
			j.setMsg("请登录后再操作");
		}
		return j;
	}

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","userController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 修改用户信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "updateUser")
	@ResponseBody
	public AjaxJson updateUser(TSUser user, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(user.getId())) {
			message = "信息修改成功";
			TSUser t = systemService.get(TSUser.class, user.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(user, t);
				systemService.saveOrUpdate(t);
				request.getSession().setAttribute(ResourceUtil.LOCAL_CLINET_USER, t);
				
				Client client = new Client();
		        client.setIp(IpUtil.getIpAddr(request));
		        client.setLogindatetime(new Date());
		        client.setUser(t);
		        ClientManager.getInstance().addClinet(request.getSession().getId(), client);
				
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "信息修改成功";
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 选择用户跳转页面
	 *
	 * @return
	 */
	@RequestMapping(params = "userSelect")
	public String userSelect() {
		return "system/user/userSelect";
	}
}