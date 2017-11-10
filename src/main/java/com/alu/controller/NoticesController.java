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
import com.alu.entity.NewsEntity;
import com.alu.entity.NoticesEntity;
import com.alu.service.NoticesServiceI;

/**   
 * @Title: Controller
 * @Description: 公告
 * @author zhangdaihao
 * @date 2017-11-07 17:16:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/noticesController")
public class NoticesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NoticesController.class);

	@Autowired
	private NoticesServiceI noticesService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 公告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/noticesList");
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
	public void datagrid(NoticesEntity notices,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(NoticesEntity.class, dataGrid);
		//查询条件组装器
		notices.setTitle(AuTools.createLikeStr(notices.getTitle()));
		
		if(StringUtil.isNotEmpty(request.getParameter("crtTime_begin1"))){
			cq.ge("crtTime", request.getParameter("crtTime_begin1"));
		}
		if(StringUtil.isNotEmpty(request.getParameter("crtTime_end2"))){
			cq.le("crtTime", request.getParameter("crtTime_end2"));
		}
		
		if(StringUtil.isNotEmpty(request.getParameter("pastDueDate_begin1"))){
			cq.ge("pastDueDate", request.getParameter("pastDueDate_begin1"));
		}
		if(StringUtil.isNotEmpty(request.getParameter("pastDueDate_end2"))){
			cq.le("pastDueDate", request.getParameter("pastDueDate_end2"));
		}
		
		//降序排列
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("crtTime", SortDirection.desc);
		cq.setOrder(map);
		
		//过滤掉删除的
		cq.eq("deleteFlag", Constant.UN_DELETE);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, notices, request.getParameterMap());
		this.noticesService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除公告
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(NoticesEntity notices, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		notices = systemService.getEntity(NoticesEntity.class, notices.getId());
		message = "公告删除成功";
		notices.setDeleteFlag(Constant.IS_DELETE);
		noticesService.updateEntitie(notices);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加公告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(NoticesEntity notices, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(notices.getId())) {
			message = "公告更新成功";
			NoticesEntity t = noticesService.get(NoticesEntity.class, notices.getId());
			try {
				notices.setLastUpdateBy(curUser.getId());
				notices.setLastUpdateByUserName(curUser.getUserName());
				notices.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(notices, t);
				noticesService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "公告更新失败";
			}
		} else {
			message = "公告添加成功";
			notices.setCrtBy(curUser.getId());
			notices.setCrtByUserName(curUser.getUserName());
			notices.setCrtTime(DateUtils.formatDateTime());
			notices.setCheckStatus(Constant.APPLY_WAIT);
			notices.setDeleteFlag(Constant.UN_DELETE);
			noticesService.save(notices);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 公告列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(NoticesEntity notices, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(notices.getId())) {
			notices = noticesService.getEntity(NoticesEntity.class, notices.getId());
			req.setAttribute("noticesPage", notices);
		}
		return new ModelAndView("alu/notices");
	}
	
	/**
	 * 审核公告
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "handleApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson handleApply(String id, Integer type) {
		AjaxJson aj = new AjaxJson();
		NoticesEntity notices = systemService.getEntity(NoticesEntity.class, id);
		TSUser curUser = ResourceUtil.getSessionUser();
		notices.setCheckBy(curUser.getId());
		notices.setCheckByUserName(curUser.getUserName());
		notices.setCheckStatus(type);
		noticesService.updateEntitie(notices);
		systemService.addLog("审核公告："+notices.getTitle(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return aj;
	}
	
}
