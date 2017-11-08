<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>公告</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="noticesController.do?save">
			<input id="id" name="id" type="hidden" value="${noticesPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							新闻标题:
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
							新闻内容:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="content" name="content" ignore="ignore"  value="${noticesPage.content}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							浏览次数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="browseCount" name="browseCount" ignore="ignore"  value="${noticesPage.browseCount}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="crtBy" name="crtBy" ignore="ignore"  value="${noticesPage.crtBy}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="crtTime" name="crtTime" ignore="ignore"    value="<fmt:formatDate value='${noticesPage.crtTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最近一次修改人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="lastUpdateBy" name="lastUpdateBy" ignore="ignore"  value="${noticesPage.lastUpdateBy}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最近一次修改时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="lastUpdateTime" name="lastUpdateTime" ignore="ignore"    value="<fmt:formatDate value='${noticesPage.lastUpdateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							审核人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="checkBy" name="checkBy" ignore="ignore"  value="${noticesPage.checkBy}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							0未删除，1已删除:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="deleteFlag" name="deleteFlag" ignore="ignore"  value="${noticesPage.deleteFlag}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>