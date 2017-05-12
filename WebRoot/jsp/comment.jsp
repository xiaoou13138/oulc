<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>评论</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid text-left" style="overflow: auto;color: black">
    <div class="row">
        <div class="col-xs-10 box-border " id = "content">

        </div>
        <div class="col-xs-1">
            <button type="button" class="btn btn-primary center-block" onclick="inputComment()" >评论</button>
        </div>
        <div class="col-xs-1">
            <button type="button" class="btn btn-primary center-block" onclick="inputLeval()" >评级</button>
        </div>
    </div>
    <div class="row comment-content-block" id="example" style="display: none" onmouseover="showComment(this,1)" onmouseout="showComment(this,2)">
        <div class="col-xs-12">
            <div class="row">
                <div class="col-xs-2 text-right" id="userName"><span class="charity" id="userTypeSpan"></span >：</div>
                <div class="col-xs-10 text-left" id="commentContent"></div>
            </div>
            <div class="row">
                <div class="col-xs-offset-7 col-xs-3 text-right" id="createDate"></div>
                <div class="col-xs-2 text-right"><a class="text-right" id="report" href="javascript:void(0)" onclick="report(this)" style="display: none">举报</a></div>
            </div>
        </div>


    </div>
</div>
<script>
    var webId ="";
    var userId= "";
    var userName="";
    var webId;
    $(document).ready(function () {
        userId = ${userId};
        userName = '${userName}';
        webId = getParam("webId");
        getComment();
    });
    function getComment() {
        webId = getParam("webId");
        doPostAjax("comment_getComment",{"webId":webId},function (data) {
            if(data.result == "Y"){
                $.each(data.commentList, function(index, value, array) {
                    createComment(value);
                });

            }



                 })
    }

    function inputLeval() {
        if(userId == ""){
            layer.confirm("请先登录", {
                btn: ['确定'] //按钮
            });
            return ;
        }
        openIFrame("星级","./leval?webId="+webId,340,150);
    }
    function showComment(obj,actionType) {
        if(actionType ==
                1){
            $(obj).find("#report").show();
        }else{
            $(obj).find("#report").hide();
        }

    }
    function report(obj) {
        var reportId = $(this).attr("data-id");
        if(userId == ""){
            layer.confirm("请先登录", {
                btn: ['确定'] //按钮
            });
            return ;
        }
        layer.prompt({title: '请输入举报描述', formType: 2,area:['800px', '80px']}, function(text, index){
            layer.close(index);
            if(text != "") {
                beginLoad("举报成功，系统会审核","举报失败",5000);
                doPostAjaxAndLoad("comment_sendComment",{"reportId":reportId,"content":text},function (data) {

                });
            }else{
                layer.confirm("内容不能为空", {
                    btn: ['确定'] //按钮
                });
            }
        });
    }
    function createComment(value){
        $("#example").clone(true).attr("id","comment"+value.id).appendTo("#content").css("display","block");
        $("#comment"+value.id).attr("display","block");
        $("#comment"+value.id).find("#userName").prepend(value.userName);

        $("#comment"+value.id).find("#commentContent").append(value.content);
        $("#comment"+value.id).find("#createDate").append(value.createDate);

        $("#comment"+value.id).find("#report").attr("data-id",value.userId);

        if(value.userType =='CHARITY' ){
            $("#comment"+value.id).find("#userTypeSpan").text("公");
        }
    }

    function inputComment() {
        if(userId == ""){
            layer.confirm("请先登录", {
                btn: ['确定'] //按钮
            });
            return ;
        }
        layer.prompt({title: '请输入评论', formType: 2,area:['800px', '80px']}, function(text, index){
            layer.close(index);
            if(text != "") {
                beginLoad("发送评论成功","发送评论失败",5000);
                doPostAjaxAndLoad("comment_sendComment",{"webId":webId,"content":text},function (data) {

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
