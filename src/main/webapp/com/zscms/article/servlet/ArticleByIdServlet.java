package com.zscms.article.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.ArticleBean;
import com.zscms.user.bean.GetChannelBean;
import com.zscms.user.service.ArticleService;

public class ArticleByIdServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获得页面输入的用户id信息
		int id=0;
		if (req.getParameter("id")!=null) {
			id=Integer.parseInt(req.getParameter("id"));
		}
		//创建ArticleService对象
		ArticleService as=new ArticleService();
		try {
			//调用as的通过id查询方法来获得全部文章的信息
			ArticleBean article = as.queryArticleById(id);
			//信息放入请求
			req.setAttribute("ARTICLEBYID",article );
			//把栏目信息带入页面
			List<GetChannelBean> channels=as.getChannel();
			req.setAttribute("CHANNEL",channels );
			//转发到修改页面
			req.getRequestDispatcher("article/articleupdate.jsp").forward(req, resp);
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
