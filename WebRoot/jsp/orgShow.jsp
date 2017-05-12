<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/5/10
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>医疗机构展示</title>
    <script src="js/head.js"></script>
</head>
<body style="background-color: #e4e4e4;">
<div class="container" style="background-color: white;height: 100%;margin-top: 100px">
    <div class="row">
        <div class="col-md-12">
            <div class="row"><h1 id="orgName"></h1></div>
            <div class="row" style="padding:20px 50px 20px 50px" id="orgDsc"></div>
        </div>
    </div>
    <div class="row">
        <div class="panel-group" id="resources" role="tablist" aria-multiselectable="true">
        </div>
    </div>

    <div class="panel panel-default" style="display: none;margin: 5px 5px 5px 5px" id="example">
        <div class="panel-heading" role="tab" id="headingOne">
            <h4 class="panel-title">
                <a  id="title" role="button" data-toggle="collapse" data-parent="#resources" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
            <div class="panel-body" id="desc">
            </div>
        </div>
    </div>
    <div class="row" id="exampleTable" style="display: none;">
        <div class="col-md-10">
            <table class="table table-bordered"  >
                <thead>
                <tr>
                    <td data-key="id" type="normal" class="hide"></td>
                    <td data-key="name" type="normal">名称</td>
                    <td data-key="num" type="normal">数量</td>
                    <td data-key="unit" type="normal">单位</td>
                    <td data-key="Dsc" type="normal">描述</td>
                </tr>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>

</div>
<script>
    var orgId;
    $(document).ready(function () {
        orgId = getParam("orgId");
        viewInit();
    });
    function legendClick(obj) {
        $('#'+$(obj).attr("data-block-id")).coolfieldset({speed:"fast"});
    }
    function viewInit(){
        beginLoad("","加载失败",5000);
        doPostAjaxAndLoad("orgShow_dealAction",{orgId:orgId,actionType:1},function (data) {
            if(data.result == "Y"){
                $.each(data.catalogList, function(index, value, array) {
                    var block = $("#resources");
                    createHtml(value,block,"resources");
                });
                alert(data.orgName);
                $("#orgName").text(data.orgName);
                $("#orgDsc").text(data.orgDesc);
            }
        });
    }
    function createHtml(value,resourcesBlock,blockId){
        try{
            $("#example").clone(true).attr("id","resource"+value.catalogId).appendTo(resourcesBlock).css("display","block");
            $("#resource"+value.catalogId).find("#title").text(value.catalogName);
            $("#resource"+value.catalogId).find("#title").attr("data-block-id","resource"+value.catalogId);
            $("#resource"+value.catalogId).find("#desc").append(value.desc);
            $("#resource"+value.catalogId).find("#headingOne").attr("id","headingOne"+value.catalogId);
            $("#resource"+value.catalogId).find("#title").attr("href","#collapseOne"+value.catalogId);
            $("#resource"+value.catalogId).find("#title").attr("aria-controls","collapseOne"+value.catalogId);
            $("#resource"+value.catalogId).find("#title").attr("data-parent","#"+blockId);
            $("#resource"+value.catalogId).find("#collapseOne").attr("aria-labelledby","headingOne"+value.catalogId);
            $("#resource"+value.catalogId).find("#collapseOne").attr("id","collapseOne"+value.catalogId);
            if(value.resourceList != undefined &&  value.resourceList.length >0){
                $("#exampleTable").clone(true).attr("id","exampleTable"+value.catalogId).appendTo($("#resource"+value.catalogId).find("#desc")).css("display","block");
                createTableHtmlUtil("exampleTable"+value.catalogId,value.resourceList);
            }
            if(value.catalogList != undefined && value.catalogList.length >0){
                var litleBlock = $("#resource"+value.catalogId).find("#desc");
                $.each(value.catalogList, function(index, newvalue, array) {
                    createHtml(newvalue,litleBlock);
                });
            }
        }catch (e){
            alert(e);
        }



    }
</script>
</body>
</html>
