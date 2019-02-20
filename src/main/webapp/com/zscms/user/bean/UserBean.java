package com.zscms.user.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 用于存放用户信息的Bean
 */
public class UserBean implements Serializable{


	//用户ID
	private int id;
//用户登录名
	private String loginname;
//用户登录密码
	private String password;
//用户真实姓名
	private String realname;
//用户的性别
	private String sex;
//用户的生日
	private String birthday;
//用户的所在部门编号
	private int dep;
//部门的中文
	private String depText;
	//用户的邮箱
	private String email;
//账号是否可用
	private int enable;

	/**
	 * 添加所有属性的setget方法
	 * 
	 * @return
	 */
	
	public String getDepText() {
		return depText;
	}
	
	public void setDepText(String depText) {
		this.depText = depText;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getDep() {
		return dep;
	}

	public void setDep(int dep) {
		this.dep = dep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", loginname=" + loginname + ", password=" + password + ", realname=" + realname
				+ ", sex=" + sex + ", birthday=" + birthday + ", dep=" + dep + ", email=" + email + ", enable=" + enable
				+ "]";
	}
}
