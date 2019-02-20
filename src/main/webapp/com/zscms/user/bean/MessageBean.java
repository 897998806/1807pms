package com.zscms.user.bean;

import java.io.Serializable;

/**
 * 这是广告的封装bean
 * @author Administrator
 *
 */
public class MessageBean implements Serializable{
	private int id;
	private String title;
	private String content;
	private String ctime;
	private String cman;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getCman() {
		return cman;
	}

	public void setCman(String cman) {
		this.cman = cman;
	}

	@Override
	public String toString() {
		return "MessageBean [id=" + id + ", title=" + title + ", content=" + content + ", ctime=" + ctime + ", cman="
				+ cman + "]";
	}

}
