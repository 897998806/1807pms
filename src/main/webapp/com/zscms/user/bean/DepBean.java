package com.zscms.user.bean;

import java.io.Serializable;

public class DepBean  implements Serializable{

	private int id;
	private String depname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public DepBean(int id, String depname) {
		super();
		this.id = id;
		this.depname = depname;
	}
	public DepBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
