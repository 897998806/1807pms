package com.zscms.channel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.service.ChannelService;


public class ChannelDeleteServlet extends HttpServlet {
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取用户id信息
		int id=0;
		if (req.getParameter("id")!=null) {
			id=Integer.parseInt(req.getParameter("id"));
		}
		//创建userservice对象
		ChannelService cs=new ChannelService();
		try {
			//调用us的方法通过id 删除
			cs.deleteChannel(id);
			//删除成功转发的页面
			req.getRequestDispatcher("channellist.do?page=1").forward(req, resp);
		} catch (SysException e) {
			//系统异常页面进行错误页面
			resp.sendRedirect("error.html");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("CHANNEL", e.getErrMsg());
			//失败转发的页面
			req.getRequestDispatcher("channellist.do").forward(req, resp);
		}
		
		
	}
}
