package com.zscms.user.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.Constants;
import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.UserBean;
import com.zscms.user.service.UserService;
import com.zscms.util.DButil;
import com.zscms.util.DateUtil;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		// 设置编码格式 因为在网页中输入中文无法和数据库进行匹配需要设置编码格式
		req.setCharacterEncoding("utf-8");
		// 获得表单提交的账号信息
		String loginname = req.getParameter("loginname");
		// 获得表单提交的密码信息
		String password = req.getParameter("password");
		//获得验证信息
		String  code=req.getParameter("chkcode");
		
		//创建封装数据Bean
		UserBean user = new UserBean();
		user.setLoginname(loginname);
		user.setPassword(password);
		// 创建UserService对象
		UserService us = new UserService();
		// 调用
		try {
			//获得session对象
			HttpSession session=req.getSession(true);
			//获得一开始生成的验证码
			String chkcode=(String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
			//判断输入的信息和生成的验证码是否一直，如果不一致就直接回到登录页面，如果一直直接验证账号密码
			if (!chkcode.equalsIgnoreCase(code)) {
					throw new AppException(1007, "验证码不匹配请重新输入");
			}
			// 调用Service 验证登录方式
			UserBean chklogin = us.chklogin(user);
			//把用户信息放入
			session.setAttribute("USER", chklogin);
			//把欢迎时间信息放入
			session.setAttribute("TIME", DateUtil.getStrDate());
			// 获得是否记住密码
			String rp = req.getParameter("remPwd");
			// 如果页面输入的信息等于1 创建cookie 记住密码
			if (rp != null && rp.equals("1")) {
				// 创建COOkie 保存账号信息
				Cookie c1 = new Cookie("username", chklogin.getLoginname());
				// 保存密码信息
				Cookie c2 = new Cookie("pwd", chklogin.getPassword());
				// 设置cookie的时长
				c1.setMaxAge(60);
				c2.setMaxAge(60);
				// 将cookie通过响应给客户端
				resp.addCookie(c1);
				resp.addCookie(c2);

			}
			// 登录成功跳转到主页
			req.getRequestDispatcher("index.html").forward(req, resp);

		}catch (SysException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect("error.html");
			}
		 catch (AppException e) {
			// TODO Auto-generated catch block
			// 报了App异常说明登录失败，从异常中获得失败信息放入请求中
			req.setAttribute("errmsg", e.getErrMsg());
			// 把错误信息带回页面
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} 
		
		
		

	}
}
