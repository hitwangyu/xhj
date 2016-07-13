var zTreeObj,
    setting = {
        view: {
            selectedMulti: false
        },
        callback: {
		onClick: zTreeOnClick
		}
    },
    zTreeNodes = [{
        "name": "商品相关",
        children: [{
            "name": "商品管理"
        }, {
            "name": "商品添加"
        }]
    }, {
        "name": "图片相关",
        children: [{
            "name": "图片管理"
        }, {
            "name": "图片添加"
        }]
    }, {
        "name": "新闻相关",
        children: [{
            "name": "新闻管理"
        }, {
            "name": "新闻添加"
        }]
    }, {
        "name": "品类相关",
        children: [{
            "name": "品类管理"
        }, {
            "name": "品类添加"
        }]
    }];

var nav_url = {
		"商品管理": "productManage",
		"商品添加": "productAdd",
		"新闻管理": "newsManage",
		"新闻添加": "newsAdd",
		"图片管理": "imgManage",
		"图片添加": "imgAdd",
        "品类管理": "productClassManage",
        "品类添加": "productClassAdd"
};

$(document).ready(function() {
	
	$("#head").prepend("<img id='page_top_img' src='img/top.jpg' />")
    var img_width = 2000;
    var screen_width = parseInt(screen.width);
    var offset = ((screen_width-img_width)/2).toString() + "px";
    $("#page_top_img").css("margin-left", offset);
	
    zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
    window.setInterval(iframeAutoHeight, 500);
    
});

function iframeAutoHeight(){
	var content_iframe = $("#content_iframe");
	var mainheight = content_iframe.contents().find("body").height()+30;
	content_iframe.height(mainheight);
}

function zTreeOnClick(event, treeId, treeNode) {
    $('#content>iframe').attr("src", nav_url[treeNode.name]);
};
