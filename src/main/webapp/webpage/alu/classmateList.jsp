<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="classmateList" title="校友" actionUrl="classmateController.do?datagrid" idField="id" fit="true" queryMode="group" onLoadSuccess="loadSuccess">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="头像" field="headPortrait" image="true" imageSize="60,60" width="80"></t:dgCol>
   <t:dgCol title="姓名" field="name"   width="70" query="true"></t:dgCol>
   <t:dgCol title="性别" field="sex"   dictionary="sex" width="40" ></t:dgCol>
   <t:dgCol title="电话" field="phone"   width="70"></t:dgCol>
   <t:dgCol title="省份" field="provinceId"  dictionary="t_s_region_view,id,name"  query="true"></t:dgCol>
   <t:dgCol title="城市" field="cityId"  dictionary="t_s_region,id,name"   query="true"></t:dgCol>
   <t:dgCol title="学院" field="collegeId"  dictionary="t_college,id,name"  width="120" query="true"></t:dgCol>
   <t:dgCol title="年届" field="yearPeriod" dictionary="nianJie"   width="50" query="true"></t:dgCol>
   <t:dgCol title="班级" field="gradeId"   dictionary="t_grade,id,name"  width="260" query="true"></t:dgCol>
   <t:dgCol title="学号" field="stuNo"  hidden="true"   width="120" ></t:dgCol>
   <t:dgCol title="教育阶段" field="educationStage" hidden="true"    width="120" ></t:dgCol>
   <t:dgCol title="单位性质" field="companyNature"  hidden="true"   width="120" ></t:dgCol>
   <t:dgCol title="邮箱" field="email" hidden="true"   width="120" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="classmateController.do?del&id={id}" urlStyle="background-color:#ec4758;"  urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="classmateController.do?addorupdate" funname="add" width="880" height="630"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="classmateController.do?addorupdate" funname="update" width="880" height="630"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="classmateController.do?addorupdate" funname="detail" width="880" height="630"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script>
	 $(function(){
	 	$('select[name="collegeId"]').on('change', function(){
	     	initGrade();
	     });
	     $('select[name="yearPeriod"]').on('change', function(){
	     	initGrade();
	     });
	     
	    $('[name="cityId"]').html(' ');
	 	$('[name="cityId"]').html('<option value="">---请选择---</option> ');
	 	$('[name="provinceId"]').on('change', function(){
	 		var typeId = $(this).val();
	 		$.ajax({
	 			url: 'sysController.do?getCityByPid&pid='+typeId,
	 			type: 'GET',
	 			dataType: 'json',
	 			success: function(data){
	 				$('[name="cityId"]').html(' ');
	 				var str = null;
	 				for(var i=0; i<data.length; i++){
	 					str += '<option value="'+data[i].id+'">'+data[i].name+' </option> ';
	 				}
	 				str = '<option value="">---请选择---</option> '+str;
	 				$('[name="cityId"]').html(str);
	 			},
	 			error: function(data){
	 				tip_old('网络异常，新闻模块加载失败');
	 			}
	 		});
	 	});
	     
	     
	 })
 	function saveObj() {
		if(!'${LOCAL_CLINET_USER.collegeName }')
			$('#collegeName', iframe.document).val($('#collegeId', iframe.document).find('option:selected').text());
		
		$('#gradeName', iframe.document).val($('#gradeId', iframe.document).find('option:selected').text());
		$('#provinceName', iframe.document).val($('#provinceId', iframe.document).find('option:selected').text());
		$('#cityName', iframe.document).val($('#cityId', iframe.document).find('option:selected').text());
		$('#btn_sub', iframe.document).click();
	}
 	//根据学院和年届返回班级
 	function initGrade(){
 		$.ajax({
 			url: 'gradeController.do?getGradeByCollegeIdAndYearPeriod&collegeId='+$('select[name="collegeId"]').val()+'&yearPeriod='+$('select[name="yearPeriod"]').val(),
 			type: 'GET',
 			dataType: 'json',
 			success: function(data){
 				$('select[name="gradeId"]').html('');
 				var str = '<option value="" selected="selected">---请选择--- </option>';
 				if(data && data.length > 0){
 					for(var i=0; i<data.length; i++){
						str += '<option  value="'+data[i].id+'">'+data[i].name+'</option>';
 					}
 				}
 				$('select[name="gradeId"]').html(str);
 			},
 			error: function(){
 				
 			}
 		});
 	}
 	
 	function loadSuccess(){
    	if('${LOCAL_CLINET_USER.collegeName }'){
    		$($('#classmateList').find('span')[0]).hide();
   	   	 	$('td').each(function(){
   	   	    	if($(this).attr('field') == 'collegeId')
   	   	    		$(this).remove();
   	   	   });
   	   	   $(window).resize();  
    	}
    }
 </script>