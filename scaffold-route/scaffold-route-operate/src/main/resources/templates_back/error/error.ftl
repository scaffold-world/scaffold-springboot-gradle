<#assign base=request.contextPath />
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- FontAwesome字体图标 -->
    <link type="text/css" href="${base}/static/static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- jQuery相关引用 -->
    <script type="text/javascript" src="${base}/static/static/plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/static/plugins/jquery/jquery.cookie.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${base}/static/static/plugins/bootstrap/css/bootstrap-ext.css">


    <script type="text/javascript">
        $(function(){
            $(window).resize(function(){
                $("#main-container").css("min-height", $(window).height() - 343);
            }).resize();
        });
        var data = 10;
    </script>

    <title>错误提示 - </title>
</head>

<body>
<div class="container" style="margin-top: 100px;">

    <div class="row main-low-margin">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-success">
                <div class="panel-heading text-center">错误提示</div>
                <div class="panel-body text-center">
                    <p class="text-success">${msg!''}</p>

                    <p></p>
                </div>
            </div>
        </div>
    </div>

</div>


<script>

</script>
</body>
</html>