<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>登录页轮播图</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow: hidden" scroll="no">
 
  <form  enctype="multipart/form-data" method="post" id="bannerForm" action="bannerController/upload.do">
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
			function submitForm(){
				$('#bannerForm').submit();
				window.close();
			}
		</script>
 </body>