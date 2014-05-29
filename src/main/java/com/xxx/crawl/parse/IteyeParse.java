package com.xxx.crawl.parse;

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
import org.htmlparser.tags.BulletList;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
import org.htmlparser.visitors.HtmlPage;

import com.xxx.crawl.dto.ArticleCategoryDTO;
import com.xxx.crawl.dto.ArticleInfoDTO;
import com.xxx.crawl.url.UrlConsts;
import com.xxx.crawl.util.CrawlerUtil;
import com.xxx.crawl.util.Util;

public class IteyeParse extends AbstractParse{

	private static List<String> rootUrls = new ArrayList<String>();
	
	static {
		rootUrls.add(UrlConsts.ITEYE_BLOG_URL);
		rootUrls.add(UrlConsts.ITEYE_EXPERT_BLOG_URL);
	}
	
	private String currentRootUrl;
	
	@Override
	public ArticleInfoDTO getArticleFromPage(String articleUrl) {
		String htmlStr = CrawlerUtil.downPageText(articleUrl);
		NodeList nodeList = getNodeList(htmlStr);
		NodeFilter filter = new CssSelectorNodeFilter("div[class='blog_main']");
		NodeFilter titleFilter = new TagNameFilter("h3");
		NodeFilter articleInfoFilter = new TagNameFilter("ul");
		NodeFilter tagFilter = new CssSelectorNodeFilter("div[class='news_tag']");
		NodeFilter otherAttrFilter = new CssSelectorNodeFilter("div[class='blog_bottom']");
		
		NodeList articleDetailNodes = nodeList.extractAllNodesThatMatch(filter, true);
		NodeList titleNodes = articleDetailNodes.extractAllNodesThatMatch(titleFilter, true);
		NodeList tagList = articleDetailNodes.extractAllNodesThatMatch(tagFilter, true);
		NodeList categoryNodes = articleDetailNodes.extractAllNodesThatMatch(articleInfoFilter,true);
		NodeList otherNodes = articleDetailNodes.extractAllNodesThatMatch(otherAttrFilter);
		
		String articleTitle = titleNodes.elementAt(0).toPlainTextString().trim();
		articleTitle = Util.getCleanText(articleTitle);
		
		String group = null;
		String author = null;
		Div tagDiv = (Div)  tagList.elementAt(0);
		if(tagDiv.getChildCount() > 0) {
			String tagText = tagDiv.getChild(tagDiv.getChildCount()-1).toPlainTextString();
			if(Util.matchTag(tagText)) {
				group = tagText.split("-")[0];
				group = Util.group.get(group.toUpperCase());
				author = tagText.split("-")[1];
				author = Util.user.get(author.toUpperCase());
			}
		}
		
		System.out.println(group+":"+author);
		
		Set<ArticleCategoryDTO> category = new HashSet<ArticleCategoryDTO>();
		int readTimes=0;
		int commentTimes=0;
		Date publishDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		SimpleNodeIterator iter = categoryNodes.elements();
		while(iter.hasMoreNodes()) {
			BulletList bullet = (BulletList)iter.nextNode();
			if(bullet.getAttribute("class")!=null
				&& bullet.getAttribute("class").equals("blog_categories")) {
				NodeFilter liFilter = new TagNameFilter("li");
				NodeList ctgrNodes = bullet.getChildren().extractAllNodesThatMatch(liFilter,true);
				for(Node node : ctgrNodes.toNodeArray()) {
					ArticleCategoryDTO ac = new ArticleCategoryDTO();
					ac.setCategory(node.toPlainTextString().trim());
					category.add(ac);
				}
			}
		}
		
		Div div = null;
		for(Node node : ((Div)articleDetailNodes.elementAt(0)).getChildrenAsNodeArray()) {
			if(node instanceof Div) {
				if(((Div)node).getAttribute("class").equals("blog_bottom")) {
					div = (Div) node;
				}
			}
		}
		NodeList otherArrtNodes = div.getChildren();
		try {
			Node[] nodes = otherArrtNodes.toNodeArray();
			for(int i=0;i<nodes.length;i++) {
				if(nodes[i] instanceof BulletList) {
					publishDate = sdf.parse(nodes[i].getChildren().elementAt(1).toPlainTextString());
					readTimes = Integer.parseInt(nodes[i].getChildren().elementAt(3).toPlainTextString().replaceAll("浏览","").trim());
					String commentTimesStr = nodes[i].getChildren().elementAt(5).toPlainTextString();
					commentTimes = Integer.parseInt(commentTimesStr.substring(commentTimesStr.indexOf("(")+1,commentTimesStr.indexOf(")")));
					break;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		ArticleInfoDTO article = new ArticleInfoDTO(); 
		article.setCategorys(category);
		article.setCommentTimes(commentTimes);
		article.setTitle(articleTitle);
		article.setUrl(articleUrl);
		article.setReadTimes(readTimes);
		article.setPublishDate(publishDate);
		article.setAuthor(author);
		article.setSource("ITEYE");
		if(currentRootUrl.equals(UrlConsts.ITEYE_BLOG_URL)) {
			article.setBlogId("小码哥BASE64");
		} else if(currentRootUrl.equals(UrlConsts.ITEYE_EXPERT_BLOG_URL)) {
			article.setBlogId("戈尔-D-罗杰");
		}
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
		NodeFilter filter = new CssSelectorNodeFilter("div[class='pagination']");
		nodelist = nodelist.extractAllNodesThatMatch(filter, true);
		if(nodelist.size()>0) {
			Div div = (Div) nodelist.elementAt(0);
			for(Node node : div.getChildrenAsNodeArray()) {
				if(node.toPlainTextString().contains("下一页")) {
					if(node instanceof LinkTag)
						return UrlConsts.ITEYE_URL+((LinkTag)node).getAttribute("href");
					break;
				}
			}
		}
		return "";
	}

	@Override
	public List<ArticleInfoDTO> getArticleInfo() {
		List<ArticleInfoDTO> result = new ArrayList<ArticleInfoDTO>();
		for(String rootUrl : rootUrls) {
			currentRootUrl = rootUrl;
			List<String> allUrls = getAllArticleUrls(rootUrl);
			for(String url : allUrls) {
				ArticleInfoDTO article = getArticleFromPage(url);
				result.add(article);
			}
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
		NodeFilter parentFilter = new TagNameFilter("h3");
		//获取<a/>标签过滤器
		NodeFilter hrefFilter = new TagNameFilter("a");
		nodeList = nodeList.extractAllNodesThatMatch(parentFilter, true);
		nodeList = nodeList.extractAllNodesThatMatch(hrefFilter, true);
		for(Node node : nodeList.toNodeArray()) {
			LinkTag linkTag = (LinkTag) node;
			result.add(UrlConsts.ITEYE_URL+linkTag.getAttribute("href"));
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
		IteyeParse  iteye = new IteyeParse();
		List<String> result = iteye.getAllArticleUrls(UrlConsts.ITEYE_BLOG_URL);
		List<ArticleInfoDTO> as = new ArrayList<ArticleInfoDTO>();
		for(String url :  result) {
			as.add(iteye.getArticleFromPage(url));
		}
		
		for(ArticleInfoDTO tmp : as) {
			System.out.println(tmp);
		}
	}
}
