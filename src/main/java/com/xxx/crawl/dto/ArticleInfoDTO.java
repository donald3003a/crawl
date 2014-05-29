package com.xxx.crawl.dto;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@Table(name="cw_article")
public class ArticleInfoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String url ;
	
	@Column
	private String title;
	@Column
	private int readTimes;
	@Column
	private int commentTimes;
	
	@Column
	private Date publishDate;
	@Column
	private String author;
	@Column
	private String devGroup;
	
	@Column
	private String source;
	
	@Column
	private String blogId;
	
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	@Column
	private Date updateDate = new Date();
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="url")
	private Set<ArticleCategoryDTO> categorys;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(int readTimes) {
		this.readTimes = readTimes;
	}
	public int getCommentTimes() {
		return commentTimes;
	}
	public void setCommentTimes(int commentTimes) {
		this.commentTimes = commentTimes;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getUpdateDate() {
		return publishDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.publishDate = updateDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDevGroup() {
		return devGroup;
	}
	public void setDevGroup(String devGroup) {
		this.devGroup = devGroup;
	}
	public void setCategorys(Set<ArticleCategoryDTO> categorys) {
		this.categorys = categorys;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@JsonSerialize
	public String getCategorys() {
		StringBuffer is=new StringBuffer("");
		if(categorys==null){
			return "";
		}
		for(ArticleCategoryDTO dto:categorys){
			if(is.length()>0){
				is.append("|");
			}
			is.append(dto.getCategory());
			System.out.println(is);
		}
		return is.toString();
	}
	
	public static void main(String[] args) {
		ArticleInfoDTO dto=new ArticleInfoDTO();
		/*	Set<ArticleCategoryDTO> categorys = new HashSet<ArticleCategoryDTO>();
		ArticleCategoryDTO c=new ArticleCategoryDTO();
		c.setCategory("ss");
		ArticleCategoryDTO d=new ArticleCategoryDTO();
		d.setCategory("ss2");
		categorys.add(d);
		categorys.add(c);
		dto.setCategorys(categorys);*/
		  ObjectMapper mapper=new ObjectMapper();
		  
		  Map<String,ArticleInfoDTO>a=new HashMap<String,ArticleInfoDTO>();
		  a.put("aa", dto);
	 		try {
	 			String jsonString=mapper.writeValueAsString(a);
	 			System.out.print(jsonString);
	 		} catch (JsonGenerationException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} catch (JsonMappingException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	}
	
}
