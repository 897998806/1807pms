package com.zscms.article.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.service.ArticleService;

public class ArticleDeleteServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//获取文章id信息
	int id=0;
	if (req.getParameter("id")!=null) {
		id=Integer.parseInt(req.getParameter("id"));
	}
	//创建articleservice对象
	ArticleService as= new ArticleService();
	//调用as方法 通过id删除
	try {
		as.deleteArticle(id);
		req.getRequestDispatcher("articlelist.do?page=1").forward(req, resp);
	} catch (SysException e) {
		//系统异常页面进行错误页面
		resp.sendRedirect("error.html");
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (AppException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		req.setAttribute("ARTICLEERR", e.getErrMsg());
		//失败转发的页面
		req.getRequestDispatcher("articlelist.do").forward(req, resp);
	}
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doGet(req, resp);
	}
}
