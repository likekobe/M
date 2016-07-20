package com.njnu.like.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.njnu.like.action.BaseAction;

public class AccessFilter implements Filter
{
    
    private String url;
    
    public void destroy()
    {
    }
    
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
        throws IOException, ServletException
    {
        arg0.setCharacterEncoding("UTF-8");
        
        String uri = ((HttpServletRequest)arg0).getRequestURI();
        HttpServletResponse response = (HttpServletResponse)arg1;
        HttpServletRequest request = (HttpServletRequest)arg0;
        HttpSession session = request.getSession();
        String IpAddress = request.getHeader("Host");
        
        if (request.getHeader("x-requested-with") != null
            && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")
            && (session.getAttribute(BaseAction.USER_SESSION_KEY) == null))
        {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");
            response.addHeader("sessionstatus", "timeout");
            return;
        }
        else
        {
            if ((!uri.equals("/ecms/")) && uri.indexOf("Login.jsp") == -1 && uri.indexOf("Logout.jsp") == -1
                && uri.indexOf("Login.action") == -1 && uri.indexOf("Logout.action") == -1
                && (session.getAttribute("USER_SESSION_KEY") == null))
            {
                /*String a = response.getHeader("HOST");*/
                /*		response.getWriter().print("<script type=\"text/javascript\"> window.open( \"http://localhost:8080/ecms\",\"_top\",\"\")  </script>");
                */
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Expires", "-1");
                
                response.getWriter().print("<script type=\"text/javascript\"> window.open( \"http://" + IpAddress
                    + "/ecms\",\"_top\",\"\")  </script>");
                return;
            }
            else if ((session.getAttribute("USER_SESSION_KEY") != null)
                && (uri.equals("/ecms/") || uri.indexOf("Login.jsp") != -1)
                && uri.indexOf("ModuleNavigator.action") == -1)
            {
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Expires", "-1");
                
                response.getWriter().print("<script type=\"text/javascript\"> window.open( \"http://" + IpAddress
                    + "/ecms/ModuleNavigator.action\",\"_top\",\"\")  </script>");
                return;
            }
            else
            {
                arg2.doFilter(request, response);
            }
        }
        
    }
    
    public void init(FilterConfig arg0)
        throws ServletException
    {
        url = arg0.getInitParameter("url");
        
        if (!url.startsWith("/"))
        {
            url = "/" + url;
        }
    }
}
