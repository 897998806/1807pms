package com.zscms.article.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.ArticleBean;
import com.zscms.user.service.ArticleService;

public class ArticleAddServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//获得页面输入的信息并封装
	ArticleBean article=new ArticleBean();
	if (req.getParameter("channel")!=null) {
		article.setChannel(Integer.parseInt(req.getParameter("channel")));
	}
	if (req.getParameter("isremod")!=null) {
		article.setIsremod(Integer.parseInt(req.getParameter("isremod")));
	}
	if (req.getParameter("ishot")!=null) {
		article.setIshot(Integer.parseInt(req.getParameter("ishot")));
	}
	article.setTitle(req.getParameter("title"));
	article.setContent(req.getParameter("content"));
	article.setAuthor(req.getParameter("author"));
	article.setCrtime(req.getParameter("crtime"));
	//创建service
	ArticleService as=new ArticleService();
	try {
		//调用serveic 新增用户方法
		as.addArticle(article);
		//新增完回到用户列表页，需要走列表页的service把列表信息带入
		req.getRequestDispatcher("articlelist.do?page=1").forward(req, resp);
	} catch (SysException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//系统异常页面进行错误页面
		resp.sendRedirect("error.html");
	} catch (AppException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//应用异常把异常信息放入请求
		req.setAttribute("ARTICLEERR", e.getErrMsg());
		//页面跳回新增页面
		req.getRequestDispatcher("articlelist.do?page=1").forward(req, resp);
	}
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doGet(req, resp);
	}
}
