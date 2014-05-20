package com.xxx.crawl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.crawl.dao.DemoDao;
import com.xxx.crawl.domain.Demo;
import com.xxx.crawl.service.DemoService;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao demoDao;

	public List<Demo> getAllDemo() {
		System.out.println("listD");
		return demoDao.listAll();
	}

}
