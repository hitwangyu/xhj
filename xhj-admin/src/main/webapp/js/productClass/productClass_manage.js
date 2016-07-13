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
    $("#btn_delete").click(del);
    $("#btn_update").click(update);
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

function del()
{
    $.ajax({
        type:"POST",
        url:"productClass/delete",
        data:"path="+$('#product_class_value').val(),
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        success:function(data)
        {
            if(data.status == "success")
            {
                alert("删除成功");
                location.reload();
            }
        }
    });
}

function update()
{
    var productClass_name = $("#productClass_name").val();
    var product_class_value = $("#product_class_value").val();
    if(isStrEmpty(productClass_name))
    {
        alert("品类名称不能为空!~");
        return;
    }
    if(isStrEmpty(product_class_value))
    {
        alert("请选择要修改的品类!~");
        return;
    }
    var product_class_value = $("#product_class_value").val();
    var ajaxData={"name": productClass_name,
            "path": product_class_value};
    $.ajax({
        type:"POST",
        url:"productClass/update",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success:function(data)
        {
        	alertTip(data,null);
            location.reload();
        }
    });
}