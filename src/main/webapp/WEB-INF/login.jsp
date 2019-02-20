<%@page import="sun.util.logging.resources.logging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>后台登录</title>
<!-- Custom Theme files -->
<link href="logincss/css/style.css" rel="stylesheet" type="text/css"
		media="all" />
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
		content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="后台登录" />
<!--Google Fonts-->
<!--<link href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
-->
<!--Google Fonts-->
<style type="text/css">
input::-webkit-input-placeholder {
	color: #c4c4c4;
}
</style>
</head>
<body>
		<!--header start here-->


		<div class="login-form">
				<div style="margin-bottom: 10px">
						<center>
								<img src="logincss/images/logo.png" alt="" />
						</center>
				</div>
				<h1>每天 学习 一小时</h1>
						<!--  -->
					<script type="text/javascript">
					
					//当提交的触发的函数 ： 验证账号密码验证码有没有输入
					function chklogin() {
						//使用 id来查找到loginname输入空并获取输入信息 
						var loginname=document.getElementById("loginname").value;
						//
						var password=document.getElementById("password").value;
						//使用id来获得验证码框的输入信息 
						var chkcode=document.getElementById("chkcode").value;
						if (loginname==""||password==""||chkcode=="") {
							//alert("信息不能为空 ");
							//有信息没有输入时，把 提示文字 放入网页中 
							document.getElementById("errmsg").innerHTML="账号,密码,验证码,不能为空!";
							return false;
						}else{
							//如果没有写return值的时候 默认 return为true 
						 return true;
						}
						
					}
					//更换验证码 
					function changecode() {
						document.getElementById("kaptcha").src="kaptcha.jpg";
					}
					</script>
				<span id="errmsg" style="color: #fff; float: left; position: absolute; top: 333px; left: 500px;">${errmsg}</span>
				
				<div class="login-top">
						<form action="login.do" method="post" onsubmit="return chklogin()">
								<div class="login-ic">
										<i></i> <input type="text" id="loginname" value="${cookie.username.value }"
												name="loginname" placeholder="请输入用户名" />
										<div class="clear"></div>
								</div>
								<div class="login-ic">
										<i class="icon"></i> <input id="password" type="password"
												value="${cookie.pwd.value }" name="password"
												placeholder="请输入密码" />
										<div class="clear"></div>
								</div>

								<!-- 验证码的input的框 -->
								
								<div class="login-ic">
										<i class="icon"></i> <input type="text" id="chkcode" value=""
												name="chkcode" placeholder="请输入验证码">
										<div class="clear"></div>
								</div>
								<img id="kaptcha" alt="" src="kaptcha.jpg"
										style="position: absolute; left: 61%; top: 58%;"onclick="changecode()">
								<div class="login-ic">
										<input type="checkbox" value="1" name="remPwd"
												style="margin-left: 40px">是否记住密码
								</div>

								<div class="log-bwn">
										<input type="submit" value="Login">
								</div>
						</form>
				</div>
				<p class="copy">© 2018 中仕学社</p>
		</div>

		<!--header start here-->
</body>
</html>