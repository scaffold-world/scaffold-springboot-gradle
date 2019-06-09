<#assign base=request.contextPath />
<#import "spring.ftl" as spring/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="shortcut icon" href="${base}/static/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${base}/static/static/css/font.css">
    <link rel="stylesheet" href="${base}/static/static/css/weadmin.css">
    <script type="text/javascript" src="${base}/static/lib/layui/layui.js" charset="utf-8"></script>
    <!-- 自定义js -->
    <script type="text/javascript" src="${base}/static/common/js/self.js" charset="utf-8"></script>

    <script>
        /* 静态演示中获取_ctx，动态演示非必须 开始 */
        var _ctx = "${base}";
        /* 静态演示中获取_ctx，动态演示非必须 结束 */
        layui.config({
            base: _ctx + '/static/static/js/'
            ,version: '101100'
        }).extend({
            admin: 'admin'
        }).extend({
            treetable: 'treeGrid'
        }).use('admin', function () {
        });
    </script>
</head>