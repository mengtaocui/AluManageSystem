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
	    margin-top: 60px;
	    text-align: center;
  	}
  	.del_a{
  		margin-top: 60px;
    	margin-left: 70px;
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
<!--  	<div class="main-wrap">
 		
 	</div> -->
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
			
	    initFileArea()
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
			    		for(var i=0; i<data.length; i++){
			    			str += '<div class="box_wrap">'+
			    			    '<div class="box_shade">'+
			    			    '<span class="check_font">审核中</span>'+
			    			    '<a href="#" class="ace_button del_a" style="background-color:#ec4758;"> <i class=" fa fa-trash-o"></i> 删除</a>'+
			    			    '</div>'+
			    				'<img style="max-width: 100%;" alt="" src="sysController/readPic.do?picPath='+data[i].filePath+'">'+
			    			'</div>';
			    		}
			    		$('#content').html(str);
			    	}
			    },
			    error: function(data){
			    	
			    }
			});
		}
		
		
	</script>  
 </body>