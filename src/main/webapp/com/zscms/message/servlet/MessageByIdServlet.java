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

public class MessageByIdServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获得页面输入的用户id信息
		 int id=0;
		 if (req.getParameter("id")!=null) {
			id= Integer.parseInt(req.getParameter("id"));
		}
		 //创建UserService对象
	MessageService ms=new MessageService();
		 try {
			 //调用us的通过id查询方法来获得全部的用户信息
			 MessageBean message = ms.queryMessageById(id);
			//信息放入请求
			req.setAttribute("MESSAGEBYID", message);
			/*//把全部的上级部门信息带入页面
			List<MessageBean> channels=cs.getPid();
			req.setAttribute("PIDS", channels);*/
			//转发到修改页面
			req.getRequestDispatcher("message/updatemessage.jsp").forward(req, resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AppException e) {
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
