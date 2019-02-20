package com.zscms.channel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.service.ChannelService;

public class ChannelDeletByIds extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//获得网页传入的全部要删除的id信息
				String []ids=req.getParameterValues("id");
				//创建service
				ChannelService cs=new ChannelService();
				try {
					//调用批量删除的方法
					cs.deleteChannelByIds(ids);
					//转发回列表页面
					req.getRequestDispatcher("channellist.do?page=1").forward(req, resp);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resp.sendRedirect("error.html");
				} catch (SysException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resp.sendRedirect("error.html");
				} catch (AppException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					req.setAttribute("MSG", e.getErrMsg());
					req.getRequestDispatcher("channellist.do?page=1").forward(req, resp);
				}
		
		
		
	
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
