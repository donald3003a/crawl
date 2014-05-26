package com.xxx.crawl.parse;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import com.xxx.crawl.dto.ArticleCategoryDTO;
import com.xxx.crawl.dto.ArticleInfoDTO;
import com.xxx.crawl.url.UrlConsts;
import com.xxx.crawl.util.CrawlerUtil;
import com.xxx.crawl.util.Util;


public class CsdnParse extends AbstractParse{

	@Override
	public ArticleInfoDTO getArticleFromPage(String articleUrl) {
		
		String htmlStr = CrawlerUtil.downPageText(articleUrl);
		NodeList nodeList = getNodeList(htmlStr);
		NodeFilter filter = new CssSelectorNodeFilter("div[class='details']");
		NodeFilter titleFilter = new TagNameFilter("h1");
		NodeFilter articleInfoFilter = new CssSelectorNodeFilter("div[class='article_manage']");
		
		NodeList articleDetailNodes = nodeList.extractAllNodesThatMatch(filter, true);
		NodeList titleNodes = articleDetailNodes.extractAllNodesThatMatch(titleFilter, true);
		NodeList articleInfoNodes = nodeList.extractAllNodesThatMatch(articleInfoFilter,true);
		
		String articleTitle = titleNodes.elementAt(0).toPlainTextString().trim();
		articleTitle = Util.getCleanText(articleTitle);
		
		Div div = (Div) articleInfoNodes.elementAt(0);
		String category = "";
		int readTimes=0;
		int commentTimes=0;
		Date updateDate = null ;
		for(Node node : div.getChildrenAsNodeArray()) {
			if(node instanceof Span) {
				Span span = (Span) node;
				String clazz = span.getAttribute("class");
				if(clazz.equals("link_categories")) {
					//第2个子元素为分类中文
					LinkTag linkTag = (LinkTag) span.getChild(1);
					category = linkTag.toPlainTextString();
				} else if (clazz.equals("link_view")) {
					System.out.println(articleTitle);
					String text = span.toPlainTextString();
					readTimes = Integer.parseInt(text.substring(0,text.indexOf("人阅读")));
				} else if(clazz.equals("link_comments")) {
					String commentText = span.toPlainTextString();
					commentTimes  = Integer.valueOf(commentText.substring(commentText.indexOf("(")+1,commentText.indexOf(")")));
				} else if(clazz.equals("link_postdate")) {
					String dateText = span.toPlainTextString();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
					try {
						updateDate = sdf.parse(span.toPlainTextString().trim());
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
//				switch(clazz) {
//				case  "link_categories": 
//					//第2个子元素为分类中文
//					LinkTag linkTag = (LinkTag) span.getChild(1);
//					category = linkTag.toPlainTextString();
//					break;
//				case "link_view":
//					String text = span.toPlainTextString();
//					views = Integer.parseInt(text.substring(0,text.indexOf("人阅读")));
//					break;
//				case "link_comments":
//					String commentText = span.toPlainTextString();
//					commentTimes  = Integer.valueOf(commentText.substring(commentText.indexOf("(")+1,commentText.indexOf(")")));
//					break;
//				case "link_postdate":
//					String dateText = span.toPlainTextString();
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//					try {
//						updateDate = sdf.parse(span.toPlainTextString().trim());
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					break;
//				}
			}
		}
		ArticleInfoDTO article = new ArticleInfoDTO(); 
		ArticleCategoryDTO ac = new ArticleCategoryDTO();
		ac.setCategory(category);
		Set<ArticleCategoryDTO> categorys = new HashSet<ArticleCategoryDTO>();
		categorys.add(ac);
		article.setCategorys(categorys);
		article.setCommentTimes(commentTimes);
		article.setTitle(articleTitle);
		article.setUrl(articleUrl);
		article.setReadTimes(readTimes);
		article.setUpdateDate(updateDate);
		return article;
	}
	
	@Override
	public String getNextPageUrl(String htmlStr) {
		Parser parser = Parser.createParser(htmlStr, "UTF-8");
		HtmlPage page = new HtmlPage(parser);
		try {
			parser.visitAllNodesWith(page);
		} catch (ParserException e1) {
			e1 = null;
		}
		NodeList nodelist = page.getBody();
		NodeFilter filter = new CssSelectorNodeFilter("div[class='pagelist']");
		nodelist = nodelist.extractAllNodesThatMatch(filter, true);
		Div div = (Div) nodelist.elementAt(0);
		for(Node node : div.getChildrenAsNodeArray()) {
			if("下一页".equals(node.toPlainTextString())) {
				return UrlConsts.CSDN_URL+((LinkTag)node).getAttribute("href");
			}
		}
		return "";
	}

	@Override
	public List<ArticleInfoDTO> getArticleInfo(String rootUrl) {
		List<ArticleInfoDTO> result = new ArrayList<ArticleInfoDTO>();
		List<String> allUrls = getAllArticleUrls(rootUrl);
		for(String url : allUrls) {
			ArticleInfoDTO article = getArticleFromPage(url);
			result.add(article);
		}
		return result;
	}

	@Override
	public List<String> getAllArticleUrls(String rootUrl) {
		List<String> result = new ArrayList<String>();
		String nextPageUrl = "";
		do {
			String url = "".equals(nextPageUrl)?rootUrl:nextPageUrl;
			String htmlStr = CrawlerUtil.downPageText(url);
			NodeList nodeList = getNodeList(htmlStr);
			result.addAll(getArticleUrlsFromNodeList(nodeList));
			nextPageUrl = getNextPageUrl(htmlStr);
		}while(!"".equals(nextPageUrl));
		return result;
	}
	
	private List<String> getArticleUrlsFromNodeList(NodeList nodeList) {
		List<String> result = new ArrayList<String>();
		//获取父标签过滤器
		NodeFilter parentFilter = new CssSelectorNodeFilter("span[class='link_title']");
		//获取<a/>标签过滤器
		NodeFilter hrefFilter = new TagNameFilter("a");
		nodeList = nodeList.extractAllNodesThatMatch(parentFilter, true);
		nodeList = nodeList.extractAllNodesThatMatch(hrefFilter, true);
		for(Node node : nodeList.toNodeArray()) {
			LinkTag linkTag = (LinkTag) node;
			result.add(UrlConsts.CSDN_URL+linkTag.getAttribute("href"));
		}
		return result;
	}
	
	@Override
	public NodeList getNodeList(String htmlStr) {
		Parser parser = Parser.createParser(htmlStr, "UTF-8");
		HtmlPage page = new HtmlPage(parser);
		try {
			parser.visitAllNodesWith(page);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return page.getBody();
	}
	
	public static void main(String[] args) {
		CsdnParse  csdn = new CsdnParse();
		List<String> result = csdn.getAllArticleUrls(UrlConsts.CSDN_BLOG_URL);
		List<ArticleInfoDTO> as = new ArrayList<ArticleInfoDTO>();
		for(String url :  result) {
			as.add(csdn.getArticleFromPage(url));
		}
		
		for(ArticleInfoDTO tmp : as) {
			System.out.println(tmp);
		}
	}
	
}
