package com.zscms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.UserBean;
import com.zscms.user.service.UserService;


/**
 * 这个类是用来修改用户信息的servlet
 * 
 * @author Administrator
 *
 */
public class UserUpDateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取所以信息 注意：新增id 是用过自增来生成 修改的从页面获得id属性 多封装一个id属性
		UserBean user = new UserBean();
		if (req.getParameter("id") != null) {
			user.setId(Integer.parseInt(req.getParameter("id")));
		}
		
			user.setBirthday(req.getParameter("birthday"));
		
		if (req.getParameter("dep") != null) {
			user.setDep(Integer.parseInt(req.getParameter("dep")));
		}
		//是否可以修改的信息所以从页面获得修改的值
		if (req.getParameter("enable")!=null) {
			user.setEnable(Integer.parseInt(req.getParameter("enable")));
		}
		
		user.setLoginname(req.getParameter("loginname"));
		user.setPassword(req.getParameter("password"));
		user.setSex(req.getParameter("sex"));
		user.setEmail(req.getParameter("email"));
		user.setRealname(req.getParameter("realname"));
		// 调用userservice
		UserService us = new UserService();
		try {
			//调用更新方法
			us.updateuser(user);
			// 修改完回到用户列表页，需要走列别页的service把列别信息带入
			req.getRequestDispatcher("userlist.do?page=1").forward(req, resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 系统异常页面进行错误页面
			resp.sendRedirect("error.html");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 修改失败
			req.setAttribute("USERERR", e.getErrMsg());
			// 页面跳回新增页面
			req.getRequestDispatcher("toupdatepage.do").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
