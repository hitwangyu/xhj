$(document).ready(news_detail_init_page);

function news_detail_init_page()
{
	queryNewsDetail();
}

function queryNewsDetail()
{
	$.get("/news/getNewsDetail?id=" + getParameter("id"), function(data){
		if(data.status="success")
		{
			var news =data.data;
			$('.news_title').text(news.title);
			$('.author').text(news.author);
			$('.create_time').text(news.createTime);
			$('.news_content').append(news.content);
		}
	});
}