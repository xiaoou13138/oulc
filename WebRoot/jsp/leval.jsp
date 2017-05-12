
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>等级</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal">
                <div class="form-group form-text" >
                    <div class="col-xs-6">
                        <div class="input-group" id = "group">
                            <input type="text" id="leval"  class="form-control" onkeyup="changeClass()">
                            <span class="input-group-addon">分</span>
                        </div>
                    </div>
                    <div class="col-xs-2">
                        <button type="button" class="btn btn-primary " style="" onclick="saveInfo()">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    var webId;
    $(document).ready(function () {
        webId = getParam("webId");
    });

    function changeClass() {
        $("#group").removeClass("has-error");
    }
    function saveInfo() {
        var json = getViewData();
        if(json.leval == undefined ||json.leval == ""){
            $("#group").addClass("has-error");
            return;
        }
        json.webId = webId;
        beginLoad("","",5000,function () {

        });
        doPostAjaxAndLoad("leval_saveInfo",json,function (data) {
            if(data.result =="Y"){
                layer.msg("评级成功");
                setTimeout(closeWindow,2000);
            }else{
                layer.msg("评级失败，请检查网络");
            }
        });
    }

    function closeWindow() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    }
</script>
</body>
</html>
