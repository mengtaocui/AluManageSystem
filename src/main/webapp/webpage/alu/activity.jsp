<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>活动</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="activityController.do?save">
			<input id="id" name="id" type="hidden" value="${activityPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" ignore="ignore"  value="${activityPage.name}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动地点:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="places" name="places" ignore="ignore"  value="${activityPage.places}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动详情:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="detail" name="detail" ignore="ignore"  value="${activityPage.detail}" />
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
						<input class="inputxt" id="startTime" name="startTime" ignore="ignore"  value="${activityPage.startTime}" />
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
						<input class="inputxt" id="endTime" name="endTime" ignore="ignore"  value="${activityPage.endTime}" />
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
						<input class="inputxt" id="applyEndTime" name="applyEndTime" ignore="ignore"  value="${activityPage.applyEndTime}" />
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
						<input class="inputxt" id="browseCount" name="browseCount" ignore="ignore"  value="${activityPage.browseCount}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							0未审核，1通过审核，未通过:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="status" name="status" ignore="ignore"  value="${activityPage.status}" datatype="n" />
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
						<input class="inputxt" id="crtBy" name="crtBy" ignore="ignore"  value="${activityPage.crtBy}" />
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
						<input class="inputxt" id="crtTime" name="crtTime" ignore="ignore"  value="${activityPage.crtTime}" />
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
						<input class="inputxt" id="lastUpdateBy" name="lastUpdateBy" ignore="ignore"  value="${activityPage.lastUpdateBy}" />
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
						<input class="inputxt" id="lastUpdateTime" name="lastUpdateTime" ignore="ignore"  value="${activityPage.lastUpdateTime}" />
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
						<input class="inputxt" id="checkBy" name="checkBy" ignore="ignore"  value="${activityPage.checkBy}" />
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
						<input class="inputxt" id="deleteFlag" name="deleteFlag" ignore="ignore"  value="${activityPage.deleteFlag}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>