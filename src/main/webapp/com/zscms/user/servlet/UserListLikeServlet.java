package com.zscms.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.SysException;
import com.zscms.user.bean.UserBean;
import com.zscms.user.service.UserService;
import com.zscms.util.Constants;

public class UserListLikeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 创建userservice对象
		// 页面获取当前页数
		int page = 0;
		if (req.getParameter("page") != null) {

			page = Integer.parseInt(req.getParameter("page"));
		}
		//获得模糊查询的关键字
		String like=req.getParameter("like");
		//创建service对象
		UserService us = new UserService();
		// 调用service的用户全查方法
		try {
			// 调用service的用户全查方法 page
			List<UserBean> users = us.queryByPageLike(like,page, Constants.NUM);
			// 把全部的用户信息放到请求
			req.setAttribute("USERS", users);
			// 总页数信息
			int pageCont = us.getCountPageLike(like);
			req.setAttribute("PAGECONT", pageCont);
			// 总条数信息
			int count = us.getCountLike(like);
			req.setAttribute("COUNT", count);
			// 把当前也信息回传给jsp
			req.setAttribute("PAGE", page);
			//把模糊查询的关键字回传给jsp
			req.setAttribute("LIKE", like);
			// 转发到用户列表页面
			req.getRequestDispatcher("user/user_listlike.jsp").forward(req, resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
