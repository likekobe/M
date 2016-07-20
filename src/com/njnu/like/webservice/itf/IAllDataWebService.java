package com.njnu.like.webservice.itf;

import javax.jws.WebService;

@WebService
public interface IAllDataWebService
{
    public String findElecData(String sBuildId, String sSerialId);
    
    public String findWaterData(String sBuildId, String sSerialId);
}
