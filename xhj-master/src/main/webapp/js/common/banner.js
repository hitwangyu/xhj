$('document').ready(loadBanner);

var bannerHtml = '<div class="cycle-slideshow"\
    data-cycle-fx=scrollHorz\
    data-cycle-timeout=4000>\
	<div class="cycle-pager"></div>\
	{{#data}}\
    <img class="banner_imgs" href="{{linkAddr}}" src="{{imgAddr}}" />\
	{{/data}}\
</div>';
/**
 * cycle2 img外层不能加标签  只能用jquery来注册点击事件了
 */
function loadBanner() {
    $.get("/getBannerImg",
        function(data) {
            Mustache.parse(bannerHtml);
            var rendered = Mustache.render(bannerHtml, data);
            $('#banner').prepend(rendered);
            $('.cycle-slideshow').cycle({
                fx: 'scrollHorz',
                speed: 300,
                manualSpeed: 300,
                timeout: 4000,
                pager: '.cycle-pager'
            });
            $(".banner_imgs").click(function(){   
            	window.open($(this).attr("href"));
            });

            var img_width = 2000;
            var screen_width = parseInt(screen.width);
            var offset = ((screen_width-img_width)/2).toString() + "px";
            $(".cycle-slideshow img").css("margin-left", offset);
        });
}
