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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.alu.common.Constant;
import com.alu.entity.ActivityEntity;
import com.alu.entity.ActivitySpaceEntity;
import com.alu.entity.BannerEntity;
import com.alu.service.BannerServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.io.File;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 登录页轮播图
 * @author cuimengtao
 * @date 2017-11-23 16:08:15
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/bannerController")
public class BannerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BannerController.class);

	@Autowired
	private BannerServiceI bannerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 登录页轮播图列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/bannerList");
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
	public void datagrid(BannerEntity banner,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BannerEntity.class, dataGrid);
		//查询条件组装器
		//降序排列
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("crtTime", SortDirection.desc);
		cq.setOrder(map);
		//过滤掉删除的
		cq.eq("deleteFlag", Constant.UN_DELETE);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, banner, request.getParameterMap());
		this.bannerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除登录页轮播图
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(BannerEntity banner, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		banner = systemService.getEntity(BannerEntity.class, banner.getId());
		message = "登录页轮播图删除成功";
		banner.setDeleteFlag(Constant.IS_DELETE);
		bannerService.updateEntitie(banner);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加登录页轮播图
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(BannerEntity banner, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser curUser = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(banner.getId())) {
			message = "登录页轮播图更新成功";
			BannerEntity t = bannerService.get(BannerEntity.class, banner.getId());
			try {
				banner.setLastUpdateBy(curUser.getId());
				banner.setLastUpdateByUserName(curUser.getUserName());
				banner.setLastUpdateTime(DateUtils.formatDateTime());
				MyBeanUtils.copyBeanNotNull2Bean(banner, t);
				bannerService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "登录页轮播图更新失败";
			}
		} else {
			message = "登录页轮播图添加成功";
			banner.setCrtBy(curUser.getId());
			banner.setCrtByUserName(curUser.getUserName());
			banner.setCrtTime(DateUtils.formatDateTime());
			banner.setDeleteFlag(Constant.UN_DELETE);
			banner.setStatus(0);
			bannerService.save(banner);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 登录页轮播图列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(BannerEntity banner, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(banner.getId())) {
			banner = bannerService.getEntity(BannerEntity.class, banner.getId());
			req.setAttribute("bannerPage", banner);
		}
		return new ModelAndView("alu/banner");
	}
	
	/**
	 * 禁用启用轮播图
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "handleApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson handleApply(String id, Integer type) {
		AjaxJson aj = new AjaxJson();
		BannerEntity banner = systemService.getEntity(BannerEntity.class, id);
		banner.setStatus(type);//1禁用,0正常
		bannerService.updateEntitie(banner);
		systemService.addLog("禁用启用轮播图：", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return aj;
	}
	
	/**
	 * 禁用启用轮播图
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "getBanners", method = RequestMethod.GET)
	@ResponseBody
	public List<BannerEntity> getBanners() {
		List<BannerEntity> banner = systemService.findByProperty(BannerEntity.class, "deleteFlag", Constant.UN_DELETE);
		return banner;
	}
	
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 * @param upload
	 * @throws Exception
	 */
    @RequestMapping(value="/upload")
    public ModelAndView upload(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam("upload") MultipartFile upload) throws Exception {
       //如果文件不为空，写入上传路径
       if(!upload.isEmpty()) {
    	   PropertiesUtil pro = new PropertiesUtil("sysConfig.properties");
           //上传文件路径
           String path = pro.readProperty("webUploadpath") + "//images//";
           //文件扩展名
           String extendName = FileUtils.getExtend(upload.getOriginalFilename());
           //上传文件名
           String fileName = UUIDGenerator.generate() + extendName + "." + extendName;
           
           File filepath = new File(path,fileName);
           //判断路径是否存在，如果不存在就创建一个
           if (!filepath.getParentFile().exists()) { 
               filepath.getParentFile().mkdirs();
           }
           //将上传文件保存到一个目标文件当中
           upload.transferTo(new File(path + File.separator + fileName));
           
           TSUser curUser = ResourceUtil.getSessionUser();
           BannerEntity banner = new BannerEntity();
           banner.setCrtBy(curUser.getId());
		   banner.setCrtByUserName(curUser.getUserName());
		   banner.setCrtTime(DateUtils.formatDateTime());
		   banner.setDeleteFlag(Constant.UN_DELETE);
		   banner.setStatus(0);
		   banner.setPicPath("sysController/readPic.do?picPath="+fileName);
		   bannerService.save(banner);
       }
       return new ModelAndView("uploadSuccess");
    }
}
