<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/3/26
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>私信</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid" style="height: 100%;overflow: hidden">
    <div class="panel panel-success" style="background-color: white;height: 100%">
        <div class="panel-heading">
            <h3 class="panel-title  text-left">私信</h3>
        </div>
        <div class="panel-body" style="height: 735px">
            <div class="row box-border">
                <div class="col-md-8">
                    <div class="row">
                        <h3 class="col-xs-10" >
                            通知
                        </h3>
                    </div>

                    <div class="row">
                        <div class="col-xs-10">
                            <div class="tab-pane active bg-warning box-border"  id="chatContent" style="height: 500px; overflow:auto;">
                            </div>
                        </div>
                        <div class="col-xs-2">
                            <ul class="nav nav-pills nav-stacked" id="friendList">

                            </ul>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-10">
                            <textarea class="form-control" placeholder="输入消息内容" rows="5" id = "sendContent"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2">
                            <button class="btn btn-primary" style="float:left;">刷新</button>
                        </div>
                        <div class="col-xs-offset-6 col-xs-2">
                            <button class="btn btn-primary" style="float:right;" onclick="sendMessage()">发送信息</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var sendUserId ="0";//正在展示的朋友的ID
    var sendUserName ="";
    $(document).ready(function () {
        messageViewInit();
    });
    function messageViewInit() {
        doAjax("POST","message_getBaseInfo",{},function(data){
            $("#tips").text(data.promptingMessage);
            $.each(data.friendsName, function(index, value, array) {
                $("#friendList").html(createFrinedDIV(value["userName"],value["userId"]));
                if(index ==0){
                    sendUserId = value["userId"];
                    sendUserName = value["userName"];
                }
            });
            $('#friendList a:first').tab('show');//初始化显示哪个tab
            $.each(data.messageList, function(index, value, array) {
                $("#chatContent").append(createMessageDIV(value));
            });

            //为右边的好友的tab绑定事件
            $('#friendList a').click(function(e) {
                e.preventDefault();//阻止a链接的跳转行为
                $(this).tab('show');//显示当前选中的链接及关联的content
                //点击的时候查询交流信息
                sendUserId = $(e.target).attr("value");
                sendUserName = $(e.target).text();
                doPostAjax("message_getMessage",{"friendId":sendUserId},function(data){
                    $.each(data.messageList, function(index, value, array) {
                        $("#chatContent").append(createMessageDIV(value));
                    });
                })

            });
        });
    }

    //根据好友的昵称和id生成页面html元素
    function createFrinedDIV(friendName,friendId){
        var html = "<li role=\"a\"><a href=\"#"
        +"a"+friendId+"\" value=\""+friendId+"\">"+friendName+"</a></li>";
        return html;
    }
    //根据消息的内容生成页面html元素
    function createMessageDIV(value){
        var html = ""
        var messageType = value["messageType"];//sennPeople如果是0就是
        var content = value["content"];
        var userName = value["userName"];
        var friendName = value["friendName"];
        if(messageType == "send"){
            html ="<h3 class = \"send bg-info text-right\">"+content + "</h3><h5 style='text-align: right'>"+value.time+"</h5>";

        }else{
            html ="<h3 class = \"accept bg-danger text-left\">"+friendName+":"+content+
                "</h3>" +
                "<h5 style='text-align: left'>"+value.time+"</h5>";
        }
        return html;
    }
    //发送消息调用的函数
    function sendMessage(){
        var content = $("#sendContent").val();
        var data = {"content":content,"acceptUserId":sendUserId};
        doAjax("POST","message_send",data,function(data){
            var value = {"sendPeople":"0","content":content};
            $("#chatContent").append(createMessageDIV(value));
            $("#sendContent").val("")
        })
    }

</script>
</body>
</html>
