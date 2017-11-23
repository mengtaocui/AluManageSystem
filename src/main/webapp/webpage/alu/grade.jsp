<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>班级</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="gradeController.do?save">
			<input id="id" name="id" type="hidden" value="${gradePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<c:if test="${ empty LOCAL_CLINET_USER.collegeName  }">
					<tr>
						<td align="right"><label class="Validform_label">学院: </label></td>
						<td class="value">
			               <t:dictSelect field="collegeId" id="collegeId"  extendJson="{datatype:'*'}" dictTable="t_college" dictField="id" 
									dictText="name" dictCondition=" where delete_flag=0" defaultVal="${classmatePage.collegeId}"  hasLabel="false"></t:dictSelect>
						    <input type="hidden" id="collegeName" name="collegeName"   value="${classmatePage.collegeName}" />
			                <span class="Validform_checktip"></span>
			            </td>
					</tr>
				</c:if>
				<tr>
					<td align="right">
						<label class="Validform_label">
							年届:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="yearPeriod" id="yearPeriod" extendJson="{datatype:'*'}" typeGroupCode="nianJie"   defaultVal="${gradePage.yearPeriod}"  hasLabel="false"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							班级名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" datatype="*"  value="${gradePage.name}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							班级QQ群:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="qq" name="qq" ignore="ignore"  value="${gradePage.qq}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>