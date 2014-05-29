package com.xxx.crawl.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	public static Map<String,String> group = new HashMap<String,String>();
	
	public static Map<String,String> user = new HashMap<String,String>();
	
	
	static {
		
		group.put("W", "文件服务组");
		group.put("L", "流程管理组");
		group.put("F", "访问安全组");
		
		user.put("LJ", "刘杰");
		user.put("GYY", "郭杨勇");
		user.put("WZJ", "魏中佳");
		user.put("ZZM", "赵芝明");
		user.put("LW", "李巍");
		user.put("ZRQ", "张睿千");
		user.put("ZH", "张恒");
		user.put("LB", "刘宝");
		user.put("WL", "王丽");
		user.put("WSL", "王苏龙");
		user.put("WG", "王刚");
		user.put("HF", "黄飞");
		
		
	}
	
	public static String getCleanText(String text) {
		text = text.replaceAll(" ", "");
		text = text.replaceAll("\r", "");
		text = text.replaceAll("\n", "");
		return text;
	}
	
	public static boolean matchTag(String str) {
		Pattern p = Pattern.compile("[a-zA-Z]*-[a-zA-Z]*");
		Matcher m = p.matcher(str);
		return  m.matches();
	}
}
