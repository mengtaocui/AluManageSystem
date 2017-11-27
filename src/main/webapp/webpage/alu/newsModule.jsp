<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>新闻模块</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="newsModuleController.do?save">
			<input id="id" name="id" type="hidden" value="${newsModulePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="newsTypeId" id="newsTypeId" extendJson="{datatype:'*'}" dictTable="t_news_type" dictField="id" 
						dictText="name" dictCondition=" where delete_flag=0" defaultVal="${newsModulePage.newsTypeId}"  hasLabel="false"></t:dictSelect>
						<%-- <input class="inputxt" id="newsTypeId" name="newsTypeId" ignore="ignore"  value="${newsModulePage.newsTypeId}" datatype="n" /> --%>
						<input type="hidden" id="newsTypeName" name="newsTypeName" value="${newsModulePage.newsTypeName}"/>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							模块名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" datatype="*1-12"  value="${newsModulePage.name}" />
						<span class="Validform_checktip">请输入模块名称</span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>