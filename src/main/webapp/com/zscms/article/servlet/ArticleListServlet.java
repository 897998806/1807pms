package com.zscms.article.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.SysException;
import com.zscms.user.bean.ArticleBean;
import com.zscms.user.service.ArticleService;
import com.zscms.util.Constants;
/**
 * 文章列表的servlet
 * @author Administrator
 */

public class ArticleListServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doPost(req, resp);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	int page=0;
			if (req.getParameter("page")!=null) {
				page=Integer.parseInt(req.getParameter("page"));
			}
			//创建userservice对象
			ArticleService as=new ArticleService();
			//调用service的用户全查方法
			try {
				//调用service的用户全查方法 page
				List<ArticleBean> articles = as.queryByPage(page, Constants.NUM);
				//把全部的用户信息放到请求
				req.setAttribute("ARTICLE", articles);
				//总页数信息
				int pageCont=as.getCountPage();
				req.setAttribute("PAGECONT", pageCont);
				//总条数信息
				int count=as.getCount();
				req.setAttribute("COUNT", count);
				//把当前信息回传给jsp
				req.setAttribute("PAGE", page);
				//转发到文章列表页面
				req.getRequestDispatcher("article/article_list.jsp").forward(req, resp);
			} catch (SysException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
