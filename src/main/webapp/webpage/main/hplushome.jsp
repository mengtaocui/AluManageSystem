<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    	<link href="css/layout.css" rel="stylesheet" type="text/css"/>
        <style>
			table{
			    position: absolute;
			    top: 0;
			    bottom: 0;
			    left: 0;
			    right: 0;
			    width: 80%;
		    	margin: auto;
		    	text-align: center;
		    	border-collapse:separate; 
		    	border-spacing:50px;
			}
			table>tbody>tr>td>div{
			    height:234px;
				background: #ffffff;
				text-align: left;
			}
			table>tbody>tr>td>div>h4{
				width: 100%;
			    background: #2F4050;
			    color: #FFFFFF;
			    text-indent: 10px;
			    padding: 5px 0px;
			}
			table>tbody>tr>td>div>ul>li {
			    height: auto;
			    font-size: 14px;
			    padding:5px 0px;
			}    
			table>tbody>tr>td>div>ul>li>span{
			    color: red;
			    margin: 0px 10px
		    }
        </style>
    </head>
    <!-- 管理员登录成功后跳转的页面 -->
	<body>
        <div  style="overflow: hidden">
            <table>
                <tbody>
	            	<tr>
	            		<td>
	            			<div>
								<h4>事务提醒</h4>
								<ul>
									<li>待审核活动<span>${sessionScope.count__activity}</span><a href="${pageContext.request.contextPath}/ShowActivityServlet">查看</a>
									<li>待审核新闻<span>000</span><a href="###">查看</a></li>
									<li>待审核公告<span>000</span><a href="###">查看</a></li>
									<li>待审核学院<span>000</span><a href="###">查看</a></li>
									<li>待审核年届<span>000</span><a href="###">查看</a></li>
									<li>待审核班级<span>000</span><a href="###">查看</a></li>
									<li>待审核文档<span>000</span><a href="###">查看</a></li>
								</ul>
							</div>
	            		</td>
	            		<td>
	            			<div >
								<h4>数据统计</h4>
								<ul>
									<li>学院<span style="margin-left:30px;">${sessionScope.count_college}</span></li>
									<li>年届<span style="margin-left:30px;">${sessionScope.count__year}</span></li>
									<li>班级<span style="margin-left:30px;">${sessionScope.count__grade}</span></li>
									<li>校友<span style="margin-left:30px;">${sessionScope.count__classmate}</span></li>
									<li>图片<span style="margin-left:30px;">000</span></li>
									<li>新闻<span style="margin-left:30px;">000</span></li>
									<li>活动<span style="margin-left:30px;">000</span></li>
								</ul>
							</div>
	            		</td>
	            	</tr>
	            	<tr>
	            		<td>
	            			<div>
								<h4>特别关注</h4>
								<ul>
									<li>最新的公告<span>000</span><a href="###">查看</a></li>
									<li>最新加入的校友<span>000</span><a href="###">查看</a></li>
									<li>最新的帖子<span>000</span><a href="###">查看</a></li>
									<li>最新的活动信息<span>000</span><a href="###">查看</a></li>
									<li>最新的新闻<span>000</span><a href="###">查看</a></li>
									<li>最近修改信息的校友<span>000</span><a href="###">查看</a></li>
									<li>最近访问统计<span>000</span><a href="###">查看</a></li>
								</ul>
							</div>
	            		</td>
	            		<td>
	            			<div>
								<h4>系统信息</h4>
								<ul>
									<li>系统名称：<span style="color:000000;">校友系统</span></li>
									<li>系统版本：<span style="color:000000;">v1.0</span></li>
									<li>技术支持：<span style="color:000000;">小熊猫科技工作室</span></li>
									<li>运行状态：<span style="color:000000;">正常</span></li>
								</ul>
							</div>
	            		</td>
	            	</tr>
            	</tbody>
            </table>
		</div>
	</body>
</html>