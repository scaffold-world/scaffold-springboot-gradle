<#assign base=request.contextPath />
<#assign title = "home">
<html>
<#include "public/head_home.ftl" >
<script type="text/javascript" src="${base}/static/static/plugins/echarts/echarts.min.js"></script>
<body>
<div class="layui-container-fluid">
    <div class="panel_box row">
        <div class="panel col">
            <a href="javascript:;" data-url="page/msg/msg.html">
                <div class="panel_icon">
                    <i class="layui-icon" data-icon=""></i>
                </div>
                <div class="panel_word newMessage">
                    <span>5</span>
                    <cite>新消息</cite>
                </div>
            </a>
        </div>
        <div class="panel col">
            <a href="javascript:;" data-url="page/user/allUsers.html">
                <div class="panel_icon" style="background-color:#FF5722;">
                    <i class="iconfont icon-dongtaifensishu" data-icon="icon-dongtaifensishu"></i>
                </div>
                <div class="panel_word userAll">
                    <span>3</span>
                    <cite>新增人数</cite>
                </div>
            </a>
        </div>
        <div class="panel col">
            <a href="javascript:;" data-url="page/user/allUsers.html">
                <div class="panel_icon" style="background-color:#009688;">
                    <i class="layui-icon" data-icon=""></i>
                </div>
                <div class="panel_word userAll">
                    <span>3</span>
                    <cite>用户总数</cite>
                </div>
            </a>
        </div>
        <div class="panel col">
            <a href="javascript:;" data-url="page/img/images.html">
                <div class="panel_icon" style="background-color:#5FB878;">
                    <i class="layui-icon" data-icon=""></i>
                </div>
                <div class="panel_word imgAll">
                    <span>31</span>
                    <cite>图片总数</cite>
                </div>
            </a>
        </div>
        <div class="panel col">
            <a href="javascript:;" data-url="page/news/newsList.html">
                <div class="panel_icon" style="background-color:#F7B824;">
                    <i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
                </div>
                <div class="panel_word waitNews">
                    <span>13</span>
                    <cite>待审核文章</cite>
                </div>
            </a>
        </div>
        <div class="panel col max_panel">
            <a href="javascript:;" data-url="page/news/newsList.html">
                <div class="panel_icon" style="background-color:#2F4056;">
                    <i class="iconfont icon-text" data-icon="icon-text"></i>
                </div>
            </a>
        </div>
    </div>
    <div class="panel_box row">
        <div class="panel col">
            <div class="lineChart">
                <#--<div id="line" style="width:2000px;height:400px;padding: 20px"></div>-->
            <#--<div data-toggle="topjui-echarts" id="lineChart" data-options="id:'lineChart',url: _ctx+'/operation/operationStatistics/operationStatisticsList?tagId=1',width:900,height:400"></div>-->
            </div>
        </div>
    </div>


</div>

</body>
</html>
<script type="text/javascript">
   /* function onLoad() {
        console.log("1123");
    }
        $.get('/dubbo/listener/echartIndex', function (result) {
            var option = $.parseJSON(result);
            var line = echarts.init(document.getElementById('line'));
            line.setOption(option);
        });


        $(function () {
            onLoad();
        });*/
</script>