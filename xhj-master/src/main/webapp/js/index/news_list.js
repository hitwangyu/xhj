/* {
    status:'success',
    data:
    [
        [{
            classification:'南京文交所', 
            imgAddr:"xxx",
            newsList:
                [{title:'xxx',id:'xxx'}, 
                {}
                ]
        },
        {
            classification:'中南文交所',
            imgAddr:"xxx",
            newsList:
                [{title:'xxx',id:'xxx'}, 
                {}
                ]
        }],
        [{
            classification:'投资分析', 
            newsList:
                [{title:'xxx',id:'xxx'}, 
                {}
                ]
        },
        {
            classification:'行业新闻', 
            newsList:
                [{title:'xxx',id:'xxx'}, 
                {}
                ]
        }]
    ]
}*/
var news_list_mst_row_with_img = "\
{{#data}}\
            <div class='news_grid'>\
                <div class='news_category'>\
                    <h2 class='category_title'>\
                    <span class='category_text'>{{classification}}</span>\
                    <span class='more'><a target='_blank' href='/html/news/news_list_class.html?typeName={{classification}}'>更多</a></span>\
                    </h2>\
                </div>\
                <div class='wjs_img'>\
                    <a title='{{classification}}' target='_blank' href='{{imgLinkAddr}}'>\
                        <img alt='{{classification}}' src='{{imgAddr}}'/>\
                    </a>\
                </div>\
                <ul class='news_ul'>\
                {{#newsList}}\
                    <li class='news_li'>\
                        <a title='{{title}}' target='_blank' href='/newsDetail?id={{id}}'>{{title}}</a>\
                    </li>\
                {{/newsList}}\
                </ul>\
            </div>\
{{/data}}";

 var news_list_mst_row_no_img = "\
{{#data}}\
            <div class='news_grid'>\
                <div class='news_category'>\
                    <h2 class='category_title'>\
                    <span class='category_text'>{{classification}}</span>\
                    <span class='more'><a target='_blank' href='/html/news/news_list_class.html?typeName={{classification}}'>更多</a></span>\
                    </h2>\
                </div>\
                <ul class='news_ul'>\
                {{#newsList}}\
                    <li class='news_li'>\
                        <a title='{{title}}' target='_blank' href='/newsDetail?id={{id}}'>{{title}}</a>\
                    </li>\
                {{/newsList}}\
                </ul>\
            </div>\
{{/data}}";


var njwjs = "南京文交所";
var znwjs = "中南文交所";
$(document).ready(load_news_list);

function load_news_list() {
    $.get("/news/getNewsList", function(data) {
    	var colNum = 2;
    	var content = data.data;
    	var blockWithImg = new Array();
    	var rowWithImg = new Array();
    	var blockNoImg = new Array();
    	var rowNoImg = new Array();
    	for(var i = 0; i < content.length; i++)
    	{
    		if(!isStrEmpty(content[i].imgAddr))
    		{
    			if(!isStrEmpty(content[i].newsList))
    			{
    				content[i].newsList = content[i].newsList.slice(0,5);
    			}
    			rowWithImg.push(content[i]);
    			if(rowWithImg.length >= 2)
    			{
    				blockWithImg.push(rowWithImg);
    				rowWithImg = new Array();
    			}
    		}
    		else
    		{
    			rowNoImg.push(content[i]);
    			if(rowNoImg.length >= 2)
    			{
    				blockNoImg.push(rowNoImg);
    				rowNoImg = new Array();
    			}
    		}
    	}
        Mustache.parse(news_list_mst_row_with_img);
        var wjs = "";
        for(var row in blockWithImg)
        {
        	var rendered = Mustache.render(news_list_mst_row_with_img, {"data": blockWithImg[row]});
        	rendered = "<div class='news_row'>" + rendered + "<div style='clear:both'></div></div>"
        	wjs += rendered;
        }
        
        $(".news_wjs").append(wjs);
        var others = "";
        for(var row in blockNoImg)
        {
        	var rendered = Mustache.render(news_list_mst_row_no_img, {"data": blockNoImg[row]});
        	rendered = "<div class='news_row'>" + rendered + "<div style='clear:both'></div></div>"
        	others += rendered;
        }
        $(".news_other").append(others);
    });
}
