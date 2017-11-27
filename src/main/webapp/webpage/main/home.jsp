<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <!-- 管理员登录成功后跳转的页面 -->
	<body class="home_body">
        <style>
			body{
				background: #FFFFFF;
			}
			.table-wrap>table{
			    width: 100%;
			    border-spacing: 20
			}
			.table-wrap{
			    position:relative;
				width:95%;
				margin: 0px auto;
				margin-top: 8%;
			}
			.item-wrap{
			    width:100%;
				border: none;
			    border-radius: 2px;
			    background: #f2f2f2;
			}
			.symbol{
				width: 40%;
    			padding: 35px 0px;
    			text-align: center;
    			border-radius: 4px 0px 0px 4px;
			}
			.value{
				width: 57%;
			    height: 100%;
			    padding-top: 5px;
			    text-align: center;
			}
			.value>a>p{
				font-size: 12px;
				color: #333;
    			font-family: Arial,Helvetica,sans-serif;
    			margin:0px;
			}
			.count1{
			    line-height: 1;
				font-size: 36px;
			    font-weight: 300;
			    color: #333;
			    font-family: Arial,Helvetica,sans-serif;
			    margin:0px;
			}
			.symbol,.value{
				display: inline-block;
			}
			.symbol>i{
				color: #fff;
    			font-size: 50px;
			}
        </style>
        <div  class="table-wrap">
            <table>
                <tr>
                    <td>
                    	<div class="item-wrap">
                			<div class="symbol" style="background: #54ade8;"> 
                				<i class="fa fa-university"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('学院管理', 'collegeController.do?list', null);">
									<h1 class="count1" id="college">0</h1>
									<p>学院</p>
								</a>
							</div>
                		</div>
                    </td>
                    <td>
                    	<div class="item-wrap">
                			<div class="symbol" style="background: #FF5722;"> 
                				<i class="fa fa-users"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('班级管理', 'gradeController.do?list', null);">
									<h1 class="count1" id="grade">0</h1>
									<p>班级</p>
								</a>
							</div>
                		</div>
                    </td>
                    
                    <td>
                    	<div class="item-wrap">
                			<div class="symbol" style="background: #009688;"> 
                				<i class="fa fa-cutlery"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('活动管理', 'activityController.do?list', null);">
									<h1 class="count1" id="activity">0</h1>
									<p>活动</p>
								</a>
							</div>
                		</div>
                    </td>
                	<td>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #2F4056;"> 
                				<i class="fa fa-file-audio-o"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('帖子管理', 'invitationController.do?list', null);">
									<h1 class="count1" id="invitation">0</h1>
									<p>帖子</p>
								</a>
							</div>
                		</div>
                	</td>
                </tr>
                <tr>
                	<td>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #337ab7;"> 
                				<i class="fa fa-tasks"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('新闻管理', 'newsController.do?list', null);">
									<h1 class="count1" id="news">0</h1>
									<p>新闻</p>
								</a>
							</div>
                		</div>
                	</td>
                	<td>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #f0ad4e;"> 
                				<i class="fa fa-bullhorn"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('公告管理', 'noticesController.do?list', null);">
									<h1 class="count1" id="notices">0</h1>
									<p>公告</p>
								</a>
							</div>
                		</div>
                	</td>
                	<td>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #d9534f;"> 
                				<i class="fa fa-camera-retro"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('资料管理', 'activitySpaceController.do?list', null);">
									<h1 class="count1" id="activitySpace">0</h1>
									<p>资料</p>
								</a>
							</div>
                		</div>
                	</td>
                	<td>
                		<div class="item-wrap">
                			<div class="symbol" style="background: #5cb85c;"> 
                				<i class="fa  fa-credit-card"></i>
							</div>
                			<div class="value">
								<a href="javascript:addOneTab('捐赠项目管理', 'donateController.do?list', null);">
									<h1 class="count1" id="donate">0</h1>
									<p>捐赠项目</p>
								</a>
							</div>
                		</div>
                	</td>
                </tr>
            </table>
		</div>
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
			    	
		    		$('.table-wrap').css({'margin-top':100});
	            
		    	} else if ((screen.width == 1600) && (screen.height == 900)) {
		    		
		    		$('.table-wrap').css({'margin-top':100});
	            
		    	} else if ((screen.width == 1366) && (screen.height == 768)) {
	            	
		    		$('.table-wrap').css({'margin-top':60,'padding':'60px 20px 40px 20px'});
                	
	            } else if ((screen.width == 1280) && (screen.height == 1024)) {
		    		
	            	$('.table-wrap').css({'margin-top':160,'padding':'60px 20px 40px 20px'});
                	
	            } else if ((screen.width == 1152) && (screen.height == 864)) {
	            	
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
							$('#news').html(data.news);
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