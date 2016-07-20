/*!
 * jQuery clueTip plugin v1.2.5
 *
 * Date: Mon Jan 16 23:33:54 2012 EST
 * Requires: jQuery v1.3+
 *
 * Copyright 2011, Karl Swedberg
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 *
 * Examples can be found at http://plugins.learningjquery.com/cluetip/demo/
 *
 */

/*		function beforeClick(treeId, treeNode) {
 var zTree = $.fn.zTree.getZTreeObj("treeDemo");
 zTree.checkNode(treeNode, !treeNode.checked, null, true);
 return false;
 }*/

function ztreeBeforeCheck(treeId, treeNode) {
	var maxSelectCount = $("ul[id='" + treeId + "']").data("maxSelectCount");

	if (maxSelectCount == "none") {
		return true;

	}

	if (!treeNode.checked) {

		var zTree = $.fn.zTree.getZTreeObj(treeId), checkedCount = zTree
				.getCheckedNodes(true).length;
		if (checkedCount >= maxSelectCount) {
			alert("已超过最大选择上限" + maxSelectCount + "个");
			return false;
		} else {
			return true;

		}

	}
	return true;

}

function ztreeBeforeDblClick(treeId, treeNode) {
	

	var zTree = $.fn.zTree.getZTreeObj(treeId);

	
	
	var nodes1 = zTree.getSelectedNodes();
	var treeNode = nodes1[0];
	var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
	for(var i=0;i<treeNode.children.length;i++) {
	    var childNode = treeNode.children[i];
	    zTree.checkNode(treeNode.children[i]);
	}

	var maxSelectCount = $("ul[id='" + treeId + "']").data("maxSelectCount");

	var checkedCount = zTree.getCheckedNodes(true).length;
	if (checkedCount >= maxSelectCount) {
		alert("已超过最大选择上限" + maxSelectCount + "个");
		for(var i=0;i<treeNode.children.length;i++) {
		    var childNode = treeNode.children[i];
		    zTree.checkNode(treeNode.children[i],false,false);
		}
		return false;
	} 
var nodes = zTree.getCheckedNodes(true), v = "";
	
	ids = "";
	for ( var i = 0, l = nodes.length; i < l; i++) {
		
		v += nodes[i].name + ",";
		ids += nodes[i].id + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	if (ids.length > 0)
		ids = ids.substring(0, ids.length - 1);
	if ($("ul[id='" + treeId + "']").data("callback") != null) {
		$("ul[id='" + treeId + "']").data("callback")(v, ids);
	}
};

function ztreeOnCheck(e, treeId, treeNode) {

	var zTree = $.fn.zTree.getZTreeObj(treeId);
	
	//加
	zTree.cancelSelectedNode();
	zTree.selectNode(treeNode, true);
	
	var nodes = zTree.getCheckedNodes(true), v = "";
	
	ids = "";
	for ( var i = 0, l = nodes.length; i < l; i++) {

		v += nodes[i].name + ",";
		ids += nodes[i].id + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	if (ids.length > 0)
		ids = ids.substring(0, ids.length - 1);
	if ($("ul[id='" + treeId + "']").data("callback") != null) {
		$("ul[id='" + treeId + "']").data("callback")(v, ids);
	}
}
function zTreeOnClick(e, treeId, treeNode) {
	
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	//改
	zTree.checkNode(treeNode, true, false);
	var nodes = zTree.getCheckedNodes(true), v = "";
	
	ids = "";
	for ( var i = 0, l = nodes.length; i < l; i++) {
		
		v += nodes[i].name + ",";
		ids += nodes[i].id + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	if (ids.length > 0)
		ids = ids.substring(0, ids.length - 1);
	if ($("ul[id='" + treeId + "']").data("callback") != null) {
		$("ul[id='" + treeId + "']").data("callback")(v, ids);
	}
}
function resetZtree(ztreeId){
	
	
/*	var zTree = $.fn.zTree.getZTreeObj(treeId+"ul"), nodes = zTree
	.getCheckedNodes(true);

for ( var i = 0, l = nodes.length; i < l; i++) {

nodes[i].checked = false ;

}*/
	
	var zTree = $.fn.zTree.getZTreeObj(ztreeId+"ul");
	zTree.checkAllNodes(false);
	

	var nodes =zTree.getCheckedNodes();
	

	if (nodes.length>0) { 
		zTree.checkNode(nodes[0],false,false,false);
//		zTree.cancelSelectedNode(nodes[0]);
	}

//	zTree.cancelSelectedNode();


	
	
}
function showMenu(ztreeInput, ztreeDiv, ztreeUl) {

	var cityObj = ztreeInput;
	var cityOffset = ztreeInput.offset();

	ztreeDiv.css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.height() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", function(event) {
		onBodyDown(ztreeInput, ztreeDiv, ztreeUl, event)
	});
}
function hideMenu(ztreeDiv) {
	ztreeDiv.fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(ztreeInput, ztreeDiv, ztreeUl, event) {
	if (!(event.target.id == ztreeInput.attr("id")
			|| event.target.id == ztreeDiv.attr("id") || $(event.target)
			.parents("#" + ztreeDiv.attr("id")).length > 0)) {
		hideMenu(ztreeDiv);
	}
}

/*		$(document).ready(function(){
 // setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };

 });*/
(function($) {

	$.cgdzztree = {
		version : '1.0',

		defaults : {
			objectType : 'room', //树的类型 addr 建筑 org 部门 water 水表  elec 电表
			width : 220, // The width of the ztree
			height : 360, // The height of the ztree
			selectType : 'radio', //radio 单选 checkbox 多选
			levelCode : 'all', // 控制有选择按钮的分级，建筑和部门是编码，水和电0,1,2,3,4 区分虚表类型和实表
			ztreeId : 'cgdzZtreeDemo', //ztreeId 同一页面必须不同
			callback : null, //回调函数的方法名，传入两个参数，第一个是名字，第二个是ID，都是英文','号隔开
			maxSelectCount : "none" //最大对比上限  默认没有上限，compare是查询最大对比参数，也可以输入大于等于1的数字
		}
	};

	// .cluetip() method
	$.fn.cgdzztree = function(options) {
		var maxSelectCount;

		// merge per-call options with defaults
		options = $.extend(true, {}, $.cgdzztree.defaults, options || {});
		if ((maxSelectCount = options.maxSelectCount) == "compare") {
			$.ajax({
				type : "POST",
				async : false,
				url : "GetMaxCompareCount.action",
				data : {},
				success : function(data) {

					maxSelectCount = data;

				}
			});
		}

		var setting = {
			check : {
				enable : true,
				chkboxType : {
					"Y" : "",
					"N" : ""
				},
				chkStyle : options.selectType,
				radioType : "all"

			},
			view : {
				dblClickExpand : false
			},
			async : { //异步加载
				dataType : "json",
				enable : true,
				
				//url : "RoomZtreeDropdownSelect.action",       
				//autoParam : [ "id=treeId" ],//需要传递的参数                                   
				//otherParam : [ "selectLevelCode", options.levelCode ]  
				
				                                
				url : options.objectType == "room" ? "RoomZtreeDropdownSelect.action"
						: options.objectType == "mid" ? "MidZtreeDropdownSelect.action"
								: options.objectType == "water" ? "WaterZtreeDropdownSelect.action"
										: "ElecZtreeDropdownSelect.action",//异步加载URL
				autoParam : [ "id=treeId" ],//需要传递的参数
				otherParam : [ "selectLevelCode", options.levelCode ]
				
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				beforeDblClick: ztreeBeforeDblClick,
				beforeCheck : ztreeBeforeCheck,
				onCheck : ztreeOnCheck
				//onClick: zTreeOnClick
				
			}
		};

		/** =create ztree divs **/
		/**    <div id="menuContent" class="menuContent" style="display:none; position: absolute; background-color:#FFF">
		<ul id="treeDemo" class="ztree" ></ul>
		</div>**/

		var $cgdzztreeDiv = $('<div></div>')
				.attr('id', options.ztreeId + "div").css({
					'display' : 'none',
					'position' : 'absolute',
					'background-color' : '#FFF'
				});
		$cgdzztreeDiv.insertAfter($(this));

		var $cgdzztreeUl = $("<ul></ul>").attr('id', options.ztreeId + "ul")
				.addClass("ztree").css({
					'margin-top' : '0px',
					'border' : '1px solid #617775',
					'background' : '#FFF',
					'overflow-y' : 'scroll',
					'overflow-x' : 'auto',
					'width' : options.width + 'px',
					'height' : options.height + 'px'
				});
		$cgdzztreeUl.appendTo($cgdzztreeDiv);
		$cgdzztreeUl.data("callback", options.callback);
		$cgdzztreeUl.data("maxSelectCount", maxSelectCount);
		$cgdzztreeUl.data("selectType", options.selectType);
		var $cgdzztreeInput = $(this);
		$(this).bind("click", function() {
			showMenu($cgdzztreeInput, $cgdzztreeDiv, $cgdzztreeUl)
		});
		
		
		// alert($(this).attr('id'));
		//  $(this).bind("click",function(){alert(11)}); 
		$.fn.zTree.init($cgdzztreeUl, setting, null);

	};

})(jQuery);
