<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tld/Pager"%>


<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="content-type" content="text/html;charset=utf-8" />


<link rel="stylesheet" href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.ztree.core-3.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dtree.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.mouse.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.button.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.datepicker.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.draggable.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.resizable.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.tabs.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ui/jquery.ui.slider.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/ui/jquery.effects.core.min.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/FusionCharts/FusionCharts.js"></script>
  <script src ="<%=basePath%>js/themeswitchertool.js"></script>
  <script src="<%=basePath%>js/jquery.hoverIntent.js"></script>
  <script src="<%=basePath%>js/jquery.bgiframe.min.js"></script>
  <script src="<%=basePath%>js/jquery.cluetip.js"></script>

  <script src="<%=basePath%>js/demo.js"></script>



<!-- 能耗新版本新增 -->
	<link href="<%=basePath%>media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="<%=basePath%>media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/ecms.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link rel="stylesheet" type="text/css" href="<%=basePath%>media/css/select2_metro.css" />

	<link rel="stylesheet" href="<%=basePath%>media/css/DT_bootstrap.css" />

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="<%=basePath%>media/image/favicon.ico" />
	
	
	
  	
  	<script src="<%=basePath%>media/chartsjs/highcharts.js"></script>
  	
  	<script src="<%=basePath%>media/chartsjs/modules/exporting.src.js"></script>     

<script type="text/javascript">
	//document.oncontextmenu = function () { window.event.returnValue = false; };

	var ecms_theme = $.cookie("ECMS_THEME_NAME", {expires: 365, path: "/"});	
	if (ecms_theme != null) {
		href = "<%=basePath%>" + "css/" + ecms_theme + "/css.css";	
		hreff = "<%=basePath%>" + "css/" + ecms_theme + "/jquery-ui.css";	
		$("#skin").attr("href", href);
		$("#skinn").attr("href", hreff);
		
	}
</script>
