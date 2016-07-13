$(document).ready(index_init_page);

function index_init_page() {
    $('#hq_nav_nj>a').click(function(){
       $('#zn_hangqing').hide();
       $('#tj_hangqing').hide();
       $('#nj_hangqing').show();
       $('#hq_nav_nj').css('background-color','#337ab7');
       $('#hq_nav_nj>a').css('color','#fff');
       $('#hq_nav_zn').css('background-color','#fff');
       $('#hq_nav_zn>a').css('color','#337ab7');
       $('#hq_nav_tj').css('background-color','#fff');
       $('#hq_nav_tj>a').css('color','#337ab7');
    });
    $('#hq_nav_zn>a').click(function(){
       $('#nj_hangqing').hide();
       $('#tj_hangqing').hide();
       $('#zn_hangqing').show();
       $('#hq_nav_zn').css('background-color','#337ab7');
       $('#hq_nav_zn>a').css('color','#fff');
       $('#hq_nav_nj').css('background-color','#fff');
       $('#hq_nav_nj>a').css('color','#337ab7');
       $('#hq_nav_tj').css('background-color','#fff');
       $('#hq_nav_tj>a').css('color','#337ab7');
    });
    $('#hq_nav_tj>a').click(function(){
       $('#nj_hangqing').hide();
       $('#zn_hangqing').hide();
       $('#tj_hangqing').show();
       $('#hq_nav_tj').css('background-color','#337ab7');
       $('#hq_nav_tj>a').css('color','#fff');
       $('#hq_nav_nj').css('background-color','#fff');
       $('#hq_nav_nj>a').css('color','#337ab7');
       $('#hq_nav_zn').css('background-color','#fff');
       $('#hq_nav_zn>a').css('color','#337ab7');
    });

    }
