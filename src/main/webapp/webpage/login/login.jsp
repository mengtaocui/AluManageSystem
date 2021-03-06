<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta charset="utf-8" />
  <title><t:mutiLang langKey="jeect.platform"/></title>
   <link rel="shortcut icon" href="images/favicon.ico">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
  <!-- bootstrap & fontawesome -->
  <link rel="stylesheet" href="plug-in/ace/css/bootstrap.css" />
  <link rel="stylesheet" href="plug-in/ace/css/font-awesome.css" />
  <link rel="stylesheet" type="text/css" href="plug-in/accordion/css/accordion.css">
  <!-- text fonts -->
  <link rel="stylesheet" href="plug-in/ace/css/ace-fonts.css" />

  <link rel="stylesheet" href="plug-in/ace/css/jquery-ui.css" />
  <!-- ace styles -->
  <link rel="stylesheet" href="plug-in/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
  <!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-part2.css" class="ace-main-stylesheet" />
  <![endif]-->

  <!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-ie.css" />
  <![endif]-->
  <!-- ace settings handler -->
  <script src="plug-in/ace/js/ace-extra.js"></script>

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

  <!--[if lte IE 8]>
  <script src="plug-in/ace/js/html5shiv.js"></script>
  <script src="plug-in/ace/js/respond.js"></script>
  <![endif]-->
  <link rel="stylesheet" href="plug-in/swiper3.4.1/idangerous.swiper2.7.6.css">
  <style>
  	input{
  		outline: none;
  	}
  	#login-box,.widget-body,.widget-main{
  		background-color: rgba(0, 0, 0, 0.2) !important;
  	}
  	.input-icon.input-icon-right > input {
	    padding-left: 6px;
	    padding-right: 24px;
	    outline: none;
	    box-shadow: 301px 34px 5px #ffffff inset !important;
	}
	.color-white{
		color: #ffffff;
	}
	
	.login-layout{
		background-color: #fff !important;
	}
	.banner{
	    position: absolute;
	    top: 0px;
	    left: 0px;
	    width: 100%;
	    height: 100%;
	    overflow: hidden;
	}
	.banner-img{
		width: 100%;
	    height: 100%;
	}
	.main-container{
		position: absolute;
	    top: 170px;
	    left: 50%;
	    z-index: 1;
	}
	.arrow-right {
	    background: url(images/arrows.png) no-repeat left bottom;
	    position: absolute;
	    right: 10px;
	    top: 50%;
	    margin-top: -15px;
	    width: 17px;
	    height: 30px;
		z-index: 1;
	}
	.arrow-left {
	    background: url(images/arrows.png) no-repeat left top;
	    position: absolute;
	    left: 10px;
	    top: 50%;
	    margin-top: -15px;
	    width: 17px;
	    height: 30px;
		z-index: 1;
	}
	.pagination {
	  position: absolute;
	  z-index: 20;
	  bottom: 10px;
	  width: 100%;
	  text-align: center;
	}
	.swiper-pagination-switch {
	  display: inline-block;
	  width: 8px;
	  height: 8px;
	  border-radius: 8px;
	  background: #555;
	  margin: 0 5px;
	  opacity: 0.8;
	  border: 1px solid #fff;
	  cursor: pointer;
	}
	.swiper-active-switch {
	  background: #fff;
	}
  </style>

    
</head>
<body class="login-layout light-login">
<!-- Swiper banner-->
	<div class="swiper-container banner">
	    <a class="arrow-left" href="#"></a>
 		<a class="arrow-right" href="#"></a>
	    <div class="swiper-wrapper">
	        <!-- <div class="swiper-slide"><img src="images/s1.jpg" class="banner-img"></div>
	        <div class="swiper-slide"><img src="images/s2.jpg" class="banner-img"></div> -->
	        <!-- <div class="swiper-slide">Slide 3</div>
	        <div class="swiper-slide">Slide 4</div>
	        <div class="swiper-slide">Slide 5</div>
	        <div class="swiper-slide">Slide 6</div>
	        <div class="swiper-slide">Slide 7</div>
	        <div class="swiper-slide">Slide 8</div>
	        <div class="swiper-slide">Slide 9</div>
	        <div class="swiper-slide">Slide 10</div> -->
	    </div>
	   <div class="pagination"></div>
	</div>
 <div class="main-container">
   <div class="main-content">
    <div class="row">
      <div class="col-sm-10 col-sm-offset-1">
        <div class="login-container">
          <div class="space-6"></div>
          <div class="position-relative">
            <div id="login-box" class="login-box visible widget-box no-border">
              <div class="widget-body">
                <form id="loinForm" class="form-horizontal"    method="post">
                <input type="hidden" id="ReturnURL"  name="ReturnURL" value="${ReturnURL }"/>
                <div class="widget-main">
                 <div class="alert alert-warning alert-dismissible" role="alert" id="errMsgContiner">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <div id="showErrMsg"></div>
				</div>
                  <h4 class="header color-white  lighter bigger font_color">
                    <i class="ace-icon fa fa-coffee color-white"></i>
                	    校友系统
                  </h4>
                  <div class="space-6"></div>
                      <label class="block clearfix">
								<span class="block input-icon input-icon-right">
								<!-- update-start--Author:yugwu  Date:20170901 for:TASK #2324 【改进】登录记住用户名不起作用---- -->
									<input type="text"  name="userName" iscookie="true" class="form-control info" placeholder="请输入用户名"  id="userName" value="admin"/>
								<!-- update-end--Author:yugwu  Date:20170901 for:TASK #2324 【改进】登录记住用户名不起作用---- -->
									<i class="ace-icon fa fa-user"></i>
								</span>
                      </label>
                      <label class="block clearfix">
								<span class="block input-icon input-icon-right">
									<input type="password" name="password" class="form-control info" placeholder="请输入密码" id="password" value="123456"/>
									<i class="ace-icon fa fa-lock"></i>
								</span>
                      </label>
                      <label class="block clearfix">
                        <div class="input-group">
                          <input type="text" style="width:150px" name="randCode" class="form-control" placeholder="请输入验证码"  id="randCode"/>
                          <span class="input-group-addon" style="padding: 0px;"><img id="randCodeImage" src="randCodeImage"  /></span>
                        </div>
                      </label>
                      <div class="space"></div>
                      <div class="clearfix">
                        <label class="inline">
                          <input type="checkbox" class="ace" id="on_off"  name="remember" value="yes"/>
                          <span class="lbl color-white">记住用户名</span>
                        </label>
                        <button type="button" id="but_login"  onclick="checkUser()" class="width-35 pull-right btn btn-sm btn-primary">
                          <i class="ace-icon fa fa-key"></i>
                          <span class="bigger-110" >登录</span>
                        </button>
                      </div>
                      <div class="space-4"></div>
                </div>
                </form>
              </div>
            </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>



<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/en.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/zh-cn.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
<!-- add-begin--Author:gengjiajia  Date:20160727 for:TASK #1217 【IE兼容】jeecg h+首页兼容性问题,不兼容的浏览器直接切换套shortcut风格 -->
<script type="text/javascript" src="plug-in/login/js/login.js"></script>
<!-- add-end--Author:gengjiajia  Date:20160727 for:TASK #1217 【IE兼容】jeecg h+首页兼容性问题,不兼容的浏览器直接切换套shortcut风格 -->
<script src="plug-in/swiper3.4.1/idangerous.swiper2.7.6.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		async : true,
		type : 'GET',
		dataType:'json',
		url : 'bannerController.do?getBanners',// 请求的action路径
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			if(data){
				for(var i=0; i<data.length; i++){
					console.log(data[i].picPath);
					$('.swiper-wrapper').append('<div class="swiper-slide"><img src="'+data[i].picPath+'" class="banner-img"></div>');
				}
			}
			
			var mySwiper = new Swiper('.swiper-container',{
				autoplay : 30000,//可选选项，自动滑动
				loop : true,//可选选项，开启循环
				mousewheelControl : true,
				pagination : '.pagination',
				paginationClickable :true
			});
			$('.arrow-left').on('click', function(e){
				e.preventDefault()
				mySwiper.swipePrev()
			  });
			$('.arrow-right').on('click', function(e){
				e.preventDefault()
				mySwiper.swipeNext()
			});
		}
  });
	
})
  
	
	$(function(){
		optErrMsg();
	});
	$("#errMsgContiner").hide();
	function optErrMsg(){
		$("#showErrMsg").html('');
		$("#errMsgContiner").hide();
	}

   //输入验证码，回车登录
  $(document).keydown(function(e){
  	if(e.keyCode == 13) {

      setTimeout("$('#but_login').click()","100");

  	}
  });

  //验证用户信息
  function checkUser(){
    if(!validForm()){
      return false;
    }
    newLogin();
  }
  //表单验证
  function validForm(){
    if($.trim($("#userName").val()).length==0){
      showErrorMsg("请输入用户名");
      return false;
    }

    if($.trim($("#password").val()).length==0){
      showErrorMsg("请输入密码");
      return false;
    }

    if($.trim($("#randCode").val()).length==0){
      showErrorMsg("请输入验证码");
      return false;
    }
    return true;
  }

  //登录处理函数
  function newLogin() {
    setCookie();

    var actionurl="loginController.do?login";//提交路径
    var checkurl="loginController.do?checkuser";//验证路径

    var formData = new Object();
    var data=$(":input").each(function() {
      formData[this.name] =$("#"+this.name ).val();
    });
    //语言
    formData['langCode']=$("#langCode").val();
    formData['langCode'] = $("#langCode option:selected").val();
    $.ajax({
      async : false,
      cache : false,
      type : 'POST',
      url : checkurl,// 请求的action路径
      data : formData,
      error : function() {// 请求失败处理函数
      },
      success : function(data) {
        var d = $.parseJSON(data);
        if (d.success) {
            window.location.href = actionurl;
       } else {
			showErrorMsg(d.msg);
		  	if(d.msg === "用户名或密码错误" || d.msg === "验证码错误")
		  		reloadRandCodeImage();
        }
      }
    });
  }
  //登录提示消息显示
  function showErrorMsg(msg){	
    $("#errMsgContiner").show();
    $("#showErrMsg").html(msg);    
    window.setTimeout(optErrMsg,3000); 
  }
  /**
   * 刷新验证码
   */
$('#randCodeImage').click(function(){
    reloadRandCodeImage();
});
function reloadRandCodeImage() {
    var date = new Date();
    var img = document.getElementById("randCodeImage");
    img.src='randCodeImage?a=' + date.getTime();
}
//设置cookie
  function setCookie()
  {

  	if ($('#on_off').attr("checked")) {

  		$("input[iscookie='true']").each(function() {
  			$.cookie(this.name, $("#"+this.name).val(), "/",24);
  			$.cookie("COOKIE_NAME","true", "/",24);
  		});
  	} else {
  		$("input[iscookie='true']").each(function() {
  			$.cookie(this.name,null);
  			$.cookie("COOKIE_NAME",null);
  		});
  	}
  }
  //读取cookie
  function getCookie()
  {
  	var COOKIE_NAME=$.cookie("COOKIE_NAME");
  	if (COOKIE_NAME !=null) {
  		$("input[iscookie='true']").each(function() {
  			$($("#"+this.name).val( $.cookie(this.name)));
              if("admin" == $.cookie(this.name)) {
                  $("#randCode").focus();
              } else {
                  $("#password").val("");
                  $("#password").focus();
              }
          });
  		$("#on_off").attr("checked", true);
  		$("#on_off").val("1");
  	} 
  	else
  	{
  		$("#on_off").attr("checked", false);
  		$("#on_off").val("0");
        $("#randCode").focus();
  	}
  }
</script>
<%=lhgdialogTheme %>
</body>
</html>