<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>文章新增</title>
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
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script type="text/javascript">
/* //文章 广告 栏目 不用判空， 我觉得可以用户这个条 正则，
var CHK="^[\u4e00-\u9fa5]{2,}$"; */
//这是新增标题的 验证  
 function ArticleTitle() {
	var titleStr=document.getElementById("title").value;
	var msg= document.getElementById("titlemsg");
	if(titleStr!=""){
		if (chkTitleExist(titleStr)) {
			return true;
		} else {
			return false;
		}

	}else {
		msg.innerHTML="×标题不能空，请重新输入！ ";
		msg.style.color="red ";
		return false;
	}
	
}

//验证文章标题是否重复
function chkTitleExist(title) {
	var flag = false;
	$.ajax({
		//请求方式 
		type : 'post',
		//请求路径
		url : 'articleexist.do',
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
 function ArticleContent() {
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
 function ArticleAuthor() {
		var authorStr=document.getElementById("author").value;
		var msg= document.getElementById("authormsg");
		if(authorStr!=""){
			msg.innerHTML="√";
			msg.style.color="green";
			return true;
		}else {
			msg.innerHTML="×作者不能为空，请重新输入！ ";
			msg.style.color="red ";
			return false;
		}
		
	}
	//判断时间不能为空
	function ArticleCrtime() {
		var crtimeStr=document.getElementById("crtime").value;
		var msg=document.getElementById("crtimemsg");
		if (crtimeStr!="") {
			msg.innerHTML="√";
			msg.style.color="green";
			return true;
		}else {
			msg.innerHTML="×时间不能为空，请重新输入！ ";
			msg.style.color="red ";
			return false;
		}
	}
	//点击提交后验证 
 function chkSubmit() {
	return ArticleTitle()&&ArticleContent()&&ArticleAuthor()&&ArticleCrtime();
}

</script>
<!-- 提示信息样式 -->
<style type="text/css">
#titlemsg,#contentmsg,#authormsg{
position: absolute;
left: 630px;
top: 7px;
font-size: 14px;
width: 200px;
}
</style>
</head>

<body data-type="generalComponents">


	<header> </header>

	<div class="tpl-portlet-components">
		<div class="portlet-title">
			<div class="caption font-green bold">
				<span class="am-icon-code"></span> 文章新增 <a href="articlelist.do?page=1"
					style="margin-left: 650px">返回</a>
			</div>
			<div class="tpl-portlet-input tpl-fz-ml">
				<div class="portlet-input input-small input-inline"></div>
			</div>

		</div>
	<div class="tpl-block ">
		<div class="am-g tpl-amazeui-form">
	<!-- 标题 -->
			<div class="am-u-sm-12 am-u-md-9">
				<form id="myform" name="myform" class="am-form am-form-horizontal"action="articleadd.do" method="post" onsubmit="return chkSubmit()">
					<div class="am-form-group">
						<label for="article-name" class="am-u-sm-3 am-form-label">标题/Title</label>
						<div class="am-u-sm-9">
							<input type="text" onblur="ArticleTitle()" id="title" name="title"  placeholder="标题 /title" style="float: left;"> 
							<small><span id="titlemsg" ></span></small>
							</div>
						</div>
	<!-- 内容 -->					
						<div class="am-form-group">
							<label for="article-name" class="am-u-sm-3 am-form-label">内容/content</label>
							<div class="am-form-group">
                                 <label for="user-intro" class="am-u-sm-3 am-form-label"></label>
                                  <div class="am-u-sm-9">
                 					<textarea class="" rows="10" id="content" onblur="ArticleContent()" placeholder="请输入文章内容/Content" name="content"></textarea>
                                   <small><span id="contentmsg" ></span></small>
                                    </div>
                                </div>
						</div>
	<!--作者  -->
						<div class="am-form-group">
							<label for="article-name" class="am-u-sm-3 am-form-label">作者/author</label>
							<div class="am-u-sm-9">
								<input type="text" id="author" onblur="ArticleAuthor()" name="author"placeholder="作者/author" style="float: left;">
									 <small><span id="authormsg" ></span></small>
							</div>
						</div>
	<!-- 所属栏目-->
						<div class="am-form-group">
							<label for="article-name" class="am-u-sm-3 am-form-label">所属栏目/channel</label>
							<div class="am-u-sm-9">
								<select name="channel" style="width: 300px">
									<c:forEach items="${CHANNEL}" var="channel">
										<option value="${channel.id}">${channel.cname}</option>
									</c:forEach>
								</select> <small></small>
							</div>
						</div>
	<!-- 是否推荐 -->
					<div class="am-form-group">
						<label for="article-name" class="am-u-sm-3 am-form-label">是否推荐/isremod</label>
						<div class="am-u-sm-9">
							<input type="radio" name="isremod" checked="checked" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
								name="isremod" value="0">否<small></small>
						</div>
					</div>
	<!-- 是否热点 -->
				<div class="am-form-group">
					<label for="article-name" class="am-u-sm-3 am-form-label">是否热点/ishot</label>
					<div class="am-u-sm-9">
						<input type="radio" name="ishot" checked="checked" value="1">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"
							name="ishot" value="0">否<small></small>
					</div>
				</div>
	<!-- 修改日期 -->	
				<div class="am-form-group">
					<label for="article-name" class="am-u-sm-3 am-form-label">添加日期/crtime</label>
					<div class="am-u-sm-9">
					<input type="text" id="crtime" onblur="setTimeout('ArticleCrtime()','1000')" name="crtime"class="am-form-field tpl-form-no-bg" placeholder="添加日期"data-am-datepicker="" readonly />
					<small><span id="crtimemsg"></span></small>
					</div>
				</div>
		<!--提交的按键  -->
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input class="am-btn am-btn-primary" type="submit" value="提交" />
								<input class="am-btn am-btn-primary" type="reset" value="重置" />
								<!-- <button type="button" class="am-btn am-btn-primary">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <button type="button" class="am-btn am-btn-primary">重置</button> -->
							</div>
						</div>
					</form>
				</div>
			</div>
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