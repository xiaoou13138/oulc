<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid" style="height: 100%;overflow: auto">
    <div class="row box-border" >
        <div class="col-md-offset-1 col-md-10">
            <div class="row" >
                <div class="col-md-12">
                    <div class="page-header">
                        <h1>基本信息<small>Basic Information</small></h1>
                    </div>
                </div>
            </div>
            <div class="row border-just-bottom-dashed" style="height: 110px" >
                <div class="col-md-2">
                    <img class="img-circle" src="showImage?imageFile=notRegist.png" style="width: auto;height: 100%;padding: 5px" id="userImage">
                </div>
                <div class="col-md-10">
                    <div>
                        <span style="font-size: 40px;font-weight: bold;" id="userName"></span>
                        <span style="font-size: 20px;padding-left: 5px;font-weight: bold;" >uid:<span id="userId"></span></span>
                        <span style="font-size: 20px;padding-left: 5px;color: #c2c2c2" id="userType"></span>
                    </div>
                    <div>注册于<span id="createDate"></span></div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-1">
                    <button class="btn btn-primary" onclick="javascript:location.href='./changePassword'">修改密码</button>
                </div>
            </div>
        </div>
    </div>

    <div class="row box-border" style="margin-top: 50px">
        <div class="col-md-offset-1 col-md-10">
            <div class="page-header">
                <h1>扩展信息<small>Basic Information</small></h1>
            </div>
            <form class="form-horizontal">
                <div class="form-group form-text">
                    <label for="realName" class="col-sm-1 control-label">真实姓名:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="realName" >
                    </div>
                </div>
                <div class="form-group form-select">
                    <label for="sex" class="col-sm-1 control-label">性别:</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="sex">
                            <option>不公开</option>
                            <option>男</option>
                            <option>女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group form-text">
                    <label for="phoneNum" class="col-sm-1 control-label">电话号码:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="phoneNum" onblur="checkPhoneAndPrompt('phoneNum')">
                    </div>
                </div>
                <div class="form-group form-text">
                    <label for="description" class="col-sm-1 control-label">个人描述:</label>
                    <div class="col-sm-6">
                        <textarea class="form-control" rows="5" id = "description" placeholder="这个人很懒，什么都没有留下"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class=" col-sm-10">
                        <button type="button" class="btn btn-primary center-block" onclick="sendUpdateInfo()" id="sendButton">保存个人信息</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    /**
     * 页面初始化获取数据
     */
    $(document).ready(function () {
        editUserInfoViewInit();
    });
    function editUserInfoViewInit(){
        doPostAjax("editUserInfo_getEditUserInfo",{},function (data) {
            setViewData(data);
            $("#userName").text(data.userName);
            $("#userId").text(data.userId);
            $("#createDate").text(data.createDate);
            if(data.userType =="normal"){
                $("#userType").text("普通用户");
            }else if(data.userType =="CHARITY"){
                $("#userType").text("第三方医疗机构");
            }else{
                alert(data.userType);
            }
        });
    }
    function sendUpdateInfo() {
        var json = getViewData();
        beginLoad("修改成功", "修改失败", 5000,function () {

        });location.reload();
        doPostAjaxAndLoad("editUserInfo_editUserInfo", json, function (data) {
        });
    }
    function gotoMessage() {
        window.parent.change("./message");
    }
</script>
</body>
</html>
