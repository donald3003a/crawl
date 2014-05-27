package com.xxx.crawl.service;

import java.util.List;

import net.sf.jasperreports.engine.JRException;

import com.xxx.crawl.common.dto.ArticleDTO;
import com.xxx.crawl.common.dto.ArticleReportDTO;
import com.xxx.crawl.common.dto.QueryCreteria;
import com.xxx.crawl.dto.ArticleInfoDTO;

public interface JasperReportService {

	/**
	 * 生成PDF报表
	 * @param artileList
	 * @return
	 * @throws JRException
	 */

	byte[] createPdfReportFile(List<ArticleReportDTO> artileList,String jasperTemplateFile) throws JRException;

	/**
	 * 生成HTML报表
	 * @param artileList
	 * @return
	 * @throws JRException
	 */
	String createHtmlReportFile(List<ArticleReportDTO> artileList,String jasperTemplateFile) throws JRException;
	

}
