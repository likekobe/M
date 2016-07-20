<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/jsp/common/CmnHdr.jsp" %>
	<base href="<%=basePath%>">
    <title>退出</title>
    <script type="text/javascript">
        $(function () {
         	if(confirm("已退出系统。是否关闭窗口？"))
    		{
    			window.opener = null;      
    			window.open("", "_self"); 
         		window.close();
         		
    		}else{
    			window.location.href="<%=basePath%>jsp/platform/Login.jsp";
    		}
        });
    </script>
</head>
<body>
</body>
</html>
