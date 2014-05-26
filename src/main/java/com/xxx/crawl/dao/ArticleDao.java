package com.xxx.crawl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xxx.crawl.domain.Demo;
import com.xxx.crawl.dto.ArticleInfoDTO;

@Repository("articleDao")
public class ArticleDao extends BaseHibernateDao<Demo, Long> {
	public void saveArticles(List<ArticleInfoDTO> articles ) {
		for(ArticleInfoDTO article : articles) {
			this.getSession().saveOrUpdate(article);
		}
	}
}
