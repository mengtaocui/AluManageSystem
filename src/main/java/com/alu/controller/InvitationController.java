package com.alu.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;

import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.alu.common.AuTools;
import com.alu.common.Constant;
import com.alu.entity.ActivityEntity;
import com.alu.entity.InvitationEntity;
import com.alu.service.InvitationServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 帖子
 * @author cuimengtao
 * @date 2017-11-15 09:27:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/invitationController")
public class InvitationController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InvitationController.class);

	@Autowired
	private InvitationServiceI invitationService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 帖子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/invitationList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(InvitationEntity invitation,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InvitationEntity.class, dataGrid);
		//查询条件组装器
		invitation.setTitle(AuTools.createLikeStr(invitation.getTitle()));
		
		if(StringUtil.isNotEmpty(request.getParameter("crtTime_begin1"))){
			cq.ge("crtTime", request.getParameter("crtTime_begin1"));
		}
		if(StringUtil.isNotEmpty(request.getParameter("crtTime_end2"))){
			cq.le("crtTime", request.getParameter("crtTime_end2"));
		}
		
		//降序排列
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("crtTime", SortDirection.desc);
		cq.setOrder(map);
		//过滤掉删除的
		cq.eq("deleteFlag", Constant.UN_DELETE);
		
		//学院管理员只能查询自己所在学院的数据,超级管理员可以查看所有学院的数据
		TSUser curUser = ResourceUtil.getSessionUser();
		if(!"admin".equals(curUser.getUserRoleCode())){
			cq.eq("collegeId", curUser.getCollegeId());
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, invitation, request.getParameterMap());
		this.invitationService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除帖子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InvitationEntity invitation, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		invitation = systemService.getEntity(InvitationEntity.class, invitation.getId());
		message = "帖子删除成功";
		invitation.setDeleteFlag(Constant.IS_DELETE);
		invitationService.updateEntitie(invitation);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加帖子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(InvitationEntity invitation, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(invitation.getId())) {
			message = "帖子更新成功";
			InvitationEntity t = invitationService.get(InvitationEntity.class, invitation.getId());
			try {
				invitation.setLastUpdateBy(curUser.getId());
				invitation.setLastUpdateByUserName(curUser.getUserName());
				invitation.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(invitation, t);
				invitationService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "帖子更新失败";
			}
		} else {
			message = "帖子添加成功";
			if(StringUtil.isNotEmpty(curUser.getCollegeId())){
				invitation.setCollegeId(curUser.getCollegeId());
				invitation.setCollegeName(curUser.getCollegeName());
			}
			invitation.setCrtBy(curUser.getId());
			invitation.setCrtByUserName(curUser.getUserName());
			invitation.setCrtTime(DateUtils.formatDateTime());
			invitation.setCheckStatus(Constant.APPLY_WAIT);
			invitation.setDeleteFlag(Constant.UN_DELETE);
			invitationService.save(invitation);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 帖子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InvitationEntity invitation, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(invitation.getId())) {
			invitation = invitationService.getEntity(InvitationEntity.class, invitation.getId());
			req.setAttribute("invitationPage", invitation);
		}
		return new ModelAndView("alu/invitation");
	}
	
	/**
	 * 审核帖子
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "handleApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson handleApply(String id, Integer type) {
		AjaxJson aj = new AjaxJson();
		InvitationEntity activity = systemService.getEntity(InvitationEntity.class, id);
		TSUser curUser = ResourceUtil.getSessionUser();
		activity.setCheckBy(curUser.getId());
		activity.setCheckByUserName(curUser.getUserName());
		activity.setCheckStatus(type);
		invitationService.updateEntitie(activity);
		systemService.addLog("审核帖子："+activity.getTitle(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return aj;
	}
}
