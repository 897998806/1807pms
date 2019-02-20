package com.zs.pms.dao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//是一个控制器
public class LoginController {

	@RequestMapping("/tologin.do")
	//去登陆页面
	public String tologin () {
		return "login";
	}
	
	@RequestMapping("/login.do")
	//去主界面
	public String login () {
		return "main";
	}
	
	@RequestMapping("/top.do")
	//去顶部页面
	public String top () {
		return "top";
	}
	
	@RequestMapping("/left.do")
	//去左侧页面
	public String left () {
		return "left";
	}
	
	@RequestMapping("/right.do")
	//去右侧页面
	public String right () {
		return "right";
	}
	
	
}
