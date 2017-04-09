<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/3/29
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>评论</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container" >
    <div style="height: 70%;" id="container">

    </div>
    <div style="height: 30%;">
        <textarea class="form-control" rows="4" id = "content"></textarea>
        <button onclick="putComment()">发送</button>
    </div>
</div>
<script>
    var webId ="";
    var userId= "";
    var userName=""
    $(document).ready(function () {
        debugger;
        getComment();
        userId = ${userId};
        userName = '${userName}';
    })
    function getComment() {
        webId = getParam("webId");
        doPostAjax("comment_getComment",{"webId":webId},function (data) {
            $("#container").html(createCommentHtmlArray(data));
        })

    }
    function createCommentHtmlArray(data){
        var strArray = new Array();
        $.each(data.commentList,function (idex,value,array) {
            var html = createCommentHtml(value);
            strArray.push(html);
        })
        var rtnHtml = String.prototype.concat.apply("", strArray);
        return rtnHtml;

    }
    function createCommentHtml(value){
        var html = "<div><span value='"+value.userId+"'>"+value.userName+":</span><span>"+value.content+"</span></div>";
        return html;

    }
    
    function putComment() {
        if(userId == ""){
            sweetAlert("请先登录");
            return ;
        }
        var content = $("#content").val();
        if(content != ""){
            doPostAjax("comment_sendComment",{"webId":webId,"content":content},function (data) {
                if(data.result = "Y"){

                    var html = createCommentHtml({"userId":userId,"userName":userName,"content":content});
                    $("#container").append(html);
                    $("#content").val("");
                }else{
                    sweetAlert("发送失败，请检查网络");
                }

            });
        }else{
            sweetAlert("内容不能为空！")
        }

    }
</script>
</body>
</html>
