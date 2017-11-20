<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>活动</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/echarts-2.2.7/build/dist/echarts-all.js"></script>
  <style type="text/css">
    *{
    	margin:0px;
        padding: 0px;
    }
  	.main-wrap{
  		text-align: center;
  	}
  	#main,#main_line{
  	   margin: 0px auto;
  	}
  	.data_table{
  		margin: 0px auto;
  		border: 1px;
  		border-collapse: collapse;
  		    margin-top: 80px;
  		        border-collapse: collapse;
    width: 90%;
    height: 80px;
    margin-bottom: 80px;
  	}
  </style>
 </head>
 <body style="overflow-y: hidden" scroll="no">
	<div class="main-wrap">
	     <div id="main" style="width: 600px;height:400px;"></div>
	     <table class="data_table" border="1" border-spaceing="10" bordercolor="#a0c6e5" style="border-collapse:collapse;">
	        <thead>
	        	<tr>
	        	    <th>年届</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        		<th>1990</th>
	        	</tr>
	        </thead>
	     	<tbody>
	     		<tr>
	     		    <td>人数</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     			<td>100</td>
	     		</tr>
	     	</tbody>
	     </table>
		 
	</div>


  <%-- <t:datagrid name="activityList" title="活动" actionUrl="activityController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="活动名称" field="name"   width="120" query="true"></t:dgCol>
   <t:dgCol title="活动地点" field="places"   width="120" query="true"></t:dgCol>
   <t:dgCol title="开始时间" field="startTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="结束时间" field="endTime"   width="120"></t:dgCol>
   <t:dgCol title="报名截止时间" field="applyEndTime"   width="120" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="浏览次数" field="browseCount"   width="60"></t:dgCol>
   <t:dgCol title="审核状态" field="checkStatus" replace="等待审核_0,已通过_1,被拒绝_2"  query="true" width="70"></t:dgCol>
   <t:dgCol title="创建人" field="crtByUserName"   width="100"></t:dgCol>
   <t:dgCol title="创建时间" field="crtTime"   width="120"></t:dgCol>
   <t:dgCol title="审核人" field="checkByUserName"   width="100"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="130"></t:dgCol>
   <t:dgFunOpt funname="lookPhoto(id,name)"  exp="checkStatus#eq#1" title="查看相册" urlStyle="background-color: #269abc;" urlclass="ace_button"  urlfont="fa-check"></t:dgFunOpt>
   <t:dgFunOpt funname="handleApplyPass(id)" exp="checkStatus#eq#0" title="通过" urlStyle="background-color: #449d44;" urlclass="ace_button"  urlfont="fa-check"></t:dgFunOpt>
   <t:dgFunOpt funname="handleApplyRefuse(id)" exp="checkStatus#eq#0" title="拒绝" urlStyle="background-color: #f0ad4e;" urlclass="ace_button"  urlfont="fa-close"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="activityController.do?del&id={id}" urlclass="ace_button" urlStyle="background-color:#ec4758;"   urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="activityController.do?addorupdate" funname="add" width="900" height="670"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="activityController.do?addorupdate" funname="update" width="900" height="670"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="activityController.do?addorupdate" funname="detail" width="900" height="670"></t:dgToolBar>
  </t:datagrid> --%>
  <script type="text/javascript">
  	$(function(){
  		var main_width = ($('.main-wrap').width())*0.8;
  		$('#main').css({'width':main_width});
  		/* $('#data_table').css({'width':main_width}); */
  		initChart();
  		//initLineChart();
  	})
  	function initChart(){
  	// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {
        	    tooltip : {
        	        trigger: 'axis',
        	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        	        }
        	    },
        	    legend: {
        	        data:['按年届统计毕业人数']
        	    },
        	    toolbox: {
        	        show : true,
        	        orient: 'vertical',
        	        x: 'right',
        	        y: 'center',
        	        feature : {
        	            mark : {show: true},
        	            dataView : {show: true, readOnly: false},
        	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
        	            restore : {show: true},
        	            saveAsImage : {show: true}
        	        }
        	    },
        	    calculable : true,
        	    xAxis : [
        	        {
        	            type : 'category',
        	            data : ['1990','1991','1992','1993','1994','1995','1996','1997','1998','1999','2000',2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016]
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'value'
        	        }
        	    ],
        	    series : [
        	        {
        	            name:'按年届统计毕业人数',
        	            type:'bar',
        	            data:[100, 50, 200, 30, 99, 330, 100,50,100,150, 20, 50, 100, 200,150,100,30,20,50,100,50,40,60,100,20,30,100]
        	        }
        	    ]
        	};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
  		
  	}
  	function initLineChart(){
  	  	// 基于准备好的dom，初始化echarts实例
  	        var myChart = echarts.init(document.getElementById('main_line'));
  	        // 指定图表的配置项和数据
  	        var option = {
  	        	    tooltip : {
  	        	        trigger: 'axis',
  	        	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
  	        	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
  	        	        }
  	        	    },
  	        	    legend: {
  	        	        data:['按年届统计毕业人数']
  	        	    },
  	        	    toolbox: {
  	        	        show : true,
  	        	        orient: 'vertical',
  	        	        x: 'right',
  	        	        y: 'center',
  	        	        feature : {
  	        	            mark : {show: true},
  	        	            dataView : {show: true, readOnly: false},
  	        	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
  	        	            restore : {show: true},
  	        	            saveAsImage : {show: true}
  	        	        }
  	        	    },
  	        	    calculable : true,
  	        	    xAxis : [
  	        	        {
  	        	            type : 'category',
  	        	            data : ['1990','1991','1992','1993','1994','1995','1996','1997','1998','1999','2000',2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016]
  	        	        }
  	        	    ],
  	        	    yAxis : [
  	        	        {
  	        	            type : 'value'
  	        	        }
  	        	    ],
  	        	    series : [
  	        	        {
  	        	            name:'按年届统计毕业人数',
  	        	            type:'line',
  	        	            data:[100, 50, 200, 30, 99, 330, 100,50,100,150, 20, 50, 100, 200,150,100,30,20,50,100,50,40,60,100,20,30,100]
  	        	        }
  	        	    ]
  	        	};
  	        // 使用刚指定的配置项和数据显示图表。
  	        myChart.setOption(option);
  	  		
  	  	}
    </script>
 </body>