package com.xxx.crawl.common.dto;


import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
/**
 * 此类用于ireport创建报表模板
 * @author Administrator
 *
 */
public class ArticleFactory {
	private static ArticleDTO[] data={
								 	 new ArticleDTO("name1","zhaowenzhen","tech",new Date(),1,1,1,"fanwen"),
									 new ArticleDTO("name2","zhaowenzhen","tech",new Date(),1,1,1,"ssss")
	};
	public static Object[] getBeanArray() {
			return data;
	}
	public static Collection<?> getBeanCollection() {
		return Arrays.asList(data);
	}
}
