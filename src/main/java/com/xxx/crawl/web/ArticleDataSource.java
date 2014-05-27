package com.xxx.crawl.web;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ArticleDataSource implements JRDataSource{

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		// TODO Auto-generated method stub
		return null;
	}
/*	private int index = -1;
	@Override
	public boolean next() throws JRException {
		index++;
		return (index < articleList.size());
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		Object value = null;
		String fieldName = jrField.getName();
		ArticleDTO dto=articleList.get(index);
		value=dto.getFieldVale(fieldName);
		return value;
	}
*/
}
