<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>活动</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/echarts-2.2.7/build/dist/echarts-all.js"></script>
  <style type="text/css">
    html,body,.main-wrap{
        width: 100%;
    	height: 100%;
    	background:#ffffff;
    }
    table{
    	width:100%;
    	height: 90%;
    }
    *{
    	margin:0px;
        padding: 0px;
    }
  	#main_pie,#main_bar,#main{
  		background:#F7F7F7;
  	}
  	#main_map,#main{
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
    <p class="chart_head">按所在省份统计校友人数</p>
	<div class="main-wrap">
	     <table>
	     	<tr>
	     		<td id="map_td">
	     			<div id="main_map" style="width: 600px;height:400px;"></div>
	     		</td>
	     		<td id="line_td">
	     			<div id="main" style="width: 600px;height:400px;"></div>
	     		</td>
	     	</tr>
	     </table>
	     
	</div>
  <script type="text/javascript">
    var xArr = new Array(), yArr = new Array(), dataObj = new Array();
  	$(function(){
  		$('#main_map').css({'width':($('#map_td').width()*0.9),'height':$('#line_td').height()}); 
  		$('#main').css({'width':($('#line_td').width()*0.9),'height':$('#line_td').height()*0.8}); 
  		
  		$.ajax({
  			url: 'statisticsController.do?getStatisticsByProvinceData',
  			type: 'GET',
  			async: false,
  			dataType: 'json',
  			success: function(data){
  				if(data && data.length > 0){
  					for(var i=0; i<data.length; i++){
  						var pname = data[i].name.trim();
  						pname = pname.substring(0, pname.length-1);
  						xArr.push(pname);
  						yArr.push(data[i].value);
  						
  						dataObj.push({'name':pname, 'value': data[i].value});
  					}
  					
  				}
  			},
  			error: function(data){
  				
  			}
  		});
  		
  		initMap();
  		initLine();
  	})
  	
  	function initMap(){
  		console.log(dataObj);
  		var myChart = echarts.init(document.getElementById('main_map'));
        var option = {
        	    tooltip : {
        	        trigger: 'item'
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        x:'left',
        	        data:['人数'],
        	        show: false
        	    },
        	    dataRange: {
        	        min: 0,
        	        max: 2500,
        	        x: 'left',
        	        y: 'bottom',
        	        text:['高','低'],           // 文本，默认为数值文本
        	        calculable : true
        	    },
        	    roamController: {
        	        show: true,
        	        x: 'right',
        	        mapTypeControl: {
        	            'china': true
        	        }
        	    },
        	    series : [
        	        {
        	            name: '人数',
        	            type: 'map',
        	            mapType: 'china',
        	            roam: false,
        	            itemStyle:{
        	                normal:{label:{show:true}},
        	                emphasis:{label:{show:true}}
        	            },
        	            data:dataObj
        	        }
        	    ]
        	};
        	                    
        	                    
        myChart.setOption(option);
  	}
  	
  	
  	
  	function initLine(){
        var myChart = echarts.init(document.getElementById('main'));
        var option = {
        	    legend: {
        	        data:['人数'],
        	       show: false
        	    },
        	    calculable : true,
        	    xAxis : [
        	        {
        	            type : 'value',
        	            boundaryGap : [0, 0.01]
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'category',
        	            data : xArr
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
    </script>
 </body>