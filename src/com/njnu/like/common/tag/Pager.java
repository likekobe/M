package com.njnu.like.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 分页标签。
 * 
 * @author caixh
 * 
 */
public class Pager extends SimpleTagSupport
{
    
    private String action;
    
    private String formId;
    
    private String pageNumber;
    
    private String pageSize;
    
    private String totalItems;
    
    public String getAction()
    {
        return action;
    }
    
    public void setAction(String action)
    {
        this.action = action;
    }
    
    public String getFormId()
    {
        return formId;
    }
    
    public void setFormId(String formId)
    {
        this.formId = formId;
    }
    
    public String getPageNumber()
    {
        return pageNumber;
    }
    
    public void setPageNumber(String pageNumber)
    {
        this.pageNumber = pageNumber;
    }
    
    public String getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(String pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public String getTotalItems()
    {
        return totalItems;
    }
    
    public void setTotalItems(String totalItems)
    {
        this.totalItems = totalItems;
    }
    
    private int getTotalPages()
    {
        if ("".equals(totalItems))
            totalItems = "0";
        if ("".equals(pageSize))
            pageSize = "0";
        int tmpTotalItems = Integer.parseInt(totalItems);
        int tmpPageSize = Integer.parseInt(pageSize);
        
        if (tmpTotalItems != 0)
        {
            if (tmpTotalItems % tmpPageSize == 0)
            {
                return tmpTotalItems / tmpPageSize;
            }
            else
            {
                return tmpTotalItems / tmpPageSize + 1;
            }
            
        }
        else
        {
            return 1;
        }
        
    }
    
    public void doTag()
        throws JspException
    {
        
        JspWriter writer = getJspContext().getOut();
        
        try
        {
            writer.write(getNavigationBar());
            
            JspFragment f = getJspBody();
            if (f != null)
                f.invoke(writer);
            
        }
        catch (java.io.IOException ex)
        {
            throw new JspException(ex.getMessage());
        }
        
    }
    
    private String getNavigationBar()
    {
        
        StringBuilder sbNav = new StringBuilder();
        sbNav.append("<table style=\"width:98%;padding:0 10px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
        sbNav.append("<tr>");
        sbNav.append("<td>");
        sbNav.append("&nbsp;第&nbsp;<span class=\"page-font\">");
        int totalpage_ = getTotalPages();
        if (null != pageNumber)
        {
            if (Integer.parseInt(pageNumber) > totalpage_)
            {
                pageNumber = String.valueOf(totalpage_);
            }
        }
        sbNav.append(pageNumber);
        sbNav.append("</span>&nbsp;/&nbsp;<span class=\"page-font\">");
        sbNav.append(totalpage_);
        sbNav.append("</span>&nbsp;页&nbsp;(共&nbsp;<span class=\"page-font\">");
        sbNav.append(totalItems);
        sbNav.append("</span>&nbsp;条)");
        sbNav.append("</td>");
        
        // 没有记录
        if (Integer.parseInt(getTotalItems()) == 0)
        {
            sbNav.append("<td width=\"120px\" align=\"right\">");
            sbNav.append("<span class=\"page-first\" title=\"首页\"></span>");
            sbNav.append("<span class=\"page-pre\" title=\"上一页\"></span>");
            sbNav.append("<span class=\"page-next\" title=\"下一页\"></span>");
            sbNav.append("<span class=\"page-last\" title=\"末页\"></span>");
            sbNav.append("</td>");
            sbNav.append("<td width=\"110px\" align=\"right\">");
            sbNav.append("每页显示&nbsp;<input type=\"text\" name=\"pageSize\" id=\"pageSize\" readonly style=\"width:30px;\" value=\"");
            sbNav.append(pageSize);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"80px\" align=\"right\">");
            sbNav.append("跳转<input name=\"pageNumber\" type=\"text\" readonly style=\"width:30px\" value=\"");
            sbNav.append(pageNumber);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"30px\">");
            sbNav.append("<span class=\"page-jump\" title=\"跳转\"></span>");
            sbNav.append("</td>");
        }
        // 只有一页
        else if (getTotalPages() == 1 && Integer.parseInt(totalItems) > 0)
        {
            sbNav.append("<td width=\"120px\" align=\"right\">");
            sbNav.append("<span class=\"page-first\" title=\"首页\"></span>");
            sbNav.append("<span class=\"page-pre\" title=\"上一页\"></span>");
            sbNav.append("<span class=\"page-next\" title=\"下一页\"></span>");
            sbNav.append("<span class=\"page-last\" title=\"末页\"></span>");
            sbNav.append("</td>");
            sbNav.append("<td width=\"110px\" align=\"right\">");
            sbNav.append("每页显示<input type=\"text\" name=\"pageSize\" id=\"pageSize\" style=\"width:30px;\" maxlength=\"9\" onkeydown=\"javascript:onlyNumber();\" onchange=\"javascript:changeCurrentPageSize()\" value=\"");
            sbNav.append(pageSize);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"80px\" align=\"right\">");
            sbNav.append("跳转<input name=\"pageNumber\" id=\"pageNumber\" type=\"text\" style=\"width:30px;\" maxlength=\"9\" onkeydown=\"javascript:onlyNumber();\" value=\"");
            sbNav.append(pageNumber);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"30px\">");
            sbNav.append("<span class=\"page-jump\" title=\"跳转\"></span>");
            sbNav.append("</td>");
        }
        // 第一页
        else if (Integer.parseInt(pageNumber) == 1)
        {
            sbNav.append("<td width=\"120px\" align=\"right\">");
            sbNav.append("<span class=\"page-first\" title=\"首页\"></span>");
            sbNav.append("<span class=\"page-pre\" title=\"上一页\"></span>");
            sbNav.append("<span class=\"page-next\" title=\"下一页\" onclick=\"javascript:return PageSubmit('next');\"></span>");
            sbNav.append("<span class=\"page-last\" title=\"末页\" onclick=\"javascript:return PageSubmit('last');\"></span>");
            sbNav.append("</td>");
            sbNav.append("<td width=\"110px\" align=\"right\">");
            sbNav.append("每页显示&nbsp;<input type=\"text\" name=\"pageSize\" id=\"pageSize\" style=\"width:30px;\" maxlength=\"9\" onkeydown=\"javascript:onlyNumber();\" onchange=\"javascript:changeCurrentPageSize()\" value=\"");
            sbNav.append(pageSize);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"80px\" align=\"right\">");
            sbNav.append("跳转<input name=\"pageNumber\" id=\"pageNumber\" type=\"text\" style=\"width:30px;\" maxlength=\"9\" onkeydown=\"javascript:onlyNumber();\" value=\"");
            sbNav.append(pageNumber);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"30px\">");
            sbNav.append("<span class=\"page-jump\" title=\"跳转\" onclick=\"javascript:return PageSubmit('go');\"></span>");
            sbNav.append("</td>");
        }
        // 最后一页
        else if (Integer.parseInt(pageNumber) == getTotalPages())
        {
            sbNav.append("<td width=\"120px\" align=\"right\">");
            sbNav.append("<span class=\"page-first\" title=\"首页\" onclick=\"javascript:return PageSubmit('first');\"></span>");
            sbNav.append("<span class=\"page-pre\" title=\"上一页\" onclick=\"javascript:return PageSubmit('pre');\"></span>");
            sbNav.append("<span class=\"page-next\" title=\"下一页\"></span>");
            sbNav.append("<span class=\"page-last\" title=\"末页\"></span>");
            sbNav.append("</td>");
            sbNav.append("<td width=\"110px\" align=\"right\">");
            sbNav.append("每页显示&nbsp;<input type=\"text\" name=\"pageSize\" id=\"pageSize\" style=\"width:30px;\" maxlength=\"9\" onkeydown=\"javascript:onlyNumber();\" onchange=\"javascript:changeCurrentPageSize()\" value=\"");
            sbNav.append(pageSize);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"80px\" align=\"right\">");
            sbNav.append("跳转<input name=\"pageNumber\" id=\"pageNumber\" type=\"text\" style=\"width:30px;\" maxlength=\"9\" onkeydown=\"javascript:onlyNumber();\" value=\"");
            sbNav.append(pageNumber);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"30px\">");
            sbNav.append("<span class=\"page-jump\" title=\"跳转\" onclick=\"javascript:return PageSubmit('go');\"></span>");
            sbNav.append("</td>");
        }
        else
        {
            sbNav.append("<td width=\"120px\" align=\"right\">");
            sbNav.append("<span class=\"page-first\" title=\"首页\" onclick=\"javascript:return PageSubmit('first');\"></span>");
            sbNav.append("<span class=\"page-pre\" title=\"上一页\" onclick=\"javascript:return PageSubmit('pre');\"></span>");
            sbNav.append("<span class=\"page-next\" title=\"下一页\" onclick=\"javascript:return PageSubmit('next');\"></span>");
            sbNav.append("<span class=\"page-last\" title=\"末页\" onclick=\"javascript:return PageSubmit('last');\"></span>");
            sbNav.append("</td>");
            sbNav.append("<td width=\"110px\" align=\"right\">");
            sbNav.append("每页显示&nbsp;<input type=\"text\" name=\"pageSize\" id=\"pageSize\" style=\"width:30px;\" maxlength=\"9\" onkeydown=\"javascript:onlyNumber();\" onchange=\"javascript:changeCurrentPageSize()\" value=\"");
            sbNav.append(pageSize);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"80px\" align=\"right\">");
            sbNav.append("跳转<input name=\"pageNumber\" id=\"pageNumber\" type=\"text\" style=\"width:30px;\" maxlength=\"9\" onkeydown=\"javascript:onlyNumber();\" value=\"");
            sbNav.append(pageNumber);
            sbNav.append("\" />");
            sbNav.append("</td>");
            sbNav.append("<td width=\"30px\">");
            sbNav.append("<span class=\"page-jump\" title=\"跳转\" onclick=\"javascript:return PageSubmit('go');\"></span>");
            sbNav.append("</td>");
        }
        
        // 脚本
        sbNav.append("<td width=\"5\">");
        sbNav.append("<script type=\"text/javascript\">");
        sbNav.append("function PageSubmit(direction)");
        sbNav.append("{");
        sbNav.append("   oldPageNumber = " + pageNumber + ";");
        sbNav.append("   pageSize = parseInt(document.getElementById(\"pageSize\").value);");
        sbNav.append("   pageNumber = parseInt(document.getElementById(\"pageNumber\").value);");
        sbNav.append(" 	 switch (direction) {");
        sbNav.append(" 	     case 'first' :");
        sbNav.append("            pageNumber = 1; break;");
        sbNav.append(" 	     case 'pre' :");
        sbNav.append("            pageNumber = pageNumber - 1;break;");
        sbNav.append(" 	     case 'next' :");
        sbNav.append("            pageNumber = pageNumber + 1;break;");
        sbNav.append(" 	     case 'last' :");
        sbNav.append("            pageNumber = " + getTotalPages() + "; break;");
        sbNav.append(" 	 }");
        //		sbNav.append("   reg = /^[1-9][0-9]*$/;");
        sbNav.append("   if (direction == 'go') {");
        sbNav.append("       if (pageNumber > " + getTotalPages() + " || !/^[1-9][0-9]*$/g.test(pageNumber)) {");
        sbNav.append("          document.getElementById(\"pageNumber\").value = oldPageNumber;");
        sbNav.append("          return false;");
        sbNav.append("       }");
        sbNav.append("   }");
        //		sbNav.append("   if (!reg.test(pageSize)) {");
        //		sbNav.append("      return false;");
        //		sbNav.append("   }");
        sbNav.append("   var frm = document.getElementById(\"" + formId + "\");");
        sbNav.append("   var hid = document.createElement(\"input\");");
        sbNav.append("   hid.type = \"hidden\";");
        sbNav.append("   hid.name = \"_page\";");
        sbNav.append("   frm.appendChild(hid);");
        sbNav.append("   document.getElementById(\"pageNumber\").value = pageNumber;");
        sbNav.append("   document.getElementById(\"" + formId + "\").submit();");
        sbNav.append("}");
        sbNav.append("function changeCurrentPageSize() {");
        sbNav.append("   oldPageSize = " + pageSize + ";");
        sbNav.append("   pageSize = parseInt(document.getElementById(\"pageSize\").value);");
        sbNav.append("   reg = /^[1-9][0-9]*$/;");
        sbNav.append("   if (!reg.test(pageSize)) {");
        sbNav.append("      document.getElementById(\"pageSize\").value = oldPageSize;");
        sbNav.append("      return false;");
        sbNav.append("   }");
        sbNav.append("   document.getElementById(\"pageNumber\").value = 1;");
        sbNav.append("   document.getElementById(\"" + formId + "\").submit();");
        sbNav.append("}");
        sbNav.append("function onlyNumber()");
        sbNav.append("{");
        sbNav.append("   if(!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)))");
        sbNav.append("      event.returnValue = false;");
        sbNav.append("}");
        sbNav.append("</script>");
        
        // CSS
        //		sbNav.append("<style type=\"text/css\">");
        //		sbNav.append("	ul#icons {margin: 0; padding: 0;}");
        //		sbNav.append("  ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}");
        //		sbNav.append("  ul#icons span.ui-icon {float: left; margin: 0 4px;}");	
        //		sbNav.append("</style>");
        
        sbNav.append("</td>");
        sbNav.append("</tr>");
        //sbNav.append("<tr><td colspan=\"5\" style=\"height:2px\"></td></tr>");
        sbNav.append("</table>");
        
        return sbNav.toString();
    }
}