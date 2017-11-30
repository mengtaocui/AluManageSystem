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
import com.alu.entity.DonateRecordEntity;
import com.alu.service.DonateRecordServiceI;

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
 * @Description: 捐赠记录
 * @author cuimengtao
 * @date 2017-11-15 09:26:09
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/donateRecordController")
public class DonateRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DonateRecordController.class);

	@Autowired
	private DonateRecordServiceI donateRecordService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 捐赠记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/donateRecordList");
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
	public void datagrid(DonateRecordEntity donateRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DonateRecordEntity.class, dataGrid);
		//查询条件组装器
		
		donateRecord.setDonateName(AuTools.createLikeStr(donateRecord.getDonateName()));
		donateRecord.setDonatePersonName(AuTools.createLikeStr(donateRecord.getDonatePersonName()));
		donateRecord.setDonatePersonPhone(AuTools.createLikeStr(donateRecord.getDonatePersonPhone()));
		donateRecord.setCrtByUserName(AuTools.createLikeStr(donateRecord.getCrtByUserName()));
		
		
		
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
		if(StringUtil.isNotEmpty(curUser.getCollegeId())){
			cq.eq("collegeId", curUser.getCollegeId());
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, donateRecord, request.getParameterMap());
		this.donateRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除捐赠记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DonateRecordEntity donateRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		donateRecord = systemService.getEntity(DonateRecordEntity.class, donateRecord.getId());
		message = "捐赠记录删除成功";
		donateRecord.setDeleteFlag(Constant.IS_DELETE);
		donateRecordService.updateEntitie(donateRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加捐赠记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DonateRecordEntity donateRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(donateRecord.getId())) {
			message = "捐赠记录更新成功";
			DonateRecordEntity t = donateRecordService.get(DonateRecordEntity.class, donateRecord.getId());
			try {
				donateRecord.setLastUpdateBy(curUser.getId());
				donateRecord.setLastUpdateByUserName(curUser.getUserName());
				donateRecord.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(donateRecord, t);
				donateRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "捐赠记录更新失败";
			}
		} else {
			message = "捐赠记录添加成功";
			donateRecord.setCrtBy(curUser.getId());
			donateRecord.setCrtByUserName(curUser.getUserName());
			donateRecord.setCrtTime(DateUtils.formatDateTime());
			donateRecord.setDeleteFlag(Constant.UN_DELETE);
			donateRecordService.save(donateRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 捐赠记录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DonateRecordEntity donateRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(donateRecord.getId())) {
			donateRecord = donateRecordService.getEntity(DonateRecordEntity.class, donateRecord.getId());
			req.setAttribute("donateRecordPage", donateRecord);
		}
		return new ModelAndView("alu/donateRecord");
	}
}
