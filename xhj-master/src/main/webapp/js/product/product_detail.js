var imgList_mst = "<div id='pro_img_cycle' class='cycle-slideshow' data-cycle-fx=scrollHorz \
                  data-cycle-timeout=0 data-cycle-prev='#preImg' data-cycle-next='#nextImg' \
                  data-cycle-allow-wrap='false'>\
                {{#data}}<img src='{{.}}' style='display:none;'/>{{/data}}</div>";

$('document').ready(product_detail_init_page);
var curIndex = 0;

function product_detail_init_page() {
    $.get("/product/getProductDetail?id=" + getParameter("id"), function(data) {
        Mustache.parse(imgList_mst);
        var rendered = Mustache.render(imgList_mst, {
            "data": data.data.detailImgAddrList
        });
        $("#banner_body").append(rendered);
        $("#pro_img_cycle").find("img").first().show();
        $('.cycle-slideshow').cycle();
        $(".price").append(data.data.price);
        $(".time").append(data.data.createTime);
        $("#pro_desc_content").append(data.data.description);
        $(".pro_title").text(data.data.name);

        //不能用children(), 因为cycle2会多添加一个img，比较坑爹
        $('#nextImg').click(function() {
            if (curIndex + 1 >= data.data.detailImgAddrList.length)
            {
                alert("已到最后一张~");
                $('#slideshow').cycle('stop');
                return false;
            }
            curIndex += 1;
        });
    });

    $('#banner_body').hover(function() {
        $('#front').show();
        $('#next').show();
    	}, function() {
        $('#front').hide();
        $('#next').hide();
    });
    $('#preImg').click(function() {
        if (curIndex <= 0) {
            alert("前面没有图片啦~");
            return false;
        }
        curIndex -= 1;
    });
}
