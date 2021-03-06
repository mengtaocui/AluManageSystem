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
			<input type="hidden" id="collegeId" name="collegeId" value="${LOCAL_CLINET_USER.collegeId}"/>
			<input type="hidden" id="collegeName" name="collegeName" value="${LOCAL_CLINET_USER.collegeName}"/>
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
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
						<input class="inputxt" id="name" name="name" datatype="*1-12"  value="${gradePage.name}" />
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
						<input class="inputxt" id="qq" name="qq" datatype="n6-16" ignore="ignore" errormsg="请输入正确信息！"  value="${gradePage.qq}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>