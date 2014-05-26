package com.xxx.crawl.parse;

import java.util.List;


import org.htmlparser.util.NodeList;
import com.xxx.crawl.dto.ArticleInfoDTO;

public abstract class AbstractParse {
	abstract public ArticleInfoDTO getArticleFromPage(String articleUrl);
	abstract public String getNextPageUrl(String htmlStr);
	abstract public List<ArticleInfoDTO> getArticleInfo(String rootUrl);
	abstract public List<String> getAllArticleUrls(String rootUrl);
	abstract public NodeList getNodeList(String htmlStr);
}
