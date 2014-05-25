package com.xxx.crawl.service;

import java.util.List;

import net.sf.jasperreports.engine.JRException;

import com.xxx.crawl.common.dto.ArticleDTO;
import com.xxx.crawl.common.dto.ArticleReportDTO;
import com.xxx.crawl.common.dto.QueryCreteria;

public interface JasperReportService {
	/**
	 * 按照条件查询文章列表
	 * 
	 */
	List<ArticleDTO>  queryAritcle(QueryCreteria creteria);

	/**
	 * 生成PDF报表
	 * @param artileList
	 * @return
	 * @throws JRException
	 */

	byte[] createPdfReportFile(List<ArticleDTO> artileList) throws JRException;

	/**
	 * 生成HTML报表
	 * @param artileList
	 * @return
	 * @throws JRException
	 */
	String createHtmlReportFile(List<ArticleReportDTO> artileList) throws JRException;
	List<ArticleReportDTO> querygroupArticle();
	

}
