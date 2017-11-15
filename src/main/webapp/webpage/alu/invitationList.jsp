<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="invitationList" title="帖子" actionUrl="invitationController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="帖子标题" field="title"   width="120"></t:dgCol>
   <t:dgCol title="内容" field="content"   width="120"></t:dgCol>
   <t:dgCol title="浏览次数" field="browseCount"   width="120"></t:dgCol>
   <t:dgCol title="创建人" field="crtBy"   width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改人" field="lastUpdateBy"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改时间" field="lastUpdateTime"   width="120"></t:dgCol>
   <t:dgCol title="审核人" field="checkBy"   width="120"></t:dgCol>
   <t:dgCol title="0未删除，1已删除" field="deleteFlag"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="invitationController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="invitationController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="invitationController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="invitationController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>