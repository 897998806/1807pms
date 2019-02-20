package com.zscms.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.SysException;
import com.zscms.user.bean.ChannelBean;
import com.zscms.user.bean.DepBean;
import com.zscms.user.dao.ChannelDao;
import com.zscms.user.service.UserService;

public class InitDepServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//调用service 获得部门的方法
		UserService us = new UserService();
		try {
			List<DepBean> deps = us.getDep();
			// 把信息放入请求中
			req.setAttribute("DEPS", deps);
			// 转发到新增页面
			req.getRequestDispatcher("user/adduser.jsp").forward(req, resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
