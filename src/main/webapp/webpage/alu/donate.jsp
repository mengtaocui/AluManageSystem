<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>捐赠</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <style type="text/css">
  	input{
  		width: 157px;
  	}
  </style>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="donateController.do?save">
			<input id="id" name="id" type="hidden" value="${donatePage.id }">
			<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<input type="hidden" id="collegeId" name="collegeId" value="${LOCAL_CLINET_USER.collegeId}"/>
				<input type="hidden" id="collegeName" name="collegeName" value="${LOCAL_CLINET_USER.collegeName}"/>
				<tr>
					<td align="right">
						<label class="Validform_label">
							捐赠项目名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" datatype="*1-12"  value="${donatePage.name}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							募捐地点:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="places" name="places" datatype="*"  value="${donatePage.places}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开始时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt easyui-datetimebox" id="startTime" name="startTime" required="required"  value="${donatePage.startTime}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结束时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt easyui-datetimebox" id="endTime" name="endTime" required="required"  value="${donatePage.endTime}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							报名截止时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt easyui-datetimebox" id="applyEndTime" name="applyEndTime" required="required"  value="${donatePage.applyEndTime}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							项目详情:
						</label>
					</td>
					<td class="value">
					<!-- 加载编辑器的容器 -->
					    <textarea id="contentDetail" name="contentDetail" cols="20" rows="2" class="ckeditor">${donatePage.detail}</textarea>
						<input type="hidden" id="detail" onclick="OnSave()" name="detail"/>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
		<!-- 配置文件 -->
	    <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
	    <!-- 实例化编辑器 -->
	    <script type="text/javascript">
		    CKEDITOR.replace('contentDetail');
			function OnSave(){  
		        if(CKEDITOR.instances.contentDetail.getData()==""){  
			        alert("内容不能为空！");  
			        return false;  
		        }else {  
		        	$("#detail").val(CKEDITOR.instances.contentDetail.getData());
		        }  
		    } 
		</script>
 </body>