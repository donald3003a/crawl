package com.xxx.crawl.dao;

import org.springframework.stereotype.Repository;

import com.xxx.crawl.domain.Demo;

@Repository("demoDao")
public class DemoDao extends BaseHibernateDao<Demo, Long> {

}
