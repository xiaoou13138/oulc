<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>搜索一下</title>
    <script src="js/head.js"></script>
</head>
<body>
<nav class="navbar navbar-fixed-top my-navbar" role="navigation">
    <div class="container-fluid">
        <div >
            <a class="navbar-brand" href="#"  id="userHref" style="float: right;margin-right: 150px">
                <div class="fa" id="hrefContent"></div>
            </a>
        </div>
        
    </div>
</nav>
<jsp:include  page="search.jsp"/>
<script>
    var userName =  "";
    var messageNum = 0;
    jQuery(function() {
        userName =  '${data.userName}';
        messageNum = '${data.messageNum}';
        //判断用户是否已经登录，如果登录
        if(userName != "" && messageNum !=""){
            changeUserIcon(true,userName);
        }else{
            changeUserIcon(false,userName);
        }
        if(userName != ""){
            $("#userHref").qtip(showUserMessage());
        }

    });
    //根据是否有用户改变样式
    function changeUserIcon(hasUserInfo,userName){
        if(hasUserInfo == true){
            $("#userHref").attr("href","http://localhost:8080/oulc/userInfo")
            $("#hrefContent").addClass("userCond");
            $("#hrefContent").text("");
        }else{
            $("#userHref").attr("href","http://localhost:8080/oulc/login");
            $("#hrefContent").text("注册/登录");
            $("#hrefContent").removeClass("userCond");
        }

    }
    $(window).scroll(function () {
        if ($(".navbar").offset().top > 50) {$(".navbar-fixed-top").addClass("top-nav");
        }else {$(".navbar-fixed-top").removeClass("top-nav");}
    })
    function showUserMessage(){
        var tips = {
            content: {
                text:"" +
                "<div style=\"width: 200px;\"><span style=\"float: left;\">" +
                userName+
                "     您有"+
                messageNum+
                "条私信" +
                "</span><span style=\"float: right;\"><a onclick=\"delUserInfoCookie()\">退出</a></span></div>"
            },
            show: {
                effect: function() {
                    $(this).fadeTo(500, 1);
                }
            },
            hide: {
                fixed: true,
                delay: 300
            },
            position: {
                my: 'top center',
                at: 'bottom center'
            },
            style: {
                classes: 'qtip-youtube'
            }
        };
        return tips;
    }
    function delUserInfoCookie(){
        $.cookie('codeVal', '', {
            expires : -1
        });
        $.cookie('passwordVal', '', {
            expires : -1
        });
        changeUserIcon(false)
    }
</script>
</body>
</html>