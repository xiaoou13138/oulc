<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/19
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增/修改资源</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid" style="overflow: hidden">
    <div class="row">
        <form class="form-horizontal">
            <div class="form-group has-check form-text">
                <label for="name" class="col-xs-2 control-label">资源名称：</label>
                <div class="col-xs-9 ">
                    <input type="text" class="form-control" id="name" >
                </div>
            </div>

            <div class="form-group form-text">
                <label for="num" class="col-xs-2 control-label">资源数量：</label>
                <div class="col-xs-3">
                    <input type="text" class="form-control" id="num" >
                </div>
                <label for="unit" class="col-xs-2 control-label">资源单位：</label>
                <div class="col-xs-3">
                    <input type="text" class="form-control" id="unit" >
                </div>
            </div>

            <div class="form-group form-text">
                <label for="desc" class="col-xs-2 control-label">资源介绍(描述)：</label>
                <div class="col-xs-9">
                    <textarea class="form-control" rows="5" id = "desc"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-offset-4 col-xs-4">
                    <button type="button" class="btn btn-primary btn-lg btn-block center-block" onclick="saveInfo()" id="saveBtn">保存信息</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    var viewType;
    var catalogId;
    var resourceId;
    $(document).ready(function () {
        viewType = getParam("viewType") ;
        catalogId = getParam("catalogId") ;
        resourceId = getParam("resourceId") ;
        console.log("viewType:"+viewType);
        console.log("catalogId:"+catalogId);
        console.log("resourceId:"+resourceId);
        if(viewType ==2){//修改
           $("#saveBtn").text("保存修改");
           getResourceByResourceId(resourceId);
        }
    });
    //修改的时候获取数据 actionType=2
    function getResourceByResourceId(resourceId){
        beginLoad("","获取资源信息失败",5000);
        doPostAjaxAndLoad("resource_action",{actionType:3,resourceId:resourceId},function (data) {
           if(data.result =="Y"){
               setViewData(data);
           }
        });
    }
    function saveInfo() {
        var json = getViewData();
        validForm();
        json.actionType =  viewType;
        json.catalogId =catalogId;
        json.resourceId =  resourceId;
        beginLoad("保存成功","保存失败",5000,function () {
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
            window.parent.searchResource(0,10,true);
        });
        doPostAjaxAndLoad("resource_action",json,function (data) {
        })
    }
</script>
</body>
</html>
