<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="donateList" title="捐赠" actionUrl="donateController.do?datagrid" idField="id" fit="true" queryMode="group" onLoadSuccess="loadSuccess">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="学院" field="collegeId"  dictionary="t_college,id,name" query="true"></t:dgCol>
   <t:dgCol title="项目名称" field="name"   width="120" query="true"></t:dgCol>
   <t:dgCol title="募捐地点" field="places"   width="120" query="true"></t:dgCol>
   <t:dgCol title="开始时间" field="startTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="结束时间" field="endTime"   width="120"></t:dgCol>
   <t:dgCol title="报名截止时间" field="applyEndTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="浏览次数" field="browseCount"   width="50"></t:dgCol>
   <t:dgCol title="审核状态" field="checkStatus" replace="等待审核_0,已通过_1,被拒绝_2" query="true"  width="70"></t:dgCol>
   <t:dgCol title="创建人" field="crtByUserName"   width="100"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="审核人" field="checkByUserName"   width="100"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="140"></t:dgCol>
   <t:dgFunOpt funname="handleApplyPass(id)" exp="checkStatus#eq#0" title="通过" urlStyle="background-color: #449d44;" urlclass="ace_button"  urlfont="fa-check"></t:dgFunOpt>
   <t:dgFunOpt funname="handleApplyRefuse(id)" exp="checkStatus#eq#0" title="拒绝" urlStyle="background-color: #f0ad4e;" urlclass="ace_button"  urlfont="fa-close"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="donateController.do?del&id={id}" urlStyle="background-color:#ec4758;" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="donateController.do?addorupdate" funname="add" width="900" height="670"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="donateController.do?addorupdate" funname="update" width="900" height="670"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="donateController.do?addorupdate" funname="detail" width="900" height="670"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <script>
	  function loadSuccess(){
	  	if('${LOCAL_CLINET_USER.collegeName }'){
	  		$($('#donateListForm').find('span')[0]).hide();
	 	   	 	$('td').each(function(){
	 	   	    	if($(this).attr('field') == 'collegeId')
	 	   	    		$(this).remove();
	 	   	    	
	 	   	    	console.log(1);
	 	   	   });
	 	   	   $(window).resize();  
	  	}
	  }
  	function saveObj() {
  		if(!'${LOCAL_CLINET_USER.collegeName }')
 			$('#collegeName', iframe.document).val($('#collegeId', iframe.document).find('option:selected').text());
		$('#detail', iframe.document).click();
		$('#btn_sub', iframe.document).click();
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
						url: 'donateController.do?handleApply&id='+id+'&type='+type,
						type: 'POST',
						dataType: 'json',
						success: function(data){
							$('#donateList').datagrid('reload');
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
 </script>