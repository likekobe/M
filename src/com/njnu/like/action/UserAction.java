package com.njnu.like.action;

/**
 * 
 * 用户登录及管理Action
 * @author  Yurj
 * @version  [版本号, 2012-9-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UserAction extends BaseAction
{
    
    private static final long serialVersionUID = -3124264046407115984L;
    
    /**
     * 登录系统功能，验证查询数据库是否存在次用户,不存在则返回登录页面，存在则把用户的相关信息放入session中
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String login()
        throws Exception
    {
        return SUCCESS;
    }
    
    public String skip()
    {
        return SUCCESS;
    }
    
    public String skipTabs()
    {
        return SUCCESS;
    }
    
}
