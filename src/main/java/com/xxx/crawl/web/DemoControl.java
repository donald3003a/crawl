package com.xxx.crawl.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.crawl.domain.Demo;
import com.xxx.crawl.service.DemoService;

@Controller
public class DemoControl {

	@Autowired
	private DemoService demoSevice;

	@RequestMapping(value = "/list.do", method = { RequestMethod.GET })
	@ResponseBody
	public List<Demo> list() {
		System.out.println("list");
		List<Demo> orderList = demoSevice.getAllDemo();
		return orderList;
	}
}
