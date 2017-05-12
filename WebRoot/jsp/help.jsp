<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/17
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="bg">
    <div class="container">
        <div class="row search-content-block">
            <div class="grid-search-center center-block" style="margin-top: 50px">
                <div class="col-md-offset-2 col-md-7 "><input type="text"  class="form-control" id="searchContent" style="border-radius:0px;"></div>
                <div class="col-md-1 "><a class="btn btn-primary" onclick="doSearch(0,10,true)">搜索</a></div>
            </div>
        </div>
        <div class="row">
            <table class="table table-bordered" style="width: 800px; margin: auto" id="groupTable">
                <thead>
                <tr>
                    <td id="groupId" class="hide">用户ID</td>
                    <td id="groupName">用户名称</td>
                    <td id="userTypeInGroup">账号类型</td>
                    <td id="groupAddress">被询问次数</td>
                    <td id="">已回复次数</td>
                    <td id="groupNum">描述</td>
                    <td ></td>
                </tr>
                </thead>
                <tr>
                    <td class="hidden">1</td>
                    <td>南昌公益机构</td>
                    <td>第三方公益机构</td>
                    <td>10</td>
                    <td>6</td>
                    <td>热情回复</td>
                    <td><a>求助/举报</a></td>
                </tr>
                <tr>
                    <td class="hidden">1</td>
                    <td>上海公益机构</td>
                    <td>第三方公益机构</td>
                    <td>15</td>
                    <td>6</td>
                    <td>热情回复</td>
                    <td><a>求助/举报</a></td>
                </tr>
                <tr>
                    <td class="hidden">1</td>
                    <td>北京公益机构</td>
                    <td>第三方公益机构</td>
                    <td>20</td>
                    <td>6</td>
                    <td>热情回复</td>
                    <td><a>求助/举报</a></td>
                </tr>
                <tr>
                    <td class="hidden">1</td>
                    <td>张医师</td>
                    <td>医疗机构</td>
                    <td>8</td>
                    <td>6</td>
                    <td>热情回复 擅长骨科方面</td>
                    <td><a>求助/举报</a></td>
                </tr>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
