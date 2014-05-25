package com.xxx.crawl.common.util;

public class StringUtil {
public static boolean isNullOrEmpty(String value){
	return value==null||"".equals(value);
	
}
public static boolean isNotNullOrEmpty(String value){
	return value!=null&&!"".equals(value);
}
}
