var product_list_mst="\
			<h2 class='category_title'><span>{{title}}</span></h2>\
			{{#data}}\
			<div class='product_row'>\
                <div class='product_img'>\
                    <a title='{{name}}' href='/productDetail?id={{id}}'><img src='{{imgAddr}}'></>\
                </div>\
                <div class='product_desc'>\
                    <h2 class='product_name'><a title='{{name}}' href='/productDetail?id={{id}}'>{{name}}</a></h2>\
                    <br/>\
                    <p>{{introduction}}</p>\
                    <div class='price'><label>市场价:</label>{{price}}</div>\
                </div>\
                <div style='clear:both'></div>\
            </div>\
            {{/data}}";
            
$(document).ready(product_list_class_init_page);

function product_list_class_init_page()
{
	queryByPage(1);
}

function queryByPage(pageNo)
{
	var curPageIndex = pageNo || 1;
    var ajaxData={"className": getParameter("className"), 
    		"pageNo": curPageIndex,
    		"pageSize": 5};
	$.ajax({
        type: "POST",
        url: "/product/getProductListByClassName",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success: function(data)
        {
            Mustache.parse(product_list_mst);
            var rendered = Mustache.render(product_list_mst, {"title": getParameter("className"), "data": data.data.target});
            $('#product_list').html(rendered);
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