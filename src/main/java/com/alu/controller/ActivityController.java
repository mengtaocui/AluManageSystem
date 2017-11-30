package com.alu.controller;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alu.common.AuTools;
import com.alu.common.Constant;
import com.alu.entity.ActivityEntity;
import com.alu.entity.ActivitySpaceEntity;
import com.alu.entity.NoticesEntity;
import com.alu.service.ActivityServiceI;

/**   
 * @Title: Controller
 * @Description: 活动
 * @author zhangdaihao
 * @date 2017-11-07 17:16:35
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/activityController")
public class ActivityController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActivityController.class);

	@Autowired
	private ActivityServiceI activityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 活动列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/activityList");
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
	public void datagrid(ActivityEntity activity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ActivityEntity.class, dataGrid);
		//查询条件组装器
		activity.setName(AuTools.createLikeStr(activity.getName()));
		activity.setPlaces(AuTools.createLikeStr(activity.getPlaces()));
		
		//报名截止时间
		if(StringUtil.isNotEmpty(request.getParameter("applyEndTime_begin1"))){
			cq.ge("applyEndTime", request.getParameter("applyEndTime_begin1"));
		}
		if(StringUtil.isNotEmpty(request.getParameter("applyEndTime_end2"))){
			cq.le("applyEndTime", request.getParameter("applyEndTime_end2"));
		}
		
		//开始时间
		if(StringUtil.isNotEmpty(request.getParameter("startTime_begin1"))){
			cq.ge("startTime", request.getParameter("startTime_begin1"));
			cq.ge("endTime", request.getParameter("startTime_begin1"));
		}
		if(StringUtil.isNotEmpty(request.getParameter("startTime_end2"))){
			cq.le("startTime", request.getParameter("startTime_end2"));
			cq.le("endTime", request.getParameter("startTime_end2"));
		}
		
		//降序排列
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("crtTime", SortDirection.desc);
		cq.setOrder(map);
		//过滤掉删除的
		cq.eq("deleteFlag", Constant.UN_DELETE);
		
		//学院管理员只能查询自己所在学院的数据,超级管理员可以查看所有学院的数据
		TSUser curUser = ResourceUtil.getSessionUser();
		if(StringUtil.isNotEmpty(curUser.getCollegeId())){
			cq.eq("collegeId", curUser.getCollegeId());
		}
		
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, activity, request.getParameterMap());
		this.activityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除活动
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ActivityEntity activity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		activity = systemService.getEntity(ActivityEntity.class, activity.getId());
		message = "活动删除成功";
		activity.setDeleteFlag(Constant.IS_DELETE);
		activityService.updateEntitie(activity);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加活动
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ActivityEntity activity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(activity.getId())) {
			message = "活动更新成功";
			ActivityEntity t = activityService.get(ActivityEntity.class, activity.getId());
			try {
				activity.setLastUpdateBy(curUser.getId());
				activity.setLastUpdateByUserName(curUser.getUserName());
				activity.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(activity, t);
				activityService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "活动更新失败";
			}
		} else {
			message = "活动添加成功";
			
			activity.setCrtBy(curUser.getId());
			activity.setCrtByUserName(curUser.getUserName());
			activity.setCrtTime(DateUtils.formatDateTime());
			activity.setCheckStatus(Constant.APPLY_WAIT);
			activity.setDeleteFlag(Constant.UN_DELETE);
			activityService.save(activity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 活动列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ActivityEntity activity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(activity.getId())) {
			activity = activityService.getEntity(ActivityEntity.class, activity.getId());
			req.setAttribute("activityPage", activity);
		}
		return new ModelAndView("alu/activity");
	}
	
	/**
	 * 审核活动
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "handleApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson handleApply(String id, Integer type) {
		AjaxJson aj = new AjaxJson();
		ActivityEntity activity = systemService.getEntity(ActivityEntity.class, id);
		TSUser curUser = ResourceUtil.getSessionUser();
		activity.setCheckBy(curUser.getId());
		activity.setCheckByUserName(curUser.getUserName());
		activity.setCheckStatus(type);
		activityService.updateEntitie(activity);
		systemService.addLog("审核活动："+activity.getName(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return aj;
	}
}
