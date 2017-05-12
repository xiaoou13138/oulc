<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/3/29
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HashMap data = (HashMap)request.getAttribute("data");
    /*boolean hasCertificate  =  (boolean)request.getAttribute("hasCertificate");*/
    boolean hasCertificate =Boolean.parseBoolean(data.get("hasCertificate").toString()) ;
%>
<!doctype html>
<head>
    <title>第三方公益机构</title>
    <script src="js/head.js"></script>
    <link rel="stylesheet" type="text/css" href="css/upload.css">
</head>
<body>
<div class="container-fluid" style="overflow: auto">
    <div class="panel panel-success" style="background-color: white;">
        <div class="panel-heading">
            <h3 class="panel-title  text-left">特殊账号申请</h3>
        </div>
        <div class="panel-body">
            <%if(hasCertificate){%>
            <form class="form-horizontal">
                <div class="form-group has-check">
                    <label for="orgName" class="col-xs-2 control-label">组织名称:</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" id="orgName" >
                    </div>
                </div>

                <div class="form-group has-check">
                    <label for="orgType" class="col-xs-2 control-label">组织类型:</label>
                    <div class="col-xs-9">
                        <select class="form-control" id="orgType">
                            <option value="1">第三方公益机构</option>
                            <option value="2">医疗机构</option>
                            <option value="3">其他</option>
                        </select>
                    </div>
                </div>

                <div class="form-group has-check">
                    <label for="cityChoice" class="col-sm-2 control-label">组织地址:</label>
                    <div class="col-xs-3">
                        <div class="input-group">
                            <div class="input-group-addon has-check">城市</div>
                            <input type="text" id="cityChoice"  class="form-control">
                            <input type="hidden" id="province" value="">
                            <input type="hidden" id="city" value="">
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="input-group">
                            <div class="input-group-addon">具体位置</div>
                            <input type="text" id="detailAddress"  class="form-control">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="orgDsc" class="col-xs-2 control-label">组织描述:</label>
                    <div class="col-xs-9">
                        <textarea class="form-control" rows="5" id = "orgDsc"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label for="certificationDsc" class="col-xs-2 control-label">认证说明:</label>
                    <div class="col-xs-9">
                        <textarea class="form-control" rows="5" id = "certificationDsc"></textarea>
                    </div>
                </div>

                <div class="form-group has-check">
                    <label for="applyUserName" class="col-xs-2 control-label">申请人名称:</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" id="applyUserName" >
                    </div>
                </div>

                <div class="form-group has-check">
                    <label for="certCode" class="col-xs-2 control-label">申请人身份证号:</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" id="certCode" >
                    </div>
                </div>

                <div class="form-group has-check">
                    <label for="applyUserPhoneNum" class="col-xs-2 control-label">申请人手机号码:</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" id="applyUserPhoneNum" onblur="checkPhoneAndPrompt('applyUserPhoneNum')">
                    </div>
                </div>

                <div class="form-group">
                    <label for="mail" class="col-xs-2 control-label">申请人联系邮箱:</label>
                    <div class="col-xs-9">
                        <input type="text" onblur="checkMailAndPrompt('mail');" class="form-control" id="mail" >
                    </div>
                </div>

                <div class="form-group has-check">
                    <label  class="col-xs-2 control-label">上传身份证件和认证证件:</label>
                    <div class="col-xs-9">
                        <div id="wrapper">
                            <div id="container">
                                <!--头部，相册选择和格式选择-->
                                <div id="uploader">
                                    <div class="queueList">
                                        <div id="dndArea" class="placeholder">
                                            <div id="filePicker"></div>
                                            <p>或将文件拖到这里，单次最多可选300份</p>
                                        </div>
                                    </div>
                                    <div class="statusBar" style="display:none;">
                                        <div class="progress">
                                            <span class="text">0%</span>
                                            <span class="percentage"></span>
                                        </div><div class="info"></div>
                                        <div class="btns">
                                            <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-offset-4 col-xs-4">
                        <button type="button" class="btn btn-primary btn-lg btn-block center-block" onclick="saveInfo()">提交</button>
                    </div>
                </div>
            </form>
            <%}else{%>
            <div class="jumbotron">
                <h2 style="text-align:center" >已经认证</h2>
            </div>
            <%}%>
        </div>
    </div>
</div>
<script>
    var nowtime ;
    $(document).ready(function () {
        nowtime = getCurrentTimeMillis();
        uploderInit("uploader","dndArea","filePicker",{time:nowtime},"uploadImage");//初始化
        var cityPicker = new IIInsomniaCityPicker({
            data: cityData,
            target: '#cityChoice',
            valType: 'k-v',
            hideCityInput: '#city',
            hideProvinceInput: '#province',
            callback: function(city_id){
            }
        });
        cityPicker.init();
    });
    function saveInfo(){
        var json = checkInfo();
        beginLoad("保存成功","保存失败",5000);
        doPostAjaxAndLoad("certification_saveCertificateInfo",json,function (data) {
        });

    }


    function checkInfo() {
        var json = {};
        //组织名称校验
        var orgName = $("#orgName").val();
        if(!orgName){
            $("#orgName").focus();
            layer.tips('组织名称必须填写', '#orgName');
            return;
        }
        json.orgName = orgName;

        //组织类型校验
        var orgType = $("#orgType").val();
        if(!orgType){
            $("#orgType").focus();
            layer.tips('组织类型必须填写', '#orgType');
            return;
        }
        json.orgType = orgType;

        //地址校验
        var cityChoice = $("#cityChoice").val();
        if(!cityChoice){
            $("#cityChoice").focus();
            layer.tips('城市必须填写', '#cityChoice');
            return;
        }
        var cityAddress = $("#province").val()+$("#city").val();
        json.cityAddress = cityAddress;

        //具体地址
        var detailAddress = $("#detailAddress").val();
        if(detailAddress){
            json.detailAddress = detailAddress;
        }

        //组织描述
        var orgDsc = $("#orgDsc").val();
        if(orgDsc){
            json.orgDsc = orgDsc;
        }

        //认证信息
        var certificationDsc = $("#certificationDsc").val();
        if(certificationDsc){
            json.certificationDsc = certificationDsc;
        }
        //申请人名称校验
        var applyUserName = $("#applyUserName").val();
        if(!applyUserName){
            $("#applyUserName").focus();
            layer.tips('申请人名称必须填写', '#applyUserName');
            return;
        }
        json.applyUserName = applyUserName;

        //申请人身份证号校验
        var certCode = $("#certCode").val();
        if(!certCode){
            $("#certCode").focus();
            layer.tips('申请人身份证号必须填写', '#certCode');
            return;
        }
        json.certCode = certCode;

        //申请人手机号码校验
        var applyUserPhoneNum = $("#applyUserPhoneNum").val();
        if(!applyUserPhoneNum){
            $("#applyUserPhoneNum").focus();
            layer.tips('申请人手机号必须填写', '#applyUserPhoneNum');
            return;
        }else{
            if(!checkPhone(applyUserPhoneNum)){
                $("#applyUserPhoneNum").focus();
                layer.tips('申请人手机号格式不正确', '#applyUserPhoneNum');
                return;
            }

        }
        json.applyUserPhoneNum = applyUserPhoneNum;

        //邮箱
        var mail = $("#mail").val();
        if(certificationDsc){
            json.mail = mail;
        }
        return json;
    }
</script>
</body>
</html>
