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
    <link rel="stylesheet" href="css/message.css">
</head>
<body>
    <div style="margin: 20px 5px 80px 5px" class="divColor">
        <div style="height: 10%">
            通知
        </div>
        <div style="height: 90%">
            <div style="height: 40px;" id="tips">
            </div>
            <div style="width: auto">
                <div style="float: left; width: 80%; height: 100%">
                    <div style="height: 80%;">
                        <div style="float:left; width:80%;" >
                            <div class="tab-content">
                                <div class="tab-content">
                                    <div class="tab-pane active"  id="chatContent">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="height: 20%;">
                        <div >
                            <textarea class="form-control" rows="5" id = "sendContent"></textarea>
                        </div>
                        <div style="height:15%;">
                            <button class="btn btn-primary" style="float:left;">刷新</button>
                            <button class="btn btn-primary" style="float:right;" onclick="sendMessage()">发送信息</button>
                        </div>
                    </div>
                </div>
                <div style="float: left; width: 20%; height: 100%" >
                    <ul class="nav nav-pills nav-stacked" id="friendList">
                    </ul>
                </div>
            </div>
        </div>
    </div>
<script>
    var sendUserId ="0";//正在展示的朋友的ID
    $(document).ready(function(){
        doAjax("POST","message_getBaseInfo",{},function(data){
            $("#tips").text(data.promptingMessage);
            $.each(data.friendsName, function(index, value, array) {
                $("#friendList").html(createFrinedDIV(value["userName"],value["userId"]));
                if(index ==0){
                    sendUserId = value["userId"];
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
                doAjax("POST","message_getMessage",{"friendId":sendUserId},function(data){
                    $.each(data.messageList, function(index, value, array) {
                        $("#chatContent").append(createMessageDIV(value));
                    });
                })

            });
        });

        //获取提示标签的信息
        //获取右边的好友的信息
        //获取右边最近聊天的好友的信息
        //获取已经点击的好友的聊天的私信的信息
    });
    function createFrinedDIV(friendName,friendId){
        var html = "<li role=\"a\"><a href=\"#"
        +"a"+friendId+"\" value=\""+friendId+"\">"+friendName+"</a></li>";
        return html;
    }
    function createMessageDIV(value){
        var html = ""
        var sendPeople = value["sendPeople"];
        var content = value["content"];
        if(sendPeople == "0"){
            html ="<div class = \"send\">"+content+"</div>"
        }else{
            html ="<div class = \"accept\">"+content+"</div>"
        }
        return html;
    }
    function sendMessage(obj){
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
