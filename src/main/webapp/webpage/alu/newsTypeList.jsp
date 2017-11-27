<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="newsTypeList" title="新闻类型" actionUrl="newsTypeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="类型名称" field="name"   width="120" query="true"></t:dgCol>
   <t:dgCol title="简介" field="detail"  showLen="14"  width="120"></t:dgCol>
   <t:dgCol title="创建人" field="crtByUserName"   width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="最近一次修改人" field="lastUpdateByUserName"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改时间" field="lastUpdateTime"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="newsTypeController.do?del&id={id}" urlclass="ace_button" urlStyle="background-color:#ec4758;"   urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="newsTypeController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="newsTypeController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>