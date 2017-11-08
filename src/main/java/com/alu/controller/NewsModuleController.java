package com.alu.controller;
import java.net.URI;
import java.util.List;
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
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
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
		newsModuleService.delete(newsModule);
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
		if (StringUtil.isNotEmpty(newsModule.getId())) {
			message = "新闻模块更新成功";
			NewsModuleEntity t = newsModuleService.get(NewsModuleEntity.class, newsModule.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(newsModule, t);
				newsModuleService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "新闻模块更新失败";
			}
		} else {
			message = "新闻模块添加成功";
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
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<NewsModuleEntity> list() {
		List<NewsModuleEntity> listNewsModules=newsModuleService.getList(NewsModuleEntity.class);
		return listNewsModules;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		NewsModuleEntity task = newsModuleService.get(NewsModuleEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody NewsModuleEntity newsModule, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NewsModuleEntity>> failures = validator.validate(newsModule);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		newsModuleService.save(newsModule);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = newsModule.getId();
		URI uri = uriBuilder.path("/rest/newsModuleController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody NewsModuleEntity newsModule) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<NewsModuleEntity>> failures = validator.validate(newsModule);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		newsModuleService.saveOrUpdate(newsModule);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		newsModuleService.deleteEntityById(NewsModuleEntity.class, id);
	}
}
