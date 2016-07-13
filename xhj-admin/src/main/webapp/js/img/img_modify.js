var class_option_mst="{{#data}}<option value='{{.}}'>{{.}}</option>{{/data}}";
$(document).ready(initPage);

function initPage()
{
    $.get("img/queryAllTypeName", function(data){
    	Mustache.parse(class_option_mst);
        rendered = Mustache.render(class_option_mst, data);
        $("#img_type").append(rendered);
        $.get("img/queryById?id="+getParameter("id"), function(data){
        	if(data.status == "success")
        	{
        		$('#img_linkAddr').val(data.data.linkAddr);
        		$('#img_weight').val(data.data.weight);
        		$('#img_description').val(data.data.description);
        		$("option[value='"+data.data.typeName+"']").attr('selected', 'selected');
        	}
        });
    });
	$("#btn_update").click(update);
	$("#btn_refresh").click(refresh);
}

function update()
{
	var img_type= $("#img_type").val();
    var img_description = $("#img_description").val();
    var img_weight = $("#img_weight").val();
    var img_linkAddr = $("#img_linkAddr").val();
    if(isStrEmpty(img_type))
    {
        alert("图片类型不能为空!~");
        return;
    }
    if(isStrEmpty(img_description))
    {
        alert("图片描述不能为空!~");
        return;
    }
    if(isStrEmpty(img_weight))
    {
        alert("图片权值不能为空!~");
        return;
    }
    var ajaxData={
    		"id": getParameter("id"),
    		"typeName": img_type, 
    		"description": img_description,
    		"weight": img_weight,
    		"linkAddr": img_linkAddr};
    $.ajax({
        type:"POST",
        url:"img/update",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success:function(data)
        {
        	alertTip(data,"imgManage");
        }
    });
}

function refresh()
{
	$.get("/refresh?key=img", function(data){
		alertTip(data,null);
	});
}