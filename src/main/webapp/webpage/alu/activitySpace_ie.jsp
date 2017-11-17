<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>活动空间</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style>
  	.main-wrap{
  		padding-left: 50px;
    	padding-top: 20px;
  	}
  	.box_wrap{
  	    position: relative;
  		width: 190px;
	    height: 190px;
	    border: 1px solid #ccc;
	    display: inline-block;
	    border-radius: 4px;
	    margin-bottom: 5px;
	    margin-right: 5px;
	    vertical-align: top;
  	}
  	.box_shade{
  		position: absolute;
	    top: 0px;
	    left: 0px;
	    width: 100%;
	    height: 100%;
	    background-color: rgba(0,0,0,0.7);
  	}
  	.check_font{
  		display: block;
	    color: #FFFFFF;
	    font-size: 18px;
	    margin-top: 40%;
	    text-align: center;
  	}
  	.del_a{
 		position: absolute;
	    top: 2px;
	    right: 2px;
        z-index: 1;
  	}
  	.upload_wrap{
  		position: relative;
  		height: 100%;
  	}
  	.upload_icon{
  		cursor: pointer;
  	}
  	.btn-wrap{
  		position: absolute;
  		bottom: 20px;
    	left: 27px;
  	}
  	#preview{
  		width: 200px;
    	height: 100px;
    	text-align: center;
  	}
 	#preview img{
  		max-width: 100%;
    	max-height: 100%;
    	margin-top: 25px;
    	margin-left: -15px;
  	}
  	#file{
  		display: none;
  	}
  </style>
 </head>
 <body style="overflow-x: hidden" scroll="no">
 
 	<div class="main-wrap">
		<div class="box_wrap" style="float:left;margin-right:3px">
			<div class="upload_wrap">
		 		<div id="preview">
		 			<img alt="" class="upload_icon" onclick="chooseFile()" src="images/uploadIcon.png">
		 		</div>  
		 		<div class="btn-wrap">
			 		<input type="file" onchange="preview(this)" id="file"/> 
			 		<a href="javascript:chooseFile()" class="ace_button"  style="background-color:#18a689;">  
			 			<i class=" fa fa-refresh"></i>
			 			重新选择
			 		</a>
			 		<a href="javascript:uploadFile()" class="ace_button" style="background-color:#1a7bb9;">  
			 			<i class=" fa fa-arrow-up"></i>
			 			上传
			 		</a>
		 		</div>
	 		</div>
		</div>
		<div id="content">
			
		</div>
	</div>
	<script type="text/javascript">
		
		function chooseFile(){
			$('#file').click();
		}
			
	    initFileArea();
		function preview(file) {
			var prevDiv = $('#preview');
			if (file.files && file.files[0]) {
				var reader = new FileReader();
				reader.onload = function(evt) {
					prevDiv.html('<img src="' + evt.target.result + '" />');
				}
				reader.readAsDataURL(file.files[0]);
			} else {
				prevDiv.html('<div class="prevImg" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\''
						+ file.value + '\'"></div>');
			}
		}
		
		function uploadFile(){
			var formData = new FormData();
			formData.append('upload', $('#file')[0].files[0]);
			formData.append('activityName', '${activityName}');
			formData.append('activityId', '${activityId}');
			$.ajax({
			    url: 'activitySpaceController/upload.do',
			    type: 'POST',
			    cache: false,
			    data: formData,
			    processData: false,
			    contentType: false,
			    dataType:'JSON'
			})
			.done(function(res) {
				try{
					initFileArea();
					$('#preview').html('<img alt="" class="upload_icon" onclick="chooseFile()" src="images/uploadIcon.png">');
					layer.msg('上传成功');
				}catch(err){
					alertTip("文件上传失败，请联系管理员","提示信息");
				}
			})
			.fail(function(res) {
				alertTip("文件上传失败，请联系管理员","提示信息");
			}); 
		}
		
		
		function initFileArea(){
			$.ajax({
			    url: 'activitySpaceController.do?getFileByActivityId&activityId=${activityId}',
			    type: 'GET',
			    dataType:'JSON',
			    success: function(data){
			    	if(data){
			    		var str = '';
			    		$('#content').html(' ');
			    		var shadeStr = '';
			    		
			    		for(var i=0; i<data.length; i++){
			    			shadeStr = '';
			    			if(data[i].checkStatus == 2){
			    				shadeStr = '<div class="box_shade">'+
						   			      	 '<span class="check_font" style="color: #d58512;">审核未通过</span>'+
						  			       '</div>';
			    			}
			    			if(data[i].checkStatus == 0){
			    				shadeStr = '<div class="box_shade">'+
						   			      	 '<span class="check_font">等待审核</span>'+
						  			       '</div>';
			    			}
			    			str += '<span class="box_wrap">'+
				    			       '<a href="javascript:delFile(\''+data[i].id+'\')" class="ace_button del_a" style="background-color:#ec4758;"><i class=" fa fa-trash-o"></i></a>'+
				    			       shadeStr +
				    				   '<img style="max-width: 100%;" alt="" src="'+data[i].filePath+'">'+
			    			       '</span>';
			    		}
			    		$('#content').html(str);
			    	}
			    },
			    error: function(data){
			    	
			    }
			});
		}
		
		function delFile(fileId){
			layer.open({
				title:'提示信息',
				content:'您确定要删除 吗',
				icon:7,
				yes:function(index){
					if(fileId){
						$.ajax({
							url: 'activitySpaceController.do?del&id='+fileId,
							type: 'POST',
							dataType: 'json',
							success: function(data){
								layer.msg('删除成功');
								initFileArea();
							},
							error: function(data){
								layer.msg('删除失败，请联系技术人员');
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
 </body>