package com.xxx.crawl.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.crawl.common.dto.ArticleReportDTO;
import com.xxx.crawl.common.dto.QueryCreteria;
import com.xxx.crawl.common.util.StringUtil;
import com.xxx.crawl.dto.ArticleInfoDTO;
import com.xxx.crawl.service.ArticleService;
import com.xxx.crawl.service.JasperReportService;

@Controller
public class QueryArticleReport {
	@Autowired
	private JasperReportService jasperReportService;
	@Autowired
	private ArticleService articleService;
	@RequestMapping(value = "/queryHtmlReport.do")
	public void queryReport(HttpServletRequest request,HttpServletResponse response) throws JRException, IOException{
		QueryCreteria articleInfoDTO = new QueryCreteria();
		List<ArticleInfoDTO> articleList=articleService.queryReportData(articleInfoDTO);
		List<ArticleReportDTO> reportList=resolveArticleList(articleList);
		String htmlUrl=jasperReportService.createHtmlReportFile(reportList,"D:\\report5.jasper");
		response.sendRedirect(htmlUrl);
	}
	private List<ArticleReportDTO> resolveArticleList(
			List<ArticleInfoDTO> articleList) {
		Map<String,ArticleReportDTO> report=new HashMap<String,ArticleReportDTO>();
		List<ArticleReportDTO> articleReportList=new ArrayList<ArticleReportDTO>();
		Date now =new Date();
		Calendar ca=Calendar.getInstance();
		ca.setTime(now);
		ca.add(Calendar.DATE, -30);
		Date date_30_=ca.getTime();
		ca.add(Calendar.DATE, -30);
		Date date_60_=ca.getTime();
		ca.add(Calendar.DATE, -30);
		Date date_90_=ca.getTime();
		for(ArticleInfoDTO article:articleList){
			String group=article.getDevGroup();
			if(StringUtil.isNullOrEmpty(group)){
				continue;
			}
			ArticleReportDTO dto=null;
			if(!report.containsKey(group)){
				dto=new ArticleReportDTO(group,0,0);
				report.put(group, dto);
			}
			dto=report.get(group);
			if(article.getUpdateDate().compareTo(date_30_)>=0){
				dto.setSumReadTimes(dto.getSumReadTimes()+article.getReadTimes());
			}else if(article.getUpdateDate().compareTo(date_60_)>=0){
				dto.setSumReadTimes(dto.getSumReadTimes()+article.getReadTimes()/2);
			}else if(article.getUpdateDate().compareTo(date_90_)>=0){
				dto.setSumReadTimes(dto.getSumReadTimes()+article.getReadTimes()/4);
			}
			dto.setArticleSum(dto.getArticleSum()+1);
			report.put(group, dto);
		}
		Iterator<String> it=report.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			ArticleReportDTO dto=report.get(key);
			articleReportList.add(dto);
		}
		return articleReportList;
	}
	
	
	
	@RequestMapping(value = "/downloadPdfReport.do")
	public void downloadPdfReport(HttpServletRequest request,HttpServletResponse response) throws JRException, IOException{
		QueryCreteria articleInfoDTO = new QueryCreteria();
		List<ArticleInfoDTO> articleList=articleService.queryReportData(articleInfoDTO);
		List<ArticleReportDTO> reportList=resolveArticleList(articleList);
		jasperReportService.createPdfReportFile(reportList,"D:\\report5.jasper");
	}
	
	

	@RequestMapping(value = "/queryArticle.do")
	@ResponseBody
	public Map<String, Object> queryArticle(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {
		QueryCreteria queryParam = new QueryCreteria();
		Map<String, Object> map=new HashMap<String, Object>();
		queryParam = perseArticleInfoDTO(request);
		List<ArticleInfoDTO> articleList = articleService.queryArticles(queryParam);
		 map.put("total", articleList.size()); 
         map.put("rows", articleList); 
         ObjectMapper mapper=new ObjectMapper();

 		try {
 			String jsonString=mapper.writeValueAsString(map);
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
		return map;
	}
	private QueryCreteria perseArticleInfoDTO(HttpServletRequest request) {
		String blogId = request.getParameter("blogId");
		String articleType = request.getParameter("articleType");
		String articleSource = request.getParameter("articleSource");
		String author = request.getParameter("author");
		String group = request.getParameter("group");
		String publishDateTo = request.getParameter("publishDateTo");
		String publishDateFrom = request.getParameter("publishDateFrom");
		QueryCreteria creteria = new QueryCreteria();
		if (StringUtil.isNotNullOrEmpty(blogId)) {
			creteria.setBlogId(blogId);
		}
		if (StringUtil.isNotNullOrEmpty(articleType)) {
			creteria.setArticleType(articleType);
		}
		if (StringUtil.isNotNullOrEmpty(articleSource)) {
			creteria.setArticleSource(articleSource);
		}
		if (StringUtil.isNotNullOrEmpty(author)) {
			creteria.setAuthor(author);
		}
		if (StringUtil.isNotNullOrEmpty(group)) {
			creteria.setGroup(group);
		}
		if (StringUtil.isNotNullOrEmpty(publishDateFrom)&&StringUtil.isNotNullOrEmpty(publishDateTo)) {
			try {
				creteria.setPublishDateFrom(DateUtil.parseDate(publishDateFrom));
				publishDateTo+="23:59:59";
				creteria.setPublishDateTo(DateUtil.parseDate(publishDateTo));
			} catch (DateParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return creteria;
	}
	
}
