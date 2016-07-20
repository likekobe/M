<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/jsp/common/CmnHdr.jsp"%>
	<base href="<%=basePath%>"/>
	<title>Insert title here</title>
	
	<style>
		.unSelectA
		{
			background-color:#1BA0E1;
			color:#FFFFFF;
		}
	</style>
	
	<script>
		$(function(){
			$('#tabs').tabs(
				{
					select:function(event,ui)
					{
						switch(ui.index)
						{
						 	case 0:
						 	{
						 		document.getElementById("building").style.display="block";
						 		document.getElementById("organ").style.display="none";
						 		
						 		break;
						 	}
						 	case 1:
						 	{
						 		if($("#organ").attr("src")=="")
								{
									$("#organ").attr("src","showOrgSingleControl.action");
								}
						 		document.getElementById("building").style.display="none";
						 		document.getElementById("organ").style.display="block";
						 		
						 		break;
						 	}
						}
					}
				}
			);
		});

		//iFrame自适应高度
		function iFrameHeight(id)
		{
			var ifm=document.getElementById(id);
			var subWeb=document.frames?document.frames[id].document:ifm.contentDocument;
			if(ifm!=null&&subWeb!=null)
			{
				ifm.height=subWeb.body.scrollHeight;
			}
		
		}
		
		$('#tabs').tabs({selected:0});
	
	</script>
	
</head>
<body  class="page-header-fixed">

	<img src="media/image2/x1.jpg" style="margin-left:0px;margin-top:1px;width:100%;height:3px" />
 	<div class="tabbable tabbable-custom boxless" style="margin-top:10px;display:block;" id="tabs">

		<ul class="nav nav-tabs" style="margin-left:204px">
		    <li class="unSelectA"><a href="#buildingSingleControl" data-toggle="tab">&nbsp;建筑空调单个控制&nbsp;</a></li>
			<li class="unSelectA"><a href="#orgSingleControl" data-toggle="tab">&nbsp;部门空调单个控制&nbsp;</a></li> 
			
		</ul>
		
	    <div id="buildingSingleControl" style="margin-top:5px">
			<iframe id="building" src="" scrolling="auto" width="100%" frameborder="0" style="margin-left:0" height="630px"></iframe>
		</div>
		
		<div id="orgSingleControl" style="margin-top:5px">
			<iframe id="organ" src="" scrolling="auto" width="100%" frameborder="0" style="margin-left:0" height="630px" onload="iFrameHeight('organ')"></iframe>
		</div> 
		
	</div> 
	
	<ul id="myTab" class="nav nav-tabs">
	   <li class="active">
	      <a href="#home" data-toggle="tab">
	         W3Cschool Home
	      </a>
	   </li>
	   <li><a href="#ios" data-toggle="tab">iOS</a></li>
	</ul>
	
	<div id="myTabContent" class="tab-content">
	   <div class="tab-pane fade in active" id="home">
	      <iframe id="organ" src="" scrolling="auto" width="100%" frameborder="0" style="margin-left:0" height="630px" onload="iFrameHeight('organ')"></iframe>
	   </div>
	   <div class="tab-pane fade" id="ios">
	     <iframe id="organ" src="" scrolling="auto" width="100%" frameborder="0" style="margin-left:0" height="630px" onload="iFrameHeight('organ')"></iframe>
	   </div>
	</div>


</body>
</html>