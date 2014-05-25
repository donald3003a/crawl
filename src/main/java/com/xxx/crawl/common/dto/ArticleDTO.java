package com.xxx.crawl.common.dto;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class ArticleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 766125666742685981L;
	private String articleTitle;
	private String articleAuthor;
	private Date publishDate;
	private int readTimes;
	private int commenttimes;
	private int forwardTimes;
	private String articleType;
	private String group;
	public ArticleDTO(String articleTitle,String articleAuthor,String articleType,
			Date publishDate,int readTimes,int commenttimes,int forwardTimes,String articleAuthorGroup
			){
		this.articleAuthor=articleAuthor;
		this.articleTitle=articleTitle;
		this.publishDate=publishDate;
		this.commenttimes=commenttimes;
		this.readTimes=readTimes;
		this.forwardTimes=forwardTimes;
		this.group=articleAuthorGroup;
		this.articleType=articleType;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleAuthor() {
		return articleAuthor;
	}
	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public int getForwardSum() {
		return forwardTimes;
	}
	public void setForwardSum(int forwardSum) {
		this.forwardTimes = forwardSum;
	}
	public String getArticleType() {
		return articleType;
	}
	public int getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(int readTimes) {
		this.readTimes = readTimes;
	}
	public int getCommenttimes() {
		return commenttimes;
	}
	public void setCommenttimes(int commenttimes) {
		this.commenttimes = commenttimes;
	}
	public int getForwardTimes() {
		return forwardTimes;
	}
	public void setForwardTimes(int forwardTimes) {
		this.forwardTimes = forwardTimes;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public Object getFieldVale(String fieldName){
		Object value=null;
		String method="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
		java.lang.reflect.Method method1 = null;
		try {
			method1 = this.getClass().getMethod(method, new Class[0]);
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			value=method1.invoke(this, new Object[0]);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
