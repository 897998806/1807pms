package com.zscms.channel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.ChannelBean;
import com.zscms.user.bean.DepBean;
import com.zscms.user.bean.UserBean;
import com.zscms.user.service.ChannelService;
import com.zscms.user.service.UserService;
/**
 * 这个类是用来控制根据id修改内容的 控制类
 * @author Administrator
 *
 */
public class ChannelByIdServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获得页面输入的用户id信息
		 int id=0;
		 if (req.getParameter("id")!=null) {
			id= Integer.parseInt(req.getParameter("id"));
		}
		 //创建UserService对象
		 ChannelService cs=new ChannelService();
		 try {
			 //调用us的通过id查询方法来获得全部的用户信息
			ChannelBean channel = cs.queryChannelById(id);
			//信息放入请求
			req.setAttribute("CHANNELBYID", channel);
			//把全部的上级部门信息带入页面
			List<ChannelBean> channels=cs.getPid();
			req.setAttribute("PIDS", channels);
			//转发到修改页面
			req.getRequestDispatcher("channel/updatechannel.jsp").forward(req, resp);
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
