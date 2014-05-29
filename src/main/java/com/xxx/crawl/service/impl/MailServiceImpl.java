package com.xxx.crawl.service.impl;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import com.xxx.crawl.common.dto.MailDTO;
import com.xxx.crawl.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Override
	public void sendMail(MailDTO mail) throws EmailException {
		
		EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath("mypictures/john.jpg");
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("Picture of John");
		  attachment.setName("John");
		Email email = new SimpleEmail();
		email.setHostName("smtp.sina.com.cn");
		email.setSmtpPort(25);
		email.setAuthentication("lcgl_526", "lcgl123");
		email.setSSLOnConnect(false);
		email.setFrom("lcgl_526@sina.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("zhaowenzhen515@pingan.com");
		email.send();
	}

	public static void main(String[] args) throws EmailException {
		MailServiceImpl m = new MailServiceImpl();
		m.sendMail(null);
	}

}
