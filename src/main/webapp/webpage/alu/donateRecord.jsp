<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>捐赠记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style type="text/css">
  	.detail{
  		width: 450px !important;
    	height: 50px !important;
  	}
  </style>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="donateRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${donateRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<c:if test="${ empty LOCAL_CLINET_USER.collegeName  }">
					<tr>
						<td align="right"><label class="Validform_label">学院: </label></td>
						<td class="value">
			               <t:dictSelect field="collegeId" id="collegeId"  extendJson="{datatype:'*'}" dictTable="t_college" dictField="id" 
									dictText="name" dictCondition=" where delete_flag=0" defaultVal="${donateRecordPage.collegeId}"  hasLabel="false"></t:dictSelect>
						    <input type="hidden" id="collegeName" name="collegeName"   value="${donateRecordPage.collegeName}" />
			                <span class="Validform_checktip"></span>
			            </td>
					</tr>
				</c:if>
				<tr>
					<td align="right">
					    <c:if test="${ not empty LOCAL_CLINET_USER.collegeName  }">
							<input type="hidden" id="collegeId" name="collegeId" value="${donateRecordPage.collegeId}"/>
						</c:if>
						<label class="Validform_label">
							捐赠项目:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="donateId" id="donateId" extendJson="{datatype:'*'}" dictTable="t_donate" dictField="id" 
						dictText="name" dictCondition=" where delete_flag=0"  defaultVal="${donateRecordPage.donateId}"  hasLabel="false"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<input type="hidden" id="donateName" name="donateName" value="${donateRecordPage.donateName}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							捐赠人姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="donatePersonName" name="donatePersonName" datatype="*1-12" value="${donateRecordPage.donatePersonName}" />
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
						<input class="inputxt" id="donatePersonPhone" name="donatePersonPhone" datatype="m" ignore="ignore"  value="${donateRecordPage.donatePersonPhone}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						<textarea class="inputxt detail" id="remark" name="remark">${donateRecordPage.remark}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>