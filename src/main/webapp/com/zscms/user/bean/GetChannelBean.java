package com.zscms.user.bean;

import java.io.Serializable;
/**
 * 所属栏目的封装bean
 * @author Administrator
 *
 */
public class GetChannelBean implements Serializable {
	//id
	private int id;
	//栏目名
	private String cname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "LanMuBean [id=" + id + ", cname=" + cname + "]";
	}
	
	
}
