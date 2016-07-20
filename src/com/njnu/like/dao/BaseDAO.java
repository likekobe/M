package com.njnu.like.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.njnu.like.dao.itf.IBaseDAO;

/**
 * @author ZhuXiaobo
 *
 */
public class BaseDAO<T, ID extends Serializable> extends HibernateDaoSupport implements IBaseDAO<T, ID>
{
    
    private static final long serialVersionUID = -1174692087896435539L;
    
    static Logger logger = Logger.getLogger(BaseDAO.class.getName());
    
    //	static{
    //	    try{
    //	        if(!License.isAuthorized()){
    //	            System.exit(0);
    //	        }
    //	    }catch(Exception e){
    //	        System.exit(0);
    //	    }
    //	}
    
    /**
     * 将数据插入到数据库
     * @param obj
     * @return 主键
     */
    public String insert(T obj)
    {
        logger.debug("begin insert object " + obj.getClass().getName());
        String pk = (String)getHibernateTemplate().save(obj);
        logger.debug("insert object with pk:" + pk);
        return pk;
    }
    
    /**
     * 将数据插入到数据库
     * @param al
     * @return 主键集合
     */
    public List<String> insert(ArrayList<T> al)
    {
        ArrayList<String> pks = new ArrayList<String>();
        for (int i = 0; i < al.size(); i++)
        {
            pks.add(insert(al.get(i)));
        }
        return pks;
    }
    
    @Override
    public Long insertLongAll(T obj)
    {
        logger.debug("begin insert object " + obj.getClass().getName());
        Long pk = (Long)getHibernateTemplate().save(obj);
        logger.debug("insert object with pk:" + pk);
        return pk;
    }
    
    @Override
    public List<Long> insertLongAll(ArrayList<T> al)
    {
        ArrayList<Long> pks = new ArrayList<Long>();
        
        for (int i = 0; i < al.size(); i++)
        {
            pks.add(insertLongAll(al.get(i)));
        }
        return pks;
    }
    
    /**
     * 根据主键查找数据
     * @param id
     * @return
     */
    public T selectById(Class<T> c, ID id)
    {
        return getHibernateTemplate().get(c, id);
    }
    
    /**
     * 根据给定的对象查询具有相同属性的数据
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> selectByExample(T obj)
    {
        return (List<T>)getHibernateTemplate().findByExample(obj);
    }
    
    /**
     * 查询所有记录
     * @param <T>
     * @param c
     * @return
     */
    public List<T> selectAll(Class<T> c)
    {
        return getHibernateTemplate().loadAll(c);
    }
    
    /**
     * 将数据更新到数据库
     * @param obj
     * @return 
     */
    public void update(T obj)
    {
        getHibernateTemplate().update(obj);
    }
    
    /**
     * 将数据集合更新到数据库
     * @param obj
     * @return 
     */
    public void update(ArrayList<T> objs)
    {
        for (T t : objs)
        {
            update(t);
        }
    }
    
    public void delete(T odj)
    {
        getHibernateTemplate().delete(odj);
    }
    
    /**
     * 查询记录总数。
     * 
     * @param hql
     *            需要查询的 HQL 语句。
     * @return 记录总数。
     */
    public int selectTotalCount(String hql)
    {
        return getHibernateTemplate().find(hql).size();
    }
    
    /**
     * 分页查询。
     * 
     * @param hql
     *            需要查询的 HQL 语句。
     * @param pageNumber
     *            当前页码。
     * @param pageSize
     *            每页显示的记录数。
     * @return 当前页的对象列表。
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> selectByPage(final String hql, final int pageNumber, final int pageSize)
    {
        
        return getHibernateTemplate().executeFind(new HibernateCallback()
        {
            public Object doInHibernate(Session s)
                throws HibernateException, SQLException
            {
                
                return (List<T>)s.createQuery(hql)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
            }
        });
    }
    
    /**
     * 分页查询。
     * 
     * @param hql
     *            需要查询的 HQL 语句。
     * @param pageNumber
     *            当前页码。
     * @param pageSize
     *            每页显示的记录数。
     * @param values
     *            HQL 的参数。
     * @return 当前页的对象列表。
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> selectByPage(final String hql, final int pageNumber, final int pageSize, final Object... values)
    {
        
        return getHibernateTemplate().executeFind(new HibernateCallback()
        {
            public Object doInHibernate(Session s)
                throws HibernateException, SQLException
            {
                
                Query q = s.createQuery(hql);
                for (int i = 0; i < values.length; i++)
                {
                    q.setParameter(i, values[i]);
                }
                
                return (List<T>)q.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize).list();
            }
        });
    }
    
    /**
     * 通过 Native SQL 查询。
     * @param nativeSQL
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public List<T> selectByNativeSql(String nativeSQL)
    {
        return getSession().createSQLQuery(nativeSQL).list();
    }
    
    /* (non-Javadoc)
     * @see com.cgdz.ecms.dao.itf.IBaseDAO#updateByNativeSql(java.lang.String)
     */
    public int updateByNativeSql(String nativeSQL)
    {
        return getSession().createQuery(nativeSQL).executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> selectByNativeSqlPage(String nativeSQL, int pageNumber, int pageSize)
    {
        return getSession().createSQLQuery(nativeSQL)
            .setFirstResult((pageNumber - 1) * pageSize)
            .setMaxResults(pageSize)
            .list();
    }
    
    public void flush()
    {
        getSession().flush();
    }
    
    public void clear()
    {
        getSession().clear();
    }
    
    public static void main(String[] a)
    {
        //		System.out.println( getDate());
    }
    
}
