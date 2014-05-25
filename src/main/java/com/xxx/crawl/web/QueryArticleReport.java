package com.xxx.crawl.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxx.crawl.common.dto.ArticleDTO;
import com.xxx.crawl.common.dto.ArticleReportDTO;
import com.xxx.crawl.common.dto.QueryCreteria;
import com.xxx.crawl.common.util.StringUtil;
import com.xxx.crawl.service.JasperReportService;

@Controller
public class QueryArticleReport {
	@Autowired
	private JasperReportService jasperReportService;
	@RequestMapping(value = "/queryHtmlReport.do")
	public void queryReport(HttpServletRequest request,HttpServletResponse response) throws JRException, IOException{
		QueryCreteria queryCondition=perseRequest( request);
		List<ArticleReportDTO> articleList=jasperReportService.querygroupArticle();
		String htmlUrl=jasperReportService.createHtmlReportFile(articleList);
		response.sendRedirect(htmlUrl);
	}
	@RequestMapping(value = "/downloadPdfReport.do")
	public void downloadPdfReport(HttpServletRequest request,HttpServletResponse response) throws JRException, IOException{
		QueryCreteria queryCondition=perseRequest( request);
		List<ArticleDTO> articleList=jasperReportService.queryAritcle(queryCondition);
		jasperReportService.createPdfReportFile(articleList);
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
