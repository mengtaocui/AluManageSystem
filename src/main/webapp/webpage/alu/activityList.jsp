<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="activityList" title="活动" actionUrl="activityController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="活动名称" field="name"   width="120"></t:dgCol>
   <t:dgCol title="活动地点" field="places"   width="120"></t:dgCol>
   <t:dgCol title="活动详情" field="detail"   width="120"></t:dgCol>
   <t:dgCol title="开始时间" field="startTime"   width="120"></t:dgCol>
   <t:dgCol title="结束时间" field="endTime"   width="120"></t:dgCol>
   <t:dgCol title="报名截止时间" field="applyEndTime"   width="120"></t:dgCol>
   <t:dgCol title="浏览次数" field="browseCount"   width="120"></t:dgCol>
   <t:dgCol title="0未审核，1通过审核，未通过" field="status"   width="120"></t:dgCol>
   <t:dgCol title="创建人" field="crtBy"   width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改人" field="lastUpdateBy"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改时间" field="lastUpdateTime"   width="120"></t:dgCol>
   <t:dgCol title="审核人" field="checkBy"   width="120"></t:dgCol>
   <t:dgCol title="0未删除，1已删除" field="deleteFlag"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="activityController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="activityController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="activityController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="activityController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>