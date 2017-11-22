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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.alu.common.AuTools;
import com.alu.common.Constant;
import com.alu.entity.ActivityEntity;
import com.alu.entity.DonateEntity;
import com.alu.service.DonateServiceI;

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
 * @Description: 捐赠
 * @author cuimengtao
 * @date 2017-11-15 09:25:42
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/donateController")
public class DonateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DonateController.class);

	@Autowired
	private DonateServiceI donateService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	/**
	 * 捐赠列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/donateList");
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
	public void datagrid(DonateEntity donate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DonateEntity.class, dataGrid);
		//查询条件组装器
		donate.setName(AuTools.createLikeStr(donate.getName()));
		donate.setPlaces(AuTools.createLikeStr(donate.getPlaces()));
		
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
		if(!"admin".equals(curUser.getUserRoleCode())){
			cq.eq("collegeId", curUser.getCollegeId());
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, donate, request.getParameterMap());
		this.donateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除捐赠
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DonateEntity donate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		donate = systemService.getEntity(DonateEntity.class, donate.getId());
		message = "捐赠删除成功";
		donate.setDeleteFlag(Constant.IS_DELETE);
		donateService.updateEntitie(donate);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加捐赠
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DonateEntity donate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(donate.getId())) {
			message = "捐赠更新成功";
			DonateEntity t = donateService.get(DonateEntity.class, donate.getId());
			try {
				donate.setLastUpdateBy(curUser.getId());
				donate.setLastUpdateByUserName(curUser.getUserName());
				donate.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(donate, t);
				donateService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "捐赠更新失败";
			}
		} else {
			message = "捐赠添加成功";
			if(StringUtil.isNotEmpty(curUser.getCollegeId())){
				donate.setCollegeId(curUser.getCollegeId());
				donate.setCollegeName(curUser.getCollegeName());
			}
			donate.setCrtBy(curUser.getId());
			donate.setCrtByUserName(curUser.getUserName());
			donate.setCrtTime(DateUtils.formatDateTime());
			donate.setCheckStatus(Constant.APPLY_WAIT);
			donate.setDeleteFlag(Constant.UN_DELETE);
			donateService.save(donate);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 捐赠列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DonateEntity donate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(donate.getId())) {
			donate = donateService.getEntity(DonateEntity.class, donate.getId());
			req.setAttribute("donatePage", donate);
		}
		return new ModelAndView("alu/donate");
	}
	
	/**
	 * 审核捐赠项目
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "handleApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson handleApply(String id, Integer type) {
		AjaxJson aj = new AjaxJson();
		DonateEntity activity = systemService.getEntity(DonateEntity.class, id);
		TSUser curUser = ResourceUtil.getSessionUser();
		activity.setCheckBy(curUser.getId());
		activity.setCheckByUserName(curUser.getUserName());
		activity.setCheckStatus(type);
		donateService.updateEntitie(activity);
		systemService.addLog("审核捐赠项目："+activity.getName(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return aj;
	}
}
