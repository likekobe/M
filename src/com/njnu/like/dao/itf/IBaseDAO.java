package com.njnu.like.dao.itf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

public interface IBaseDAO<T, ID extends Serializable>
{
    public String insert(T obj);
    
    public List<String> insert(ArrayList<T> al);
    
    public Long insertLongAll(T obj);
    
    public List<Long> insertLongAll(ArrayList<T> al);
    
    public T selectById(Class<T> c, ID id);
    
    public List<T> selectByExample(T obj);
    
    public List<T> selectAll(Class<T> c);
    
    public void update(T obj);
    
    public void update(ArrayList<T> objs);
    
    public void delete(T odj);
    
    public int selectTotalCount(String hql);
    
    public List<T> selectByPage(final String hql, final int pageNumber, final int pageSize);
    
    public List<T> selectByPage(final String hql, final int pageNumber, final int pageSize, final Object... values);
    
    public List<T> selectByNativeSql(String nativeSQL);
    
    public List<T> selectByNativeSqlPage(String nativeSQL, int pageNumber, int pageSize);
    
    public void flush();
    
    public int updateByNativeSql(String nativeSQL);
    
    public void clear();
    
    public SessionFactory getSessionFactory();
    
}
