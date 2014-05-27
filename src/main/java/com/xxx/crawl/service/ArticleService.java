package com.xxx.crawl.service;

import java.util.List;

import com.xxx.crawl.common.dto.ArticleReportDTO;
import com.xxx.crawl.dto.ArticleInfoDTO;

public interface ArticleService {
	void saveArticles(List<ArticleInfoDTO> articles) ;
	List<ArticleInfoDTO> queryReportData();
	
}
