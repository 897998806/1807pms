<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 导入标签库 -->
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>栏目列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/style/adminStyle.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/public.js"></script>
</head>
<body>
	<div class="wrap">
		<div class="page-title">
			<span class="modular fl"><i class="channel"></i><em>栏目列表</em></span> <span
				class="modular fr"><a href="initpid.do" class="pt-link-btn">+添加新栏目</a></span>
		</div>
		<div class="operate">
			<form id="myform" name="myform" action="channellistlike.do?page=1" method="post".onsubmit="return chk1()">
				<input type="text" class="textBox length-long" name="like"
					placeholder="请输入用户ID" /> <input type="submit" value="查询"
					class="tdBtn" />
			</form>
		</div>
		<form name="update" action="" method="post" onsubmit="return chk()">
			<table class="list-style Interlaced">
				<tr>
					<th>ID</th>
					<th>栏目名</th>
					<th>上级栏目</th>
					<th>级别</th>
					<th>是否叶子</th>
					<th>顺序</th>
					<th colspan="2">操作</th>
				</tr>

				<c:forEach items="${CHANNELS}" var="channel">
				<tr>
					<td>
					<input type="checkbox" name="id" id="id" value="${channel.id}" />
						 <span class="middle">${channel.id}</span>
					</td>
					<td class="center">${channel.cname}</td>
					<td class="center">${channel.name}</td>
					<td class="center">${channel.lev}</td>
					<td class="center">
						<!-- 判断是否叶子 -->
						<c:if test="${channel.isleaf==1}">
							<img src="${pageContext.request.contextPath}/images/yes.gif" />
						</c:if> 
						<c:if test="${channel.isleaf==0}">
						<img src="${pageContext.request.contextPath}/images/no.gif" />
						</c:if>
					</td>
					<td class="center">${channel.sort}</td>
					<!-- 是否删除-->
					<td class="center">
					<a href="channelbyid.do?id=${channel.id}">
					<img src="${pageContext.request.contextPath}/images/icon_edit.gif" /></a></td>
					<td>
					<a onclick="return confirm('是否删除？')" href="channeldelete.do?id=${channel.id}">
					<img src="${pageContext.request.contextPath}/images/icon_drop.gif" /></a></td>
				</tr>
				</c:forEach>
			</table>
			<!-- BatchOperation -->
			<div style="overflow: hidden;">
				<!-- Operation -->
				<div class="BatchOperation fl">
					<input type="checkbox" id="del" onchange="allcheck()" /> <label
						for="del" class="btnStyle middle">全选</label> <input type="submit"
						value="批量删除" class="btnStyle" />
				</div>
				<!-- turn page -->
				<div class="turnPage center fr">
										<a href="channellistlike.do?page=1&like=${LIKE}">第一页</a> 
										<a href="channellistlike.do?page=${PAGE>1?PAGE-1:1}&like=${LIKE}">上一页</a> 
										<a href="channellistlike.do?page=${PAGE==PAGECONT?PAGE:PAGE+1}&like=${LIKE}">下一页</a> 
										<a href="channellistlike.do?page=${PAGECONT}&like=${LIKE}">最后一页</a> 第${PAGE}/共${PAGECONT}页,${COUNT}条信息
				</div>
			</div>
		</form>
	</div>
</body>
</html>