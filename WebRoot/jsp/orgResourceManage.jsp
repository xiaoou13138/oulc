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
<div class="container-fluid">
    <div class="panel panel-success" style="background-color: white;height: 100%">
        <div class="panel-heading">
            <h3 class="panel-title  text-left">医疗信息维护</h3>
        </div>
        <div class="panel-body" style="height: 735px">
            <div class="row">
                <div class="col-md-3">
                    <div id="tree"></div>
                </div>
                <div class="col-md-9" >
                    <div class="row">
                        <div class="col-xs-offset-6 col-xs-4">
                            <button type="button"  class="btn btn-primary pull-right" data-toggle="context" data-target="#context-menu" onclick="deleteResource()" >删除</button>
                            <button type="button" style="margin-right: 15px;" class="btn btn-primary pull-right" data-toggle="context" data-target="#context-menu" onclick="updateResource()" >修改</button>
                            <button type="button" style="margin-right: 15px;" class="btn btn-primary pull-right" data-toggle="context" data-target="#context-menu" onclick="addResource()" >新增</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10">
                            <table class="table table-bordered"  id="resourceTable">
                                <thead>
                                <tr>
                                    <td data-key="resourceId" type="checkBox"></td>
                                    <td data-key="resourceId" type="normal">资源标识</td>
                                    <td data-key="name" type="normal">资源名称</td>
                                    <td data-key="num" type="normal">资源数量</td>
                                    <td data-key="unit" type="normal">资源单位</td>
                                    <td data-key="Dsc" type="normal">资源描述</td>
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
            <div id="context-menu">
                <ul class="dropdown-menu" role="menu">
                    <li><a tabindex="1" href="javascript:void(0)">新增同级目录</a></li>
                    <li><a tabindex="2" href="javascript:void(0)">添加下级目录</a></li>
                    <li><a tabindex="3" href="javascript:void(0)">删除目录</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script>
    var catalogId;
    $(document).ready(function () {
        getTreeData();
    });

    /**
     * 根据目录查找资源
     * @param catalogId
     */
    function searchResource(begin,end,isFirst) {
        beginLoad("","",5000);
        doPostAjaxAndLoad("orgResourceManage_getResource",{catalogId:catalogId,begin:begin,end:end},function (data) {
            if(data.result == "Y"){
                createTableHtmlUtil("resourceTable",data.resourceList);
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        searchAuditInfo(begin,end,false);
                    });
                }
            }
        });
    }

    //右键事件
    function dealAction(actionType) {
        if(actionType == 1){
            //修改
            openIFrame("修改资源","./")
        }
    }
    /**
     * 新增资源
     */
    function addResource(){
        if(catalogId ==""|| catalogId == undefined){
            layer.confirm("请先选中左边的一个目录", {
                btn: ['确定'] //按钮
            });
            return;
        }
        openIFrame('新增资源',"./resource?viewType=1&catalogId="+catalogId);
    }
    //修改资源
    function updateResource() {
        var array = getTableCheckData('resourceTable');
        if(array.length>1){
            layer.confirm("修改的时候只能选择一条记录", {
                btn: ['确定'] //按钮
            });
            return;
        }
        openIFrame('修改资源',"./resource?viewType=2&resourceId="+array[0]);
    }
    //删除资源
    function deleteResource() {
        var array =getTableCheckData("resourceTable");
        var confirmLayer = layer.confirm("确定删除", {
            btn: ['确定','取消'] //按钮
        },function () {
            beginLoad("删除成功","删除失败",5000,function () {
                searchResource(0,10,true);
            });
            doPostAjaxAndLoad("orgResourceManage_dealAction",{array:array,actionType:2},function (data) {
            });
            layer.close(confirmLayer);
        });
    }



    function getTreeData() {
        doPostAjax("orgResourceManage_getCatalogData",{},function (data) {
            if(data.result =="Y"){
                $('#tree').treeview({
                    data: data.nodes,         // data is not optional
                    levels: 5,
                    backColor: 'white',
                });
                $('#tree').on('nodeSelected', function(event, data) {
                    //节点被选中回调的事件
                    catalogId = data.catalogId;
                    searchResource(0,10,true);
                    //查询该目录下面有什么资源
                });

            }
        });
    }
    function itemOnclick(target){
        //找到当前节点id
        var nodeid = $(target).attr('data-nodeid');
        var tree = $('#tree');
        //获取当前节点对象
        var node = tree.treeview('getNode', nodeid);

        if(node.state.expanded){
            //处于展开状态则折叠
            tree.treeview('collapseNode', node.nodeId);
        } else {
            //展开
            tree.treeview('expandNode', node.nodeId);
        }
    }

    function nodeAction(catalogId,leval,upCatalogId,catalogName,actionType) {
        beginLoad("操作成功","操作失败",5000);
        doPostAjaxAndLoad("orgResourceManage_catalogAction",{catalogId:catalogId,catalogName:catalogName,actionType:actionType,upCatalogId:upCatalogId,leval:leval},function (data) {
           if(data.result =="Y"){
                location.reload();
           }
        });
    }

</script>
</body>
</html>
