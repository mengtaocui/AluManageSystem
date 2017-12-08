<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    	<link href="css/layout.css" rel="stylesheet" type="text/css"/>
    	<link href="plug-in/ace/css/font-awesome.css" rel="stylesheet" type="text/css"/>
    	<script src="plug-in/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="plug-in/tools/curdtools_zh-cn.js" type="text/javascript"></script>
    	
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
        </style>
    </head>
    <!-- 管理员登录成功后跳转的页面 -->
	<body>
        <div  class="table-wrap">
            <table>
                <tr>
                    <!-- sys_sys_manage &&  sch_manage begin-->
                    <c:if test="${ (LOCAL_CLINET_USER.userRoleCode eq 'sys_manage')  || (LOCAL_CLINET_USER.userRoleCode eq 'sch_manage') }">
                    	<td>
	                    	<div class="item-wrap">
	                			<div class="symbol" style="background: #54ade8;"> 
	                				<i class="fa fa-university"></i>
								</div>
	                			<div class="value">
	                			    <c:if test="${ (LOCAL_CLINET_USER.userRoleCode eq 'sys_manage') }">
										<a href="javascript:void(0);"
											<h1 class="count1" id="college">0</h1>
											<p>学院</p>
										</a>
									</c:if>
									<c:if test="${ (LOCAL_CLINET_USER.userRoleCode eq 'sch_manage') }">
										<a href="javascript:void(0);"
											<h1 class="count1" id="college">0</h1>
											<p>学院</p>
										</a>
										<script>
											$(function(){
												initCollegeCount();
						        		    	setInterval(function(){
						        		    		initCollegeCount();
						        				}, 5000);
						        		    })
										</script>
									</c:if>
								</div>
	                		</div>
	                    </td>
	                    <td>
	                    	<div class="item-wrap">
	                			<div class="symbol" style="background: #FF5722;"> 
	                				<i class="fa fa-users"></i>
								</div>
	                			<div class="value">
									<a href="javascript:void(0);">
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
									<a href="javascript:void(0);">
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
									<a href="javascript:void(0);">
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
									<a href="javascript:void(0);">
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
									<a href="javascript:void(0);">
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
									<a href="javascript:void(0);">
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
									<a href="javascript:void(0);">
										<h1 class="count1" id="donate">0</h1>
										<p>捐赠项目</p>
									</a>
								</div>
	                		</div>
	                	</td>
                    </c:if>
                    <!-- col_manage &&  tea_manage end-->
                    
                    <c:if test="${ (LOCAL_CLINET_USER.userRoleCode eq 'col_manage')  || (LOCAL_CLINET_USER.userRoleCode eq 'tea_manage') }">
	                    <td>
	                    	<div class="item-wrap">
	                			<div class="symbol" style="background: #54ade8;"> 
	                				<i class="fa fa-university"></i>
								</div>
	                			<div class="value">
									<a href="javascript:void(0);"
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
	                	<script>
		                	$(function(){
		        		    	initCount();
		        		    	setInterval(function(){
		        					initCount();
		        				}, 5000);
		        		    })
	                	</script>
                	</c:if>
                    <!-- sys_sys_manage &&  sch_manage end-->
                </tr>
            </table>
		</div>
		<script>
		    $(function(){
		    	initDomSize();
		    })
		    
		    function initCollegeCount(){
		    	$.ajax({
					url: 'statisticsController.do?getAwaitInfo',
					type: 'GET',
					dataType: 'json',
					success: function(data){
						if(data){
							$('#college').html(data.college);
						}
					},
					error: function(data){
						
					}
				});
		    }
		    
		   function initDomSize(){
		    	
		    	if ((screen.width == 1920) && (screen.height == 1080)) {
		    	
		    		$('.table-wrap').css({'margin-top':140});
	            
		    	} else if ((screen.width == 1600) && (screen.height == 900)) {
		    		
		    		$('.table-wrap').css({'margin-top':130});
	            
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
						//	$('#college').html(data.college);
							$('#news').html(data.news);
							$('#notices').html(data.notices);
							$('#activity').html(data.activity);
							$('#activitySpace').html(data.activitySpace);
							$('#invitation').html(data.invitation);
							$('#grade').html(data.grade);
							$('#donate').html(data.donate);
						}
					},
					error: function(data){
						
					}
				});
			}
		</script>
	</body>
</html>