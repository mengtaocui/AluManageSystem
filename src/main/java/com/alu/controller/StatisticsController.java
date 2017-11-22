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
import com.alu.service.StatisticsServiceI;

/**   
 * @Title: Controller
 * @Description: 活动
 * @author zhangdaihao
 * @date 2017-11-07 17:16:35
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/statisticsController")
public class StatisticsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StatisticsController.class);

	@Autowired
	private ActivityServiceI activityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private StatisticsServiceI statisticsService;


	/**
	 * 按毕业年届统计 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "statisticsByYearPeriodPage")
	public ModelAndView statisticsByYearPeriodPage(HttpServletRequest request) {
		return new ModelAndView("alu/statisticsByYearPeriod");
	}

	/**
	 * 按教育阶段统计 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "statisticsByEducationStagePage")
	public ModelAndView statisticsByEducationStagePage(HttpServletRequest request) {
		return new ModelAndView("alu/statisticsByEducationStage");
	}
	
	/**
	 * 按省份统计 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "statisticsByProvincePage")
	public ModelAndView statisticsByProvincePage(HttpServletRequest request) {
		return new ModelAndView("alu/statisticsByProvince");
	}
	
	/**
	 * 按单位性质统计 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "statisticsByCompanyNaturePage")
	public ModelAndView statisticsByCompanyNaturePage(HttpServletRequest request) {
		return new ModelAndView("alu/statisticsByCompanyNature");
	}
	
	
	/**
	 * 获取按年届统计校友人数的数据
	 * @return
	 */
	@RequestMapping(params = "getStatisticsByYearPeriodData", method = RequestMethod.GET)
	@ResponseBody
	public List getStatisticsByYearPeriodData() {
		TSUser curUser = ResourceUtil.getSessionUser();
		if(!"admin".equals(curUser.getUserKey()))
			return statisticsService.getStatisticsByYearPeriodData(curUser.getCollegeId());
		return statisticsService.getStatisticsByYearPeriodData(null);
	}
	
	/**
	 * 获取按省份统计校友人数的数据
	 * @return
	 */
	@RequestMapping(params = "getStatisticsByProvinceData", method = RequestMethod.GET)
	@ResponseBody
	public List getStatisticsByProvinceData() {
		TSUser curUser = ResourceUtil.getSessionUser();
		if(!"admin".equals(curUser.getUserKey()))
			return statisticsService.getStatisticsByProvinceData(curUser.getCollegeId());
		return statisticsService.getStatisticsByProvinceData(null);
	}
	
	/**
	 * 获取按单位性质统计校友人数的数据
	 * @return
	 */
	@RequestMapping(params = "getStatisticsByCompanyNatureData", method = RequestMethod.GET)
	@ResponseBody
	public List getStatisticsByCompanyNatureData() {
		TSUser curUser = ResourceUtil.getSessionUser();
		if(!"admin".equals(curUser.getUserKey()))
			return statisticsService.getStatisticsByCompanyNatureData(curUser.getCollegeId());
		return statisticsService.getStatisticsByCompanyNatureData(null);
	}
	
	/**
	 * 获取按教育阶段统计校友人数的数据
	 * @return
	 */
	@RequestMapping(params = "getStatisticsByEducationStageData", method = RequestMethod.GET)
	@ResponseBody
	public List getStatisticsByEducationStageData() {
		TSUser curUser = ResourceUtil.getSessionUser();
		if(!"admin".equals(curUser.getUserKey()))
			return statisticsService.getStatisticsByEducationStageData(curUser.getCollegeId());
		return statisticsService.getStatisticsByEducationStageData(null);
	}
}
