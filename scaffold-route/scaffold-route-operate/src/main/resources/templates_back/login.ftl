<#assign base=request.contextPath />
<html>
    <#include "public/head_login.ftl">
<body>
<div class="box">


    <div class="login-box">
        <div class="login-title text-center">
            <span class="flag"><i class="fa fa-user"></i> 用户登陆</span>
            <h1>
                <small></small>
            </h1>
        </div>
        <div class="login-content ">
            <div class="form">
                <form id="modifyPassword" class="form-horizontal" action="#" method="post">
                    <input type="hidden" id="referer" name="referer" value="<#--${param.referer}-->">
                    <div class="form-group">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" id="username" name="username" class="form-control" placeholder="用户名">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" id="password" name="password" class="form-control" placeholder="密码">
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-xs-12 text-center">
                            <button type="button" id="login" class="btn btn-sm btn-success">
                                <span class="fa fa-check-circle"></span> <@spring.msg "sys.login"/>
                            </button>
                            <button type="button" id="reset" class="btn btn-sm btn-danger">
                                <span class="fa fa-close"></span> 重置
                            </button>

                        </div>
                    </div>
                    <div class="form-group" style="text-align: center">
                        <a href="javascript:void(0)"  data-toggle="topjui-menubutton" class="btn btn-sm"
                           data-options="iconCls:'fa fa-language'" onclick="changeLanguage('zh_CN')" >中文</a>
                        <a href="javascript:void(0)"  data-toggle="topjui-menubutton" class="btn btn-sm"
                           data-options="iconCls:'fa fa-language'" onclick="changeLanguage('en_US')" >English</a>
                        <a href="javascript:void(0)"  data-toggle="topjui-menubutton" class="btn btn-sm"
                           data-options="iconCls:'fa fa-language'" onclick="changeLanguage('id_ID')" >印尼</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <span class="text-danger"><i class="fa fa-warning"></i> 用户名或密码错误，请重试！</span>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        window.onload = function () {
            if (window.parent.window != window) {
                window.top.location = "${base}/login";
            }
        }

        $('#password').keyup(function (event) {
            if (event.keyCode == "13") {
                $("#login").trigger("click");
                return false;
            }
        });

        $("#login").on("click", function () {
            submitForm();
        });

        function submitForm() {
            if (navigator.appName == "Microsoft Internet Explorer" &&
                    (navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE6.0" ||
                            navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE7.0" ||
                            navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE8.0")
            ) {
                alert("您的浏览器版本过低，请使用360安全浏览器的极速模式或IE9.0以上版本的浏览器");
            } else {
                var formData = {
                    username: $('#username').val(),
                    password: $('#password').val()
                    /*referer: $('#referer').val()*/
                };
                $.ajax({
                    type: 'POST',
                    url:  _ctx + '/login/check?username='+$("#username").val()+"&password="+$("#password").val(),
                    contentType: "application/json; charset=utf-8",
                    /*         data: JSON.stringify(formData),*/
                    success: function (data) {
                        if (data.code == 200) {
                            location.href = _ctx +  '/index';
                            /*location.href = data.referer;*/
                        } else {
                            $.iMessager.alert(data.title,data.msg,'info');
                        }
                    },
                    error: function () {

                    }
                });
            }
        }




        $("#reset").on("click", function () {
            $("#username").val("");
            $("#password").val("");
        });
    });
    function changeLanguage(lang){
        $.ajax({
            type: 'POST',
            url:  _ctx + '/lang/changeLanguage?lang='+lang,
            contentType: "application/json; charset=utf-8",
            /*         data: JSON.stringify(formData),*/
            success: function (data) {
                if (data.code == 200) {
                    window.location.reload();
                    //location.href = _ctx +  '/index';
                    /*location.href = data.referer;*/
                } else {
                    $.iMessager.alert(data.title,data.msg,'info');
                }
            },
            error: function () {

            }
        });
    }
</script>
</body>
</html>