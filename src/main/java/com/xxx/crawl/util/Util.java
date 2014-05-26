package com.xxx.crawl.util;

public class Util {
	public static String getCleanText(String text) {
		text = text.replaceAll(" ", "");
		text = text.replaceAll("\r", "");
		text = text.replaceAll("\n", "");
		return text;
	}
}
