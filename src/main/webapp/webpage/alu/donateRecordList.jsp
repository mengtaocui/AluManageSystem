<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="donateRecordList" title="捐赠记录" actionUrl="donateRecordController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="捐赠项目ID" field="donateId" hidden="true"></t:dgCol>
   <t:dgCol title="捐赠项目" style="color:blue"   url="donateController.do?addorupdate&load=detail&id={donateId}&_=1510794388831" field="donateName"   width="120" query="true"></t:dgCol>
   <t:dgCol title="捐赠人" url="classmateController.do?addorupdate&load=detail&name={donatePersonName}&phone={donatePersonPhone}&_=1510794388831" field="donatePersonName"   width="120" query="true"></t:dgCol>
   <t:dgCol title="捐赠人手机号" field="donatePersonPhone"   width="120" query="true"></t:dgCol>
   <t:dgCol title="处理人" field="crtByUserName"   width="120" query="true"></t:dgCol>
   <t:dgCol title="捐赠时间" field="crtTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="最近一次修改人" field="lastUpdateByUserName"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改时间" field="lastUpdateTime"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" urlStyle="background-color:#ec4758;" url="donateRecordController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="donateRecordController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="donateRecordController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="donateRecordController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script>
 /**
  * 创建不带按钮的窗口
  * 
  * @param title
  * @param addurl
  * @param saveurl
  */
 function openwindow(title, url,name, width, height) {
	 width = 900;
	 height = 670;
 	if(width=="100%" || height=="100%"){
 		width = window.top.document.body.offsetWidth;
 		height =window.top.document.body.offsetHeight-100;
 	}

 	gridname=name;
 	if (typeof (width) == 'undefined'&&typeof (height) != 'undefined')
 	{
 		if(typeof(windowapi) == 'undefined'){
 			$.dialog({
 				content: 'url:'+url,
 				zIndex: getzIndex(),
 				title : title,
 				cache:false,
 				lock : true,
 				width: 'auto',
 			    height: height
 			});
 		}else{
 			$.dialog({
 				content: 'url:'+url,
 				zIndex: getzIndex(),
 				title : title,
 				cache:false,
 				parent:windowapi,
 				lock : true,
 				width: 'auto',
 			    height: height
 			});
 		}
 	}
 	if (typeof (height) == 'undefined'&&typeof (width) != 'undefined')
 	{
 		if(typeof(windowapi) == 'undefined'){
 			$.dialog({
 				content: 'url:'+url,
 				title : title,
 				zIndex: getzIndex(),
 				lock : true,
 				width: width,
 				cache:false,
 			    height: 'auto'
 			});
 		}else{
 			$.dialog({
 				content: 'url:'+url,
 				zIndex: getzIndex(),
 				title : title,
 				lock : true,
 				parent:windowapi,
 				width: width,
 				cache:false,
 			    height: 'auto'
 			});
 		}
 	}
 	if (typeof (width) == 'undefined'&&typeof (height) == 'undefined')
 	{
 		if(typeof(windowapi) == 'undefined'){
 			$.dialog({
 				content: 'url:'+url,
 				zIndex: getzIndex(),
 				title : title,
 				lock : true,
 				width: 'auto',
 				cache:false,
 			    height: 'auto'
 			});
 		}else{
 			$.dialog({
 				content: 'url:'+url,
 				zIndex: getzIndex(),
 				title : title,
 				lock : true,
 				parent:windowapi,
 				width: 'auto',
 				cache:false,
 			    height: 'auto'
 			});
 		}
 	}
 	
 	if (typeof (width) != 'undefined'&&typeof (height) != 'undefined')
 	{
 		if(typeof(windowapi) == 'undefined'){
 			$.dialog({
 				width: width,
 			    height:height,
 				content: 'url:'+url,
 				zIndex: getzIndex(),
 				title : title,
 				cache:false,
 				lock : true
 			});
 		}else{
 			$.dialog({
 				width: width,
 			    height:height,
 				content: 'url:'+url,
 				zIndex: getzIndex(),
 				parent:windowapi,
 				title : title,
 				cache:false,
 				lock : true
 			});
 		}
 	}
 }
 	function saveObj() {
		$('#donateName', iframe.document).val($('#donateId', iframe.document).find('option:selected').text());
		$('#btn_sub', iframe.document).click();
	}
 </script>