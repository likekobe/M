package com.njnu.like.webservice;

import javax.jws.WebService;

import com.njnu.like.webservice.itf.IAllDataWebService;
import com.njnu.like.webservice.service.itf.IGatherDataWService;

@WebService(endpointInterface = "com.cgdz.ecms.webservice.itf.IAllDataWebService", targetNamespace = "http://itf.webservice.ecms.cgdz.com/")
public class AllDataWebService implements IAllDataWebService
{
    private IGatherDataWService gatherDataWService;
    
    @Override
    public String findElecData(String sBuildId, String sSerialId)
    {
        return gatherDataWService.findElecData(sBuildId, sSerialId);
    }
    
    @Override
    public String findWaterData(String sBuildId, String sSerialId)
    {
        return gatherDataWService.findWaterData(sBuildId, sSerialId);
    }
    
    public IGatherDataWService getGatherDataWService()
    {
        return gatherDataWService;
    }
    
    public void setGatherDataWService(IGatherDataWService gatherDataWService)
    {
        this.gatherDataWService = gatherDataWService;
    }
    
}
