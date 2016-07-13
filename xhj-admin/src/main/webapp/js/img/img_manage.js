var query_result_mst="\
    {{#target}}\
    <tr>\
        <td>{{id}}</td><td>{{description}}</td><td>{{linkAddr}}</td><td>{{typeName}}</td><td>{{weight}}</td>\
        <td><a class='delete_img' href='javascript:void(0);' onclick='deleteImg({{id}})'>删除</a>&nbsp;&nbsp;<a class='modify_img' href='imgModify?id={{id}}'>修改</a>&nbsp;&nbsp;<a class='preview_img' href='{{imgAddr}}' target='_blank'>预览</a></td>\
    </tr>\
    {{/target}}\
";
var class_option_mst="{{#data}}<option value='{{.}}'>{{.}}</option>{{/data}}";

$(document).ready(initPage);

function initPage()
{
    $("#btn_query").click(query);
    $("#btn_refresh").click(refresh);
    $.get("img/queryAllTypeName", function(data){
    	Mustache.parse(class_option_mst);
        rendered = Mustache.render(class_option_mst, data);
        $("#img_type").append(rendered);
    });
}

var img_type;
var img_description;
var img_id;
function query()
{
    img_type= $("#img_type").val();
    img_description = $("#img_description").val();
    img_id = $("#img_id").val();
    if(isStrEmpty(img_type) && isStrEmpty(img_description) && isStrEmpty(img_id))
    {
        alert("查询条件不能都为空!~");
        return;
    }
    queryByPage(1);
}

function deleteImg(id)
{
	if(isStrEmpty(id))
	{
		return;
	}
	$.get("img/delete?id="+id, function(data){
		query();
	});
}

function refresh()
{
	$.get("/refresh?key=img", function(data){
		alertTip(data,null);
	});
}

function queryByPage(pageNo)
{
	var curPageIndex = pageNo || 1;
    var ajaxData={"typeName": img_type, 
    		"description": img_description,
    		"id": img_id,
    		"pageNo": curPageIndex,
    		"pageSize": 20};
    $.ajax({
        type:"POST",
        url:"img/query",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success:function(data)
        {
            Mustache.parse(query_result_mst);
            rendered = Mustache.render(query_result_mst, data.data);
            $("#result_tbody").html(rendered);
            $(".pagination").myPagination({
            	cssStyle: "pageTool",
		        currPage: data.data.queryCondition.pageNo,
		        pageCount: data.data.queryCondition.totalPage,
		        ajax: {
		            onClick: function(clickPageNo) {
		            	queryByPage(clickPageNo);
		            }
		        }
		    });
        }
    });
}

