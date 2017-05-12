<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/3/26
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <script src="js/head.js"></script>
<body>
<div class="bg">
    <div class="container-fluid" style="background-color: white;margin-left: 50px;margin-right: 50px">
        <div class="row">
            <div class="col-md-2" style="height:800px;padding-right: 0px !important;">
                <div class="row" style="height: 24%">
                    <a style=" " href="#"  id="" style="float: right;margin-right: 150px">
                        <img class="img-circle" src="showImage?imageFile=notRegist.png" style="width: auto;height: 100%;padding: 5px">
                    </a>
                </div>
                <div class="row" style="height: 70%;">
                    <ul class="nav nav-pills nav-stacked bg-success" id="userInfoTab">
                        <li role="presentation" class="active"><a href="#editUserInfo" data-toggle="tab" onclick="change('./editUserInfo')">编辑个人资料</a></li>
                        <li role="presentation" ><a href="#message" data-toggle="tab" onclick="change('./message')">私信</a></li>
                        <li role="presentation"><a href="#specialCertification" data-toggle="tab" onclick="change('./specialCertification')">特殊账号认证</a></li>
                        <li role="presentation"><a href="#specialCertification" data-toggle="tab" onclick="change('./orgResourceManage')">医疗信息维护</a></li>
                        <li role="presentation"><a href="#map" data-toggle="tab" onclick="change('./audit')">待审核信息</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-10" style="height:800px;padding-right: 0px !important;">
                <div class="tab-content"  style="height: auto">
                    <div class="tab-pane active" id="main"><iframe src="./editUserInfo" width="100%" height="100%" allowTransparency="true" id="rightFrame" style="border: hidden"></iframe></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        $('#userInfoTab a:first').tab('show');//初始化显示哪个tab

        $('#userInfoTab a').click(function(e) {
            e.preventDefault();//阻止a链接的跳转行为
            $(this).tab('show');//显示当前选中的链接及关联的content
        });
    });
    function change(address) {
        $("#rightFrame").attr("src",address);
    }
</script>

</body>
</html>
