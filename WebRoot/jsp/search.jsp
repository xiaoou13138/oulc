<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
	<title>搜索一下</title>
	<script src="js/head.js"></script>
</head>
<body>
    <div class="bg">
		<div class="container" style="height: 1000px;">
			<div class="row search-content-block">
				<div class="grid-search-center center-block" style="margin-top: 50px">
					<div class="col-md-offset-2 col-md-7 "><input type="text"  class="form-control" id="searchContent" style="border-radius:0px;"></div>
					<div class="col-md-1 "><a class="btn btn-primary" onclick="webSearch(0,10,true)">搜索</a></div>
				</div>
			</div>
			<div class="row box-border" >
				<div class="container-fluid" id = "products">

				</div>
			</div>
			<div>
				<ul class="pagination"></ul>
			</div>
		</div>
    </div>

	<div class="row search-result-block text-left " id="example" style="display: none">
		<div class="container-fluid"	>
			<div class="row search-result-title">
				<a href="" target="_blank" id="title"></a>
			</div>
			<div class="row search-result-content" id="content">
			</div>
			<div class="row search-result-comment">
				<a href="javascript:void(0)" onclick="openCommentWindow(this)" id="comment" >评级/评价</a>
			</div>
		</div>
	</div>
    </div>
    <script>
	document.onkeydown=function(e){
		if (!e) e = window.event;
		if ((e.keyCode || e.which) == 13){
			doSearch();
		}
	};
    var searchContent;
     $(document).ready(function(){

         webSearch(0,10,true);
     });

    function webSearch(begin,end,isFirst){
        //获取值

        searchContent = $("#searchContent").val();
        var data = {
            "begin":0,
            "end":10,
            "searchContent":searchContent
        };
        doAjax("POST","search_doSearch",data,function(data){
            if("Y"==data.result){
                $("#products").empty();
                $.each(data.webList, function(index, value, array) {
                    createSearchHtml(value);
				});
                $("#searchDIV").attr("align","left");
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        doSearch(begin,end,false);
                    });
				}
            }
        });
        //查询后台获取数据
    }
    function createSearchHtml(value) {
        $("#example").clone(true).attr("id","block"+value.id).appendTo("#products").css("display","block");
        var block = $("#block"+value.id).find('#title');
        block.attr("href",value.url);
        block.text(value.title);
        var commnet = $("#block"+value.id).find('#comment');
        commnet.attr("data-id",value.id);
        //查询网站的信息
		doPostAjax("search_dealAction",{url:value.url,searchContent:searchContent},function (data) {
			if(data.result == "Y"){
                $("#block"+value.id).find('#content').html(data.html);
			}
        })
        debugger;
    }
     function openCommentWindow(obj){
         var id = parseInt($(obj).attr("data-id"));
         openIFrame("评论/评级","./comment?webId="+id);
     }
    </script>
</body>
</html>