package com.zscms.channel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.SysException;
import com.zscms.user.bean.ChannelBean;
import com.zscms.user.bean.DepBean;
import com.zscms.user.service.ChannelService;

import sun.nio.cs.Surrogate.Generator;

/**
 * 这里是获得Pid的servlet类 用于在栏目修改页面或添加页面下拉菜单显示栏目内容
 * @author Administrator
 */
public class InitPidServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//调用service 获得上级栏目的pid的方法
		ChannelService cs=new ChannelService();
		try {
			List<ChannelBean> pids=	cs.getPid();
			//把信息放入请求中， 方便在修改或者添加时调用
			req.setAttribute("CHANNELS", pids);
			//转发到新增页面
			req.getRequestDispatcher("channel/addchannel.jsp").forward(req, resp);
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

