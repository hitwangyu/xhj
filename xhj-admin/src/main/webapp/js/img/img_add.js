var class_option_mst="{{#data}}<option value='{{.}}'>{{.}}</option>{{/data}}";
$(document).ready(initPage);

function initPage()
{
    var uploader = new plupload.Uploader({
        runtimes : 'html5,flash,silverlight,html4',
        browse_button : 'file_upload', // you can pass in id...
        multi_selection: false,
        url : '/admin/fs/upload?fileType=img',  //plupload有点问题，相对路径有问题，不知道为啥

        filters : {
            max_file_size : '2024KB',
            mime_types: [
                {title : "Image Files", extensions : "jpg,png"}
            ]
        },

        init: {
            PostInit: function() {
                $('#file_upload_txt').empty();
                $("#file_upload_submit").click(function () {
                    uploader.start();
                    return false;
                })
            },
            FilesAdded: function(up, files) {
                plupload.each(files, function(file) {
                    $('#file_upload_txt').html('<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>');
                });

                $.each(up.files, function (i, file) {
                    if (up.files.length <= 1) {
                        return;
                    }

                    up.removeFile(file);
                });
            },
            FileUploaded: function(up, files, data) {
                // Called when all files are either uploaded or failed
                var data = eval("(" + data.response + ")");
                if (data.status == "success") {
                    $("#img_imgAddr").val(data.data);
                    $("#file_upload_preview").html('<div class="info-pic"><img src="'+ data.data +'"></div>');
                } else {
                    alert(data.wolongError.error);
                }
            },


            Error: function(up, err) {
                alert("Error #" + err.code + ": " + err.message);
            }
        }
    });

    uploader.init();

    $.get("img/queryAllTypeName", function(data){
    	Mustache.parse(class_option_mst);
        rendered = Mustache.render(class_option_mst, data);
        $("#img_type").append(rendered);
    });
	$("#btn_add").click(add);
	$("#btn_refresh").click(refresh);
}

function add()
{
	var img_type= $("#img_type").val();
    var img_description = $("#img_description").val();
    var img_weight = $("#img_weight").val();
    var img_linkAddr = $("#img_linkAddr").val();
    var img_imgAddr = $("#img_imgAddr").val();
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
    if(isStrEmpty(img_imgAddr))
    {
        alert("图片不能为空!~");
        return;
    }
    var ajaxData={"typeName": img_type, 
    		"description": img_description,
    		"weight": img_weight,
    		"linkAddr": img_linkAddr,
            "imgAddr": img_imgAddr};
    $.ajax({
        type:"POST",
        url:"img/add",
        data: JSON.stringify(ajaxData),
        contentType:"application/json",
        success:function(data)
        {
        	//alertTip("imgManage");
        	alertTip(data,null);
        	location.reload();
        }
    });
}

function refresh()
{
	$.get("/refresh?key=img", function(data){
		alertTip(data,null);
	});
}