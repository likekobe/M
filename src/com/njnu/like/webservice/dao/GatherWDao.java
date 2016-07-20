package com.njnu.like.webservice.dao;

import java.util.List;

import org.hibernate.Hibernate;

import com.njnu.like.dao.BaseDAO;
import com.njnu.like.webservice.dao.itf.IGatherWDao;

@SuppressWarnings("rawtypes")
public class GatherWDao extends BaseDAO implements IGatherWDao
{
    
    @Override
    public List<Object[]> findElec(String sSql)
    {
        
        return getSession().createSQLQuery(sSql)
            .addScalar("total_used", Hibernate.DOUBLE)
            .addScalar("data_date", Hibernate.STRING)
            .list();
    }
    
    @Override
    public List<Object[]> findWater(String sSql)
    {
        return getSession().createSQLQuery(sSql)
            .addScalar("sum_used", Hibernate.DOUBLE)
            .addScalar("data_date", Hibernate.STRING)
            .list();
    }
    
    @Override
    public List<Integer> findMeterType(String sSql)
    {
        
        return getSession().createSQLQuery(sSql).addScalar("code", Hibernate.INTEGER).list();
    }
    
}
