package com.alu.controller;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alu.common.AuTools;
import com.alu.common.Constant;
import com.alu.entity.NewsEntity;
import com.alu.service.NewsServiceI;

/**   
 * @Title: Controller
 * @Description: 新闻
 * @author cuimengtao
 * @date 2017-11-07 17:15:48
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/newsController")
public class NewsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NewsController.class);

	@Autowired
	private NewsServiceI newsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	/**
	 * 新闻列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/newsList");
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
	public void datagrid(NewsEntity news,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(NewsEntity.class, dataGrid);
		//查询条件组装器
		news.setTitle(AuTools.createLikeStr(news.getTitle()));
		
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
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, news, request.getParameterMap());
		this.newsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除新闻
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(NewsEntity news, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		news = systemService.getEntity(NewsEntity.class, news.getId());
		message = "新闻删除成功";
		news.setDeleteFlag(Constant.IS_DELETE);
		newsService.updateEntitie(news);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加新闻
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(NewsEntity news, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(news.getId())) {
			message = "新闻更新成功";
			NewsEntity t = newsService.get(NewsEntity.class, news.getId());
			try {
				news.setLastUpdateBy(curUser.getId());
				news.setLastUpdateByUserName(curUser.getUserName());
				news.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(news, t);
				newsService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "新闻更新失败";
			}
		} else {
			message = "新闻添加成功";
			news.setCrtBy(curUser.getId());
			news.setCrtByUserName(curUser.getUserName());
			news.setCrtTime(DateUtils.formatDateTime());
			news.setDeleteFlag(Constant.UN_DELETE);
			news.setCheckStatus(Constant.APPLY_WAIT);
			newsService.save(news);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 新闻列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(NewsEntity news, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(news.getId())) {
			news = newsService.getEntity(NewsEntity.class, news.getId());
			req.setAttribute("newsPage", news);
		}
		return new ModelAndView("alu/news");
	}
	
	
	/**
	 * 审核新闻
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "handleApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson handleApply(String id, Integer type) {
		AjaxJson aj = new AjaxJson();
		NewsEntity news = systemService.getEntity(NewsEntity.class, id);
		TSUser curUser = ResourceUtil.getSessionUser();
		news.setCheckBy(curUser.getId());
		news.setCheckByUserName(curUser.getUserName());
		news.setCheckStatus(type);
		newsService.updateEntitie(news);
		systemService.addLog("审核新闻："+news.getTitle(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return aj;
	}
	
}
