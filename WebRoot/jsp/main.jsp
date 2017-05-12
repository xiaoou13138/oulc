<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<head>
    <title>搜索一下</title>
    <script src="js/head.js"></script>
    <link href="css/main.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-fixed-top my-navbar" role="navigation">
    <div class="container-fluid">
        <div >
            <a class="navbar-brand" onclick="manageChange('./search')"  style="float: left;margin-right: 40px">
                主页
            </a>
        </div>
        <div >
            <a class="navbar-brand" onclick="manageChange('./openAccount')"  style="float: left;margin-right: 40px">
                公开账号
            </a>
        </div>
        <div >
            <a class="navbar-brand" onclick="manageChange('./crawler')"  style="float: left;margin-right: 40px">
                爬虫
            </a>
        </div>
        <div >
            <a class="navbar-brand" href="javascript:void(0)"  id="userHref" style="float: right;margin-right: 150px" onclick="manageChange('./manage')">
                <div class="fa" id="hrefContent"></div>
            </a>
        </div>
    </div>
</nav>
<div style="height: 100%;">
    <iframe src="./search" width="100%" height="100%" allowTransparency="true" id="bottonFrame" ></iframe>
</div>
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
            $("#userHref").attr("href","javascript:void(0)")
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
    function manageChange(address){
        $("#bottonFrame",parent.document.body).attr("src",address);
    }
</script>
</body>
</html>