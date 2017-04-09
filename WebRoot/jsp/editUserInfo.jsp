<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/3/28
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div class="container" style="margin: 8px 8px 8px 8px ">
        <div style="height:30%;">
            这里是基本信息
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <label for="realName" class="col-sm-2 control-label">真实姓名:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="realName" placeholder="realName">
                </div>
            </div>
            <div class="form-group">
                <label for="sex" class="col-sm-2 control-label">性别:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="sex">
                        <option>不公开</option>
                        <option>男</option>
                        <option>女</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="phoneNum" class="col-sm-2 control-label">电话号码:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="phoneNum" placeholder="phoneNum">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" onclick="sendUpdateInfo()" id="sendButton">保存个人信息</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    var oldViewJson = {};
    var viewJson = {};
    $(document).ready(function() {
        //获取信息
        doPostAjax("editUserInfo_getEditUserInfo",{},function (data) {
            debugger;
            for(var key in data){
                oldViewJson = data;
                if($("#"+key).length>0){
                    $("#"+key).val(data[key]);
                    viewJson[key] = data[key];
                }

            }
        })
    });
    function sendUpdateInfo(){
        var count = 0;
        for(var key in viewJson){
            if(viewJson[key] != $("#"+key).val()){
                viewJson[key] = $("#"+key).val();
                count+=1;
            }
        }
        if(count !=0){
            var rtnJson = $.extend(oldViewJson,viewJson);
            doPostAjax("editUserInfo_editUserInfo",rtnJson,function (data) {
                if(data.result =="Y"){
                    changeButton("success");
                    layer.msg('保存成功');
                    setTimeout("changeButton('primary');",2000);
                }
             });
        }else{
            changeButton("warn");
            setTimeout("changeButton('primary');",2000);
        }
    }
    function changeButton(type) {
        if(type == "primary"){
            $("#sendButton").text("保存个人信息");
            $("#sendButton").attr("class","btn btn-primary");
        }else if(type =="warn"){
            $("#sendButton").text("信息未改变");
            $("#sendButton").attr("class","btn btn-warning");
        }else if(type =="success"){
            $("#sendButton").text("修改成功");
            $("#sendButton").attr("class","btn btn-success");
        }

    }
</script>
</body>
</html>
