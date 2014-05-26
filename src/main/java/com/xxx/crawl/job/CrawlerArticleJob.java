package com.xxx.crawl.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xxx.crawl.dto.ArticleInfoDTO;
import com.xxx.crawl.parse.AbstractParse;
import com.xxx.crawl.service.ArticleService;

@Component
public class CrawlerArticleJob {

	@Autowired
	private ArticleService articleService;
	
	@Scheduled(cron="*/30 * * * * *") 
    public void updateArticleInfos(){
//		updateDb(new CsdnParse());
//		updateDb(new IteyeParse());
    }
	
	private void updateDb(AbstractParse parse) {
		List<ArticleInfoDTO> articles = parse.getArticleInfo();
		articleService.saveArticles(articles);
	}
	
}
