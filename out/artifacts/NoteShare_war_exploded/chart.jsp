<%--
  Created by IntelliJ IDEA.
  User: Aki
  Date: 2022/6/28
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>网站信息统计</title>
    <script src="js/jquery-1.11.3.js"></script>
    <script src="js/echarts.js"></script>
</head>
<style>
    html{
        background-color: #3a2d7d;
    }
    .grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        grid-gap: 10px;
        background-color: #5e49ca;
    }
</style>
<script>
    $(document).ready(function(){
            $.get('${pageContext.request.contextPath}/mapChart.action',function(data){
                /*alert("数据: " + data);*/
                $.get('map/json/China.json', function (chinaJson) {
                    echarts.registerMap('china', chinaJson); // 注册地图
                    console.log(chinaJson)
                    console.log(data)
                    var mapChart = echarts.init(document.getElementById('map-wrap'));
                    var option = {
                        backgroundColor: '#ced4da',
                        title: {
                            text: '用户中国分布',
                            subtext: 'Fake Data',
                            left: 'center'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left'
                        },
                        tooltip: {},
                        geo: {
                            map: 'china',
                            roam: true,
                            label: {
                                show: true
                            }
                        },
                        series: [
                            {
                                name: '人数',
                                type: 'scatter',
                                coordinateSystem: 'geo',

                                data: data, // series数据内容
                                geoIndex: 0,
                                type: 'map',
                            }
                        ],
                        visualMap: {
                            min: 0,
                            max: 30,
                        }
                    }
                    mapChart.setOption(option);

                });
        },"json");
    });
</script>
<script>
    $(document).ready(function(){
        $.get('${pageContext.request.contextPath}/pieChart.action',function(data){
            /*alert("数据: " + data);*/
                console.log(data)
                var mapChart = echarts.init(document.getElementById('SexChart'));
                var option = {
                    backgroundColor: '#ced4da',
                    title: {
                        text: '用户男女比例',
                        subtext: 'Fake Data',
                        left: 'center'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    tooltip: {},
                    series: [
                        {
                            type: 'pie',
                            data: data
                        }
                    ]
                };
                mapChart.setOption(option);

        },"json");
    });
</script>

<script>
    $(document).ready(function(){
        $.get('${pageContext.request.contextPath}/toneChart.action',function(data){
            /*alert("数据: " + data);*/
            console.log(data)
            var mapChart = echarts.init(document.getElementById('mostUploadChart'));
            var option = {
                backgroundColor: '#ced4da',
                title: {
                    text: '用户上传笔记数量',
                    subtext: 'Real Data',
                    left: 'center'
                },
                legend: {},
                tooltip: {},
                dimensions: ['name', 'value'],
                xAxis: {
                    type: 'category',
                    data: data,
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: data,
                        type: 'bar'
                    }
                ]
            };
            mapChart.setOption(option);

        },"json");
    });
</script>
<script>
    $(document).ready(function(){
        $.get('${pageContext.request.contextPath}/areaChart.action',function(data){
            /*alert("数据: " + data);*/
            console.log(data)
            var mapChart = echarts.init(document.getElementById('areaChart'));
            var option = {
                backgroundColor: '#ced4da',
                title: {
                    text: '各科目上传笔记数量',
                    subtext: 'Real Data',
                    left: 'center'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                tooltip: {},
                series: [
                    {
                        type: 'pie',
                        data:data,
                        roseType: 'area'
                    }
                ]
            };
            mapChart.setOption(option);

        },"json");
    });
</script>
<body>

    <div class="grid">
        <div id="map-wrap" style="height: 350px;">
            <!-- 这里以后是地图 -->
        </div>
        <div id="mostUploadChart" style="height: 352px;">
        </div>
        <div id="SexChart" style="height: 350px;">
        </div>
        <div id="areaChart" style="height: 350px;">
        </div>
    </div>

</body>
</html>
