<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>帖子</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="invitationController.do?save">
			<input id="id" name="id" type="hidden" value="${invitationPage.id }">
			<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<input type="hidden" id="collegeId" name="collegeId" value="${LOCAL_CLINET_USER.collegeId}"/>
				<input type="hidden" id="collegeName" name="collegeName" value="${LOCAL_CLINET_USER.collegeName}"/>
				<tr>
					<td align="right">
						<label class="Validform_label">
							帖子标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" datatype="*1-12"  value="${invitationPage.title}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
						<!-- 加载编辑器的容器 -->
					    <textarea id="contentDetail" name="contentDetail" cols="20" rows="2" class="ckeditor">${invitationPage.content}</textarea>
						<input type="hidden" id="content" onclick="OnSave()" name="content"/>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
		<!-- 配置文件 -->
	    <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
	    <script type="text/javascript">
		    CKEDITOR.replace('contentDetail');
			function OnSave(){  
		        if(CKEDITOR.instances.contentDetail.getData()==""){  
			        alert("内容不能为空！");  
			        return false;  
		        }else {  
		        	$("#content").val(CKEDITOR.instances.contentDetail.getData());
		        }  
		    } 
		</script>
 </body>