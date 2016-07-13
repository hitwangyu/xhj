/* {
    status:"success",
    data:
    [
        {
            classification:"邮票", 
            productList:
                [{imgAddr:"xxx",name:"三轮生肖兔大版", price:"200~300"}, 
                {}
                ]
        },
        {
            classification:"钱币", 
            productList:
                [{imgAddr:"xxx",name:"三轮生肖兔大版", price:"200~300"}, 
                {}
                ]
        }
    ]
}*/
var product_list_mst = "\
{{#data}}\
<div class='product_category' width='100%'>\
    <h2 class='category_title'>\
                    <span class='category_text'>{{classification}}</span>\
                    <span class='more'><a target='_blank' href='/productListInClass?className={{classification}}'>更多</a></span>\
                </h2>\
</div>\
<div class='product_row'>\
    <ul class='product_ul'>\
        {{#productList}}\
        <li class='product_grid'>\
            <ul>\
                <li>\
                    <a href='/productDetail?id={{id}}' target='_blank'>\
                    <img class='pro_img' src='{{imgAddr}}' />\
                    </a>\
                </li>\
                <li class='pro_name'>\
                    <a href='/productDetail?id={{id}}' target='_blank'>{{name}}</a>\
                </li>\
                <li class='price'>\
                    <label class='price_label'>市场价:</label>&nbsp;{{price}}\
                </li>\
            </ul>\
        </li>\
        {{/productList}}\
    </ul>\
</div>\
{{/data}}";


$(document).ready(load_product_list);

function load_product_list() {
	  $.get('product/getProductList', function(data) {
		  	Mustache.parse(product_list_mst);
	    	var rendered = Mustache.render(product_list_mst, data);
	    	$('.product_area').append(rendered);
	  });
}