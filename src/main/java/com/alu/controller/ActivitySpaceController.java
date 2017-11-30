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
import org.jeecgframework.core.util.BrowserUtils;
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

import com.alu.common.AuTools;
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
		activitySpace.setActivityName(AuTools.createLikeStr(activitySpace.getActivityName()));
		
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
		message = "活动文件删除成功";
		activitySpace.setDeleteFlag(Constant.IS_DELETE);
		activitySpaceService.updateEntitie(activitySpace);
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
           @RequestParam("upload") MultipartFile upload, @RequestParam("activityId") String activityId, @RequestParam("activityName") String activityName) throws Exception {
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
           activitySpace.setCollegeId(curUser.getCollegeId());
           activitySpace.setCollegeName(curUser.getCollegeName());
           activitySpace.setActivityId(activityId);
           activitySpace.setActivityName(new String(activityName.getBytes("iso-8859-1"), "utf-8"));
           activitySpace.setCheckStatus(Constant.APPLY_WAIT);
           activitySpace.setCrtBy(curUser.getId());
           activitySpace.setCrtByUserName(curUser.getUserName());
           activitySpace.setCrtTime(DateUtils.formatDateTime());
           activitySpace.setDeleteFlag(Constant.UN_DELETE);
           activitySpace.setFilePath("sysController/readPic.do?picPath="+fileName);
           
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
	public ModelAndView addorupdate(String activityId, String activityName, HttpServletRequest req) {
		req.setAttribute("activityId", activityId);
		req.setAttribute("activityName", activityName);
		System.out.println(BrowserUtils.isIE(req)+"*******************************************");
		return new ModelAndView("alu/activitySpace");
	}
}
