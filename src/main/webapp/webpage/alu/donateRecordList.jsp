<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="donateRecordList" title="捐赠记录" actionUrl="donateRecordController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="捐赠项目" field="donateName"   width="120" query="true"></t:dgCol>
   <t:dgCol title="捐赠人姓名" field="donatePersonName"   width="120" query="true"></t:dgCol>
   <t:dgCol title="捐赠人手机号" field="donatePersonPhone"   width="120" query="true"></t:dgCol>
   <t:dgCol title="处理人" field="crtByUserName"   width="120" query="true"></t:dgCol>
   <t:dgCol title="捐赠时间" field="crtTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="最近一次修改人" field="lastUpdateBy"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改时间" field="lastUpdateTime"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="donateRecordController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="donateRecordController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="donateRecordController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="donateRecordController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>