<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="newsList" title="新闻" actionUrl="newsController.do?datagrid"  idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="学院" field="collegeId"  dictionary="t_college,id,name" hidden="true"></t:dgCol>
   <t:dgCol title="新闻类型" field="newsTypeId"   dictionary="t_news_type_view,id,name"  width="120" query="true"></t:dgCol>
   <t:dgCol title="新闻模块" field="newsModuleId"  dictionary="t_news_module,id,name"  width="120" query="true"></t:dgCol>
   <t:dgCol title="新闻标题" field="title"  width="150" query="true"></t:dgCol>
   <t:dgCol title="新闻内容" field="content" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="浏览次数" field="browseCount"   width="70"></t:dgCol>
   <t:dgCol title="审核状态" field="checkStatus" replace="等待审核_0,已通过_1,被拒绝_2" query="true"  width="120"></t:dgCol>
   <t:dgCol title="创建人" field="crtByUserName"   width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime" hidden="true"  width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="最近一次修改人" field="lastUpdateByUserName"   width="120"></t:dgCol>
   <t:dgCol title="最近一次修改时间" field="lastUpdateTime" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="审核人" field="checkByUserName"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="150"></t:dgCol>
   <t:dgFunOpt funname="handleApplyPass(id)" exp="checkStatus#eq#0" operationCode="apply" title="通过" urlStyle="background-color: #449d44;" urlclass="ace_button"  urlfont="fa-check"></t:dgFunOpt>
   <t:dgFunOpt funname="handleApplyRefuse(id)" exp="checkStatus#eq#0" operationCode="refuse" title="拒绝" urlStyle="background-color: #f0ad4e;" urlclass="ace_button"  urlfont="fa-close"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="newsController.do?del&id={id}" urlclass="ace_button" urlStyle="background-color:#ec4758;"    urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="newsController.do?addorupdate" funname="add" width="900" height="600" ></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="newsController.do?addorupdate" funname="update" width="900" height="600"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="newsController.do?addorupdate" funname="detail" width="900" height="600"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
	function saveObj() {
	
        $('#newsTypeName', iframe.document).val($('#newsTypeId', iframe.document).find('option:selected').text());
		$('#newsModuleName', iframe.document).val($('#newsModuleId', iframe.document).find('option:selected').text());
		
		$('#content', iframe.document).click();
		$('#btn_sub', iframe.document).click();
	}
	$('[name="newsModuleId"]').html(' ');
	$('[name="newsModuleId"]').html('<option value="">---请选择---</option> ');
	$('[name="newsTypeId"]').on('change', function(){
		var typeId = $(this).val();
		$.ajax({
			url: 'newsModuleController.do?getByTypeId&typeId='+typeId,
			type: 'GET',
			dataType: 'json',
			success: function(data){
				$('[name="newsModuleId"]').html(' ');
				var str = null;
				for(var i=0; i<data.length; i++){
					str += '<option value="'+data[i].id+'">'+data[i].name+' </option> ';
				}
				str = '<option value="">---请选择---</option> '+str;
				$('[name="newsModuleId"]').html(str);
			},
			error: function(data){
				tip_old('网络异常，新闻模块加载失败');
			}
		});
	});
	
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
						url: 'newsController.do?handleApply&id='+id+'&type='+type,
						type: 'POST',
						dataType: 'json',
						success: function(data){
							$('#newsList').datagrid('reload');
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