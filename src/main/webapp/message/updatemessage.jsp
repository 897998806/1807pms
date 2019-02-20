<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>广告修改</title>
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
var titlestr="";
//使用就绪事件，来获得修改之前标题名信息
window.onload=function(){
	titlestr=$("#title").val();
}
//判断创建人的 正则 可用可不用
var CHKCMAN="^[\u4e00-\u9fa5]{2,}$"; 
//这是修改标题不能为空的验证方法 
function MessageTitle() {
	var str=document.getElementById("title").value;
	if (str==titlestr&&str!="") {
		$("#titlemsg").html("√");
		$("#titlemsg").css("color", "green");
		return true;
	}
	var msg= document.getElementById("titlemsg");
	if(str!=""){
		if (chkTitleExist(str)) {
			return true;
		}else {
			return false;
		}
		ret
	}else {
		msg.innerHTML="×标题不能空，请重新输入！ ";
		msg.style.color="red ";
		return false;
	}
	
}
//验证广告标题是否重复
function chkTitleExist(title) {
	var flag = false;
	$.ajax({
		//请求方式 
		type : 'post',
		//请求路径
		url : 'messageexist.do',
		//请求参数 
		data : 'title=' + title,
		//是否同步请求 true 为异步 
		async : false,
		//请求数据类型
		dataType : 'text',
		success : function(data) {
			//true 表示有重复的用户名
			if (data == "true") {
				$("#titlemsg").html("该标题已被占用...");
				$("#titlemsg").css("color", "red");
				flag = false;
			} else {
				$("#titlemsg").html("√");
				$("#titlemsg").css("color", "green");
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


//判断内容不能为空的
function MessageContent() {
		var contentStr=document.getElementById("content").value;
		var msg= document.getElementById("contentmsg");
		if(contentStr!=""){
			msg.innerHTML="√";
			msg.style.color="green";
			return true;
		}else {
			msg.innerHTML="×内容不能空，请重新输入！ ";
			msg.style.color="red ";
			return false;
		}
		
	}
//判断作者不能为空的
function MessageCman() {
		var cmanStr=document.getElementById("cman").value;
		// 创建正则对象 
		var reg =new RegExp(CHKCMAN);
		var msg= document.getElementById("cmanmsg");
		if(reg.test(cmanStr)){
			msg.innerHTML="√";
			msg.style.color="green";
			return true;
		}else {
			msg.innerHTML="×作者不符合规范，请重新输入！ ";
			msg.style.color="red ";
			return false;
		}
		
	}
//验证时间不能为空的 
function MessageCtime() {
	var ctimeStr=document.getElementById("ctime").value;
	//创建正则对象 
		var msg=document.getElementById("ctimemsg");
		if(ctimeStr!=""){
			msg.innerHTML="√";
			msg.style.color="green";
			return true;
		}else {
			msg.innerHTML="×时间不能为空 ，请重新输入！ ";
			msg.style.color="red ";
			return false;
		}
		
	}
			//点击提交后验证 
function chkSubmit() {
	return MessageTitle()&&MessageContent()&&MessageCman()&&MessageCtime();
	}
			</script>
<!-- 提示信息样式 -->
<style type="text/css">
#titlemsg,#contentmsg,#cmanmsg,#ctimemsg{
position: absolute;
left: 635px;
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
								<span class="am-icon-code"></span> 广告修改 
								<a href="messagelist.do?page=1" style="margin-left: 650px">返回</a>
						</div>
						<div class="tpl-portlet-input tpl-fz-ml">
								<div class="portlet-input input-small input-inline"></div>
						</div>

				</div>
				<div class="tpl-block ">

						<div class="am-g tpl-amazeui-form">

								<div class="am-u-sm-12 am-u-md-9">
										<form id="myform" name="myform"
												class="am-form am-form-horizontal" action="messageupdate.do"method="post" onsubmit="return chkSubmit()">
												<input type="hidden" value="${MESSAGEBYID.id}" name="id">
											<div class="am-form-group">
	<!-- 修改标题 -->
												<label for="advertise-name" class="am-u-sm-3 am-form-label">标题
																/ title</label>
												<div class="am-u-sm-9">
													<input type="text" name="title"	value="${MESSAGEBYID.title}" id="title" onblur="MessageTitle()"> 
													<small>标题的要求：不能为空<span id="titlemsg"></span></small>
												</div>
											</div>
	<!--修改内容  -->
												<div class="am-form-group">
												<label for="user-name" class="am-u-sm-3 am-form-label">内容/ content</label>
												<div class="am-u-sm-9">
												<textarea class="" rows="10" id="content" placeholder="请输入文章内容/Content" name="content"onblur="MessageContent()">${MESSAGEBYID.content}</textarea>
												<small><span id="contentmsg"></span></small>
												</div>
												</div>

<!-- 修改创建时间 -->
												<div class="am-form-group">
														<label for="advertise-name" class="am-u-sm-3 am-form-label">创建时间/ctime</label>
														<div class="am-u-sm-9">
														<input type="text" id="ctime" onblur="MessageCtime()" name="ctime"class="am-form-field tpl-form-no-bg"placeholder="创建时间" value="${MESSAGEBYID.ctime}" data-am-datepicker="" readonly />
														<small><span id="ctimemsg" ></span></small>
														</div>
												</div>
<!-- 修改创建人 -->
												<div class="am-form-group">
													<label for="cman" class="am-u-sm-3 am-form-label">创建人/ cman</label>
													<div class="am-u-sm-9">
														<input type="text" id="cman" name="cman" onblur="MessageCman()" value="${MESSAGEBYID.cman}"placeholder="创建人 / cman"> 
														<small>创建人建议两个汉字以上<span id="cmanmsg"></span></small>
													</div>
												</div>
<!-- 提交按钮 -->
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