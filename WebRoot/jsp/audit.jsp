<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/17
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>审核</title>
    <script src="js/head.js"></script>
</head>
<body>
    <div class="container-fluid" style="overflow: auto">
        <div class="panel panel-success" style="background-color: white;">
            <div class="panel-heading">
                <h3 class="panel-title  text-left">特殊账号申请</h3>
            </div>
            <div class="panel-body">
                <div class="row search-content-block">
                    <div class="grid-search-center center-block" style="margin-top: 50px">
                        <div class="col-xs-offset-2 col-xs-7 "><input type="text"  class="form-control" id="searchContent" style="border-radius:0px;"></div>
                        <div class="col-xs-1 "><a class="btn btn-primary" onclick="doSearch(0,10,true)">搜索</a></div>
                    </div>
                </div>
                <div class="row">
                    <table class="table table-bordered" style="width:90%; margin-left: 30px;margin-right: 30px" id="auditTable">
                        <thead>
                        <tr>
                            <td data-key="auditId" type="normal">记录ID</td>
                            <td data-key="auditType" type="normal">审核类型</td>
                            <td data-key="applyUserId" type="normal">提交人标识</td>
                            <td data-key="applyUserName" type="normal">提交人名称</td>
                            <td data-key="auditDsc" type="normal">描述</td>
                            <td data-key="detail" type="normal">详细</td>
                            <td data-key="time" type="normal">申请时间</td>
                            <td data-key="action" type="normal">操作</td>
                        </tr>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="text-center">
                        <ul class="pagination"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    $(document).ready(function () {
        searchAuditInfo(0,10,true);
    });
    function searchAuditInfo(begin,end,isFirst){
        doPostAjax("audit_getAuditInfo",{"begin":begin,"end":end},function (data) {
            if(data.result =="Y"){
                var specialData = {
                    action:{
                        specialFunction:function (value) {
                            return "<a onclick='dealAction("+value.auditId+",2"+")'>同意/</a><a onclick='dealAction("+value.auditId+",3"+")'>拒绝</a>";
                        }
                    }
                };
                createTableHtmlUtil("auditTable",data.auditList,specialData);
                //分页的重新初始化
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        searchAuditInfo(begin,end,false);
                    });
                }
            }
        });
    }
    function dealAction(auditId,actionType){
        beginLoad("处理成功","处理失败",10000);
        doPostAjaxAndLoad("audit_changeState",{auditId:auditId,actionType:actionType},function (data) {
            if(data.result =="Y"){
                location.reload();
            }
        });
    }
</script>
</html>
