<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/5/8
  Time: 1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>网络爬虫</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="bg">
    <div class="container-fluid" style="overflow: auto">
        <div class="panel panel-success" style="background-color: white;">
            <div class="panel-heading">
                <h3 class="panel-title  text-left">网络爬虫</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group form-text">
                        <label for="urls" class="col-xs-2 control-label">种子网页:</label>
                        <div class="col-xs-9">
                            <textarea class="form-control" rows="5" id = "urls"></textarea>
                        </div>
                    </div>
                    <div class="form-group form-text">
                        <label for="downFileCount" class="col-xs-2 control-label">每秒爬取网页:</label>
                        <div class="col-xs-2">
                            <input type="text" id="downFileCount"  class="form-control">
                        </div>
                        <label for="findFileCount" class="col-xs-2 control-label">每秒获取网页:</label>
                        <div class="col-xs-2">
                            <input type="text" id="findFileCount"  class="form-control">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-offset-2 col-xs-1">
                            <button type="button" class="btn btn-primary btn-lg btn-block center-block" onclick="addUrls()">添加种子网址</button>
                        </div>
                        <div class=" col-xs-1">
                            <button type="button" class="btn btn-primary btn-lg btn-block center-block" onclick="startWork()">开始爬取</button>
                        </div>
                        <div class="col-xs-1">
                            <button type="button" class="btn btn-primary btn-lg btn-block center-block" onclick="stopWork()">结束爬取</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

<script>
    $(document).ready(function () {
        window.setInterval(getPrecess, 10000);
    });
    function getState() {

    }
    function addUrls() {
        var urls = $("#urls").val();
        var urlArray = urls.split("\n");
        var confirmLayer = layer.confirm("确定添加", {
            btn: ['确定','取消'] //按钮
        },function () {
            beginLoad("添加成功","添加失败",5000,function () {
                location.reload();
            });
            doPostAjaxAndLoad("crawler_dealAction",{urlArray:urlArray,actionType:1},function (data) {
            });
            layer.close(confirmLayer);
        });
    }
    function startWork() {
        var confirmLayer = layer.confirm("确定开始运行", {
            btn: ['确定','取消'] //按钮
        },function () {
            beginLoad("运行开始","运行开始失败",5000,function () {
                location.reload();
            });
            doPostAjaxAndLoad("crawler_dealAction",{actionType:2},function (data) {
            });
            layer.close(confirmLayer);
        });
    }
    function stopWork() {
        var confirmLayer = layer.confirm("确定停止运行", {
            btn: ['确定','取消'] //按钮
        },function () {
            beginLoad("停止成功","停止失败",5000,function () {
                location.reload();
            });
            doPostAjaxAndLoad("crawler_dealAction",{actionType:3},function (data) {
            });
            layer.close(confirmLayer);
        });
    }
    function getPrecess(){
        doPostAjax("crawler_dealAction",{actionType:4},function (data) {
            if(data.result =="Y"){
                $("#downFileCount").val(data['downFileCount']);
                $("#findFileCount").val(data['findFileCount']);
            }

        })
    }
</script>
</body>
</html>
