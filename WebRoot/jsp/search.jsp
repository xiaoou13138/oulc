<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>搜索一下</title>
	<script src="js/head.js"></script>
</head>
<body>
    <div class="bg">
    	<div class="grid-total" align="center" id="searchDIV">
    		<div class="grid-search-left"></div>
    		<div class="grid-search-center">
    			<div class="grid-left"><input type="text"  class="myform-control" id="searchContent"></div>
    			<div class="grid-right"><a class="search-button btn-primary no-line " onclick="doSearch(0,10,true)">搜索</a></div>
    		</div>
    		<div class="grid-search-right"></div>
    		<div style="clear:both"></div>
    		
    		<div class="grid-options"></div>
    		<div class="grid-sort"></div>
	    	<div id="searchInfo" class="products">

	    	</div>
    	</div>
		<div style="padding-left:100px;">
			<ul class="pagination"></ul>
		</div>
    </div>
    <script>  
     $(document).ready(function(){
         $(window).scroll(function () {
             if ($(".navbar").offset().top > 50) {$(".navbar-fixed-top").addClass("top-nav");
             }else {$(".navbar-fixed-top").removeClass("top-nav");}
         });
         document.onkeydown=function(e){
             if (!e) e = window.event;
             if ((e.keyCode || e.which) == 13){
                 doSearch();
             }
         };
     });

    function doSearch(begin,end,isFirst){
        //获取值
        var searchData = $("#searchContent").val();

        var data = {
            "begin":1,
            "end":2,
            "searchParams":[searchData]
        };
        doAjax("POST","search_doSearch",data,function(data){
            if("success"==data.result){
                $.each(data.html, function(index, value, array) {
                    $("#searchInfo").html(value);
				});

                $("#searchDIV").attr("align","left");
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        doSearch(begin,end,false);
                    });
				}
            }
            $("#code").qtip(getRentingTips('查询失败'));
            $("#code").focus();
        });
        //查询后台获取数据
    }
     function openCommentWindow(obj){
         var id = $(obj).attr("value");
         window.open("./comment?webId="+id, "newwindow","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=750,height=470,left=80,top=40")
     }
    </script>
        
</body>
</html>