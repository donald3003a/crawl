package com.xxx.crawl.common.dto;

import java.io.Serializable;

public class ArticleReportDTO implements Serializable{
/**
	 * 
	 */
private static final long serialVersionUID = -3661377783904591747L;
private String group;
private int sumReadTimes;
private int articleSum;
public ArticleReportDTO(String group,int sumReadTimes,int articleSum){
	this.group=group;
	this.sumReadTimes=sumReadTimes;
	this.articleSum=articleSum;
}
public ArticleReportDTO(){
	
}
public String getGroup() {
	return group;
}
public void setGroup(String group) {
	this.group = group;
}
public int getSumReadTimes() {
	return sumReadTimes;
}
public void setSumReadTimes(int sumReadTimes) {
	this.sumReadTimes = sumReadTimes;
}
public int getArticleSum() {
	return articleSum;
}
public void setArticleSum(int articleSum) {
	this.articleSum = articleSum;
}
}
