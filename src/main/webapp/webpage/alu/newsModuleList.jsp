<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <t:datagrid name="newsModuleList" title="模块管理" actionUrl="newsModuleController.do?datagrid" 
   idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="新闻类型" field="newsTypeId"   dictionary="t_news_type_view,id,name"  width="120" query="true"></t:dgCol>
   <t:dgCol title="模块名称" field="name"   width="120" query="true"></t:dgCol>
   <t:dgCol title="创建人" field="crtByUserName"   width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime" width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="最近一次修改人" field="lastUpdateByUserName"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改时间" field="lastUpdateTime"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="newsModuleController.do?del&id={id}" urlclass="ace_button" urlStyle="background-color:#ec4758;"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="newsModuleController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="newsModuleController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:datagrid>
 <script type="text/javascript">
	function saveObj() {
		$('#newsTypeName', iframe.document).val($('#newsTypeId', iframe.document).find('option:selected').text());
		$('#btn_sub', iframe.document).click();
	}
</script>
