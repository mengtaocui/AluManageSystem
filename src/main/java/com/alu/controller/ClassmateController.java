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
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;

import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.alu.common.AuTools;
import com.alu.common.Constant;
import com.alu.entity.ClassmateEntity;
import com.alu.entity.NewsModuleEntity;
import com.alu.service.ClassmateServiceI;
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

import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 校友
 * @author cuimengtao
 * @date 2017-11-15 09:25:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/classmateController")
public class ClassmateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ClassmateController.class);

	@Autowired
	private ClassmateServiceI classmateService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private GradeServiceI gradeService;


	/**
	 * 校友列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/classmateList");
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
	public void datagrid(ClassmateEntity classmate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ClassmateEntity.class, dataGrid);
		//查询条件组装器
		classmate.setName(AuTools.createLikeStr(classmate.getName()));
		classmate.setStuNo(AuTools.createLikeStr(classmate.getStuNo()));
		classmate.setPhone(AuTools.createLikeStr(classmate.getPhone()));
		classmate.setAddress(AuTools.createLikeStr(classmate.getAddress()));
		classmate.setEmail(AuTools.createLikeStr(classmate.getEmail()));
		
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
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, classmate, request.getParameterMap());
		this.classmateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除校友
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ClassmateEntity classmate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		classmate = systemService.getEntity(ClassmateEntity.class, classmate.getId());
		message = "校友删除成功";
		classmate.setDeleteFlag(Constant.IS_DELETE);
		classmateService.updateEntitie(classmate);
		//减少班级人数
		gradeService.setGradePersonnelCount(-1, classmate.getGradeId());
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加校友
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ClassmateEntity classmate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(classmate.getId())) {
			message = "校友更新成功";
			ClassmateEntity t = classmateService.get(ClassmateEntity.class, classmate.getId());
			try {
				classmate.setLastUpdateBy(curUser.getId());
				classmate.setLastUpdateByUserName(curUser.getUserName());
				classmate.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(classmate, t);
				classmateService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "校友更新失败";
			}
		} else {
			message = "校友添加成功";
			if(StringUtil.isNotEmpty(curUser.getCollegeId())){
				classmate.setCollegeId(curUser.getCollegeId());
				classmate.setCollegeName(curUser.getCollegeName());
			}
			classmate.setCrtBy(curUser.getId());
			classmate.setCrtByUserName(curUser.getUserName());
			classmate.setCrtTime(DateUtils.formatDateTime());
			classmate.setDeleteFlag(Constant.UN_DELETE);
			classmateService.save(classmate);
			
			//增加班级人数
			gradeService.setGradePersonnelCount(1, classmate.getGradeId());
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 校友列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ClassmateEntity classmate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(classmate.getId())) {
			classmate = classmateService.getEntity(ClassmateEntity.class, classmate.getId());
			req.setAttribute("classmatePage", classmate);
		}else{
			if(StringUtil.isNotEmpty(classmate.getName()) && StringUtil.isNotEmpty(classmate.getPhone())){
				
				DetachedCriteria salesDc = DetachedCriteria.forClass(ClassmateEntity.class);
				try {
					salesDc.add(Restrictions.eq("name", 
							new String(classmate.getName().getBytes("ISO-8859-1"), "UTF-8")
							));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				salesDc.add(Restrictions.eq("phone", classmate.getPhone()));
				salesDc.add(Restrictions.eq("deleteFlag", Constant.UN_DELETE));
				
				List<ClassmateEntity> list = classmateService.findByDetached(salesDc);
				
				if(!ListUtils.isNullOrEmpty(list))
					req.setAttribute("classmatePage", list.get(0));
			}
		}
		return new ModelAndView("alu/classmate");
	}
}
