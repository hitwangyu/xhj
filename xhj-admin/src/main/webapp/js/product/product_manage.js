var zTreeObj,
    setting = {
        view: {
            selectedMulti: false
        },
        callback: {
        	onClick: zTreeOnClick
		}
    };

var query_result_mst="\
    {{#target}}\
    <tr>\
        <td>{{id}}</td><td>{{name}}</td><td>{{className}}</td><td>{{price}}</td>\
        <td><a href='{{imgAddr}}' target='_blank'>查看图片</a></td><td><a class='delete_pro' href='javascript:void(0);' onclick='deletePro({{id}})'>删除</a>&nbsp;&nbsp;<a class='modify_pro' href='productModify?id={{id}}'>修改</a>&nbsp;&nbsp;<a class='preview_pro' target='_blank' href='/html/product/product_detail.html?id={{id}}'>预览</a>{{{statusRenderer}}}</td>\
    </tr>\
    {{/target}}\
";

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
    $.get("productClass/getProductClassTree", function(data){
        zTreeNodes = data.data.children;
        if(zTreeNodes == null || zTreeNodes.length==0)
        {
            return;
        }
        zTreeObj = $.fn.zTree.init($("#product_class"), setting, zTreeNodes);
    });
    $("#btn_query").click(query);
}

var product_class;
var product_name;
function query()
{
    product_class= $("#product_class_value").val();
    product_name = $("#product_name").val();
    if(isStrEmpty(product_name) && isStrEmpty(product_class))
    {
        alert("查询条件不能都为空!~");
        return;
    }
    queryByPage(1);
}

function deletePro(id)
{
	if(isStrEmpty(id))
	{
		return;
	}
	$.get("product/delete?id="+id, function(data){
		query();
	});
}

function onlinePro(id)
{
	if(isStrEmpty(id))
	{
		return;
	}
	$.get("product/online?id="+id, function(data){
		query();
	});
}

function offlinePro(id)
{
	if(isStrEmpty(id))
	{
		return;
	}
	$.get("product/offline?id="+id, function(data){
		query();
	});
}

function queryByPage(pageNo)
{
	var curPageIndex = pageNo || 1;
    var ajaxData={"productName": product_name, 
    		"productClassPath": product_class,
    		"pageNo": curPageIndex,
    		"pageSize": 20};
    $.ajax({
        type:"POST",
        url:"product/query",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success:function(data)
        {
        	var newData = {"statusRenderer":function()
        			{
		        		if(this.status == 0)
		        		{
		        			return "&nbsp;&nbsp;<a class='online_pro' href='javascript:void(0);' onclick='onlinePro("+this.id+")'>上架</a>";
		        		}
		        		else
		        		{
		        			return "&nbsp;&nbsp;<a class='offline_pro' href='javascript:void(0);' onclick='offlinePro("+this.id+")'>下架</a>";
		        		}
        			},
        			"target": data.data.target,
        			"queryCondition": data.data.queryCondition
        	};
            Mustache.parse(query_result_mst);
            rendered = Mustache.render(query_result_mst, newData);
            $("#result_tbody").html(rendered);
            $(".pagination").myPagination({
            	cssStyle: "pageTool",
		        currPage: newData.queryCondition.pageNo,
		        pageCount: newData.queryCondition.totalPage,
		        ajax: {
		            onClick: function(clickPageNo) {
		            	queryByPage(clickPageNo);
		            }
		        }
		    });
        }
    });
}

