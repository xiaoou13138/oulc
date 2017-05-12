<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/5/12
  Time: 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid" style="height: 100%;overflow: auto">
    <div class="row">
        <div class="col-md-offset-1 col-md-10">
            <div class="page-header">
                <h3>修改密码</h3>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-1 col-md-10">
            <form class="form-horizontal">
                <div class="form-group form-text has-check">
                    <label for="oldPassword" class="col-sm-1 control-label">旧密码:</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="oldPassword" >
                    </div>
                </div>
                <div class="form-group form-text has-check">
                    <label for="newPassword" class="col-sm-1 control-label">新密码:</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="newPassword" >
                    </div>
                </div>
                <div class="form-group form-text has-check">
                    <label for="confirmPassword" class="col-sm-1 control-label">重复密码:</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="confirmPassword" >
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-1 col-md-2">
            <button class="btn btn-primary" onclick="saveInfo()">保存修改</button>
        </div>
    </div>
</div>
<script>
    function saveInfo() {
        validForm();
        var json = getViewData();
        if(json.newPassword != json.confirmPassword){
            layer.tips('两次密码不一样', '#confirmPassword',{
                tips: [2, '#3595CC'],
                time: 4000
            });
            return;
        }
        beginLoad("修改成功","修改失败",5000,function () {
            location.href="./editUserInfo"
        });
        doPostAjaxAndLoad("changePassword_updateInfo",json,function (data) {
        });
    }
</script>
</body>
</html>
