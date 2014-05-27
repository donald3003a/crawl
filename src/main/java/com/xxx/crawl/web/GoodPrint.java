package com.xxx.crawl.web;
import java.io.Serializable;
public class GoodPrint implements Serializable{
/**
* 
*/
private static final long serialVersionUID = 1L;
private String group;
private int readTimes;
private int articleSum;
private java.math.BigDecimal quantity;
private String quantityUnit;
public GoodPrint(){
}
public GoodPrint(String goodName,int readTimes,int articleSum,java.math.BigDecimal quantity,String quantityUnit){
this.group=goodName;
this.readTimes=readTimes;
this.articleSum=articleSum;
this.quantity=quantity;
this.quantityUnit=quantityUnit;
}

public java.math.BigDecimal getQuantity() {
return quantity;
}
public void setQuantity(java.math.BigDecimal quantity) {
this.quantity = quantity;
}
public String getGroup() {
	return group;
}
public void setGroup(String group) {
	this.group = group;
}
public int getReadTimes() {
	return readTimes;
}
public void setReadTimes(int readTimes) {
	this.readTimes = readTimes;
}
public int getArticleSum() {
	return articleSum;
}
public void setArticleSum(int articleSum) {
	this.articleSum = articleSum;
}
public String getQuantityUnit() {
return quantityUnit;
}
public void setQuantityUnit(String quantityUnit) {
this.quantityUnit = quantityUnit;
} 
}