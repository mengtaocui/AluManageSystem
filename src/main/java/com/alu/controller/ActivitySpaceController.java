package com.alu.controller;
import java.io.File;
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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
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

import com.alu.common.Constant;
import com.alu.entity.ActivitySpaceEntity;
import com.alu.entity.NewsModuleEntity;
import com.alu.entity.NoticesEntity;
import com.alu.service.ActivitySpaceServiceI;

/**   
 * @Title: Controller
 * @Description: 活动空间
 * @author zhangdaihao
 * @date 2017-11-07 17:16:56
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/activitySpaceController")
public class ActivitySpaceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActivitySpaceController.class);

	@Autowired
	private ActivitySpaceServiceI activitySpaceService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 活动空间列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("alu/activitySpaceList");
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
	public void datagrid(ActivitySpaceEntity activitySpace,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ActivitySpaceEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, activitySpace, request.getParameterMap());
		this.activitySpaceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除活动空间
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ActivitySpaceEntity activitySpace, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		activitySpace = systemService.getEntity(ActivitySpaceEntity.class, activitySpace.getId());
		message = "活动空间删除成功";
		activitySpaceService.delete(activitySpace);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	
	/**
	 * 根据活动Id获取空间内文件
	 * @param activityId
	 * @return
	 */
	@RequestMapping(params = "getFileByActivityId", method = RequestMethod.GET)
	@ResponseBody
	public List<ActivitySpaceEntity> getFileByActivityId(String activityId) {
		
		DetachedCriteria salesDc = DetachedCriteria.forClass(ActivitySpaceEntity.class);
		salesDc.add(Restrictions.eq("activityId", activityId));
		salesDc.add(Restrictions.eq("deleteFlag", Constant.UN_DELETE));
		salesDc.addOrder(Order.desc("crtTime"));
		List<ActivitySpaceEntity> listActivitySpaces= activitySpaceService.findByDetached(salesDc);
		return listActivitySpaces;
	}

	/**
	 * 上传文件
	 * @param request
	 * @param response
	 * @param upload
	 * @throws Exception
	 */
    @RequestMapping(value="/upload")
    @ResponseBody
    public AjaxJson upload(HttpServletRequest request,
    		HttpServletResponse response,
           @RequestParam("upload") MultipartFile upload, @RequestParam("activityId") String activityId) throws Exception {
    	AjaxJson aj = new AjaxJson();
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
           aj.setMsg(fileName);
           
           TSUser curUser = ResourceUtil.getSessionUser();
           ActivitySpaceEntity activitySpace = new ActivitySpaceEntity();
           
           activitySpace.setActivityId(activityId);
           activitySpace.setCheckStatus(Constant.APPLY_WAIT);
           activitySpace.setCrtBy(curUser.getId());
           activitySpace.setCrtByUserName(curUser.getUserName());
           activitySpace.setCrtTime(DateUtils.formatDateTime());
           activitySpace.setDeleteFlag(Constant.UN_DELETE);
           activitySpace.setFilePath(fileName);
           
           activitySpaceService.save(activitySpace);
       }
       
       return aj;
    }
	
    
    /**
	 * 审核文件
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "handleApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson handleApply(String id, Integer type) {
		AjaxJson aj = new AjaxJson();
		ActivitySpaceEntity as = systemService.getEntity(ActivitySpaceEntity.class, id);
		TSUser curUser = ResourceUtil.getSessionUser();
		as.setCheckBy(curUser.getId());
		as.setCheckByUserName(curUser.getUserName());
		as.setCheckStatus(type);
		as.setCheckTime(DateUtils.formatDate());
		activitySpaceService.updateEntitie(as);
		systemService.addLog("审核活动文件", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return aj;
	}
    
    
    
    
	/**
	 * 添加活动空间
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ActivitySpaceEntity activitySpace, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(activitySpace.getId())) {
			message = "活动空间更新成功";
			ActivitySpaceEntity t = activitySpaceService.get(ActivitySpaceEntity.class, activitySpace.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(activitySpace, t);
				activitySpaceService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "活动空间更新失败";
			}
		} else {
			message = "活动空间添加成功";
			activitySpaceService.save(activitySpace);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 活动空间列表页面跳转
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String activityId, HttpServletRequest req) {
		req.setAttribute("activityId", activityId);
		return new ModelAndView("alu/activitySpace");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ActivitySpaceEntity> list() {
		List<ActivitySpaceEntity> listActivitySpaces=activitySpaceService.getList(ActivitySpaceEntity.class);
		return listActivitySpaces;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ActivitySpaceEntity task = activitySpaceService.get(ActivitySpaceEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ActivitySpaceEntity activitySpace, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActivitySpaceEntity>> failures = validator.validate(activitySpace);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		activitySpaceService.save(activitySpace);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = activitySpace.getId();
		URI uri = uriBuilder.path("/rest/activitySpaceController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ActivitySpaceEntity activitySpace) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActivitySpaceEntity>> failures = validator.validate(activitySpace);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		activitySpaceService.saveOrUpdate(activitySpace);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		activitySpaceService.deleteEntityById(ActivitySpaceEntity.class, id);
	}
}
