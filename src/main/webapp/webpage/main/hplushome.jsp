<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    	<link href="css/layout.css" rel="stylesheet" type="text/css"/>
    	<link href="plug-in/ace/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <style>
			body{
				background: #FFFFFF;
			}
			.table-wrap>table{
			    width: 100%;
			}
			.title{
				position: absolute;
			    left: 50%;
			    top: -13px;
			    margin-left: -51px;
			    background: #FFFFFF;
			    padding: 0px 20px;
			    font-family: "微软雅黑","Microsoft YaHei","Microsoft YaHei UI","Hiragino Sans GB W3","Segoe UI", Arial, Verdana, Sans-Serif, sans-serif;
				font-size: 17px;
    			font-weight: 300;
    			line-height: 1.42857143;
   				color: #333;
			}
			.table-wrap{
			    position:relative;
				width:95%;
				margin: 0px auto;
				border: 1px solid #e2e2e2;
				padding: 90px 20px 60px 20px;
			}
			.item-wrap{
			    display:inline-block;
				border: none;
			    border-radius: 2px;
			    background: #f2f2f2;
			    margin: 0px 15px 20px 15px;
			}
			.symbol{
				width: 40%;
    			padding: 25px 0px;
    			text-align: center;
    			border-radius: 4px 0px 0px 4px;
			}
			.value{
				width: 58%;
			    height: 100%;
			    padding-top: 5px;
			    text-align: center;
			}
			.value>a>p{
				font-size: 12px;
				color: #333;
    			font-family: Arial,Helvetica,sans-serif;
			}
			.count1{
			    line-height: 1;
				font-size: 36px;
			    font-weight: 300;
			    color: #333;
			    font-family: Arial,Helvetica,sans-serif;
			}
			.symbol,.value{
				display: inline-block;
			}
			.symbol>i{
				color: #fff;
    			font-size: 50px;
			}
		    /*@media screen and (max-width: 1920px) {
			   .table-wrap{
			   		margin-top:100px;
			   }
			   .item-wrap{
					width: 22.89%;
				}
			}
			@media screen and (max-width: 1600px) {
			   .table-wrap{
			   		margin-top:100px;
			   }
			   .item-wrap{
					width: 22.4%;
				}
			}
			@media screen and (max-width: 1366px) {
			   .table-wrap {
				    margin-top: 60px;
				    padding: 60px 20px 40px 20px;
				}
			   .item-wrap{
					width: 21.9%;
				}
			}
			@media screen and (max-width: 1280px) {
			   .table-wrap {
				    margin-top: 160px;
				    padding: 60px 20px 40px 20px;
				}
			   .item-wrap{
					width: 21.6%;
				}
				.value{
					width: 57%;
				}
			}
			@media screen and (max-width: 1152px) {
			   .table-wrap {
				    margin-top: 80px;
				    padding: 60px 20px 40px 20px;
				}
			   .item-wrap{
					width: 21.1%;
				}
				.value{
					width: 57%;
				}
			}*/
        </style>
    </head>
    <!-- 管理员登录成功后跳转的页面 -->
	<body>
        <div  class="table-wrap">
            <p class="title">待审核</p>
            <table>
                <tr>
                	<td>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #54ade8;"> 
                				<i class="fa fa-university"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('学院管理', 'collegeController.do?list', null);">
									<h1 class="count1" id="college">10</h1>
									<p>学院</p>
								</a>
							</div>
                		</div>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #FF5722;"> 
                				<i class="fa fa-users"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('班级管理', 'gradeController.do?list', null);">
									<h1 class="count1" id="grade">10</h1>
									<p>班级</p>
								</a>
							</div>
                		</div>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #009688;"> 
                				<i class="fa fa-cutlery"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('活动管理', 'activityController.do?list', null);">
									<h1 class="count1" id="activity">10</h1>
									<p>活动</p>
								</a>
							</div>
                		</div>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #2F4056;"> 
                				<i class="fa fa-file-audio-o"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('帖子管理', 'invitationController.do?list', null);">
									<h1 class="count1" id="invitation">10</h1>
									<p>帖子</p>
								</a>
							</div>
                		</div>
                		
                		<div class="item-wrap">
                			<div class="symbol" style="background: #337ab7;"> 
                				<i class="fa fa-tasks"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('新闻管理', 'newsController.do?list', null);">
									<h1 class="count1" id="news">10</h1>
									<p>新闻</p>
								</a>
							</div>
                		</div>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #f0ad4e;"> 
                				<i class="fa fa-bullhorn"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('公告管理', 'noticesController.do?list', null);">
									<h1 class="count1" id="notices">10</h1>
									<p>公告</p>
								</a>
							</div>
                		</div>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #d9534f;"> 
                				<i class="fa fa-camera-retro"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('资料管理', 'activitySpaceController.do?list', null);">
									<h1 class="count1" id="activity">10</h1>
									<p>资料</p>
								</a>
							</div>
                		</div>
                	</td>
                </tr>
            </table>
		</div>
		<script src="plug-in/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="plug-in/tools/curdtools_zh-cn.js" type="text/javascript"></script>
		<script>
		    $(function(){
		    	initDomSize();
		    	
		    	initCount();
		    	setInterval(function(){
					initCount();
				}, 5000);
		    })
		    
		   function initDomSize(){
		    	
		    	if ((screen.width == 1920) && (screen.height == 1080)) {
		    		
		    		$('.item-wrap').width('372.64');
		    		$('.symbol').width('147.5');
		    		$('.value').width('213.88');
		    		$('.table-wrap').css({'margin-top':100});
                	
	            } else if ((screen.width == 1600) && (screen.height == 900)) {
	            	
	            	$('.item-wrap').width('297.4');
	            	$('.symbol').width('117.09');
		    		$('.value').width('169.8');
		    		$('.table-wrap').css({'margin-top':100});
                	
	            } else if ((screen.width == 1366) && (screen.height == 768)) {
	            	
	            	$('.item-wrap').width('241.71');
	            	$('.symbol').width('93.73');
		    		$('.value').width('133.58');
		    		$('.table-wrap').css({'margin-top':60,'padding':'60px 20px 40px 20px'});
                	
	            } else if ((screen.width == 1280) && (screen.height == 1024)) {
	            	
	            	$('.item-wrap').width('221.27');
	            	$('.symbol').width('86.08');
		    		$('.value').width('122.67');
		    		$('.table-wrap').css({'margin-top':160,'padding':'60px 20px 40px 20px'});
                	
	            } else if ((screen.width == 1152) && (screen.height == 864)) {
	            	
	            	$('.item-wrap').width('189.5');
	            	$('.symbol').width('74.44');
		    		$('.value').width('106.06');
		    		$('.table-wrap').css({'margin-top':80,'padding':'60px 20px 40px 20px'});
	                
	            }
		    }
		    
			function initCount(){
				$.ajax({
					url: 'statisticsController.do?getAwaitInfo',
					type: 'GET',
					dataType: 'json',
					success: function(data){
						if(data){
							$('#college').html(data.college);
							$('#news').html(news.news);
							$('#notices').html(data.notices);
							$('#activity').html(data.activity);
							$('#activitySpace').html(data.activitySpace);
							$('#invitation').html(data.invitation);
							$('#grade').html(data.grade);
						}
					},
					error: function(data){
						
					}
				});
			}
		</script>
	</body>
</html>