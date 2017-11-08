<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>新闻</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="newsController.do?save">
			<input id="id" name="id" type="hidden" value="${newsPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="newsTypeId" name="newsTypeId" ignore="ignore"  value="${newsPage.newsTypeId}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							newsModuleId:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="newsModuleId" name="newsModuleId" ignore="ignore"  value="${newsPage.newsModuleId}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							新闻标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" ignore="ignore"  value="${newsPage.title}" />
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
						<input class="inputxt" id="content" name="content" ignore="ignore"  value="${newsPage.content}" />
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
						<input class="inputxt" id="browseCount" name="browseCount" ignore="ignore"  value="${newsPage.browseCount}" datatype="n" />
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
						<input class="inputxt" id="crtBy" name="crtBy" ignore="ignore"  value="${newsPage.crtBy}" />
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
						<input class="inputxt" id="crtTime" name="crtTime" ignore="ignore"  value="${newsPage.crtTime}" />
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
						<input class="inputxt" id="lastUpdateBy" name="lastUpdateBy" ignore="ignore"  value="${newsPage.lastUpdateBy}" />
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
						<input class="inputxt" id="lastUpdateTime" name="lastUpdateTime" ignore="ignore"  value="${newsPage.lastUpdateTime}" />
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
						<input class="inputxt" id="checkBy" name="checkBy" ignore="ignore"  value="${newsPage.checkBy}" />
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
						<input class="inputxt" id="deleteFlag" name="deleteFlag" ignore="ignore"  value="${newsPage.deleteFlag}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>