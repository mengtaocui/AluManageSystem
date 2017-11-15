<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>捐赠记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="donateRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${donateRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							捐赠项目id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="donateId" name="donateId" ignore="ignore"  value="${donateRecordPage.donateId}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							捐赠人姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="donatePersonName" name="donatePersonName" ignore="ignore"  value="${donateRecordPage.donatePersonName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							捐赠人手机号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="donatePersonPhone" name="donatePersonPhone" ignore="ignore"  value="${donateRecordPage.donatePersonPhone}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							处理人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="crtBy" name="crtBy" ignore="ignore"  value="${donateRecordPage.crtBy}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							捐赠时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="crtTime" name="crtTime" ignore="ignore"  value="${donateRecordPage.crtTime}" />
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
						<input class="inputxt" id="lastUpdateBy" name="lastUpdateBy" ignore="ignore"  value="${donateRecordPage.lastUpdateBy}" />
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
						<input class="inputxt" id="lastUpdateTime" name="lastUpdateTime" ignore="ignore"  value="${donateRecordPage.lastUpdateTime}" />
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
						<input class="inputxt" id="deleteFlag" name="deleteFlag" ignore="ignore"  value="${donateRecordPage.deleteFlag}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>