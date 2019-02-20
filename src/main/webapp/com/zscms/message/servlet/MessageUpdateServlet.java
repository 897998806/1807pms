package com.zscms.message.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;

import com.zscms.user.bean.MessageBean;
import com.zscms.user.service.MessageService;

public class MessageUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获得页面输入信息 并封装
		MessageBean message = new MessageBean();
		if (req.getParameter("id") != null) {
			message.setId(Integer.parseInt(req.getParameter("id")));
		}
		message.setTitle(req.getParameter("title"));
		message.setContent(req.getParameter("content"));
		message.setCtime(req.getParameter("ctime"));
		message.setCman(req.getParameter("cman"));
		MessageService ms = new MessageService();
		try {
			ms.updateMessage(message);
			// 新增完回到用户列表页，需要走列别页的service把列别信息带入
			req.getRequestDispatcher("messagelist.do?page=1").forward(req, resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 系统异常页面进行错误页面
			resp.sendRedirect("error.html");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 应用异常把异常信息放入请求
			req.setAttribute("USERERR", e.getErrMsg());
			// 页面跳回新增页面
			req.getRequestDispatcher("message/addmessage.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp.sendRedirect("error.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
