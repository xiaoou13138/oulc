<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/5/11
  Time: 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公开账号</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="bg">
    <div class="container">
        <div class="row search-content-block">
            <div class="grid-search-center center-block" style="margin-top: 50px">
                <div class="col-md-offset-2 col-md-7 "><input type="text"  class="form-control" id="searchContent" style="border-radius:0px;"></div>
                <div class="col-md-1 "><a class="btn btn-primary" onclick="getUserInfo(0,10,true)">搜索</a></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-10">
                <table class="table table-bordered"  id="userTable">
                    <thead>
                    <tr>
                        <td data-key="userId" type="normal" class="hide"></td>
                        <td data-key="userName" type="normal">用户名</td>
                        <td data-key="accountType" type="normal">用户类型</td>
                        <td data-key="userCode" type="normal">用户账号</td>
                        <td data-key="userDsc" type="normal">用户介绍</td>
                        <td data-key="report" type="normal">被举报/确定违规</td>
                       <%-- <td data-key="history" type="normal">求助历史</td>--%>
                        <td data-key="action" type="normal">操作</td>
                    </tr>
                    <tbody>
                    </tbody>
                </table>
                <div>
                    <ul class="pagination"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var userId;
    $(document).ready(function () {
        userId = ${userId};
        getUserInfo(0,10,true);
    });
    function getUserInfo(begin,end,isFirst) {
        var searchContent = $("#searchContent").val();
        beginLoad("","加载失败",5000);
        doPostAjaxAndLoad("openAccount_dealAction",{begin:begin,end:end,searchContent:searchContent,actionType:1},function (data) {
            if(data.result == "Y"){
                var specialData = {
                    userName:{
                        specialFunction:function (value) {
                            return "<a href='javascript:void(0)' onclick=\"openMessageView("+value.userId+")\">"+value.userName+"</a>";
                        }
                    },
                    action:{
                        specialFunction:function (value) {
                            var html ="<a href='javascript:void(0)' onclick=\"help("+value.userId+")\">"+"求助/"+"</a>"
                            +"<a href='javascript:void(0)' onclick=\"report("+value.userId+")\">"+"举报"+"</a>";
                            return html;
                        }
                    }
                };
                createTableHtmlUtil("userTable",data.userList,specialData);
            }
        })
    }
    function openMessageView(openUserId) {
        if(userId == undefined ||userId <=0){
            layer.confirm("请先登录", {
                btn: ['确定'] //按钮
            });
            return;
        }else{
            openIFrame('私信','./message?openUserId='+openUserId,893,800);
        }
    }
    function help(userId) {

    }
    function report(userId) {
        layer.prompt({title: '请输入举报描述', formType: 2,area:['800px', '80px']}, function(text, index){
            layer.close(index);
            if(text != "") {
                beginLoad("举报成功，系统会审核","举报失败",5000);
                doPostAjaxAndLoad("openAccount_dealAction",{"userId":userId,"content":text,actionType:2},function (data) {

                });
            }else{
                layer.confirm("内容不能为空", {
                    btn: ['确定'] //按钮
                });
            }
        });
    }
</script>
</body>
</html>
