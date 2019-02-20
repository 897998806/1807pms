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

public class ArticleUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取所有信息 注意：新增id 是用过自增来生成 修改的从页面获得id属性 多封装一个id属性
		ArticleBean article = new ArticleBean();
		if (req.getParameter("id") != null) {
			article.setId(Integer.parseInt(req.getParameter("id")));
		}
		if (req.getParameter("channel") != null) {
			article.setChannel(Integer.parseInt(req.getParameter("channel")));
		}
		if (req.getParameter("isremod") != null) {
			article.setIsremod(Integer.parseInt(req.getParameter("isremod")));
		}
		if (req.getParameter("ishot") != null) {
			article.setIshot(Integer.parseInt(req.getParameter("ishot")));
		}
		article.setTitle(req.getParameter("title"));
		article.setContent(req.getParameter("content"));
		article.setAuthor(req.getParameter("author"));
		article.setCrtime(req.getParameter("crtime"));
		// 调用articleservice
		ArticleService as = new ArticleService();
		try {
			// 调用更新的方法
			as.updateArticle(article);
			// 修改完回到用户列表，需要走列表页面把新增的信息带过来
			req.getRequestDispatcher("articlelist.do?page=1").forward(req, resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 系统异常页面进行错误页面
			resp.sendRedirect("error.html");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 修改失败
			// 修改失败跳回新增页面
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
