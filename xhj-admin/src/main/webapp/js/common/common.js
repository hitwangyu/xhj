function isStrEmpty(str) {
    if (str == null || str == undefined || str == '') {
        return true;
    }
    return false;
}

function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    }
    return null;
}

function alertTip(data, hrefUrl)
{
	if(data.status =='success')
    {
    	alert("操作成功");
    	if(!isStrEmpty(hrefUrl))
    	{
    		window.location.href=hrefUrl;
    	}
    }
    else
    {
    	alert("操作失败");
    }
}

