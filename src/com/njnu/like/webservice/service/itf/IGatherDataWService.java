package com.njnu.like.webservice.service.itf;

public interface IGatherDataWService
{
    public String findElecData(String sBuildId, String sSerialId);
    
    public String findWaterData(String sBuildId, String sSerialId);
}
