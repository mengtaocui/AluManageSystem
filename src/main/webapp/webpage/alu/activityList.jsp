<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="activityList" title="活动" actionUrl="activityController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="活动名称" field="name"   width="120" query="true"></t:dgCol>
   <t:dgCol title="活动地点" field="places"   width="120" query="true"></t:dgCol>
   <t:dgCol title="开始时间" field="startTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="结束时间" field="endTime"   width="120"></t:dgCol>
   <t:dgCol title="报名截止时间" field="applyEndTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="浏览次数" field="browseCount"   width="60"></t:dgCol>
   <t:dgCol title="审核状态" field="checkStatus" replace="等待审核_0,已通过_1,被拒绝_2"   width="70"></t:dgCol>
   <t:dgCol title="创建人" field="crtBy"   width="100"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime"   width="120"></t:dgCol>
   <t:dgCol title="审核人" field="checkBy"   width="100"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="130"></t:dgCol>
   <t:dgFunOpt funname="handleApplyPass(id)"  exp="checkStatus#eq#1" title="查看相册" urlStyle="background-color: #269abc;" urlclass="ace_button"  urlfont="fa-check"></t:dgFunOpt>
   <t:dgFunOpt funname="handleApplyPass(id)" exp="checkStatus#eq#0" title="通过" urlStyle="background-color: #449d44;" urlclass="ace_button"  urlfont="fa-check"></t:dgFunOpt>
   <t:dgFunOpt funname="handleApplyRefuse(id)" exp="checkStatus#eq#0" title="拒绝" urlStyle="background-color: #f0ad4e;" urlclass="ace_button"  urlfont="fa-close"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="activityController.do?del&id={id}" urlclass="ace_button" urlStyle="background-color:#ec4758;"   urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="activityController.do?addorupdate" funname="add" width="900" height="670"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="activityController.do?addorupdate" funname="update" width="900" height="670"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="activityController.do?addorupdate" funname="detail" width="900" height="670"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script>
 function saveObj() {
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
						url: 'activityController.do?handleApply&id='+id+'&type='+type,
						type: 'POST',
						dataType: 'json',
						success: function(data){
							$('#activityList').datagrid('reload');
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