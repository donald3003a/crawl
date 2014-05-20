package com.xxx.crawl.dao;


public interface BaseDao<T extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public PK save(T model);

    public void saveOrUpdate(T model);
    
    public void merge(T model);

    public void delete(PK id);

    public void deleteObject(T model);

    public T get(PK id);
    
    boolean exists(PK id);
    
    public void flush();
    
    public void clear();



}
