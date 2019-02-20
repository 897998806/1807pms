package com.zscms.article.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.SysException;
import com.zscms.user.service.ArticleService;
import com.zscms.user.service.ChannelService;
import com.zscms.user.service.UserService;

public class ChkArticleExistServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//创建service对象
		ArticleService us=new ArticleService();
		//定义表示返回的结果
		String flag=null;
			//获得传入的用户名
			String title=req.getParameter("title");
			 try {
				if (us.chkExistTitle(title)) {
					//表示有同名用户
					flag="true";
				} else {
					flag="false";
				}
			} catch (SysException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.getRequestDispatcher("error.html").forward(req, resp);
				return;
			}
			
		
		//将结果写出,使用print（）或者println()方法输出字符串有空格，所以匹配不成功
		resp.getWriter().write(flag);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
