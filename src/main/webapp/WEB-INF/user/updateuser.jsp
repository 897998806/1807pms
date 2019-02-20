<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>用户修改</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
		href="${pageContext.request.contextPath}/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
		href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/amazeui.min.css" />
<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/admin.css">
<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/app.css">
	<script type="text/javascript">
	 //全局变量用于接收修改之前的登录名和邮箱信息 
	 var loginstr="";
	 var emailstr="";
	 //实用化温度就绪事件，来获得修改之前的登录名和邮箱信息 
	 window.onload=function(){
		 loginstr=$("#loginname").val();
		 emailstr=$("#email").val();
	 }
	
	//用户名的要求 必须包含字母 结束之前不能全部都是数字，6-15
	var CHKLOGINNAME = "^(?![0-9]+$)[a-zA-Z0-9]{6,9}$";
	//密码 必须包含数字和字母结束之前不能以纯数字结尾或不能以纯字母结束
	var CHKPWD="^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,15}$";
	//验证邮箱xxxx(可以是数字字母  )@xxx(2-5位的字母 ).xxx(2-3位 )
	var CHKEMAIL="^[a-z0-9A-Z_]+@[a-zA-Z0-9]{2,5}(\.[a-z]{2,3}){1,2}$";
	//真实姓名包含两个或多个汉字 
	var CHKRELNAME="^[\u4e00-\u9fa5]{2,}$";
	//验证用户
	function chkloginname() {

		//获得用户名输入框并获得输入信息
		var str=document.getElementById("loginname").value;
		//修改验证唯一性的时候，先判断登录名和加载信息是否一直，如果一直并且不等于空说明没有修改
		//直接return true跳过下面 的验证 
		if (str==loginstr&&str!="") {
			$("#loginnamemsg").html("√");
			$("#loginnamemsg").css("color", "green");
		
			return true;
		}
		// 创建正则对象 
		var reg = new RegExp(CHKLOGINNAME);
		//获得显示用户检验结果的元素 
		var msg = document.getElementById("loginnamemsg");
		//验证输入信息 
		if (reg.test(str)) {
			//匹配是把对勾打印到页面同时变成绿色 表示可用
			//通过正则判断之后在判断唯一性
			if (chkLoginnameExist(str)) {
				return true;
			} else {
				return false;
			}
		} else {
			msg.innerHTML = "×用户名不符合规范！ ";
			msg.style.color = "red ";
			return false;
		}
	}
	//验证用户名是否重复
	function chkLoginnameExist(loginname) {
		var flag = false;
		$.ajax({
			//请求方式 
			type : 'post',
			//请求路径
			url : 'exist.do',
			//请求参数 
			data : 'type=1&loginname=' + loginname,
			//是否同步请求 true 为异步 
			async : false,
			//请求数据类型
			dataType : 'text',
			success : function(data) {
				//true 表示有重复的用户名
				if (data == "true") {
					$("#loginnamemsg").html("该用户名已被使用！");
					$("#loginnamemsg").css("color", "red");
					flag = false;
				} else {
					$("#loginnamemsg").html("√");
					$("#loginnamemsg").css("color", "green");
					flag = true;
				}
	
			},
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
	               // 状态码
	               console.log(XMLHttpRequest.status);
	               // 状态
	               console.log(XMLHttpRequest.readyState);
	               // 错误信息   
	               console.log(textStatus);
	               //异常原因
	               alert(errorThrown)
	           }
	
		})
		return flag;
	}
	//验证密码
	function chkPwd() {
		//获得密码输入框 并获得输入的信息 
		var passwordStr=document.getElementById("password").value;
		// 创建正则对象 
		var reg =new RegExp(CHKPWD);
		//获得显示用户检验结果的元素 
		var msg= document.getElementById("passwordmsg");
		//验证输入信息 
		if (reg.test(passwordStr)) {
			msg.innerHTML="√";
			msg.style.color="green";
			return true;
		}else{
			msg.innerHTML="×密码不符合规范！ ";
			msg.style.color="red ";
			return false;
		}
	}
	//验证两次密码输入是否一样的 
	function chkRePwd() {
		//分别获取两个密码的信息 
		var pwdStr=document.getElementById("password").value;
		var repwdSrt=document.getElementById("chkpwd").value;
		//获得显示两次一致性 结果的元素 
		var msg=document.getElementById("chkpasswordmsg");
		if (pwdStr==repwdSrt) {
			//匹配时把 对勾打印在页面设置为绿色表示两次一直 
			msg.innerHTML="√";
			msg.style.color="green";
			return true;
		}else{
			msg.innerHTML="×两次密码输入不一致  ";
			msg.style.color="red ";
			return false;
		}
	}
	
	//验证真实姓名 
	function chkRealName() {
		//获得真实姓名输入框 并获得输入的信息 
		var realnameStr=document.getElementById("realname").value;
		// 创建正则对象 
		var reg =new RegExp(CHKRELNAME);
		//获得显示用户检验结果的元素 
		var msg= document.getElementById("realnamemsg");
		//验证输入信息 
		if (reg.test(realnameStr)) {
			msg.innerHTML="√";
			msg.style.color="green";
			return true;
		}else{
			msg.innerHTML="×真实姓名不符合规范！请重新输入！ ";
			msg.style.color="red ";
			return false;
		}
	}
	
//验证邮箱 
function chkEmail() {
	//获得邮箱输入框 并获得输入的信息 
	var str=document.getElementById("email").value;
	if (str==emailstr&&str!="") {
		$("#emailmsg").html("√");
		$("#emailmsg").css("color", "green");
		return true;
	}
	// 创建正则对象 
	var reg =new RegExp(CHKEMAIL);
	//获得显示用户检验结果的元素 
	var msg= document.getElementById("emailmsg");
	//验证输入信息 
	if (reg.test(str)) {
		//匹配是把对勾打印到页面同时变成绿色 表示可用
		//通过正则判断之后在判断唯一性
		if (chkEmailExist(str)) {
			return true;
		} else {
			return false;
		}
	}else{
		msg.innerHTML="×邮箱不符合规范！请重新输入！ ";
		msg.style.color="red ";
		return false;
	}
}	
//验证邮箱是否重复
function chkEmailExist(email) {
	var flag = false;
	$.ajax({
		//请求方式 
		type : 'post',
		//请求路径
		url : 'exist.do',
		//请求参数 
		data : 'type=2&email=' + email,
		//是否同步请求 true 为异步 
		async : false,
		//请求数据类型
		dataType : 'text',
		success : function(data) {
			//true 表示有重复的用户名
			if (data == "true") {
				$("#emailmsg").html("该邮箱已被占用...");
				$("#emailmsg").css("color", "red");
				flag = false;
			} else {
				$("#emailmsg").html("√");
				$("#emailmsg").css("color", "green");
				flag = true;
			}

		},
		 error: function (XMLHttpRequest, textStatus, errorThrown) {
               // 状态码
               console.log(XMLHttpRequest.status);
               // 状态
               console.log(XMLHttpRequest.readyState);
               // 错误信息   
               console.log(textStatus);
               //异常原因
               alert(errorThrown)
           }

	})
	return flag;
}
//验证时间不能为空的 
function chkBirthday() {
	var birthdayStr=document.getElementById("birthday").value;
	//创建正则对象 
	var msg=document.getElementById("birthdaymsg");
	if(birthdayStr!=""){
		msg.innerHTML="√";
		msg.style.color="green";
		return true;
	}else {
		msg.innerHTML="×时间不能为空 ，请重新输入！ ";
		msg.style.color="red ";
		return false;
	}
	
}


function chkSubmit() {
	//在提交时去调用每一个验证方法 ，只要有一个验证方法没有通过返回值为false 
	//因为使用&& 链接整体为false，只有全部通过时才能是true 才可以提交 
	return chkPwd()&&chkRePwd()&&chkRealName()&&chkEmail()&&chkBirthday();
}

		</script>
<style type="text/css">
#loginnamemsg,#passwordmsg,#chkpasswordmsg,#realnamemsg,#emailmsg,#chkpasswordmsg{
position: absolute;
left: 630px;
top: 7px;
font-size: 14px;
width: 200px;
}
</style>		
		
		
</head>
<body data-type="generalComponents">
		<div class="tpl-portlet-components">
				<div class="portlet-title">
						<div class="caption font-green bold">
						<span class="am-icon-code"></span> 用户修改 <a href="userlist.do?page=1"style="margin-left: 650px">返回</a>
						</div>
						<div class="tpl-portlet-input tpl-fz-ml">
								<div class="portlet-input input-small input-inline"></div>
						</div>

				</div>
				<div class="tpl-block ">
						<div class="am-g tpl-amazeui-form">
								<div class="am-u-sm-12 am-u-md-9">
										<form id="myform" name="myform"
												class="am-form am-form-horizontal" action="updateuser.do"
												method="post" onsubmit="return chkSubmit()">
												<input type="hidden" value="${USERBYID.id}" name="id">
												<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">姓名
																/ Name</label>
														<div class="am-u-sm-9">
														<input type="text" name="loginname" onblur="chkloginname() "value="${USERBYID.loginname}" id="loginname" readonly="readonly"> 
														<small>用户登录名长度不得小于5位 <span id="loginnamemsg"></span></small>
														</div>
												</div>
												<div class="am-form-group">
													<label for="user-name" class="am-u-sm-3 am-form-label">密码/ Password</label>
														<div class="am-u-sm-9">
															<input type="password" id="password" name="password"onblur="chkPwd()" value="${USERBYID.password}"> 
															<small>密码要求不小于6位<span id="passwordmsg"></span></small>
														</div>
												</div>
												<div class="am-form-group">
													<label for="user-name" class="am-u-sm-3 am-form-label">密码/ Password</label>
													<div class="am-u-sm-9">
														<input type="password" id="chkpwd" name="chkpwd" onblur="chkRePwd()" value="${USERBYID.password}"> 
														<small>两次密码必须一致<span id="chkpasswordmsg"></span></small>
													</div>
												</div>

												<div class="am-form-group">
													<label for="user-name" class="am-u-sm-3 am-form-label">真实姓名/ Realname</label>
													<div class="am-u-sm-9">
														<input type="text" id="realname" name="realname" onblur="chkRealName()" value="${USERBYID.realname}"> 
														 <small>需要填写真实姓名(两个汉字以上)<span id="realnamemsg"></span></small>
													</div>
												</div>
												<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">部门/Dep</label>
														<div class="am-u-sm-9">

																<select name="dep" style="width: 300px" id="dep">
																		<c:forEach items="${DEPS}" var="dep">
																				<c:if test="${dep.id==USERBYID.dep}">
																						<option value="${dep.id}" selected="selected">${dep.depname}</option>
																				</c:if>
																				<c:if test="${dep.id!=USERBYID.dep}">
																						<option value="${dep.id}">${dep.depname}</option>
																				</c:if>
																		</c:forEach>
																</select> <small></small>
														</div>
												</div>
												<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">性别
																/ gender</label>
														<div class="am-u-sm-9">
																<c:if test="${USERBYID.sex==null}">
																		<input type="radio" name="sex" checked="checked"
																				value="男">男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
																				type="radio" name="sex" value="女">女 
														</c:if>
																<c:if test="${USERBYID.sex=='男'}">
																		<input type="radio" name="sex" checked="checked"
																				value="男">男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
																				type="radio" name="sex" value="女">女 
																		</c:if>
																<c:if test="${USERBYID.sex=='女'}">
																		<input type="radio" name="sex"
																				value="男">男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
																				type="radio" name="sex"  checked="checked" value="女">女 
																		</c:if>
																<small></small>
														</div>

												</div>
												<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">是否可用
																/ enable</label>
														<div class="am-u-sm-9">
																<c:if test="${USERBYID.enable==1}">
																		<input type="radio" name="enable" checked="checked"
																				value="1">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
																				type="radio" name="enable" value="0">否 
														</c:if>
																<c:if test="${USERBYID.enable==0}">
																		<input type="radio" name="enable" 
																				value="1">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
																				type="radio" name="enable" checked="checked" value="0">否
																		</c:if>

																<small></small>
														</div>

												</div>
												<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">出生年月/Birthday</label>
														<div class="am-u-sm-9">
														<input type="text" id="birthday" name="birthday" onblur="chkBirthday()"	class="am-form-field tpl-form-no-bg" placeholder=""value="${USERBYID.birthday}" data-am-datepicker=""readonly /> 
														<small><span id="birthdaymsg"></span></small>
														</div>
												</div>
												<div class="am-form-group">
													<label for="user-email" class="am-u-sm-3 am-form-label">电子邮件/ Email</label>
													<div class="am-u-sm-9">
														<input type="text" id="email" name="email"value="${ USERBYID.email}" onblur="chkEmail()">
														<small>邮箱你懂得...<span id="emailmsg"></span></small>
													</div>
												</div>

												<div class="am-form-group">
														<div class="am-u-sm-9 am-u-sm-push-3">
																<input class="am-btn am-btn-primary" type="submit"
																		value="修改" /> <input class="am-btn am-btn-primary"
																		type="reset" value="重置" />
																<!-- <button type="button" class="am-btn am-btn-primary">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="am-btn am-btn-primary">重置</button> -->
														</div>
												</div>
										</form>
								</div>
						</div>
				</div>

		</div>

		<script
				src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
		<script
				src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</body>

</html>