package com.xxx.crawl.service.impl;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.stereotype.Service;

import com.xxx.crawl.common.dto.MailDTO;
import com.xxx.crawl.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Override
	public void sendMail(MailDTO mail) throws EmailException {
		
		EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath("pom.xml");
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("Picture of John");
		  attachment.setName("pom.xml");
		  MultiPartEmail email = new MultiPartEmail(); 
		email.setHostName("smtp.sina.com.cn");
		email.setSmtpPort(25);
		email.setAuthentication("lcgl_526@sina.com", "aaaaa888");
		email.setSSLOnConnect(false);
		email.setFrom("lcgl_526@sina.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("liwei387@pingan.com");
		email.attach(attachment);
		email.send();
	}

	public static void main(String[] args) throws EmailException {
		MailServiceImpl m = new MailServiceImpl();
		m.sendMail(null);
	}

}
