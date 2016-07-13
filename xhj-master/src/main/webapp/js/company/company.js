$(document).ready(company_init_page);

function company_init_page()
{
	var tab = getParameter("tab");
	if(!isStrEmpty(tab))
	{
		$('#tab'+tab).click();
	}
}

function clickTab(tab)
{
	$('.sb_box').hide();
	$('#sb_box_'+tab).show();
	$('.sb_nav .zm').removeClass('click_on');
	$('#tab'+tab).addClass('click_on');
}