package com.xxx.crawl.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.crawl.domain.Demo;
import com.xxx.crawl.dto.ArticleInfoDTO;
import com.xxx.crawl.parse.CsdnParse;
import com.xxx.crawl.parse.IteyeParse;
import com.xxx.crawl.service.ArticleService;
import com.xxx.crawl.service.DemoService;

@Controller
public class DemoControl {

	@Autowired
	private DemoService demoSevice;
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/list.do", method = { RequestMethod.GET })
	@ResponseBody
	public List<Demo> list() {
		System.out.println("list");
		List<Demo> orderList = demoSevice.getAllDemo();
		return orderList;
	}
	
	@RequestMapping(value = "/csdn.do", method = { RequestMethod.GET })
	@ResponseBody
	public void testCsdn() {
		System.out.println("test begin");
		CsdnParse cp = new CsdnParse();
		List<ArticleInfoDTO> result = new  ArrayList<ArticleInfoDTO>();
		result = cp.getArticleInfo();
		articleService.saveArticles(result);
		System.out.println("test begin");
	}
	
	@RequestMapping(value = "/iteye.do", method = { RequestMethod.GET })
	@ResponseBody
	public void testIteye() {
		System.out.println("test begin");
		IteyeParse cp = new IteyeParse();
		List<ArticleInfoDTO> result = new  ArrayList<ArticleInfoDTO>();
		result = cp.getArticleInfo();
		articleService.saveArticles(result);
		System.out.println("test begin");
	}
	
	public static void main(String[] args) {
		CsdnParse cp = new CsdnParse();
		List<ArticleInfoDTO> result = new  ArrayList<ArticleInfoDTO>();
		result = cp.getArticleInfo();
		System.out.println("end");
	}
}
