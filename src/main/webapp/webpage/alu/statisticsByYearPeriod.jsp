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
  		margin-top:2%;
  	}
  	#main,#main_line{
  	   margin: 0px auto;
  	}
  	.data_table{
  	 	width: 85%;
	    height: 80px;
  		margin: 5% auto;
  		border: 1px;
  	    border-collapse: collapse;
  	}
  	.chart_head{
	  	 padding: 20px 0px;
	     text-align: center;
	     font-size: 17px;
  	}
  </style>
 </head>
 <body style="overflow-y: hidden" scroll="no">
    <p class="chart_head">按年届统计校友人数</p>
	<div class="main-wrap">
	     <div id="main" style="width: 600px;height:400px;"></div>
	     <table class="data_table" border="1" border-spaceing="10" bordercolor="#a0c6e5" style="border-collapse:collapse;">
	        <thead>
	        	<tr id="t_head">
	        	    <th>年届</th>
	        		<!-- <th>1990</th>
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
	        		<th>1990</th> -->
	        	</tr>
	        </thead>
	     	<tbody>
	     		<tr id="t_content">
	     		    <td>人数</td>
	     			<!-- <td>100</td>
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
	     			<td>100</td> -->
	     		</tr>
	     	</tbody>
	     </table>
		 
	</div>
  <script type="text/javascript">
  	$(function(){
  		var main_width = ($('.main-wrap').width())*0.8;
  		var main_height = ($('.main-wrap').height())*0.5;
  		$('#main').css({'width':main_width,'height':main_height});
  		initChart();
  	})
  	function initChart(){
  		
  		$.ajax({
  			url: 'statisticsController.do?getStatisticsByYearPeriodData',
  			type: 'GET',
  			dataType: 'json',
  			success: function(data){
  				if(data && data.length > 0){
  					var xArr = new Array(), yArr = new Array();
  					for(var i=0; i<data.length; i++){
  						xArr.push(data[i].name);
  						yArr.push(data[i].value);
  						
  						$('#t_head').append('<th>'+data[i].name+'</th>');
  						$('#t_content').append('<th>'+data[i].value+'</th>');
  						
  					}
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
  			        	        data:['按年届统计毕业人数'],
  			        	        show: false
  			        	    },
  			        	    calculable : true,
  			        	    xAxis : [
  			        	        {
  			        	            type : 'category',
  			        	            data : xArr
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
  			        	            data:yArr
  			        	        }
  			        	    ]
  			        	};
  			        myChart.setOption(option);
  				}
  			},
  			error: function(data){
  				
  			}
  		});
        
  	}
    </script>
 </body>