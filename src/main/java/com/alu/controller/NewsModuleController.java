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
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.DetachedCriteriaUtil;
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
import com.alu.entity.NewsModuleEntity;
import com.alu.service.NewsModuleServiceI;

/**   
 * @Title: Controller
 * @Description: 新闻模块
 * @author zhangdaihao
 * @date 2017-11-07 17:15:21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/newsModuleController")
public class NewsModuleController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NewsModuleController.class);

	@Autowired
	private NewsModuleServiceI newsModuleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 新闻模块列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/newsModuleList");
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
	public void datagrid(NewsModuleEntity newsModule,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(NewsModuleEntity.class, dataGrid);
		//查询条件组装器
		newsModule.setName(AuTools.createLikeStr(newsModule.getName()));
		
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
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, newsModule, request.getParameterMap());
		
		this.newsModuleService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除新闻模块
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(NewsModuleEntity newsModule, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		newsModule = systemService.getEntity(NewsModuleEntity.class, newsModule.getId());
		message = "新闻模块删除成功";
		newsModule.setDeleteFlag(Constant.IS_DELETE);
		newsModuleService.updateEntitie(newsModule);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加新闻模块
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(NewsModuleEntity newsModule, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(newsModule.getId())) {
			message = "新闻模块更新成功";
			NewsModuleEntity t = newsModuleService.get(NewsModuleEntity.class, newsModule.getId());
			try {
				newsModule.setLastUpdateBy(curUser.getId());
				newsModule.setLastUpdateByUserName(curUser.getUserName());
				newsModule.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(newsModule, t);
				newsModuleService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "新闻模块更新失败";
			}
		} else {
			message = "新闻模块添加成功";
			newsModule.setCrtBy(curUser.getId());
			newsModule.setCrtByUserName(curUser.getUserName());
			newsModule.setCrtTime(DateUtils.formatDateTime());
			newsModule.setDeleteFlag(Constant.UN_DELETE);
			newsModuleService.save(newsModule);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 新闻模块列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(NewsModuleEntity newsModule, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(newsModule.getId())) {
			newsModule = newsModuleService.getEntity(NewsModuleEntity.class, newsModule.getId());
			req.setAttribute("newsModulePage", newsModule);
		}
		return new ModelAndView("alu/newsModule");
	}
	
	
	@RequestMapping(params = "getByTypeId", method = RequestMethod.GET)
	@ResponseBody
	public List<NewsModuleEntity> getByTypeId(String typeId) {
		DetachedCriteria salesDc = DetachedCriteria.forClass(NewsModuleEntity.class);
		salesDc.add(Restrictions.eq("newsTypeId", typeId));
		salesDc.add(Restrictions.eq("deleteFlag", Constant.UN_DELETE));
		List<NewsModuleEntity> listNewsModules = newsModuleService.findByDetached(salesDc);
		return listNewsModules;
	}
}
