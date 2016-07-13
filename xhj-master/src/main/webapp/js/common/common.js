var top_nav_html = "<nav id='top_nav'>\
	<div id='top_tab'>\
		<ul id='top_nav_ul'>\
			<li class='top_left_nav top_nav_li'><a title='全部商品' href='javascript:void(0);'><span>全部商品</span></a></li>\
			<li class='top_nav_li'><a title='首页'\
				href='/index'>首页\
			</a></li>\
			<li class='top_nav_li'><a title='新闻'\
				href='/html/news/news_list_class.html?typeName=南京文交所'>新闻\
			</a></li>\
			<li class='top_nav_li top_nav_li_kaihu'><a title='开户'\
				href='javascript:void(0);'>开户\
			</a><ul id='top_nav_li_kaihu_hover' style='display:none;'><li style='line-height: 40px;'><a href='/html/kaihu.html?name=南京文交所'>南京文交所</a></li><li style='line-height: 40px;'><a href='/html/kaihu.html?name=中南文交所'>中南文交所</a></li><li style='line-height: 40px;'><a href='/html/kaihu.html?name=天津文交所'>天津文交所</a></li></ul></li>\
			<li class='top_nav_li'><a title='招贤纳士'\
				href='/html/company/company.html?tab=4'>招贤纳士\
			</a></li>\
			<li class='top_nav_li'><a title='关于我们'\
				href='/html/company/company.html?tab=1'>关于我们\
			</a></li>\
			<div style='clear: both;'></div>\
		</ul>\
	</div>\
</nav>";

var left_nav_mst = "<div class='left_nav' style='display: none;'>\
    {{#data}}\
	<div>\
		<h4 id='left_nav_{{index}}' class='left_nav_el' index='{{index}}'><a href='{{proListInOneClassUrl}}'>{{name}}</a></h4>\
	</div>\
    {{/data}}\
</div>";

/*var left_nav_hover_mst = "{{#data}}\
<div id='left_nav_hover_{{index}}' class='left_nav_hover' index='{{index}}'\
	style='display: none'>\
	{{#children}}\
    <div>\
		<dl class='left_nav_hover_menu_list'>\
			<dt class='dt'>\
				<p>{{name}}</p>\
			</dt>\
			<dd class='dd'>\
                {{#children}}\
				<a href='{{proListInOneClassUrl}}'>{{name}}</a>\
                {{/children}}\
			</dd>\
			<div style='clear: both'></div>\
		</dl>\
	</div>\
    {{/children}}\
</div>\
{{/data}}";*/
left_nav_hover_mst = "{{#data}}\
	<div id='left_nav_hover_{{index}}' class='left_nav_hover' index='{{index}}'\
		style='display: none'>\
	<ul class='left_nav_hover_menu_list'>\
		{{#children}}\
			<li>\
				<a href='{{proListInOneClassUrl}}'>{{name}}</a>\
			</li>\
	    {{/children}}\
	</ul>\
	</div>\
	{{/data}}";

var footer = '\
<div class="footer">\
<div class="inner">\
    <div class="foot-nav"><a href="/html/company/company.html?tab=1" title="关于我们">关于我们</a><span>|</span><a href="/html/company/company.html?tab=4" title="招贤纳士">招贤纳士</a><span>|</span><a href="/html/company/company.html?tab=5" title="联系我们">联系我们</a><span>|</span><a href="sitemap/" title="网站地图">网站地图</a><span>|</span><a href="link/" title="友情链接">友情链接</a></div>\
    <div class="foot-text">\
        <p>北京和贵礼源商贸有限公司 版权所有  京ICP备16003961号 </p>\
        <p>地址:北京市朝阳区甘露园小区枫叶健身二楼  电话:400-638-1302 E-mail:p787806@126.com</p>\
    </div>\
</div>\
</div>';

var qq_html = "\
<div id='floatTools' class='floatQQ'>\
        <div class='floatL'>\
            <a title='查看在线客服' class='btnOpen' id='aFloatTools_Show' style='display: none;' href='javascript:void(0);'>展开</a>\
            <a title='关闭在线客服' class='btnCtn' id='aFloatTools_Hide' style='display: block;'  href='javascript:void(0);'>收缩</a>\
        </div>\
        <div id='divFloatToolsView' class='floatR'>\
            <div class='tp'></div>\
            <div class='cn'>\
                <ul>\
                    <li class='top'>\
                        <h3 class='titZx'>\
								QQ咨询\
							</h3>\
                    </li>\
                    <li>\
                        <span class='icoZx'>在线咨询</span>\
                    </li>\
                    <li>\
                        <a class='qqOn' target='_blank' href='http://wpa.qq.com/msgrd?v=3&uin=3298722120&site=qq&menu=yes'><img border='0' src='http://wpa.qq.com/pa?p=2:2278808484:41 &r=0.8940031429092906'></a>\
                    </li>\
                    <li>\
                        <a class='qqOn' target='_blank' href='http://wpa.qq.com/msgrd?v=3&uin=964454478&site=qq&menu=yes'><img border='0' src='http://wpa.qq.com/pa?p=2:954149011:41 &r=0.8940031429092906'></a>\
                    </li>\
                    <li>\
                        <a class='qqOn' target='_blank' href='http://wpa.qq.com/msgrd?v=3&uin=343776750&site=qq&menu=yes'><img border='0' src='http://wpa.qq.com/pa?p=2:954149011:41 &r=0.8940031429092906'></a>\
                    </li>\
                    <li>\
                        <a class='qqOn' target='_blank' href='http://shang.qq.com/wpa/qunwpa?idkey=ab07540ca247c4ecbb310c8930a03cd32880194bac58430bd9d3c951a4311ca8'><img border='0' src='/img/qqonline/qq_group.png' alt='祥宏聚南京1302会员群' title='祥宏聚南京1302会员群'></a>\
                    </li>\
                </ul>\
                <ul>\
                    <li>\
                        <h3 class='titDh'>\
								电话咨询\
							</h3>\
                    </li>\
                    <li>\
                        <span class='icoTl'>400-638-1302</span>\
                    </li>\
                    <li class='bot' style='height:100px'>\
                    	<img src='/img/gzh_hgly.jpg' width='100px' height='100px'/>	\
                    </li>\
                </ul>\
            </div>\
        </div>\
    </div>";

$(document).ready(common_init_page);

function common_init_page()
{
    $("body").prepend("<img id='page_top_img' src='/img/top.jpg' />")
    var img_width = 2000;
    var screen_width = parseInt(screen.width);
    var offset = ((screen_width-img_width)/2).toString() + "px";
    $("#page_top_img").css("margin-left", offset);
    
    $.get("/productClass/getProductClassTree", function(data){
        var firstLevel = data.data.children;
        Mustache.parse(left_nav_mst);
        var rendered1 = Mustache.render(left_nav_mst, {"data": firstLevel});
        Mustache.parse(left_nav_hover_mst);
        var rendered2 = Mustache.render(left_nav_hover_mst, {"data": firstLevel});
        $('#head_nav').prepend(top_nav_html+rendered1+rendered2);

        $('.top_left_nav').hover(leftNavHoverIn, leftNavHoverOut);
        $('.left_nav').hover(leftNavHoverIn, leftNavHoverOut);
        $('.left_nav_el').each(function(){
        	if($(this).text() == "钱币")
        		$(this).hover(leftNavHoverInYp, leftNavHoverOutYp);
        	}
        );
        $('.left_nav_hover').hover(allNavHoverInYp, allNavHoverOutYp);
        $('.top_nav_li_kaihu').hover(kaihuHoverIn, kaihuHoverOut);
        $('.top_nav_li_kaihu > a').hover(kaihuHoverIn, null);
        $('#top_nav_li_kaihu_hover').hover(kaihuHoverIn, kaihuHoverOut);
    });
    $('body').append(footer);
    qqonline();
}

function leftNavHoverInYp() {
    $('#left_nav_hover_' + $(this).attr("index")).show();
}

function leftNavHoverOutYp() {
    $('#left_nav_hover_' + $(this).attr("index")).hide();
}

function leftNavHoverIn() {
    $('.left_nav').show();
}

function leftNavHoverOut() {
    $('.left_nav').hide();
}

function kaihuHoverIn() {
	$('#top_nav_li_kaihu_hover').show();
}

function kaihuHoverOut() {
	$('#top_nav_li_kaihu_hover').hide();
}

function allNavHoverInYp() {
    $('.left_nav').show();
    $('#left_nav_hover_' + $(this).attr("index")).show();
}

function allNavHoverOutYp() {
    $('.left_nav').hide();
    $('#left_nav_hover_' + $(this).attr("index")).hide();
}

function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    }
    return null;
}

function qqonline() {
    $("body").append(qq_html);
    $(".floatL>.btnOpen").click(function() {
        $('#divFloatToolsView').show(300);
        $('#aFloatTools_Show').attr('style', 'display:none');
        $('#aFloatTools_Hide').attr('style', 'display:block');
    });
    $(".floatL>.btnCtn").click(function() {
        $('#divFloatToolsView').hide(300);
        $('#aFloatTools_Show').attr('style', 'display:block');
        $('#aFloatTools_Hide').attr('style', 'display:none');
    });
}


function isStrEmpty(str) {
    if (str == null || str == undefined || str == '') {
        return true;
    }
    return false;
}