<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/jsp/common/CmnHdr.jsp" %>
	<base href="<%=basePath%>"/>
    <title>江苏城建学院::能耗平台::登录</title>
    <script type="text/javascript">
		if (window != top) 
		{
			top.location.href = location.href;
		}
    
		if ('${returnMessage}' != '') 
		{
			alert('${returnMessage}');
		}
		
        $(function () {        	
        	if (!navigator.cookieEnabled) 
        	{
        		alert("您的浏览器未启用 Cookie，部分功能(如换肤和快捷菜单)将无法使用。");
        	}
        	
        	$("#name").focus();
        	
        	var name = $("#name"),
			password = $("#password"),			
			allFields = $([]).add(name).add(password);
        	
        	$("#submitbn").click(function(){
				var bValid = true;
				allFields.removeClass("ui-state-error");
				if ($.trim(name.val()).length <= 0) 
				{
					name.addClass("ui-state-error");
					bValid=false;
					alert("请输入用户名。");
					name.addClass("ui-state-highlight");
					setTimeout(function() {
						name.removeClass("ui-state-highlight");
					}, 1500);
					return false;
				}
				
				if ($.trim(password.val()).length <= 0) 
				{
					password.addClass("ui-state-error");
					bValid=false;
					alert("请输入密码。");
					password.addClass("ui-state-highlight");
					setTimeout(function() {
						password.removeClass("ui-state-highlight");
					}, 1500);
					return false;
				}
				
				/*if (bValid){
	  			    $("#LoginForm").attr("action","Login.action");
	  			    alert($("#LoginForm").attr("action"));
  			    	$("#LoginForm").submit();
				}*/
				
				return true;
        	});
        });
    </script>
</head>
<body class="login-body">
	<table style="width:100%;height:320px" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td class="login-image01">&nbsp;</td>
		</tr>
	</table>
    <table style="width:100%;height:197px" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td class="login-image02" valign="top">
                <table style="width:500px;" align="center" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="height:131px;" valign="top" align="center">
                        	<form id="LoginForm"  method="post" action="Login.action">
	                            <table style="width:440px;" align="right" border="0" cellpadding="0" cellspacing="4">
	                                <tr>
	                                    <td id="tiaozheng" style="height:34px" colspan="3">&nbsp;</td>
	                                </tr>
	                                <tr>
	                                    <td style="width:92px;height:32px;text-align:right;"></td>
	                                    <td style="width:182px;" align="left" colspan="2">
	                                        <input type="text" id="name" name="name" class="date start" style="font-size:14px; border:0px; height:20px;background-color: #004C6E;color: white;" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td style="height:57px;text-align:right;"></td>
	                                    <td align="left" colspan="2">
	                                        <input type="password" id="password" name="password" style="font-size:14px; border:0px; height:20px;background-color: #004C6E;color: white;" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                	<td colspan="2"  >
	                                		<input type="submit" id="submitbn" value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" class="btn " style="width: 278px;margin-left: 52px;font-size: 18px;margin-top:20px;"/>
	                                	</td>
	                                </tr>
	                            </table>
                            </form>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <table style="width:100%; height:39px" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td class="login-image03">&nbsp;</td>
        </tr>
    </table>
    <table style="width:100%;height:162px" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td class="login-notice">
                <table style="width:800px; height:136px;" align="center" border="0" cellpadding="0" cellspacing="2">
                    <tr style="line-height:21px;">
                        <td style="width:262px; text-align:right; vertical-align:top;">注意：</td>
                        <td style="width:532px; text-align:left; vertical-align:top;">
                            1、不要在公共场合保存登录信息。<br />
                            2、尽量避免多人使用同一帐号。<br />
                            3、为保证您的帐号安全，退出系统时请注销登录。<br />
                            4、建议使用 FireFox、Google浏览器，屏幕分辨率 1440*900。
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <table style="width:800px;height:36px;" align="center" border="0" cellpadding="0" cellspacing="2">
        <tr>
            <td style="width:794px;text-align:center;">
                Copyright&copy; 2011 - 2014 常州新区常工电子计算机有限公司. All Rights Reserved
            </td>
        </tr>
    </table>	
</body>
<script type="text/javascript">
	if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i)=="9;")
	{
		document.getElementById("tiaozheng").style.height="46px";
	}
</script>
</html>
