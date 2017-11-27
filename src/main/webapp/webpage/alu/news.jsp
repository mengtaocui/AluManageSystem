<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>新闻</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style type="text/css">
  	.cke_button_icon cke_button__save_icon{
  		display: none !important;
  	}
  </style>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="newsController.do?save">
			<input id="id" name="id" type="hidden" value="${newsPage.id }">
			<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<c:if test="${ empty LOCAL_CLINET_USER.collegeName  }">
					<tr>
						<td align="right"><label class="Validform_label">学院: </label></td>
						<td class="value">
			               <t:dictSelect field="collegeId" id="collegeId"  extendJson="{datatype:'*'}" dictTable="t_college" dictField="id" 
									dictText="name" dictCondition=" where delete_flag=0" defaultVal="${newsPage.collegeId}"  hasLabel="false"></t:dictSelect>
						    <input type="hidden" id="collegeName" name="collegeName"   value="${newsPage.collegeName}" />
			                <span class="Validform_checktip"></span>
			            </td>
					</tr>
				</c:if>
				<tr>
					<td align="right">
					    <c:if test="${ not empty LOCAL_CLINET_USER.collegeName  }">
							<input type="hidden" id="collegeId" name="collegeId" value="${newsPage.collegeId}"/>
						</c:if>
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="newsTypeId" id="newsTypeId" extendJson="{datatype:'*'}" dictTable="t_news_type" dictField="id" 
						dictText="name" dictCondition=" where delete_flag=0"  defaultVal="${newsPage.newsTypeId}"  hasLabel="false"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<input type="hidden" id="newsTypeName" name="newsTypeName" value="${newsPage.newsTypeName}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							模块:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="newsModuleId" id="newsModuleId" extendJson="{datatype:'*'}" dictTable="t_news_module" dictField="id" 
						dictText="name" dictCondition=" where delete_flag=0" defaultVal="${newsPage.newsModuleId}"  hasLabel="false"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<input type="hidden" id="newsModuleName" name="newsModuleName" value="${newsPage.newsModuleName}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							新闻标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" datatype="*1-12"  value="${newsPage.title}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr style="height:460px">
					<td align="right">
						<label class="Validform_label">
							新闻内容:
						</label>
					</td>
					<td class="value">
						<!-- 加载编辑器的容器 -->
					    <textarea id="contentDetail" name="contentDetail" cols="20" rows="2" class="ckeditor">${newsPage.content}</textarea>
						<input type="hidden" id="content" onclick="OnSave()" name="content"/>
					</td>
				</tr>
			</table>
		</t:formvalid>
		<!-- 配置文件 -->
	    <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
	    <!-- 实例化编辑器 -->
	    <script type="text/javascript">
		    CKEDITOR.replace('contentDetail');
			function OnSave(){  
		        if(CKEDITOR.instances.contentDetail.getData()==""){  
			        alert("内容不能为空！");  
			        return false;  
		        }else {  
		        	$("#content").val(CKEDITOR.instances.contentDetail.getData());
		        }  
		    } 
			
			$('#newsModuleId').html('');
			
			
			$.ajax({
				url: 'newsModuleController.do?getByTypeId&typeId=${newsPage.newsTypeId}',
				type: 'GET',
				dataType: 'json',
				success: function(data){
					$('#newsModuleId').html(' ');
					var str = null;
					for(var i=0; i<data.length; i++){
						if(data[i].id == '${newsPage.newsModuleId}')
							str += '<option value="'+data[i].id+'" selected="selected">'+data[i].name+' </option> ';
						str += '<option value="'+data[i].id+'">'+data[i].name+' </option> ';
					}
					$('#newsModuleId').html(str);
				},
				error: function(data){
					tip_old('网络异常，新闻模块加载失败');
				}
			});
			
			$('#newsTypeId').on('change', function(){
				var typeId = $(this).val();
				$.ajax({
					url: 'newsModuleController.do?getByTypeId&typeId='+typeId,
					type: 'GET',
					dataType: 'json',
					success: function(data){
						$('#newsModuleId').html(' ');
						var str = null;
						for(var i=0; i<data.length; i++){
							str += '<option value="'+data[i].id+'">'+data[i].name+' </option> ';
						}
						str = '<option value="">---请选择---</option> '+str;
						$('#newsModuleId').html(str);
					},
					error: function(data){
						tip_old('网络异常，新闻模块加载失败');
					}
				});
			}); 
			if(location.href.indexOf("load=detail")!=-1){
				$(":input").attr("disabled","true");
				//$(":input").attr("style","border:0;border-bottom:1 solid black;background:white;");
			}
	</script>
 </body>