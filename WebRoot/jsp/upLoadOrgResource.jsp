<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/17
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>医疗信息维护</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <button type="button" class="btn btn-primary pull-left" onclick="putComment()" >新增</button>
    </div>
    <div class="row">
        <form class="form-horizontal">
            <div class="form-group">
                <label for="orgName" class="col-xs-2 control-label">组织名称:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="orgName" >
                </div>
            </div>

            <div class="form-group">
                <label for="orgType" class="col-xs-2 control-label">组织类型:</label>
                <div class="col-xs-9">
                    <select class="form-control" id="orgType">
                        <option value="1">第三方公益机构</option>
                        <option value="2">医疗机构</option>
                        <option value="3">官方权威机构(政府)</option>
                        <option value="4">其他</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="cityChoice" class="col-sm-2 control-label">组织地址:</label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <div class="input-group-addon">城市</div>
                        <input type="text" id="cityChoice"  class="form-control">
                        <input type="hidden" id="province" value="">
                        <input type="hidden" id="city" value="">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="input-group">
                        <div class="input-group-addon">具体位置</div>
                        <input type="text" id="addressDeatail"  class="form-control">
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

            <div class="form-group">
                <label for="applicantName" class="col-xs-2 control-label">申请人名称:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="applicantName" >
                </div>
            </div>

            <div class="form-group">
                <label for="certCode" class="col-xs-2 control-label">申请人身份证号:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="certCode" >
                </div>
            </div>

            <div class="form-group">
                <label for="applicantPhoneNum" class="col-xs-2 control-label">申请人手机号码:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="applicantPhoneNum" >
                </div>
            </div>

            <div class="form-group">
                <label for="mail" class="col-xs-2 control-label">申请人联系邮箱:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="mail" >
                </div>
            </div>

            <div class="form-group">
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
        </form>
    </div>
</div>
<script>

</script>
</body>
</html>
