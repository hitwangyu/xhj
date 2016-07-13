var class_option_mst="{{#data}}<option value='{{.}}'>{{.}}</option>{{/data}}";
$(document).ready(initPage);

function initPage()
{
    $.get("news/queryAllClass", function(data){
    		Mustache.parse(class_option_mst);
            rendered = Mustache.render(class_option_mst, data);
            $("#news_class").append(rendered);
            $.get("news/queryById?id="+getParameter("id"), function(data){
            	if(data.status == "success")
            	{
            		$('#news_title').val(data.data.title);
            		$('#news_author').val(data.data.author);
            		$('#news_content').val(data.data.content);
            		$("option[value='"+data.data.typeName+"']").attr('selected', 'selected');
            	}
            	tinymce.init({
                    selector: "#news_content",
                    language:"zh_CN",
                    plugins: [
        		        "advlist autolink lists link image charmap print preview anchor",
        		        "searchreplace visualblocks code fullscreen",
        		        "insertdatetime media table contextmenu paste"
            		],
                    toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
                    table_default_attributes: {
                        class: 'table table-condensed table-striped table-hover'
                    }
            	});
            });
    });
    $("#btn_update").click(update);
}

function update()
{
	var news_class= $("#news_class").val();
    var news_title = $("#news_title").val();
    var news_author = $("#news_author").val();
    if(isStrEmpty(news_class))
    {
        alert("分类不能为空!~");
        return;
    }
    if(isStrEmpty(news_title))
    {
        alert("标题不能为空!~");
        return;
    }
    tinymce.triggerSave(true);
    var news_content = $("#news_content").val();
    if(isStrEmpty(news_content))
    {
        alert("新闻内容不能为空!~");
        return;
    }
    var ajaxData={
    		"id": getParameter("id"),
    		"newsClassName": news_class,
    		"title": news_title,
    		"content": news_content,
    		"author": news_author};
    $.ajax({
        type:"POST",
        url:"news/update",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success:function(data)
        {
        	alertTip(data, "newsManage");
        }
    });
}