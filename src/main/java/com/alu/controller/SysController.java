package com.alu.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
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
@RequestMapping("/sysController")
public class SysController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SysController.class);


	/**
	 * ckEditor上传文件
	 * @param request
	 * @param response
	 * @param upload
	 * @throws Exception
	 */
    @RequestMapping(value="/uploadByCkEditor")
    public void upload(HttpServletRequest request,
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
           PrintWriter out = response.getWriter();   
           String callback =request.getParameter("CKEditorFuncNum");   
           out.println("<script type=\"text/javascript\">");  
           out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" +"sysController/readPic.do?picPath="+ fileName + "','')");   
           out.println("</script>"); 
       }
    }
    
    
    
    /**
	 * ckEditor上传文件
	 * @param request
	 * @param response
	 * @param upload
	 * @throws Exception
	 */
    @RequestMapping(value="/upload")
    @ResponseBody
    public AjaxJson uploadFile(HttpServletRequest request,
    		HttpServletResponse response,
           @RequestParam("upload") MultipartFile upload) throws Exception {
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
       }
       return aj;
    }
    
    /**
     * 通过url请求返回图像的字节流
     * @param cateogry
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value="readPic")
    public void getIcon(String picPath,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        if(StringUtil.isEmpty(picPath)) {
        	picPath = "";
        }
 	   PropertiesUtil pro = new PropertiesUtil("sysConfig.properties");
       //上传文件路径
        String path = pro.readProperty("webUploadpath") + "//images//";
       
        String fileName = path + picPath;
        FileInputStream fis = null;  
        OutputStream os = null;  
        try {  
            fis = new FileInputStream(fileName);  
            os = response.getOutputStream();  
            int count = 0;  
            byte[] buffer = new byte[1024 * 8];  
            while ((count = fis.read(buffer)) != -1) {  
                os.write(buffer, 0, count);  
                os.flush();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try {  
            fis.close();  
            os.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
}
