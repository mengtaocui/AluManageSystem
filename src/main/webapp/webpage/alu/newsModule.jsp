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
							类型id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="newsTypeId" name="newsTypeId" ignore="ignore"  value="${newsModulePage.newsTypeId}" datatype="n" />
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
						<input class="inputxt" id="name" name="name" ignore="ignore"  value="${newsModulePage.name}" />
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
						<input class="inputxt" id="crtBy" name="crtBy" ignore="ignore"  value="${newsModulePage.crtBy}" />
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
						<input class="inputxt" id="crtTime" name="crtTime" ignore="ignore"  value="${newsModulePage.crtTime}" />
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
						<input class="inputxt" id="lastUpdateBy" name="lastUpdateBy" ignore="ignore"  value="${newsModulePage.lastUpdateBy}" />
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
						<input class="inputxt" id="lastUpdateTime" name="lastUpdateTime" ignore="ignore"  value="${newsModulePage.lastUpdateTime}" />
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
						<input class="inputxt" id="deleteFlag" name="deleteFlag" ignore="ignore"  value="${newsModulePage.deleteFlag}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>