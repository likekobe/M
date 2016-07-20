package com.njnu.like.webservice.dao.itf;

import java.util.List;

public interface IGatherWDao
{
    public List<Object[]> findElec(String sSql);
    
    public List<Object[]> findWater(String sSql);
    
    public List<Integer> findMeterType(String sSql);
}
