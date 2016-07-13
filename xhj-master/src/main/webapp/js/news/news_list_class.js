var news_list_mst="\
			<h2 class='category_title'>{{typeName}}</h2>\
            <ul>\
			{{#data}}\
			<li>\
            <span>{{createTime}}</span>\
            <a href='/newsDetail?id={{id}}'>{{title}}</a>\
            </li>\
            {{/data}}\
            </ul>";
            
$(document).ready(news_list_class_init_page);

var idNameMap = 
{
    "南京文交所": 1,
    "中南文交所": 2,
    "行业新闻": 3,
    "投资分析": 4
}

function news_list_class_init_page()
{
	queryByPage(1);
    var tab = getParameter("typeName");
    if(!isStrEmpty(tab))
    {
        clickTab(idNameMap[tab]);
    }
}

function queryByPage(pageNo)
{
	var curPageIndex = pageNo || 1;
    var ajaxData={"newsClassName": getParameter("typeName"), 
    		"pageNo": curPageIndex,
    		"pageSize": 20};
    
    $.ajax({
        type: "POST",
        url: "/news/query",
        data: JSON.stringify(ajaxData),
        contentType: "application/json;",
        success: function(data)
        {
            Mustache.parse(news_list_mst);
            var rendered = Mustache.render(news_list_mst, {"typeName": getParameter("typeName"), "data": data.data.target});
            $('#news_list').html(rendered);
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

function clickTab(tab)
{
    $('.nav_ul a').removeClass('click_on');
    $('#tab'+tab).addClass('click_on');
}