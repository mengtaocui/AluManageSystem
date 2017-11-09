<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>新闻类型</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="newsTypeController.do?save">
			<input id="id" name="id" type="hidden" value="${newsTypePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name"  value="${newsTypePage.name}" datatype="*"/>
						<span class="Validform_checktip">请输入类型名称</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							简介:
						</label>
					</td>
					<td class="value">
					    <textarea rows="20" cols="20" class="inputxt" id="detail" name="detail" style="width:292px;height:46px;">${newsTypePage.detail}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>