package com.njnu.like.webservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.njnu.like.webservice.dao.itf.IGatherWDao;
import com.njnu.like.webservice.service.itf.IGatherDataWService;

public class GatherDataWService implements IGatherDataWService
{
    private IGatherWDao gatherWDao;
    
    @Override
    public String findElecData(String sBuildId, String sSerialId)
    {
        /*try
        {*/
        
        //select total_used from (select  *  from bs_originaldata where elec_meter_id in ('8a8a8ae54461eb62014466e3da84005c') order by data_date desc) where rownum<=1
        String sSql =
            "select total_used,data_date from (select  *  from bs_originaldata where elec_meter_id in ('" + sSerialId
                + "') and total_used is not null order by data_date desc) where rownum<=1";
        
        List<Object[]> list = gatherWDao.findElec(sSql);
        if (list != null && list.size() > 0)
        {
            if (list.get(0) == null)
            {
                return null;
            }
            else
            {
                Object[] obj = list.get(0);
                String sData = obj[0] == null ? "0" : obj[0].toString();
                String sTime = obj[1].toString().substring(0, 10);
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String sNow = format.format(date);
                if (!sTime.equals(sNow))
                {
                    sData = "N" + sData;
                }
                
                return sData;
            }
        }
        else
        {
            return null;
        }
        /*}
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }*/
        
    }
    
    @Override
    public String findWaterData(String sBuildId, String sSerialId)
    {
        /*try
        {*/
        String sSqlMeterType =
            "select b.code from (select w.meter_type_id from us_watermeter_info w where w.meter_id in ('" + sSerialId
                + "')) a left join sys_meter_type b on a.meter_type_id=b.id";
        
        List<Integer> listType = gatherWDao.findMeterType(sSqlMeterType);
        Integer iType = listType.get(0);
        String sSql = "";
        //迪纳超声波水表
        if (iType == 7)
        {
            sSql =
                "select (total_used+total_buy) as sum_used,data_date from (select  *  from bs_originaldata where water_meter_id in ('"
                    + sSerialId
                    + "') and total_used is not null and total_buy is not null order by data_date desc) where rownum<=1";
        }
        else
        {
            sSql =
                "select total_used as sum_used,data_date from (select  *  from bs_originaldata where water_meter_id in ('"
                    + sSerialId + "') and total_used is not null order by data_date desc) where rownum<=1";
        }
        
        List<Object[]> list = gatherWDao.findWater(sSql);
        if (list != null && list.size() > 0)
        {
            if (list.get(0) == null)
            {
                return null;
            }
            else
            {
                Object[] obj = list.get(0);
                String sData = obj[0] == null ? "0" : obj[0].toString();
                Double dData = Double.parseDouble(sData);
                dData = Math.abs(dData);
                sData = dData.toString();
                
                String sTime = obj[1].toString().substring(0, 10);
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String sNow = format.format(date);
                if (!sTime.equals(sNow))
                {
                    sData = "N" + sData;
                }
                
                return sData;
            }
        }
        else
        {
            return null;
        }
        /* }
         catch (Exception e)
         {
             e.printStackTrace();
             return null;
         }*/
        
    }
    
    public IGatherWDao getGatherWDao()
    {
        return gatherWDao;
    }
    
    public void setGatherWDao(IGatherWDao gatherWDao)
    {
        this.gatherWDao = gatherWDao;
    }
    
}
