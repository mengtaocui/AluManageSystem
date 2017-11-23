<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>登录页轮播图</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 
  <form  enctype="multipart/form-data" method="post"  action="bannerController.do?upload">
			<%-- <input id="id" name="id" type="hidden" value="${bannerPage.id }"> --%>
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							图片:
						</label>
					</td>
					<td class="value">
					     <input type="file" name="upload"/>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<%-- <tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
					     <input type="radio" name="status" value="0"
						    	<c:if test="${bannerPage.status == 0}">checked="checked"</c:if>
						    />启用
						    <input type="radio" name="status" value="1"
						    	<c:if test="${bannerPage.status == 1}">checked="checked"</c:if>
						    />禁用
						<span class="Validform_checktip"></span>
					</td>
				</tr> --%>
				<tr>
					<td align="right">
						 <input type="submit"/>
					</td>
				</tr>
			</table>
		</form>
 </body>