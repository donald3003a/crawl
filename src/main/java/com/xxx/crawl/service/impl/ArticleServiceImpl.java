package com.xxx.crawl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.crawl.common.dto.QueryCreteria;
import com.xxx.crawl.dao.ArticleDao;
import com.xxx.crawl.dto.ArticleInfoDTO;
import com.xxx.crawl.service.ArticleService;

@Service("articleService")  
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleDao articleDao;
	
	public void saveArticles(List<ArticleInfoDTO> articles)  {
		articleDao.saveArticles(articles);
	}

	@Override
	public List<ArticleInfoDTO> queryReportData(QueryCreteria dto) {
		return  articleDao.queryReportData(dto);
	}

	@Override
	public List<ArticleInfoDTO> queryArticles(QueryCreteria articleInfoDTO) {
		return articleDao.queryReportData(articleInfoDTO);
	}
	
}
