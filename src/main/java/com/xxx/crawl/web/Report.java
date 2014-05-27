package com.xxx.crawl.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.crawl.domain.Demo;
import com.xxx.crawl.service.impl.JasperReportServiceImpl;
@Controller
public class Report {
	@RequestMapping(value = "/getReport.do", method = { RequestMethod.GET })
	@ResponseBody
	public List<Demo> list(HttpServletRequest request,HttpServletResponse response) {
		String group=request.getParameter("groupId");
		System.out.println("ss");
		return null;
		}
	public static void compile(String jrxml) throws JRException {
		JasperReportServiceImpl service=new JasperReportServiceImpl();
		service.compileReportXml(jrxml);
	}
	public static void main(String args[]) throws JRException, ClassNotFoundException, SQLException, FileNotFoundException {  
		 String file="D:\\report5.jrxml";
		 compile(file);
		  File reportFilePath =new File( "D:\\report1sss.jasper");
		/*//  String reportFilePath = "D:\\goodPrint.jasper";
		  List goodList=new ArrayList<GoodPrint>();
		  goodList.add(new GoodPrint("GOO1",100,200,new BigDecimal(100),"物料"));
		  goodList.add(new GoodPrint("GOO2",100,200,new BigDecimal(80),"物料"));
		//   JRDataSource dataSource = this.createDataSource();
		  JRDataSource dataSource = new JRBeanCollectionDataSource(goodList);;
		//   Map<String, Object> parameters = new HashMap<String, Object>();
		//   List dataList =new ArrayList();
		//   parameters.put("dataList", dataList);
		  JasperReport report = (JasperReport)JRLoader.loadObject(reportFilePath);
		  JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);
		  String repTpl="123";
		  JasperExportManager.exportReportToPdfFile(jasperPrint,"D:\\123.pdf");
		  JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:\\123.html");
		  */
		  
		// JasperRunManager.runReportToHtmlFile(root_path+"report/123.pdf",null,null);
		//如果创建报表成功，则转向该报表，其实可以把报表套在框架内，这样实现比较有意义的报表格式。
		//response.sendRedirect("report2.html");
		//   JasperFillManager.fillReport(report, parameters, dataSource);
		// OutputStream ouputStream = response.getOutputStream();  
		// response.setContentType("application/pdf");
		// response.setCharacterEncoding("UTF-8");  
		// String repName=URLEncoder.encode("物料报表", "UTF-8");
		// response.setHeader("Content-Disposition", "attachment; filename=\""+repName+"\".pdf"); 
//		              
//		         // 使用JRPdfExproter导出器导出pdf  
//		         JRPdfExporter exporter = new JRPdfExporter();  
//		         exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//		         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);  
//		         exporter.exportReport();
		// ouputStream.close(); 
    }  
      
    public static Connection getConnection() throws ClassNotFoundException, SQLException {  
        Connection conn = null;  
        Class.forName("com.mysql.jdbc.Driver");  
        conn = DriverManager.getConnection("jdbc:mysql://localhost/report", "root", "Pa888888");  
        return conn;  
    }  
      
  
	
}
