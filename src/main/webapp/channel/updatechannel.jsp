<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<!-- 导入标签库 -->
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
//定义全局变量 
var cnamestr="";
//实用就绪事件，来获得修改之前栏目名信息
window.onload=function(){
	 cnamestr=$("#cname").val();
	
}

//这是修改栏目不能为空的验证  
function ChannelCname() {
	var str=document.getElementById("cname").value;
	if (str==cnamestr&&str!="") {
		$("#cnamemsg").html("√");
		$("#cnamemsg").css("color", "green");
		return true;
	}
	var msg= document.getElementById("cnamemsg");
	if(str!=""){
		if (chkCnameExist(str)) {
			return true;
		} else {
			return false;
		}
		return true;
	}else {
		msg.innerHTML="×栏目名不能为空，请重新输入！ ";
		msg.style.color="red ";
		return false;
	}
	
}
//验证栏目名是否重复
function chkCnameExist(cname) {
	var flag = false;
	$.ajax({
		//请求方式 
		type : 'post',
		//请求路径
		url : 'channelexist.do',
		//请求参数 
		data : 'cname=' + cname,
		//是否同步请求 true 为异步 
		async : false,
		//请求数据类型
		dataType : 'text',
		success : function(data) {
			//true 表示有重复的用户名
			if (data == "true") {
				$("#cnamemsg").html("该栏目名已被占用...");
				$("#cnamemsg").css("color", "red");
				flag = false;
			} else {
				$("#cnamemsg").html("√");
				$("#cnamemsg").css("color", "green");
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
//判断顺序不能为空的
function ChannelSort() {
		var sortStr=document.getElementById("sort").value;
		var msg= document.getElementById("sortmsg");
		if(sortStr!=""){
			msg.innerHTML="√";
			msg.style.color="green";
			return true;
		}else {
			msg.innerHTML="×顺序不能为空，请重新输入！ ";
			msg.style.color="red ";
			return false;
		}
		
	}
	//点击提交后验证 
function chkSubmit() {
	return ChannelCname()&&ChannelSort();
}

</script>
<!-- 提示信息样式 -->
<style type="text/css">
#cnamemsg,#sortmsg{
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
							<span class="am-icon-code"></span> 栏目修改 
							<a href="channellist.do?page=1" style="margin-left: 650px">返回</a>
						</div>
						<div class="tpl-portlet-input tpl-fz-ml">
								<div class="portlet-input input-small input-inline"></div>
						</div>
				</div>
				<div class="tpl-block ">
						<div class="am-g tpl-amazeui-form">
								<div class="am-u-sm-12 am-u-md-9">
										<form id="myform" name="myform"
												class="am-form am-form-horizontal" action="channelupdate.do"method="post" onsubmit="return chkSubmit()">
												<input type="hidden" value="${CHANNELBYID.id}" name="id">
												<!-- 栏目名 -->
												<div class="am-form-group">
														<label for="channel-name" class="am-u-sm-3 am-form-label">栏目名/Name</label>
														<div class="am-u-sm-9">
																<input type="text" value="${CHANNELBYID.cname}"	id="cname" onblur="ChannelCname()" name="cname">
																 <small><span id="cnamemsg" ></span></small>
														</div>
												</div>
												<!-- 上级栏目 -->
												<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">上级栏目/</label>
														<div class="am-u-sm-9">
																<select name="pid" style="width: 300px">
																		<c:forEach items="${PIDS}" var="channel">
																				<option value="${channel.id}">${channel.cname}</option>
																		</c:forEach>
																</select> <small></small>
														</div>
												</div>

												<!-- 级别 -->
												<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">级别/Level</label>
														<div class="am-u-sm-9">
																<select name="lev" style="width: 300px">
																		<option value="1">1</option>
																		<option value="2">2</option>
																</select> <small></small>
														</div>
												</div>

												<!-- 是否叶子 -->
												<div class="am-form-group">
														<label for="user-name" class="am-u-sm-3 am-form-label">是否叶子/Isleaf</label>
														<div class="am-u-sm-9">
																<!-- <input type="radio" name="isleaf" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input type="radio" name="isleaf" value="0">否 <small></small> -->
																<c:if test="${CHANNELBYID.isleaf==1}">
																		<input type="radio" name="isleaf" checked="checked"
																				value="1">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input type="radio" name="isleaf" value="0">否 <small></small>
																</c:if>
																<c:if test="${CHANNELBYID.isleaf==0}">
																		<input type="radio" name="isleaf" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input type="radio" name="isleaf" checked="checked" value="0">否 <small></small>
																</c:if>
														</div>
												</div>

												<!-- 顺序 -->
											<div class="am-form-group">
												<label for="user-name" class="am-u-sm-3 am-form-label">顺序/Sort</label>
													<div class="am-u-sm-9">
														<input type="text" id="sort" name="sort" onblur="ChannelSort()"  placeholder=""	style="float: left;" value="${CHANNELBYID.sort}">
														<small> <span id="sortmsg" ></span></small>
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