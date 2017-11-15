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
import com.alu.entity.CollegeEntity;
import com.alu.service.CollegeServiceI;

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
 * @Description: 学院
 * @author cuimengtao
 * @date 2017-11-15 09:24:14
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/collegeController")
public class CollegeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CollegeController.class);

	@Autowired
	private CollegeServiceI collegeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 学院列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/collegeList");
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
	public void datagrid(CollegeEntity college,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CollegeEntity.class, dataGrid);
		//查询条件组装器
		college.setName(AuTools.createLikeStr(college.getName()));
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
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, college, request.getParameterMap());
		this.collegeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除学院
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CollegeEntity college, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		college = systemService.getEntity(CollegeEntity.class, college.getId());
		message = "学院删除成功";
		college.setDeleteFlag(Constant.IS_DELETE);
		collegeService.updateEntitie(college);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加学院
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CollegeEntity college, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(college.getId())) {
			message = "学院更新成功";
			CollegeEntity t = collegeService.get(CollegeEntity.class, college.getId());
			try {
				college.setLastUpdateBy(curUser.getId());
				college.setLastUpdateByUserName(curUser.getUserName());
				college.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(college, t);
				collegeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "学院更新失败";
			}
		} else {
			message = "学院添加成功";
			college.setCrtBy(curUser.getId());
			college.setCrtByUserName(curUser.getUserName());
			college.setCrtTime(DateUtils.formatDateTime());
			college.setCheckStatus(Constant.APPLY_WAIT);
			college.setDeleteFlag(Constant.UN_DELETE);
			collegeService.save(college);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 学院列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CollegeEntity college, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(college.getId())) {
			college = collegeService.getEntity(CollegeEntity.class, college.getId());
			req.setAttribute("collegePage", college);
		}
		return new ModelAndView("alu/college");
	}
	
	/**
	 * 审核学院
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "handleApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson handleApply(String id, Integer type) {
		AjaxJson aj = new AjaxJson();
		CollegeEntity activity = systemService.getEntity(CollegeEntity.class, id);
		TSUser curUser = ResourceUtil.getSessionUser();
		activity.setCheckBy(curUser.getId());
		activity.setCheckByUserName(curUser.getUserName());
		activity.setCheckStatus(type);
		collegeService.updateEntitie(activity);
		systemService.addLog("审核学院："+activity.getName(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return aj;
	}
}
