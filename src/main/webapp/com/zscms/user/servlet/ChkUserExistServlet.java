package com.zscms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.SysException;
import com.zscms.user.service.UserService;

public class ChkUserExistServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获得type的值，通过type的值来判断进行用户名的检测，还是邮箱的检测
		String type=req.getParameter("type");
		//创建service对象
		UserService us=new UserService();
		//定义表示返回的结果
		String flag=null;
		switch (type) {
		case "1":
			//获得传入的用户名
			String loginname=req.getParameter("loginname");
			 try {
				if (us.chkExistLoginname(loginname)) {
					//表示有同名用户
					flag="true";
				} else {
					flag="false";
				}
			} catch (SysException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.getRequestDispatcher("error.html").forward(req, resp);
				return;
			}
			 break;
		case "2":
			//获得传入的邮箱
			String email=req.getParameter("email");
			try {
				if (us.chkExistEmail(email)) {
					//表示同名邮箱
					flag="true";
				} else {
					//表示不是重名可用
					flag="false";
				}
				
			} catch (SysException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.getRequestDispatcher("error.html").forward(req, resp);
				return;
			}
			break;
		default:
			break;
		}
		//将结果写出,使用print（）或者println()方法输出字符串有空格，所以匹配不成功
		resp.getWriter().write(flag);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
