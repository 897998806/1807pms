package com.zs.pms.dao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//��һ��������
public class LoginController {

	@RequestMapping("/tologin.do")
	//ȥ��½ҳ��
	public String tologin () {
		return "login";
	}
	
	@RequestMapping("/login.do")
	//ȥ������
	public String login () {
		return "main";
	}
	
	@RequestMapping("/top.do")
	//ȥ����ҳ��
	public String top () {
		return "top";
	}
	
	@RequestMapping("/left.do")
	//ȥ���ҳ��
	public String left () {
		return "left";
	}
	
	@RequestMapping("/right.do")
	//ȥ�Ҳ�ҳ��
	public String right () {
		return "right";
	}
	
	
}
