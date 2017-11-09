<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>公告</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style type="text/css">
  	input{
  		width: 157px;
  	}
  </style>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="noticesController.do?save">
			<input id="id" name="id" type="hidden" value="${noticesPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" ignore="ignore"  value="${noticesPage.title}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							过期时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt easyui-datetimebox" readonly="readonly" id="pastDueDate" name="pastDueDate" required="required"  value="${noticesPage.pastDueDate}" />
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
					    <textarea  class="inputxt" id="content" style="width:400px;height:80px" name="content" ignore="ignore" >${noticesPage.content}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
		<script>
			
		</script>
 </body>