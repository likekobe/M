	function Print(printpage)
	{
		var headstr = "<html><head><title></title> <style> .printTable{border:1px solid black;width:100%;}  .printTable td{text-align:center;vertical-align:middle;border:1px solid black; height:37px;line-height:37px}  .printTable th{text-align:center;vertical-align:middle;border:1px solid black;height:37px;line-height:37px}</style>  </head><body>";
		
		var footstr = "</body>";
		var newstr = document.all.item(printpage).innerHTML;
		var oldstr = document.body.innerHTML;
		document.body.innerHTML = headstr+newstr+footstr;
		window.print(); 
		document.body.innerHTML = oldstr;
		return false;
	}