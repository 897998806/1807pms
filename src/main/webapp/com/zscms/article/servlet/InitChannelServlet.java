package com.zscms.article.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.SysException;
import com.zscms.user.bean.GetChannelBean;
import com.zscms.user.service.ArticleService;

public class InitChannelServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
ArticleService as=new  ArticleService();
//调用service 获得栏目的方法
	try {
		List<GetChannelBean> channels=as.getChannel();
		//把信息放入请求中
		req.setAttribute("CHANNEL", channels);
		// 转发到新增页面
		req.getRequestDispatcher("article/addarticle.jsp").forward(req, resp);
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
