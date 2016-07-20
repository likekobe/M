package com.njnu.like.domain;

/**
 * 
 * <系统功能实体类>
 * <功能详细描述>
 * 
 * @author  like
 * @version  [版本号, 2016-6-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysFunction implements java.io.Serializable
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -9103782955090407925L;
    
    //主键ID
    private Integer iFunctionId;
    
    //功能图标
    private String sFunImage;
    
    //功能名称
    private String sFunName;
    
    //功能地址
    private String sFunUrl;
    
    //上级地址
    private Integer iSuperId;
    
    //是否启用
    private String sEnable;
    
    //层次编码
    private String sLevelCode;
    
    //删除标识
    private String sDr;
    
    public SysFunction()
    {
        
    }
    
    public Integer getiFunctionId()
    {
        return iFunctionId;
    }
    
    public void setiFunctionId(Integer iFunctionId)
    {
        this.iFunctionId = iFunctionId;
    }
    
    public String getsFunImage()
    {
        return sFunImage;
    }
    
    public void setsFunImage(String sFunImage)
    {
        this.sFunImage = sFunImage;
    }
    
    public String getsFunName()
    {
        return sFunName;
    }
    
    public void setsFunName(String sFunName)
    {
        this.sFunName = sFunName;
    }
    
    public String getsFunUrl()
    {
        return sFunUrl;
    }
    
    public void setsFunUrl(String sFunUrl)
    {
        this.sFunUrl = sFunUrl;
    }
    
    public Integer getiSuperId()
    {
        return iSuperId;
    }
    
    public void setiSuperId(Integer iSuperId)
    {
        this.iSuperId = iSuperId;
    }
    
    public String getsEnable()
    {
        return sEnable;
    }
    
    public void setsEnable(String sEnable)
    {
        this.sEnable = sEnable;
    }
    
    public String getsLevelCode()
    {
        return sLevelCode;
    }
    
    public void setsLevelCode(String sLevelCode)
    {
        this.sLevelCode = sLevelCode;
    }
    
    public String getsDr()
    {
        return sDr;
    }
    
    public void setsDr(String sDr)
    {
        this.sDr = sDr;
    }
    
}
