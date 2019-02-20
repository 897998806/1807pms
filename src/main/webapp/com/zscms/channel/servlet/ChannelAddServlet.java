package com.zscms.channel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.ChannelBean;

import com.zscms.user.service.ChannelService;

public class ChannelAddServlet extends HttpServlet {
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获得页面输入信息 并封装
		   ChannelBean channel=new  ChannelBean();
		   if (req.getParameter("id")!=null) {
			   channel.setId(Integer.parseInt(req.getParameter("id")));
		}
		   if (req.getParameter("pid")!=null) {
			   channel.setPid(Integer.parseInt(req.getParameter("pid")));
		}
		   if (req.getParameter("lev")!=null) {
			   channel.setLev(Integer.parseInt(req.getParameter("lev")));
		}
		   if (req.getParameter("isleaf")!=null) {
			   channel.setIsleaf(Integer.parseInt(req.getParameter("isleaf")));
		}
		   if (req.getParameter("sort")!=null) {
			   channel.setSort(Integer.parseInt(req.getParameter("sort")));
		}
		  channel.setCname(req.getParameter("cname"));
		  ChannelService cs=new ChannelService();
		  try {
			cs.addChannel(channel);
			//新增完回到用户列表页，需要走列别页的service把列别信息带入
			req.getRequestDispatcher("channellist.do?page=1").forward(req, resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//系统异常页面进行错误页面
			resp.sendRedirect("error.html");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//应用异常把异常信息放入请求
			req.setAttribute("USERERR", e.getErrMsg());
			//页面跳回新增页面
			req.getRequestDispatcher("channel/addchannel.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
