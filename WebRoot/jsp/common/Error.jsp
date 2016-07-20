<%@ page import="java.io.*" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--<%
Throwable ex = null;
if (exception != null)
	ex = exception;
if (request.getAttribute("javax.servlet.error.exception") != null)
	ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

StringBuilder sb = new StringBuilder(ex.toString() + "<br />Stacktrace:<br />");
StackTraceElement[] elements = ex.getStackTrace();
for (int i = 0; i < elements.length; i ++) {
	sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at " + elements[i].getClassName() + "." + elements[i].getMethodName() + "(" + elements[i].getFileName() + ":" + elements[i].getLineNumber() + ")<br />");
}
%>

--%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="CmnHdr.jsp"%>	
    <base href="<%=basePath%>">    
    <title>出错啦</title>
    <script type="text/javascript">
        $(function () {         	
        	$("#btnBack").button().click(function(){
        		history.go(-1);
        	});
        	
        	$("#btnDetail").button().click(function(){
        		$("#divErrorDetail").toggle();
        	});
        });
    </script>
</head>
  
<body class="page-header-fixed">

	 <div class="row-fluid">
	
		<div class="tabbable tabbable-custom boxless" style="margin-bottom:0px" id="tabs">
			<div class="tab-content" style="margin-top:0px;border:0px" id="tab1">
				<div class="tab-pane active " id="tab_1">
					<div class="span9" style="width:98%;">
						<div style="margin-left:35px;height:620px;margin-top:40px">
						
						    <div class="error">
						        <div class="error-head">出错啦</div>
						        <div class="error-content">
						            <div class="error-text">
						            	服务器内部错误。
						            </div>
						        </div>
						        <div class="error-footer">
						        	<a class="btn" type="button" id="btnBack"  style="width:85px">返回上一页</a>
									<a class="btn" type="button" id="btnDetail" style="width:85px">详细错误信息</a>
						        </div>
						    </div>
						    
						    <div id="divErrorDetail" class="error-detail" style="display: none;overflow:auto;width:90%;height:500px">
						    	<s:set name="stackTraceElements" value="exceptionStack"/>
						        <%
						    		if (pageContext.getAttribute("stackTraceElements") != null) 
						    		{
							    		String[] stackTraceElements = ((String)pageContext.getAttribute("stackTraceElements")).split("\\sat\\s");
							    		out.print("&nbsp;" + stackTraceElements[0]);
							    		for(int i = 1, len = stackTraceElements.length; i < len; i++) 
							    		{
							    			out.print("<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at&nbsp;" + stackTraceElements[i]);
							    		}
						    		}
						    	%>
						    </div>
	        			</div>
	            	</div>
	    		</div>
	   		</div>
		</div>
		
	  </div>

</body>

</html>