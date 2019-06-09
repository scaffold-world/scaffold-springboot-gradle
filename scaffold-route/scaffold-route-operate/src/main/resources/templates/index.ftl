<#assign base=request.contextPath />
<!doctype html>
<html lang="en">

<head>
    <#include "head.ftl">
</head>

<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="index.ftl">欢迎使用后台管理系统</a>
    </div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav left fast-add" lay-filter="" id="head_menu">
        <#list menus as item>
            <li class="layui-nav-item" style="border-right: 1px solid #D3D4D3; border-bottom: 3px solid #D3D4D3">
                <a href="javascript:;" id="menu-${item.id}" onclick="changeLeftMenus(${item.id})">${item.name}</a>
            </li>
        </#list>
    </ul>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${sysOperate.userName},欢迎你！</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <dd>
                    <a onclick="WeAdminShow('个人信息','')">个人信息</a>
                </dd>
                <dd>
                    <a class="loginout" href="/logout">退出</a>
                </dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index">
            <a href="http://zhangjiaheng.cn/" target="_blank">作者博客</a>
        </li>
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        ${menuList!''}
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="wenav_tab" id="WeTabTip" lay-allowclose="true">
        <ul class="layui-tab-title">
            <li>系统首页</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${base}/static/pages/welcome.html' frameborder="0" scrolling="yes"
                        class="weIframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright ©2018 <a href="https://fatezhang.github.io" target="_blank" style="color: #93D1FF">&nbsp;&nbsp;&nbsp;&nbsp; zhangjiahengpoping@gmail.com</a></div>
</div>
<!-- 底部结束 -->
<script type="text/javascript">
    layui.use(['jquery', 'admin'], function () {
        var $ = layui.jquery;
        var admin = layui.admin;
        $(function () {
            $('#side-nav ul').hide();
            $('#side-nav').children(":first").show();
        });
        window.changeLeftMenus = function (pid) {
            $('#side-nav ul').hide();
            $('.nav-'+pid).show();
        }
    });
</script>
</body>
<!--Tab菜单右键弹出菜单-->
<ul class="rightMenu" id="rightMenu">
    <li data-type="fresh">刷新</li>
    <li data-type="current">关闭当前</li>
    <li data-type="other">关闭其它</li>
    <li data-type="all">关闭所有</li>
</ul>
</html>