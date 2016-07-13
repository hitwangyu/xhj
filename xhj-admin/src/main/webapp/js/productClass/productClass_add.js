var zTreeObj,
    setting = {
        view: {
            selectedMulti: false
        },
        callback: {
            onClick: zTreeOnClick
        }
    };

$(document).ready(initPage);

function initPage()
{
    $.get("productClass/getProductClassTree", function(data){
        zTreeNodes = data.data.children;
        if(zTreeNodes == null || zTreeNodes.length==0)
        {
            return;
        }
        zTreeObj = $.fn.zTree.init($("#product_class"), setting, zTreeNodes);
    });
    $("#btn_add").click(add);
    $("#btn_refresh").click(refresh);
}

var preClickTreeNodeName;

function zTreeOnClick(event, treeId, treeNode) {
    if(treeNode.name == preClickTreeNodeName)
    {
        var tag = $(".curSelectedNode");
        tag.removeClass("curSelectedNode");
        $("#product_class_value").attr("value", "");
        preClickTreeId = null;
    }
    else
    {
        var path = "/" + treeNode.name;
        var p = treeNode.getParentNode();
        while(!!p) {
            path = "/" + p.name + path
            p = p.getParentNode();
        }
        $("#product_class_value").attr("value", path);
        preClickTreeNodeName = treeNode.name;
    }
        
};

function add()
{
    var productClass_name = $("#productClass_name").val();
    if(isStrEmpty(productClass_name))
    {
        alert("品类名称不能为空!~");
        return;
    }
    var product_class_value = $("#product_class_value").val();
    var ajaxData={"name": productClass_name,
    		"parentPath": product_class_value};
    $.ajax({
        type:"POST",
        url:"productClass/add",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success:function(data)
        {
        	alertTip(data,null);
            location.reload();
        }
    });
}

function refresh()
{
	$.get("/refresh?key=proClass", function(data){
		alertTip(data,null);
	});
}