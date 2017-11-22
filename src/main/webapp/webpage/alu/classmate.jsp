<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>校友</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style type="text/css">
  	.detail{
  		width: 590px !important;
    	height: 50px !important;
  	}
  	.header_img{
  		display: inline-block;
	    width: 155px;
	    height: 100px;
	    border: 1px solid #D7D7D7;
	    border-radius: 3Px;
	    vertical-align: bottom;
  	}
  	.choose{
  		display: inline-block;
	    color: blue;
	    font-size: 15px;
	    font-weight: initial;
	    vertical-align: bottom;
  	}
  	#preview{
  		display: inline-block;
  	}
  </style>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="classmateController.do?save">
			<input id="id" name="id" type="hidden" value="${classmatePage.id }">
			<table style="width: 100%;height:600px" cellpadding="0" cellspacing="1" class="formtable">
				<c:if test="${ empty LOCAL_CLINET_USER.collegeName  }">
					<tr>
						<td align="right">
							<label class="Validform_label">
								学院:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="collegeId" id="collegeId"  extendJson="{datatype:'*'}" dictTable="t_college" dictField="id" 
							dictText="name" dictCondition=" where delete_flag=0" defaultVal="${classmatePage.collegeId}"  hasLabel="false"></t:dictSelect>
							<input type="hidden" id="collegeName" name="collegeName"   value="${classmatePage.collegeName}" />
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								年届:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="yearPeriod" id="yearPeriod" extendJson="{datatype:'*'}" typeGroupCode="nianJie"   defaultVal="${classmatePage.yearPeriod}"  hasLabel="false"></t:dictSelect>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								班级:
							</label>
						</td>
						<td class="value">
							<select name="gradeId" datatype="*" id="gradeId"> 
								<option value="" selected="selected">---请选择--- </option>
							</select>
							<span class="Validform_checktip Validform_wrong"></span>
							<input type="hidden" id="gradeName" name="gradeName"  value="${classmatePage.gradeName}" />
						</td>
						<td align="right">
							<label class="Validform_label">
								学号:
							</label>
						</td>
						<td class="value">
							<input class="inputxt" id="stuNo" name="stuNo" ignore="ignore"  value="${classmatePage.stuNo}" />
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单位性质:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="companyNature" defaultVal="${classmatePage.companyNature}" typeGroupCode="dwxz" hasLabel="false" extendJson="{datatype:'*'}"></t:dictSelect>
							<%-- <input class="inputxt" id="companyNature" name="companyNature" ignore="ignore"  value="${classmatePage.companyNature}" /> --%>
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								教育阶段:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="educationStage" defaultVal="${classmatePage.educationStage}" typeGroupCode="jyjd" hasLabel="false" extendJson="{datatype:'*'}"></t:dictSelect>
							<%-- <input class="inputxt" id="educationStage" name="educationStage" ignore="ignore"  value="${classmatePage.companyNature}" /> --%>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
					   <td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
							<input class="inputxt" id="name" name="name" datatype="*"  value="${classmatePage.name}" />
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
						    <input type="radio" name="sex" value="0"
						    	<c:if test="${classmatePage.sex == 0}">checked="checked"</c:if>
						    />男
						    <input type="radio" name="sex" value="1"
						    	<c:if test="${classmatePage.sex == 1}">checked="checked"</c:if>
						    />女
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								电话:
							</label>
						</td>
						<td class="value">
							<input class="inputxt" id="phone" name="phone" ignore="ignore"  value="${classmatePage.phone}" />
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								邮箱:
							</label>
						</td>
						<td class="value">
							<input class="inputxt" id="email" name="email" ignore="ignore"  value="${classmatePage.email}" />
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								头像:
							</label>
						</td>
						<td class="value" colspan="3">
						    
					    	<div id="preview"><img alt="" src="${classmatePage.headPortrait}" class="header_img"></div>
					    	<input type="file" style="display:none" onchange="preview(this)" id="head_file"/>
					    	<a href="javascript:chooseImg()" class="ace_button" style="background-color:#18a689;">  
				 				<i class=" fa fa-refresh"></i>
				 				选择
				 			</a>
				 			<a href="javascript:uploadFile()" class="ace_button" style="background-color:#1a7bb9;">  
					 			<i class=" fa fa-arrow-up"></i>
					 			上传
					 		</a>
							<input type="hidden" id="headPortrait" name="headPortrait" ignore="ignore"  value="${classmatePage.headPortrait}" />
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所在省份:
							</label>
						</td>
						<td class="value">
							 <t:dictSelect field="provinceId" id="provinceId"  extendJson="{datatype:'*'}" dictTable="t_s_region" dictField="id" 
							dictText="name" dictCondition=" where pid=1" defaultVal="${classmatePage.provinceId}"  hasLabel="false"></t:dictSelect>
							<input type="hidden" id="provinceName" name="provinceName"   value="${classmatePage.provinceName}" />
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								所在城市:
							</label>
						</td>
						<td class="value">
							<select name="cityId" datatype="*" id="cityId"> 
								<option value="" selected="selected">---请选择--- </option>
							</select>
							<input type="hidden" id="cityName" name="cityName"   value="${classmatePage.cityName}" />
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								详细地址:
							</label>
						</td>
						<td class="value" colspan="3">
						    <textarea class="inputxt detail" style="margin-top:10px" id="address" name="address" ignore="ignore">${classmatePage.address}</textarea>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								简介:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea class="inputxt detail" id="descri" name="descri">${classmatePage.descri}</textarea>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
				</c:if>
				
				
				<c:if test="${ not empty LOCAL_CLINET_USER.collegeName  }">
					<tr>
						<td align="right">
							<label class="Validform_label">
								头像:
							</label>
						</td>
						<td class="value">
						    
					    	<div id="preview"><img alt="" src="${classmatePage.headPortrait}" class="header_img"></div>
					    	<input type="file" style="display:none" onchange="preview(this)" id="head_file"/>
					    	<a href="javascript:chooseImg()" class="ace_button" style="background-color:#18a689;">  
				 				<i class=" fa fa-refresh"></i>
				 				选择
				 			</a>
				 			<a href="javascript:uploadFile()" class="ace_button" style="background-color:#1a7bb9;">  
					 			<i class=" fa fa-arrow-up"></i>
					 			上传
					 		</a>
							<input type="hidden" id="headPortrait" name="headPortrait" ignore="ignore"  value="${classmatePage.headPortrait}" />
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								年届:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="yearPeriod" id="yearPeriod" extendJson="{datatype:'*'}" typeGroupCode="nianJie"   defaultVal="${classmatePage.yearPeriod}"  hasLabel="false"></t:dictSelect>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								班级:
							</label>
						</td>
						<td class="value">
							<select name="gradeId" datatype="*" id="gradeId"> 
								<option value="" selected="selected">---请选择--- </option>
							</select>
							<span class="Validform_checktip Validform_wrong"></span>
							<input type="hidden" id="gradeName" name="gradeName"  value="${classmatePage.gradeName}" />
						</td>
						<td align="right">
							<label class="Validform_label">
								学号:
							</label>
						</td>
						<td class="value">
							<input class="inputxt" id="stuNo" name="stuNo" ignore="ignore"  value="${classmatePage.stuNo}" />
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单位性质:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="companyNature" defaultVal="${classmatePage.companyNature}" typeGroupCode="dwxz" hasLabel="false" extendJson="{datatype:'*'}"></t:dictSelect>
							<%-- <input class="inputxt" id="companyNature" name="companyNature" ignore="ignore"  value="${classmatePage.companyNature}" /> --%>
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								教育阶段:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="educationStage" defaultVal="${classmatePage.educationStage}" typeGroupCode="jyjd" hasLabel="false" extendJson="{datatype:'*'}"></t:dictSelect>
							<%-- <input class="inputxt" id="educationStage" name="educationStage" ignore="ignore"  value="${classmatePage.companyNature}" /> --%>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
					   <td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
							<input class="inputxt" id="name" name="name" datatype="*"  value="${classmatePage.name}" />
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
						    <input type="radio" name="sex" value="0"
						    	<c:if test="${classmatePage.sex == 0}">checked="checked"</c:if>
						    />男
						    <input type="radio" name="sex" value="1"
						    	<c:if test="${classmatePage.sex == 1}">checked="checked"</c:if>
						    />女
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								电话:
							</label>
						</td>
						<td class="value">
							<input class="inputxt" id="phone" name="phone" ignore="ignore"  value="${classmatePage.phone}" />
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								邮箱:
							</label>
						</td>
						<td class="value">
							<input class="inputxt" id="email" name="email" ignore="ignore"  value="${classmatePage.email}" />
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所在省份:
							</label>
						</td>
						<td class="value">
							 <t:dictSelect field="provinceId" id="provinceId"  extendJson="{datatype:'*'}" dictTable="t_s_region" dictField="id" 
							dictText="name" dictCondition=" where pid=1" defaultVal="${classmatePage.provinceId}"  hasLabel="false"></t:dictSelect>
							<input type="hidden" id="provinceName" name="provinceName"   value="${classmatePage.provinceName}" />
							<span class="Validform_checktip"></span>
						</td>
						<td align="right">
							<label class="Validform_label">
								所在城市:
							</label>
						</td>
						<td class="value">
							<select name="cityId" datatype="*" id="cityId"> 
								<option value="" selected="selected">---请选择--- </option>
							</select>
							<input type="hidden" id="cityName" name="cityName"   value="${classmatePage.cityName}" />
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								详细地址:
							</label>
						</td>
						<td class="value" colspan="3">
						    <textarea class="inputxt detail" style="margin-top:10px" id="address" name="address" ignore="ignore">${classmatePage.address}</textarea>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								简介:
							</label>
						</td>
						<td class="value" colspan="3">
							<textarea class="inputxt detail" id="descri" name="descri">${classmatePage.descri}</textarea>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
				</c:if>
				
			</table>
		</t:formvalid>
  <script>
    $(function(){
    	if('${classmatePage.id }'){
    		//需要对班级赋值
    		initGrade();
    		initCity();
    	}
    	$('#collegeId').on('change', function(){
        	initGrade();
        });
        $('#yearPeriod').on('change', function(){
        	initGrade();
        });
        
        $('#provinceId').on('change', function(){
        	initCity();
		}); 
        
        
        
        if(location.href.indexOf("load=detail")!=-1){
    		$("a").attr("href","javascript:void(0);");
    	}
    })
    
    function initCity(){
    	$.ajax({
			url: 'sysController.do?getCityByPid&pid='+$('#provinceId').val(),
			type: 'GET',
			dataType: 'json',
			success: function(data){
				$('#cityId').html(' ');
				var str = null;
				for(var i=0; i<data.length; i++){
					if("${classmatePage.cityId}" == data[i].id){
						str += '<option selected="selected" value="'+data[i].id+'">'+data[i].name+' </option> ';
					}else{
						str += '<option value="'+data[i].id+'">'+data[i].name+' </option> ';
					}
					
				}
				str = '<option value="">---请选择---</option> '+str;
				$('#cityId').html(str);
			},
			error: function(data){
				tip_old('网络异常，新闻模块加载失败');
			}
		});
    }
    
    
 	//根据学院和年届返回班级
 	function initGrade(){
 		$.ajax({
 			url: 'gradeController.do?getGradeByCollegeIdAndYearPeriod&collegeId='+$('#collegeId').val()+'&yearPeriod='+$('#yearPeriod').val(),
 			type: 'GET',
 			dataType: 'json',
 			success: function(data){
 				$('#gradeId').html('');
 				var str = '<option value="" selected="selected">---请选择--- </option>';
 				if(data && data.length > 0){
 					for(var i=0; i<data.length; i++){
 						if(data[i].id == '${classmatePage.gradeId }'){
 							str += '<option selected="selected" value="'+data[i].id+'">'+data[i].name+'</option>';
 						}else{
 							str += '<option  value="'+data[i].id+'">'+data[i].name+'</option>';
 						}
 					}
 				}
 				$('#gradeId').html(str);
 			},
 			error: function(){
 				
 			}
 		});
 	}
    
    function chooseImg(){
    	$('#head_file').click();
    }
    
    function preview(file) {
		var prevDiv = $('#preview');
		if (file.files && file.files[0]) {
			var reader = new FileReader();
			reader.onload = function(evt) {
				prevDiv.html('<img class="header_img" src="' + evt.target.result + '" />');
			}
			reader.readAsDataURL(file.files[0]);
		} else {
			prevDiv.html('<div class="prevImg" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\''
					+ file.value + '\'"></div>');
		}
	}
    
	function uploadFile(){
		var formData = new FormData();
		formData.append('upload', $('#head_file')[0].files[0]);
		$.ajax({
		    url: 'sysController/upload.do',
		    type: 'POST',
		    cache: false,
		    data: formData,
		    processData: false,
		    contentType: false,
		    dataType:'JSON'
		})
		.done(function(res) {
			try{
				$('#headPortrait').val('sysController/readPic.do?picPath='+res.msg);
				layer.msg('上传成功');
			}catch(err){
				alertTip("文件上传失败，请联系管理员","提示信息");
			}
		})
		.fail(function(res) {
			alertTip("文件上传失败，请联系管理员","提示信息");
		}); 
	}
 </script>
 </body>