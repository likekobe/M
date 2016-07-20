// 验证
var tips ;
//用于换肤
function changeSkin(window, pos,pos1) {
	
	var cssSkin = $(window.document).find("#skin");
	var cssSkinn = $(window.document).find("#skinn");
	if (cssSkin != null) {
		$(cssSkin).attr("href", pos);
	}
	if (cssSkinn != null) {
		$(cssSkinn).attr("href", pos1);
	}
	
	$(window.document).find("iframe").each(function() {
		
		changeSkin(this.contentWindow, pos,pos1);
	});
	var frameswindow = window.document.frames;
	for ( var i = 0; i < frameswindow.length; i++) {
		changeSkin(frameswindow[i], pos,pos1);

	}

} 

function updateTips(t) {
	tips.text(t).addClass("ui-state-highlight");
	setTimeout(function() {
		tips.removeClass("ui-state-highlight");
		tips.empty();
		tips.append("&nbsp;");
	}, 3000);
}

function checkRequired(o, field) {
	if ($.trim(o.val()).length <= 0) {
		o.addClass("ui-state-error");
		updateTips(field + "必须填写。");
		o.bind("keydown",function(){
			
			o.removeClass("ui-state-error");
		});
		return false;
	} else {
		return true;
	}
}

function checkPasswordRequired(o, field) {
	if (o.val().length <= 0) {
		o.addClass("ui-state-error");
		updateTips(field + "必须填写。");
         o.bind("keydown",function(){			
			o.removeClass("ui-state-error");
		});
		return false;
	} else {
		return true;
	}
}

function checkLength(o, field, min, max) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips(field + "的长度必须在 " + min + "到" + max + "位之间。");
        o.bind("keydown",function(){			
			o.removeClass("ui-state-error");
		});
        return false;
	} else {
		return true;
	}
}

function checkLengthTrim(o, field, min, max) {
	if ($.trim(o.val()).length > max || $.trim(o.val()).length < min) {
		o.addClass("ui-state-error");
		updateTips(field + "的长度必须在 " + min + "到" + max + "位之间。");
        o.bind("keydown",function(){			
			o.removeClass("ui-state-error");
		});
        return false;
	} else {
		return true;
	}
}

function checkRegexp(o, regexp, msg) {
	
	
	
	var value=$.trim(o.val());
	if(value!=null){
	if (value.length > 0 && !(regexp.test(value))) {
		o.addClass("ui-state-error");
        o.bind("keydown",function(){			
			o.removeClass("ui-state-error");
			
		});
        updateTips(msg);
		return false;
	} else {
		
		o.val(value);
		return true;
	}
}
}

function checkEquals(o1, o2, msg) {
	if (o1.val() != o2.val()) {
		o1.addClass("ui-state-error");
		o2.addClass("ui-state-error");
        o1.bind("keydown",function(){			
			o1.removeClass("ui-state-error");
		});
        o2.bind("keydown",function(){			
			o2.removeClass("ui-state-error");
		});                
        updateTips(msg);
		return false;
	} else {
		return true;
	} 	
}

///////////////////////////////////////////////////////////////////////////////
// checkbox 全选或取消

function checkAll(name, obj) {
	$(":checkbox[name=" + name + "]").each(function() {  
			$(this).attr("checked", obj.checked);
	});
}

// 检查 checkbox 是否被选中
function isChecked(name) {
	var rv = false;
	$(":checkbox[name=" + name + "]").each(function() {
		if ($(this).attr("checked")) {
			rv = true;
		}
	});
	
	return rv;
}

///////////////////////////////////////////////////////////////////////////////
// 日期格式化
Date.prototype.pattern = function(fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份        
        "d+": this.getDate(), //日        
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时        
        "H+": this.getHours(), //小时        
        "m+": this.getMinutes(), //分        
        "s+": this.getSeconds(), //秒        
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度        
        "S": this.getMilliseconds() //毫秒        
    };
    var week = {
        "0": "\u65e5",
        "1": "\u4e00",
        "2": "\u4e8c",
        "3": "\u4e09",
        "4": "\u56db",
        "5": "\u4e94",
        "6": "\u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

///////////////////////////////////////////////////////////////////////////////
//
function textAreaMaxLength(o, len) {	

	if(o.value.length > len) {
	    if($.trim(o.value)==""){
	    	
			o.value = o.value.substr(0, len-1);
        return;
	    }
		o.value = o.value.substr(0, len);

	}
};


///////////////////////////////////////////////////////////////////////////////
// 通过 cookie 单独设置页面 img 的 src
function setImgTheme(t, img) {
	if (t != null) {
		var src = "css/" + t + "/images/" + img;		
		$("img[id!=avatar]").each(function () {			
			$(this).attr("src", src);
		});
	}
	else {
		// 默认
		$("img[id!=avatar]").each(function () {
			$(this).attr("src", "css/blue/images/" + img);
		});
	}
}

///////////////////////////////////////////////////////////////////////////////
// 隐藏 / 显示菜单
function switchMenu() {
	var middleFrame = parent.document.getElementById("middleFrame");
	var split = document.getElementById("split");
	if (middleFrame.cols == "194,14,*")	{
		middleFrame.cols = "0,14,*";
		split.src = "images/split-right.gif";		
	}
	else {
		middleFrame.cols = "194,14,*";
		split.src = "images/split-left.gif";
	}
}

///////////////////////////////////////////////////////////////////////////////
// 浏览目录(存在安全问题)
// 1.IE添加授信站点 https://localhost
// 2.启用未对标记为可安全执行的 ActiveX 控件初始化并执行脚本
function browseFolder(o) {
	try {
        var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
        var Shell = new ActiveXObject("Shell.Application");
        var Folder = Shell.BrowseForFolder(0, Message, 64, 17); //起始目录为：我的电脑
        if (Folder != null) {
            Folder = Folder.items();
            Folder = Folder.item();
            Folder = Folder.Path;
            if (Folder.charAt(Folder.length - 1) != "\\") {
                Folder = Folder + "\\";
            }
            o.val(Folder);
            return Folder;
        }
    }
    catch (e) {
    }
}


// /////////////////////////////////////////////////////////////////////////////
// jquery alert

function jAlert(msg) {
	alertDiv = "<div id=\"jquery-dialog-message\" title=\"信息\">";
	alertDiv += "<p><span class=\"ui-icon ui-icon-info\" style=\"float:left; margin:0 7px 50px 0;\"></span>";
	alertDiv += msg;
	alertDiv += "</p></div>";

	$("body").prepend(alertDiv);
	$("#jquery-dialog-message").dialog({
		height : 160,
		modal : true,
		buttons : {
			确定 : function() {
				$(this).dialog("close");
			}
		}
	});
}

function createOption(value,text){
	var objOption=document.createElement("OPTION");
	objOption.value=value;
	objOption.text=text;
	return objOption;
}

function initTimes(beginDate,endDate){
		beginDate.datepicker({
	    dateFormat: 'yy-mm-dd',
	    changeMonth: true,
	    changeYear: true
	});
		endDate.datepicker({
	    dateFormat: 'yy-mm-dd',
	    changeMonth: true,
	    changeYear: true
	}); 
		beginDate.change(function(){
			if(endDate.val()==""){
				endDate.val(beginDate.val());
			}else{
				if(beginDate.val()>endDate.val()){
					endDate.val("");
				}
			}
		});
		endDate.change(function(){
			if(beginDate.val()==""){
				beginDate.val(endDate.val());
			}else{
				if(beginDate.val()>endDate.val()){
					beginDate.val("");
				}
			}
		});
}


function TextValidate()
{
    var code;
    var character;
    var err_msg = "Text can not contain SPACES or any of these special characters other than underscore (_) and hyphen (-).";
    if (document.all) //判断是否是IE浏览器
    {
        code = window.event.keyCode;
    }
    else
    {
        code = arguments.callee.caller.arguments[0].which;
    }
    var character = String.fromCharCode(code);
    
    var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
    //特殊字符正则表达式
    if (txt.test(character))
    {
        if (document.all)
        {
            window.event.returnValue = false;
        }
        else
        {
            arguments.callee.caller.arguments[0].preventDefault();
        }
    }
    
}  






