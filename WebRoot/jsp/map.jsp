<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/3/29
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="js/head.js"></script>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=09BOANftFs7uVaipXxFGsRF2txzTOiT9" type="text/javascript"></script>
</head>
<body>
<div id="container" style="height: 800px;">

</div>
<script>
    $(document).ready(function () {
        loadScript();
    });
    function loadScript() {
        var map = new BMap.Map("container");          // 创建地图实例
        var point = new BMap.Point(116.404, 39.915);  // 创建点坐标
        map.centerAndZoom(point, 15);
    }
</script>
</body>
</html>
