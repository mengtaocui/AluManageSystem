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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
import com.alu.entity.CollegeEntity;
import com.alu.entity.GradeEntity;
import com.alu.entity.NewsModuleEntity;
import com.alu.service.GradeServiceI;

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
 * @Description: 班级
 * @author cuimengtao
 * @date 2017-11-15 09:24:42
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/gradeController")
public class GradeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GradeController.class);

	@Autowired
	private GradeServiceI gradeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 班级列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/gradeList");
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
	public void datagrid(GradeEntity grade,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(GradeEntity.class, dataGrid);
		//查询条件组装器
		grade.setName(AuTools.createLikeStr(grade.getName()));
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
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, grade, request.getParameterMap());
		this.gradeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除班级
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(GradeEntity grade, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		grade = systemService.getEntity(GradeEntity.class, grade.getId());
		message = "班级删除成功";
		grade.setDeleteFlag(Constant.IS_DELETE);
		gradeService.updateEntitie(grade);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加班级
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(GradeEntity grade, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(grade.getId())) {
			message = "班级更新成功";
			GradeEntity t = gradeService.get(GradeEntity.class, grade.getId());
			try {
				grade.setLastUpdateBy(curUser.getId());
				grade.setLastUpdateByUserName(curUser.getUserName());
				grade.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(grade, t);
				gradeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "班级更新失败";
			}
		} else {
			message = "班级添加成功";
			if(StringUtil.isNotEmpty(curUser.getCollegeId())){
				grade.setCollegeId(curUser.getCollegeId());
				grade.setCollegeName(curUser.getCollegeName());
			}
			grade.setCrtBy(curUser.getId());
			grade.setCrtByUserName(curUser.getUserName());
			grade.setCrtTime(DateUtils.formatDateTime());
			grade.setCheckStatus(Constant.APPLY_WAIT);
			grade.setDeleteFlag(Constant.UN_DELETE);
			grade.setStuCount(0);
			gradeService.save(grade);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 班级列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(GradeEntity grade, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(grade.getId())) {
			grade = gradeService.getEntity(GradeEntity.class, grade.getId());
			req.setAttribute("gradePage", grade);
		}
		return new ModelAndView("alu/grade");
	}
	
	/**
	 * 审核班级
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "handleApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson handleApply(String id, Integer type) {
		AjaxJson aj = new AjaxJson();
		GradeEntity activity = systemService.getEntity(GradeEntity.class, id);
		TSUser curUser = ResourceUtil.getSessionUser();
		activity.setCheckBy(curUser.getId());
		activity.setCheckByUserName(curUser.getUserName());
		activity.setCheckStatus(type);
		gradeService.updateEntitie(activity);
		systemService.addLog("审核班级："+activity.getName(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return aj;
	}
	
    /**
     * 根据学院ID和年届获取班级
     * @param collegeName
     * @param yearPeriod
     * @return
     */
	@RequestMapping(params = "getGradeByCollegeIdAndYearPeriod", method = RequestMethod.GET)
	@ResponseBody
	public List<GradeEntity> getGradeByCollegeIdAndYearPeriod(String collegeId, String yearPeriod) {
		DetachedCriteria salesDc = DetachedCriteria.forClass(GradeEntity.class);
		
		if(StringUtil.isNotEmpty(collegeId))
			salesDc.add(Restrictions.eq("collegeId", collegeId));
		
		if(StringUtil.isNotEmpty(yearPeriod))
			salesDc.add(Restrictions.eq("yearPeriod", yearPeriod));
		
		salesDc.add(Restrictions.eq("deleteFlag", Constant.UN_DELETE));
		List<GradeEntity> list = gradeService.findByDetached(salesDc);
		return list;
	}
}
