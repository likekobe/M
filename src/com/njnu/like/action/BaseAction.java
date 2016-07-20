package com.njnu.like.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements Serializable
{
    
    private static final long serialVersionUID = -897675235149185801L;
    
    public final static String MODULE_SYSTEM = "01";
    
    public final static String MODULE_ELECTRICITY = "02";
    
    public final static String MODULE_WATER = "03";
    
    public final static String MODULE_GAS = "04";
    
    public final static String MODULE_WARM = "05";
    
    public final static String MODULE_CHARGE = "06";
    
    public final static String MODULE_MAP = "07";
    
    public final static String MODULE_AIR = "07";
    
    public final static String MODULE_JSZM = "08";
    
    /**
     * 没有任何权限时返回
     */
    public final static String NOAUTH = "noauth";
    
    /**
     * 数据发生错误时返回
     */
    public final static String DATAERR = "dataerr";
    
    /**
     * Session中用户信息KEY
     */
    public final static String USER_SESSION_KEY = "USER_SESSION_KEY";
    
    public final static String SCHOOL_SESSION_KEY = "SCHOOL_SESSION_KEY";
    
    /**
     * Session中用户所有模块功能菜单KEY
     */
    public final static String USER_ALL_MODULE_FUNCTION_SESSION_KEY = "USER_ALL_MODULE_FUNCTION_SESSION_KEY";
    
    /**
     * Session中用户选择的子系统模块编码和名称KEY
     */
    public final static String USER_MODULE_CODE = "USER_MODULE_CODE";
    
    public final static String USER_MODULE_NAME = "USER_MODULE_NAME";
    
    /**
     * 当前客户端登录用户账号
     */
    private String currentUserAccount = null;
    
    /**
     * 当前客户端登录用户姓名
     */
    private String currentUserName = null;
    
    public String jsonString;
    
    public String stringEscapeJavaScript(String str)
    {
        
        return StringEscapeUtils.escapeJavaScript(str);
    }
    
    public String stringEscapeHtml(String str)
    {
        
        return StringEscapeUtils.escapeHtml(str);
    }
    
    public String stringEscapeXml(String str)
    {
        
        return StringEscapeUtils.escapeXml(str);
    }
    
    public void outJsonString(String str)
    {
        getResponse().setContentType("text/javascript;charset=UTF-8");
        outString(str);
    }
    
    public void outJson(Object obj)
    {
        outJsonString(JSONObject.fromObject(obj).toString());
    }
    
    public void outJsonArray(Object array)
    {
        outJsonString(JSONArray.fromObject(array).toString());
    }
    
    public void outString(String str)
    {
        try
        {
            PrintWriter out = getResponse().getWriter();
            out.write(str);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void outXMLString(String xmlStr)
    {
        getResponse().setContentType("application/xml;charset=UTF-8");
        outString(xmlStr);
    }
    
    /**
     * 获得request
     * 
     * @return
     */
    public HttpServletRequest getRequest()
    {
        return ServletActionContext.getRequest();
    }
    
    /**
     * 获得response
     * 
     * @return
     */
    public HttpServletResponse getResponse()
    {
        return ServletActionContext.getResponse();
    }
    
    /**
     * 获得session
     * 
     * @return
     */
    public HttpSession getSession()
    {
        return getRequest().getSession();
    }
    
    /*public UsUserInfor getCurrentUser()
    {
        return (UsUserInfor)getSession().getAttribute(USER_SESSION_KEY);
    }*/
    
    /**
     * 获得servlet上下文
     * 
     * @return
     */
    public ServletContext getServletContext()
    {
        return ServletActionContext.getServletContext();
    }
    
    public String getRealyPath(String path)
    {
        return getServletContext().getRealPath(path);
    }
    
    public void setCurrentUserAccount(String currentUserAccount)
    {
        this.currentUserAccount = currentUserAccount;
    }
    
    public String getCurrentUserAccount()
    {
        return currentUserAccount;
    }
    
    public void setCurrentUserName(String currentUserName)
    {
        this.currentUserName = currentUserName;
    }
    
    public String getCurrentUserName()
    {
        return currentUserName;
    }
    
    // -- 页面属性:返回消息 -- //
    private String returnMessage = null;
    
    public String getReturnMessage()
    {
        return returnMessage;
    }
    
    public void setReturnMessage(String returnMessage)
    {
        this.returnMessage = returnMessage;
    }
    
    // -- 页面属性:分页 -- //
    private int pageSize = 5; // 每页显示1条
    
    private int pageNumber = 1; // 当前页码
    
    private int total = 0; // 记录总数
    
    private String _page; // 分页 Action标志
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public int getPageNumber()
    {
        if (_page == null)
        {
            pageNumber = 1;
        }
        if (pageNumber > 1)
        {
            
            if (getTotal() <= (pageNumber - 1) * pageSize)
            {
                
                if (getTotal() % pageSize == 0)
                {
                    pageNumber = getTotal() / pageSize;
                }
                else
                {
                    pageNumber = getTotal() / pageSize + 1;
                    
                }
                
            }
        }
        if (pageNumber == 0)
        {
            pageNumber = 1;
        }
        return pageNumber;
    }
    
    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }
    
    public int getTotal()
    {
        return total;
    }
    
    public void setTotal(int total)
    {
        this.total = total;
    }
    
    public String get_page()
    {
        return _page;
    }
    
    public void set_page(String _page)
    {
        this._page = _page;
    }
    
}