package com.zscms.user.bean;

import java.io.Serializable;

/**
 * 这是文章的封装bean
 * 
 * @author Administrator
 *
 */
public class ArticleBean implements Serializable {
	// id
	private int id;
	// 标题
	private String title;
	// 内容
	private String content;
	// 作者
	private String author;
	// 添加日期
	private String crtime;
	// 所属栏目
	private int channel;
	// 是否推荐
	private int isremod;
	// 是否热点
	private int ishot;
	//栏目的中文显示
	private String cname;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCrtime() {
		return crtime;
	}

	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getIsremod() {
		return isremod;
	}

	public void setIsremod(int isremod) {
		this.isremod = isremod;
	}

	public int getIshot() {
		return ishot;
	}

	public void setIshot(int ishot) {
		this.ishot = ishot;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	// tostring 方法
	@Override
	public String toString() {
		return "ArticleBean [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author
				+ ", crtime=" + crtime + ", channel=" + channel + ", isremod=" + isremod + ", ishot=" + ishot
				+ ", cname=" + cname + "]";
	}
	


	

}
