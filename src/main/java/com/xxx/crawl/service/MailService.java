package com.xxx.crawl.service;

import org.apache.commons.mail.EmailException;

import com.xxx.crawl.common.dto.MailDTO;

public interface MailService {
	public void sendMail(MailDTO mail) throws EmailException;
}
