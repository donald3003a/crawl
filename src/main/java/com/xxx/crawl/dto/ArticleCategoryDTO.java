package com.xxx.crawl.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cw_article_category")
public class ArticleCategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long articleCategoryId;
	
	@Column
	private String category;  //文章分类
	
	@Column
	private Long articleId;
	
//	@ManyToOne(targetEntity = ArticleInfoDTO.class)
//	@JoinColumn(name="articleId",updatable=false,insertable=false)
//	private ArticleInfoDTO articleInfo;

	public Long getArticleCategoryId() {
		return articleCategoryId;
	}

	public void setArticleCategoryId(Long articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getId() {
		return articleCategoryId;
	}

	public void setId(Long id) {
		this.articleCategoryId = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
