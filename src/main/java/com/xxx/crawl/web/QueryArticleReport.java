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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
		List<ArticleInfoDTO> articleList=articleService.queryReportData();
		List<ArticleReportDTO> reportList=resolveArticleList(articleList);
		System.out.println(articleList.size());
		String htmlUrl=jasperReportService.createHtmlReportFile(reportList,"D:\\report5.jasper");
		response.sendRedirect(htmlUrl);
	}
	private List<ArticleReportDTO> resolveArticleList(
			List<ArticleInfoDTO> articleList) {
		Map<String,ArticleReportDTO> report=new HashMap<String,ArticleReportDTO>();
		List<ArticleReportDTO> articleReportList=new ArrayList<ArticleReportDTO>();
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
			System.out.println("getSumReadTimes="+dto.getSumReadTimes());
			System.out.println("article getSumReadTimes="+article.getReadTimes());
			
			dto.setSumReadTimes(dto.getSumReadTimes()+article.getReadTimes());
System.out.println("sss+"+dto.getArticleSum());
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
		List<ArticleInfoDTO> articleList=articleService.queryReportData();
		List<ArticleReportDTO> reportList=resolveArticleList(articleList);
		jasperReportService.createPdfReportFile(reportList,"D:\\report5.jasper");
	}
	private QueryCreteria perseRequest(HttpServletRequest request) {
		String blogId=request.getParameter("blogId");
		String articleType=request.getParameter("articleType");
		String articleSource=request.getParameter("articleSource");
		String author=request.getParameter("author");
		String group=request.getParameter("group");
		String publishDateTo=request.getParameter("publishDateTo");
		String publishDateFrom=request.getParameter("publishDateFrom");
		QueryCreteria creteria=new QueryCreteria();
		if(StringUtil.isNotNullOrEmpty(blogId)){
			creteria.setBlogId(blogId);
		}
		if(StringUtil.isNotNullOrEmpty(articleType)){
			creteria.setArticleType(articleType);
		}
		if(StringUtil.isNotNullOrEmpty(articleSource)){
			creteria.setArticleSource(articleSource);
		}
		if(StringUtil.isNotNullOrEmpty(author)){
			creteria.setAuthor(author);
		}
		if(StringUtil.isNotNullOrEmpty(group)){
			creteria.setGroup(group);
		}
		if(StringUtil.isNotNullOrEmpty(publishDateFrom)){
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			try {
				creteria.setPublishDateFrom(df.parse(publishDateFrom));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtil.isNotNullOrEmpty(publishDateTo)){
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			try {
				Calendar ca=Calendar.getInstance();
				Date toDate=df.parse(publishDateTo);
				ca.setTime(toDate);
				ca.add(Calendar.DATE, 1);
				creteria.setPublishDateTo(ca.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return creteria;
	}
	
}
