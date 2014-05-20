package com.xxx.crawl.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

public abstract class BaseHibernateDao<T extends java.io.Serializable, PK extends java.io.Serializable> implements
		BaseDao<T, PK> {
	public static final Logger logger = LoggerFactory.getLogger(BaseHibernateDao.class);

	private final Class<T> entityClass;
	private String pkName = null;

	@SuppressWarnings("unchecked")
	public BaseHibernateDao() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Field[] fields = this.entityClass.getDeclaredFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(Id.class)) {
				this.pkName = f.getName();
			}
		}
		Assert.notNull(pkName);
	}

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public PK save(T model) {
		return (PK) getSession().save(model);
	}

	public void saveOrUpdate(T model) {
		getSession().saveOrUpdate(model);
	}

	public void update(T model) {
		getSession().update(model);

	}

	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		System.out.println("list1");
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		Criteria criteria = getSession().createCriteria(entityClass);
		System.out.println("list2");
		return criteria.list();
	}

	public void merge(T model) {
		getSession().merge(model);
	}

	public void delete(PK id) {
		getSession().delete(this.get(id));

	}

	public void deleteObject(T model) {
		getSession().delete(model);

	}

	public boolean exists(PK id) {
		return get(id) != null;
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return (T) getSession().get(this.entityClass, id);
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

}
