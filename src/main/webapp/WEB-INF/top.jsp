<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>header</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/adminStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="header">
 <div class="logo">
  <img src="images/logo.png"  style="height: 65%; width: 85%; margin-top: 10px"/>
 </div>
 <div class="fr top-link">
  <i class="shopLinkIcon"></i><span></span>
  <a href="admin_list.html" target="mainCont" title="DeathGhost"><i class="adminIcon"></i><span>${TIME}${USER.realname}</span></a>
  
  <a href="revise_password.html" target="mainCont" title="修改密码"><i class="revisepwdIcon"></i><span>修改密码</span></a>
  <a   onclick="window.parent.location.href='tologin.do'" title="安全退出" style="background:rgb(60,60,60);"><i class="quitIcon"></i><span>安全退出</span></a>
 </div>
</div>
</body>
</html>