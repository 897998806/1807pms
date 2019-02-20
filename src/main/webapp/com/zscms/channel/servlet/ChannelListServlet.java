package com.zscms.channel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.SysException;
import com.zscms.user.bean.ChannelBean;
import com.zscms.user.service.ChannelService;
import com.zscms.util.Constants;


public class ChannelListServlet extends HttpServlet {
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page=0;
		if (req.getParameter("page")!=null) {
			
			page=Integer.parseInt(req.getParameter("page"));
		}
		//创建userservice对象
		ChannelService ch=new ChannelService();
		//调用service的用户全查方法
		try {
			
			//调用service的用户全查方法
			List<ChannelBean> channels = ch.queryByPage(page, Constants.NUM);
			//把全部的用户信息放到请求
			req.setAttribute("CHANNELS", channels);
			//总页数信息
			int pageCont=ch.getCountPage();
			req.setAttribute("PAGECONT", pageCont);
			//总条数信息
			int count=ch.getCount();
			req.setAttribute("COUNT", count);
			//把当前也信息回传给jsp
			req.setAttribute("PAGE", page);
			//转发到用户列表页面
			//转发到用户列表页面
			req.getRequestDispatcher("channel/channel_list.jsp").forward(req, resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
