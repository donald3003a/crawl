package com.xxx.crawl.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;

public class CrawlerUtil {
	public static String downPageText(String url) {
		GetMethod getMethod = null;
		try {
			//创建一个客户端，类似于打开一个浏览器
			HttpClient httpclient=new HttpClient();
			httpclient.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
			//创建一个get 方法，类似于在浏览器地址栏中输入一个地址
			getMethod=new GetMethod(url);
			//设置编码格式
			//回车，获得响应状态码
			int statusCode=httpclient.executeMethod(getMethod);
			InputStream in = getMethod.getResponseBodyAsStream();
			String result = IOUtils.toString(in);
			return result;
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(getMethod!=null)
				getMethod.releaseConnection();
		}
		return null;
	}
}
