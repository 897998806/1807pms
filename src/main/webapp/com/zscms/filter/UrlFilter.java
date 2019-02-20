package com.zscms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zscms.user.bean.UserBean;

public class UrlFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 因为要做的是URL的操作相关于http的 所以把对象转成HttpServletRequest 和HttpServletRespone
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 从请求中获得URL信息
		String url = req.getRequestURI();
		// 判断路径是否包含login。do 或者login.jsp直接放行
		if (url.indexOf("login.jsp") != -1 || url.indexOf("login.do") != -1) {
			chain.doFilter(req, resp);
			return;
		}
		// 获得session对象
		HttpSession session = req.getSession();
		// 获得用户信息
		UserBean user = (UserBean) session.getAttribute("USER");
		//如果是空的 没有登录
		if (user == null) {
			// 去登录，因为不知道访问的哪一个位置发起的，所以不能采用相对路径来跳转，采用绝对路径重定向到登录页面
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		} else {
			// 放行
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
