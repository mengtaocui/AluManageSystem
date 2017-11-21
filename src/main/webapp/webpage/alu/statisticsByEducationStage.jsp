<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>活动</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/echarts-2.2.7/build/dist/echarts-all.js"></script>
  <style type="text/css">
    html,body,#main-wrap,table{
        width: 100%;
    	height: 100%;
    	background:#ffffff;
    }
    *{
    	margin:0px;
        padding: 0px;
    }
  	#main_pie,#main_bar,#main{
  		background:#F7F7F7;
  	}
  	#main{
  		margin: 0px auto;
  	}
  	.chart_head{
	  	 padding: 20px 0px;
	     text-align: center;
	     font-size: 17px;
  	}
  </style>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 	<p class="chart_head">按教育阶段统计校友人数</p>
	<div class="main-wrap">
	     <table>
	     	<tr>
	     		<td id="pie_td">
	     			<div id="main_pie" style="width: 600px;height:400px;"></div>
	     		</td>
	     		<td>
	     			<div id="main_bar" style="width: 600px;height:400px;"></div>
	     		</td>
	     	</tr>
	     	<tr>
	     		<td colspan="2" id="line_td">
	     			<div id="main" style="width: 600px;height:400px;"></div>
	     		</td>
	     	</tr>
	     </table>
	     
	</div>
  <script type="text/javascript">
    var xArr = new Array(), yArr = new Array(), dataObj = null;
  	$(function(){
  		$('#main_pie').css({'width':($('#pie_td').width()*0.8),'height':$('#line_td').height()*0.8}); 
  		$('#main_bar').css({'width':($('#pie_td').width()*0.8),'height':$('#line_td').height()*0.8});
  		$('#main').css({'width':($('#line_td').width()*0.9),'height':$('#line_td').height()*0.8}); 
  		
  		$.ajax({
  			url: 'statisticsController.do?getStatisticsByEducationStageData',
  			type: 'GET',
  			async: false,
  			dataType: 'json',
  			success: function(data){
  				if(data && data.length > 0){
  					dataObj = data;
  					for(var i=0; i<data.length; i++){
  						xArr.push(data[i].name);
  						yArr.push(data[i].value);
  					}
  					
  				}
  			},
  			error: function(data){
  				
  			}
  		});
  		
  		initPie();
  		initBar();
  		initLine();
  	})
  	
  	function initPie(){
  		var myChart = echarts.init(document.getElementById('main_pie'));
        var option = {
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    legend: {
        	        data:xArr,
        	        show: false
        	    },
        	    calculable : true,
        	    series : [
        	        {
        	            name:'人数',
        	            type:'pie',
        	            radius : '55%',
        	            center: ['50%', '60%'],
        	            data:dataObj
        	        }
        	    ]
        	};
        	                    
        myChart.setOption(option);
  	}
  	
  	
  	function initBar(){
        var myChart = echarts.init(document.getElementById('main_bar'));
        var option = {
        	    tooltip : {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:['人数'],
        	        show:false
        	    },
        	    calculable : true,
        	    xAxis : [
        	        {
        	            type : 'category',
        	            data :xArr
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'value'
        	        }
        	    ],
        	    series : [
        	        {
        	            name:'人数',
        	            type:'bar',
        	            data:yArr
        	        }
        	    ]
        	};
        myChart.setOption(option);
  	}
  	
  	function initLine(){
        var myChart = echarts.init(document.getElementById('main'));
        var option = {
        	    tooltip : {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:['人数'],
        	        show:false
        	    },
        	    calculable : true,
        	    xAxis : [
        	        {
        	            type : 'category',
        	            boundaryGap : false,
        	            data :xArr
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'value',
        	            axisLabel : {
        	                formatter: '{value} 人'
        	            }
        	        }
        	    ],
        	    series : [
        	        {
        	            name:'人数',
        	            type:'line',
        	            data:yArr
        	        }
        	    ]
        	};
        myChart.setOption(option);
  	}
    </script>
 </body>