<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/common/CmnHdr.jsp" %>
<base href="<%=basePath%>"/>
<title>Insert title here</title>

<style type="text/css">

    

	
	 
</style>

<script>
	function skipToFrame(str)
	{
		if(str!="")
		{
			document.getElementById("ifraMain").src=str+"";
		}

	}
</script>

</head>

<body>
	<div id="menuDiv" style="height:180px;line-height:180px;background-image:url('/M/media/top.jpg');"> 
		<a href="">
			<div style="width:80px;height:80px;float:left">
				<div align="center" style="height:60px" > <img style="height:30px;width:30px" src="/M/media/zhyl.png"/> </div>
				<div align="center" style="height:20px;color:white"><span>综合一览</span></div>
			</div>
			
		</a>
		
		
	</div>
	
	<div id="contentDiv" style="height:85%;width:100%">
		<iframe name="ifraMain" id="ifraMain" src="skip.action" frameborder="0" width="100%" height="100%" scrolling="auto" ></iframe>
	</div>
</body>


</html>