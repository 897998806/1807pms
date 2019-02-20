package com.zscms.user.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zscms.exception.AppException;
import com.zscms.exception.SysException;
import com.zscms.user.bean.UserBean;
import com.zscms.user.service.UserService;
import com.zscms.util.DateUtil;

public class UserAddServlet extends HttpServlet {
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//获得页面输入信息 并封装
	   UserBean user=new UserBean();
	  
		user.setBirthday(req.getParameter("birthday"));
	
	  //页面来的值为字符串，dep 为int类型set是要进行转化，如果为null使用转换会报错所以进行判空操作
	   if (req.getParameter("dep")!=null) {
		   user.setDep(Integer.parseInt(req.getParameter("dep")));

	}
	   user.setEmail(req.getParameter("email"));
		//新增用户默认可用 直接设置为1
		user.setEnable(1);
		user.setLoginname(req.getParameter("loginname"));
		user.setPassword(req.getParameter("password"));
		user.setRealname(req.getParameter("realname"));
		user.setSex(req.getParameter("sex"));
		//创建用户service
		UserService us=new UserService();
		try {
			//调用serveic 新增用户方法
			us.addUser(user);
			//新增完回到用户列表页，需要走列别页的service把列别信息带入
			req.getRequestDispatcher("userlist.do?page=1").forward(req, resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//系统异常页面进行错误页面
			resp.sendRedirect("error.html");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//应用异常把异常信息放入请求
			req.setAttribute("USERERR", e.getErrMsg());
			//页面跳回新增页面
			req.getRequestDispatcher("useradd.jsp").forward(req, resp);
		}

}
   @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
