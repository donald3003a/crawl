package com.xxx.crawl.service.impl;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import com.xxx.crawl.common.dto.MailDTO;
import com.xxx.crawl.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Override
	public void sendMail(MailDTO mail) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.sina.com.cn");
		email.setSmtpPort(25);
		email.setAuthentication("xxxxx", "xxxxxxx");
		email.setSSLOnConnect(false);
		email.setFrom("xxxxxxxx@sina.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("xxxxxxx@qq.com");
		email.send();
	}

	public static void main(String[] args) throws EmailException {
		MailServiceImpl m = new MailServiceImpl();
		m.sendMail(null);
	}

}
