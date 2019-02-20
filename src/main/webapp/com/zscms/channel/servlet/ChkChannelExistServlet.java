package com.zscms.channel.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.SysException;
import com.zscms.user.service.ChannelService;

public class ChkChannelExistServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//创建service对象
		ChannelService cs=new ChannelService();
		//定义表示返回的结果
		String flag=null;
			//获得传入的用户名
			String cname=req.getParameter("cname");
			 try {
				if (cs.chkExistCname(cname)) {
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
		
		//将结果写出,使用print（）或者println()方法输出字符串有空格，所以匹配不成功
		resp.getWriter().write(flag);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}