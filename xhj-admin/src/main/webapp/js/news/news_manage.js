var query_result_mst="\
    {{#target}}\
    <tr>\
        <td>{{id}}</td><td>{{title}}</td><td>{{typeName}}</td><td>{{author}}</td>\
        <td><a class='delete_news' href='javascript:void(0);' onclick='deleteNews({{id}})'>删除</a>&nbsp;&nbsp;<a class='modify_news' href='newsModify?id={{id}}'>修改</a>&nbsp;&nbsp;<a class='preview_news' target='_blank' href='/html/news/news_detail.html?id={{id}}'>预览</a>{{{statusRenderer}}}</td>\
    </tr>\
    {{/target}}\
";
var class_option_mst="{{#data}}<option value='{{.}}'>{{.}}</option>{{/data}}";

$(document).ready(initPage);

function initPage()
{
    $("#btn_query").click(query);
    $.get("news/queryAllClass", function(data){
    	Mustache.parse(class_option_mst);
        rendered = Mustache.render(class_option_mst, data);
        $("#news_class").append(rendered);
    });
}

var news_class;
var news_title;
function query()
{
    news_class= $("#news_class").val();
    news_title = $("#news_title").val();
    if(isStrEmpty(news_class) && isStrEmpty(news_title))
    {
        alert("查询条件不能都为空!~");
        return;
    }
    queryByPage(1);
}

function deleteNews(id)
{
	if(isStrEmpty(id))
	{
		return;
	}
	$.get("news/delete?id="+id, function(data){
		query();
	});
}

function onlineNews(id)
{
	if(isStrEmpty(id))
	{
		return;
	}
	$.get("news/online?id="+id, function(data){
		query();
	});
}

function offlineNews(id)
{
	if(isStrEmpty(id))
	{
		return;
	}
	$.get("news/offline?id="+id, function(data){
		query();
	});
}

function queryByPage(pageNo)
{
	var curPageIndex = pageNo || 1;
    var ajaxData={"newsClassName": news_class, 
    		"title": news_title,
    		"pageNo": curPageIndex,
    		"pageSize": 20};
    $.ajax({
        type:"POST",
        url:"news/query",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success:function(data)
        {
        	var newData = {"statusRenderer":function()
        			{
		        		if(this.status == 0)
		        		{
		        			return "&nbsp;&nbsp;<a class='online_news' href='javascript:void(0);' onclick='onlineNews("+this.id+")'>发布</a>";
		        		}
		        		else
		        		{
		        			return "&nbsp;&nbsp;<a class='offline_news' href='javascript:void(0);' onclick='offlineNews("+this.id+")'>下线</a>";
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

