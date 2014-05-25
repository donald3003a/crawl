package com.xxx.crawl.common.dto;

import java.io.Serializable;
import java.util.Date;

public class QueryCreteria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1511427376968698271L;
	private String articleSource;
	private String group;
	private Date publishDateFrom;
	private Date publishDateTo;
	private String articleType;
	private String blogId;
	private String author;
	public String getArticleSource() {
		return articleSource;
	}
	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Date getPublishDateFrom() {
		return publishDateFrom;
	}
	public void setPublishDateFrom(Date publishDateFrom) {
		this.publishDateFrom = publishDateFrom;
	}

	public String getArticleType() {
		return articleType;
	}
	public Date getPublishDateTo() {
		return publishDateTo;
	}
	public void setPublishDateTo(Date publishDateTo) {
		this.publishDateTo = publishDateTo;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
}
