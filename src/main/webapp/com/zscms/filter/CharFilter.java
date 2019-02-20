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
/**
 * 过来字符编码的
 * @author Administrator
 *
 */

public class CharFilter  implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//请求对象转换成httpservletrequest
		HttpServletRequest req= (HttpServletRequest) request;
		//将响应对象转换成httpservletresponse
		HttpServletResponse resp=(HttpServletResponse) response;
		// 设置响应编码格式
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//将响应内容的类型
		resp.setContentType("text/html");
		//继续向后执行(把转换后的req和resp向后传)
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
