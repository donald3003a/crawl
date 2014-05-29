package com.xxx.crawl.common.quartz;


import javax.annotation.Resource;

import org.apache.commons.mail.EmailException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.xxx.crawl.common.dto.MailDTO;
import com.xxx.crawl.service.MailService;

public class MailJob implements Job {
	@Resource(name="mailService")
	private MailService mailService;
	public void execute(JobExecutionContext context) throws JobExecutionException {
		MailDTO mail=new MailDTO();
		try {
			mailService.sendMail(mail);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
