package com.xxx.crawl.common.dto;


import java.util.Arrays;
import java.util.Collection;
/**
 * 此类用于ireport创建报表模板
 * @author Administrator
 *
 */
public class ArticleReportFactory {
	private static ArticleReportDTO[] data={
								 	 new ArticleReportDTO("访问安全组",500,12),
									 new ArticleReportDTO("流程管理组",800,23),
									 new ArticleReportDTO("文件服务组",800,23),
									 new ArticleReportDTO("科技支持组",800,23)
	};
	public static Object[] getBeanArray() {
			return data;
	}
	public static Collection<?> getBeanCollection() {
		return Arrays.asList(data);
	}
}
