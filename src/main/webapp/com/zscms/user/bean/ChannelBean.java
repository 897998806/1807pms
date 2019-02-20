package com.zscms.user.bean;

import java.io.Serializable;

public class ChannelBean  implements Serializable{
	//id
	private int id;
	//栏目名
	private String cname;
	//上级栏目
	private int pid;
	//等级
	private int lev;
	//是否叶子
	private int isleaf;
	//顺序
	private int sort;
	//新增的栏目显示中文的属性
	private String name;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getLev() {
		return lev;
	}


	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
//这是新增的 让上级栏目显示中文的属性
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ChannelBean [id=" + id + ", cname=" + cname + ", pid=" + pid + ", lev=" + lev + ", isleaf=" + isleaf
				+ ", sort=" + sort + "]";
	}

	
}
