<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="gradeList" title="班级" actionUrl="gradeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="学院" field="collegeId" dictionary="t_college,id,name"  width="120" hidden="true"></t:dgCol>
   <t:dgCol title="年届" field="yearPeriod" dictionary="nianJie"   width="50" query="true"></t:dgCol>
   <t:dgCol title="班级名称" showLen="12" field="name"   width="140" query="true"></t:dgCol>
   <t:dgCol title="班级QQ群" field="qq"   width="120"></t:dgCol>
   <t:dgCol title="人数" field="stuCount"   width="50"></t:dgCol>
   <t:dgCol title="审核状态" field="checkStatus" replace="等待审核_0,已通过_1,被拒绝_2" query="true"   width="70"></t:dgCol>
   <t:dgCol title="创建人" field="crtByUserName"   width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime" hidden="true"  width="120" formatter="yyyy-MM-dd hh:mm:ss"  queryMode="group"></t:dgCol>
   <t:dgCol title="最近一次修改人" field="lastUpdateByUserName"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改时间" field="lastUpdateTime" hidden="true"   width="120"></t:dgCol>
   <t:dgCol title="审核人" field="checkByUserName"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="140"></t:dgCol>
   <t:dgFunOpt funname="handleApplyPass(id)" exp="checkStatus#eq#0" operationCode="apply" title="通过" urlStyle="background-color: #449d44;" urlclass="ace_button"  urlfont="fa-check"></t:dgFunOpt>
   <t:dgFunOpt funname="handleApplyRefuse(id)" exp="checkStatus#eq#0" operationCode="refuse" title="拒绝" urlStyle="background-color: #f0ad4e;" urlclass="ace_button"  urlfont="fa-close"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="gradeController.do?del&id={id}" urlclass="ace_button" urlStyle="background-color:#ec4758;"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="gradeController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="gradeController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="gradeController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <script>
   function saveObj() {
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
						url: 'gradeController.do?handleApply&id='+id+'&type='+type,
						type: 'POST',
						dataType: 'json',
						success: function(data){
							$('#gradeList').datagrid('reload');
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