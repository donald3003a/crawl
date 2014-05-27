package com.xxx.crawl.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.crawl.common.dto.ArticleDTO;
import com.xxx.crawl.common.dto.ArticleReportDTO;
import com.xxx.crawl.common.dto.QueryCreteria;
import com.xxx.crawl.dao.ArticleDao;
import com.xxx.crawl.dto.ArticleInfoDTO;
import com.xxx.crawl.service.ArticleService;
import com.xxx.crawl.service.JasperReportService;

@Service("jasperReportService")
public class JasperReportServiceImpl  implements JasperReportService{
	
	
	Log log=LogFactory.getLog(getClass());
	public void compileReportXml(String srcJrxml) throws JRException{
		String desJasper=srcJrxml.replace("jrxml", "jasper");
		try {
			JasperCompileManager.compileReportToFile(srcJrxml, desJasper);
		} catch (JRException e) {
			log.equals("Compile src jrxml file to jasper file ERROR!");
			throw e;
		}
	}
	public byte[] createPdfReportFile(List<ArticleReportDTO> artileList,String jasperTemplateFile) throws JRException{
		  File reportFilePath =new File( jasperTemplateFile);
		  JRDataSource dataSource = new JRBeanCollectionDataSource(artileList);;
		  JasperReport report = (JasperReport)JRLoader.loadObject(reportFilePath);
		  JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);
		  byte pdf[]=JasperExportManager.exportReportToPdf(jasperPrint);
		  return pdf;
	}
	public String  createHtmlReportFile(List<ArticleReportDTO> artileList,String jasperTemplateFile) throws JRException{
		File reportFilePath =new File(jasperTemplateFile);
		JRDataSource dataSource = new JRBeanCollectionDataSource(artileList);
		JasperReport report = (JasperReport)JRLoader.loadObject(reportFilePath);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);
		String htmlUrl=new Date().getTime()+".html";
		JasperExportManager.exportReportToHtmlFile(jasperPrint,htmlUrl);
		return htmlUrl;
		
	}
	public List<ArticleDTO> queryAritcle(QueryCreteria creteria) {
		List<ArticleDTO> goodList=new ArrayList<ArticleDTO>();
		goodList.add(new ArticleDTO("一致性hash算法","AAAAA","技术",new Date(),20,189,100,"访问安全组"));
		goodList.add(new ArticleDTO("信息安全-对称加密","BBBBB","技术",new Date(),20,200,10,"流程管理组"));
		goodList.add(new ArticleDTO("信息安全-非对称加密","CCCCC","管理",new Date(),50,300,10,"科技支持组"));
		goodList.add(new ArticleDTO("多线程安全","DDDD","管理",new Date(),34,250,450,"文件服务组"));
		return goodList;
	}
	public List<ArticleReportDTO> querygroupArticle(){
		List<ArticleReportDTO> goodList=new ArrayList<ArticleReportDTO>();
		goodList.add(new ArticleReportDTO("访问安全组",500,10));
		goodList.add(new ArticleReportDTO("流程管理组",800,0));
		goodList.add(new ArticleReportDTO("文件服务组",200,0));
		goodList.add( new ArticleReportDTO("科技支持组",800,0));
		return goodList;
	}

}
