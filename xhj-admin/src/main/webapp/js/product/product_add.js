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

var preClickTreeNodeName;

function zTreeOnClick(event, treeId, treeNode) {
	if(treeNode.name == preClickTreeNodeName)
	{
		var tag = $(".curSelectedNode");
		tag.removeClass("curSelectedNode");
		$("#product_class_value").attr("value", "");
		preClickTreeNodeName = null;
	}
	else
	{
	    $("#product_class_value").attr("value", treeNode.path);
	    preClickTreeNodeName = treeNode.name;
	}
		
};

function initPage()
{
	tinymce.init({
            selector: "#description",
            language:"zh_CN",
            plugins: [
		        "advlist autolink lists link image charmap print preview anchor",
		        "searchreplace visualblocks code fullscreen",
		        "insertdatetime media table contextmenu paste"
    		],
            toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
            table_default_attributes: {
                class: 'table table-condensed table-striped table-hover'
            }
    });

	$.get("productClass/getProductClassTree", function(data){
        zTreeNodes = data.data.children;
        if(zTreeNodes == null || zTreeNodes.length==0)
        {
            return;
        }
        zTreeObj = $.fn.zTree.init($("#product_class"), setting, zTreeNodes);
    });
    $("#btn_add").click(add);
}

function add()
{
	var product_class_value= $("#product_class_value").val();
    var product_name = $("#product_name").val();
    var price = $("#price").val();
    var img_ids = $("#img_ids").val();
    var introduction = $("#introduction").val();
    if(isStrEmpty(product_class_value))
    {
        alert("分类不能为空!~");
        return;
    }
    if(isStrEmpty(product_name))
    {
        alert("名称不能为空!~");
        return;
    }
    if(isStrEmpty(price))
    {
        alert("价格不能为空!~");
        return;
    }
    if(isStrEmpty(img_ids))
    {
        alert("商品图片不能为空!~");
        return;
    }
    if(isStrEmpty(introduction))
    {
        alert("简介不能为空!~");
        return;
    }
    tinymce.triggerSave(true);
    var description = $("#description").val();
    if(isStrEmpty(description))
    {
        alert("详情不能为空!~");
        return;
    }
    var ajaxData={"productName": product_name,
    		"productClassPath": product_class_value,
    		"imgIds": img_ids,
    		"introduction": introduction,
    		"description": description,
    		"price": price};
    $.ajax({
        type:"POST",
        url:"product/add",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success:function(data)
        {
        	//alertTip("productManage");
        	alertTip(data,null);
        	location.reload();
        }
    });
}