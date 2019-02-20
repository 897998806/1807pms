<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<!-- 要使用jstl 导入标签库 -->
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/style/adminStyle.css"
		rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/public.js"></script>

</head>
<body>
		<div class="wrap">
				<div class="page-title">
						<span class="modular fl"><i class="user"></i><em>广告列表</em></span>
						<span class="modular fr"><a href="message/addmessage.jsp"
								class="pt-link-btn">+添加新广告</a></span>
				</div>
				<div class="operate">
						<form id="myform" name="myform" action="messagelistlike.do?page=1" method="post"
								onsubmit="return chk1()">
								<input type="text" class="textBox length-long" name="like"
										placeholder="请输入广告标题" /> <input type="submit" value="查询"
										class="tdBtn" />
						</form>
				</div>
				<form name="update" action="" method="post" onsubmit="return chk()">
						<table class="list-style Interlaced">
								<tr>
										<th>ID</th>
										<th>标题</th>
										<th>内容</th>
										<th>公告时间</th>
										<th>公告人</th>
										<th colspan="2">操作</th>

								</tr>
								<c:forEach items="${MESSAGES}" var="message">
										<tr>
												<td><input type="checkbox" name="id" id="id"
														value="${message.id}" /> <span class="middle">${message.id}</span></td>
												<td class="center">${message.title}</td>
												<td class="center">${message.content}</td>
												<td class="center">${message.ctime}</td>
												<td class="center">${message.cman}</td>
											<!--修改   删除-->									
												<td class="center">
												<a href="messagebyid.do?id=${message.id}" ><img src="${pageContext.request.contextPath}/images/icon_edit.gif" /></a>
												</td>
												<td><a onclick="return confirm('确定删除吗？ ')" href="messagedelete.do?id=${message.id}"><img
																src="${pageContext.request.contextPath}/images/icon_drop.gif" /></a>

												</td>
										</tr>

								</c:forEach>

						</table>
						<!-- BatchOperation -->
						<div style="overflow: hidden;">
								<!-- Operation -->
								<div class="BatchOperation fl">
										<input type="checkbox" id="del" onchange="allcheck()" /> <label
												for="del" class="btnStyle middle">全选</label> <input
												type="submit" value="批量删除" class="btnStyle" />
								</div>
								<!-- turn page -->
							<div class="turnPage center fr">
										<a href="messagelistlike.do?page=1&like=${LIKE}">第一页</a> 
										<a href="messagelistlike.do?page=${PAGE>1?PAGE-1:1}&like=${LIKE}">上一页</a> 
										<a href="messagelistlike.do?page=${PAGE==PAGECONT?PAGE:PAGE+1}&like=${LIKE}">下一页</a> 
										<a href="messagelistlike.do?page=${PAGECONT}&like=${LIKE}">最后一页</a> 第${PAGE}/共${PAGECONT}页,${COUNT}条信息
								</div>
						</div>
				</form>
		</div>

</body>
</html>