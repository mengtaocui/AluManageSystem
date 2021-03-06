<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<style>
	select{
		width: 154px !important;
	}
</style>
<t:datagrid name="userList" title="common.operation" actionUrl="userController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group" sortName="createDate,userName" sortOrder="asc,desc">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.username" sortable="false" field="userName" query="true" width="100"></t:dgCol>
	<t:dgCol title="common.real.name" field="realName" query="false" width="100"></t:dgCol>
	<t:dgCol title="学院" sortable="false" field="collegeId" dictionary="t_college,id,name" query="true" width="100"></t:dgCol>
	<t:dgCol title="common.createby" field="createBy"  hidden="true" width="100"></t:dgCol>
	<t:dgCol title="common.createtime" field="createDate" formatter="yyyy-MM-dd" hidden="false"></t:dgCol>
	<t:dgCol title="common.updateby" field="updateBy" hidden="true"></t:dgCol>
	<t:dgCol title="common.updatetime" field="updateDate" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
	<t:dgCol title="common.status" sortable="true" field="status" width="100" replace="common.active_1,common.inactive_0,super.admin_-1" ></t:dgCol>
	
	<t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
	<t:dgFunOpt funname="deleteDialog(id)" title="common.delete" urlclass="ace_button"  urlfont="fa-trash-o"></t:dgFunOpt>
	<!-- update-begin--Author:LiShaoQing  Date:20170802 for 新增页面调高高度-------------------- -->
	<t:dgToolBar title="common.add.param" langArg="common.user" icon="icon-add" url="userController.do?addorupdate" funname="add" height="420"></t:dgToolBar>
	<!-- update-end--Author:LiShaoQing  Date:20170802 for 新增页面调高高度-------------------- -->
	<t:dgToolBar title="common.edit.param" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar>
	<t:dgToolBar title="common.password.reset" icon="icon-edit" url="userController.do?changepasswordforuser" funname="update"></t:dgToolBar>
	<t:dgToolBar title="common.lock.user" icon="icon-edit" url="userController.do?lock&lockvalue=0" funname="lockObj"></t:dgToolBar>
	<t:dgToolBar title="common.unlock.user" icon="icon-edit" url="userController.do?lock&lockvalue=1" funname="unlockObj"></t:dgToolBar>
</t:datagrid>
<script>
    $(function() {
        var datagrid = $("#userListtb");
		datagrid.find("div[name='searchColums']").find("form#userListForm").append($("#realNameSearchColums div[name='searchColumsRealName']").html());
		$("#realNameSearchColums").html('');
        datagrid.find("div[name='searchColums']").find("form#userListForm").append($("#tempSearchColums div[name='searchColums']").html());
        $("#tempSearchColums").html('');
	});
</script>
<%-- <div id="realNameSearchColums" style="display: none;">
	<div name="searchColumsRealName">
		<t:userSelect hasLabel="true" selectedNamesInputId="realName" windowWidth="1000px" windowHeight="600px" title="用户名称"></t:userSelect>
	</div>
</div>
<div id="tempSearchColums" style="display: none;">
    <div name="searchColums">
       <t:departSelect hasLabel="true" selectedNamesInputId="orgNames"></t:departSelect>
    </div>
</div> --%>
<script type="text/javascript">
function deleteDialog(id){
	var url = "userController.do?deleteDialog&id=" + id;
	createwindow("删除模式", url, 200, 100);
}
function lockObj(title,url, id) {

	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
		return;
	}
		url += '&id='+rowsData[0].id;

	$.dialog.confirm('<t:mutiLang langKey="common.lock.user.tips"/>', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}
function unlockObj(title,url, id) {
	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
		return;
	}
		url += '&id='+rowsData[0].id;

	$.dialog.confirm('<t:mutiLang langKey="common.unlock.user.tips"/>', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}


function lockuploadify(url, id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
			var msg = d.msg;
				tip(msg);
				reloadTable();
			}
		}
	});
}

function saveObj() {
	$('#collegeName', iframe.document).val($('#collegeId', iframe.document).find('option:selected').text());
	$('#btn_sub', iframe.document).click();
}

</script>
