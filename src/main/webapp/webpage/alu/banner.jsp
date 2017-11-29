<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>登录页轮播图</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow: hidden" scroll="no">
 
  <form  enctype="multipart/form-data" method="post" id="bannerForm" >
			<%-- <input id="id" name="id" type="hidden" value="${bannerPage.id }"> --%>
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td class="value">
					    <input type="file" name="upload" id="imgFile"/>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
		$('#bannerList', window.parent.document).datagrid('reload');
			function submitForm(){
				$('#bannerForm').form('submit',{
					url: 'bannerController/upload.do',
					data: $("#bannerForm").serialize(),
					onSubmit:function(){
						tip_old('上传中');
						return true;
					},
					success:function(data){
						data  = $.parseJSON(data);
					    if(data && data.msg == '上传成功'){
					    	tip_old('上传成功');
					    }
					}
				});
			}
		</script>
 </body>