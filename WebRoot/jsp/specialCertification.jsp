<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/3/29
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>第三方公益机构</title>
    <script src="js/head.js"></script>
    <link rel="stylesheet" href="css/webuploader.css">
    <link rel="stylesheet" href="css/upload.css">
    <script src="js/webuploader.js"></script>
    <script src="js/upload.js"></script>
</head>
<body>
<div>
    <div style="margin: 8px 8px 8px 8px ">
        <div style="height: 70%;">
            <div class="edit-row ">
                <div class="row-left float-left ">
                    <div class="headFont float-right">组织名称:</div>
                </div>
                <div class="row-right float-left ">
                    <input type="text" class="form-control" style="height:80%;" id="orgName" value="" />
                </div>
            </div>
            <div class="edit-row">
                <div class="row-left float-left ">
                    <div class="headFont float-right">组织类型:</div>
                    <div class="star float-right">*</div>
                </div>
                <div class="row-right float-left ">
                    <div class="input-group " style="margin: auto;width:110px;float:left">
                        <select class=" " id="sex">
                            <option value="1">第三方公益机构</option>
                            <option value="2">医疗机构</option>
                            <option value="3">官方权威机构(政府)</option>
                            <option value="4">其他</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="edit-row ">
                <div class="row-left float-left ">
                    <div class="headFont float-right">地址:</div>
                </div>
                <div class="row-right float-left ">
                    <input type="text" class="form-control" style="height:80%;" id="creater" value="" />
                </div>
            </div>
            <div class="edit-row ">
                <div class="row-left float-left ">
                    <div class="headFont float-right">组织描述:</div>
                    <div class="star float-right">*</div>
                </div>
                <div class="row-right float-left ">
                    <input type="text" class="form-control" style="height:80%;" id="registAdd" value="" />
                </div>
            </div>
            <div class="edit-row ">
                <div class="row-left float-left ">
                    <div class="headFont float-right">认证说明:</div>
                    <div class="star float-right">*</div>
                </div>
                <div class="row-right float-left ">
                    <input type="text" class="form-control" style="height:80%;" id="applyDsc" value="" />
                </div>
            </div>
            <div class="edit-row ">
                <div class="row-left float-left ">
                    <div class="headFont float-right">申请人名称:</div>
                    <div class="star float-right">*</div>
                </div>
                <div class="row-right float-left ">
                    <input type="text" class="form-control" style="height:80%;" id="applyPeopleName" value="" />
                </div>
            </div>
            <div class="edit-row ">
                <div class="row-left float-left ">
                    <div class="headFont float-right">申请人身份证号:</div>
                    <div class="star float-right">*</div>
                </div>
                <div class="row-right float-left ">
                    <input type="text" class="form-control" style="height:80%;" id="certCode" value="" />
                </div>
            </div>
            <div class="edit-row ">
                <div class="row-left float-left ">
                    <div class="headFont float-right">申请人手机号码:</div>
                    <div class="star float-right">*</div>
                </div>
                <div class="row-right float-left ">
                    <input type="text" class="form-control" style="height:80%;" id="applyPhoneNum" value="" />
                </div>
            </div>
            <div class="edit-row ">
                <div class="row-left float-left ">
                    <div class="headFont float-right">申请人联系邮箱:</div>
                    <div class="star float-right">*</div>
                </div>
                <div class="row-right float-left ">
                    <input type="text" class="form-control" style="height:80%;" id="wordAdd" value="" />
                </div>
            </div>

            <div class="edit-row ">
                <div class="row-left float-left boder">
                    <div class="headFont float-right">上传身份证件和认证证件:</div>
                    <div class="star float-right">*</div>
                </div>
                <div class="row-right float-left ">
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
        </div>
    </div>
</div>
<script>
</script>
</body>
</html>
