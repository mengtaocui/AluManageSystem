<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="activitySpaceList" title="活动空间" actionUrl="activitySpaceController.do?datagrid" idField="id" fit="true" queryMode="group" onLoadSuccess="loadSuccess">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="活动ID" field="activityId"   hidden="true"></t:dgCol>
   <t:dgCol title="学院" field="collegeId"  dictionary="t_college,id,name" query="true"></t:dgCol>
   <t:dgCol title="活动" query="true" url="activityController.do?addorupdate&load=detail&id={activityId}&_=1510794388831" field="activityName"   width="120"></t:dgCol>
   <t:dgCol title="照片" field="filePath" image="true"   width="120" imageSize="60,60"></t:dgCol>
   <t:dgCol title="审核状态" query="true" field="checkStatus" replace="等待审核_0,已通过_1,被拒绝_2"   width="120"></t:dgCol>
   <t:dgCol title="审核人" field="checkByUserName"   width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt funname="handleApplyPass(id)" exp="checkStatus#eq#0" title="通过" urlStyle="background-color: #449d44;" urlclass="ace_button"  urlfont="fa-check"></t:dgFunOpt>
   <t:dgFunOpt funname="handleApplyRefuse(id)" exp="checkStatus#eq#0" title="拒绝" urlStyle="background-color: #f0ad4e;" urlclass="ace_button"  urlfont="fa-close"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="activitySpaceController.do?del&id={id}" urlclass="ace_button" urlStyle="background-color:#ec4758;" urlfont="fa-trash-o"/>
   <%-- <t:dgToolBar title="查看" icon="icon-search" url="activitySpaceController.do?addorupdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script>
 function loadSuccess(){
 	if('${LOCAL_CLINET_USER.collegeName }'){
 		$($('#activitySpaceListForm').find('span')[0]).hide();
	   	 	$('td').each(function(){
	   	    	if($(this).attr('field') == 'collegeId')
	   	    		$(this).remove();
	   	    	
	   	    	console.log(1);
	   	   });
	   	   $(window).resize();  
 	}
 }
 function handleApplyPass(id){
		handleApply(id, 1, '通过');
	}
	
	function handleApplyRefuse(id){
		handleApply(id, 2, '拒绝');
	}
	
	//处理审核
	function handleApply(id, type, msg){
		layer.open({
			title:'审核确认',
			content:'确定'+msg+'吗',
			icon:7,
			yes:function(index){
				if(id && type){
					$.ajax({
						url: 'activitySpaceController.do?handleApply&id='+id+'&type='+type,
						type: 'POST',
						dataType: 'json',
						success: function(data){
							$('#activitySpaceList').datagrid('reload');
						},
						error: function(data){
							
						}
					});
				}
				layer.close(index);
			},
			btn:['确定','取消'],
			btn2:function(index){
				layer.close(index);
			}
		});
	}
	
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
 </script>