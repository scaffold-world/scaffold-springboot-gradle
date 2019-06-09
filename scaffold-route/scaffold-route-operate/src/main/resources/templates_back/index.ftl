<#assign base=request.contextPath />
<#assign title = "贷超管理">
<html>
<#include "public/head_index.ftl">
<body>
<div id="loading" class="loading-wrap">
    <div class="loading-content">
        <div class="loading-round"></div>
        <div class="loading-dot"></div>
    </div>
</div>

<div id="mm" class="submenubutton" style="width: 140px;">
    <div id="mm-tabclose" name="6" iconCls="fa fa-refresh">刷新</div>
    <div class="menu-sep"></div>
    <div id="Div1" name="1" iconCls="fa fa-close">关闭</div>
    <div id="mm-tabcloseother" name="3">关闭其他</div>
    <div id="mm-tabcloseall" name="2">关闭全部</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright" name="4">关闭右侧标签</div>
    <div id="mm-tabcloseleft" name="5">关闭左侧标签</div>
</div>
<script>
    $(function () {
        $('#ulMenu>li').hover(
                function () {
                    var m = $(this).data('menu');
                    if (!m) {
                        m = $(this).find('ul').clone();
                        m.appendTo(document.body);
                        $(this).data('menu', m);
                        var of = $(this).offset();
                        m.css({left: of.left, top: of.top + this.offsetHeight});
                        m.hover(function () {
                            clearTimeout(m.timer);
                        }, function () {
                            m.hide()
                        });
                    }
                    m.show();
                }, function () {
                    var m = $(this).data('menu');
                    if (m) {
                        m.timer = setTimeout(function () {
                            m.hide();
                        }, 100);//延时隐藏，时间自定义，100ms
                    }
                }
        );
    });
</script>
<div data-toggle="topjui-layout" data-options="id:'index_layout',fit:true">
    <div id="north" class="banner" data-options="region:'north',border:false,split:false"
         style="height: 50px; padding:0;margin:0; overflow: hidden;">
        <table style="float:left;border-spacing:0px;">
            <tr>
                <td class="webname">
                    <span class="fa fa-envira" style="font-size:26px; padding-right:8px;"></span>贷超管理
                </td>
                <td class="collapseMenu" style="text-align: center;cursor: pointer;">
                    <span class="fa fa-chevron-circle-left" style="font-size: 18px;"></span>
                </td>
                <td>
                    <table id="topmenucontent" cellpadding="0" cellspacing="0">
                    <#list menus as item>
                        <td id="${item.id}" title="${item.name}" class="topmenu selected systemName">
                            <a class="l-btn-text bannerMenu" href="javascript:void(0)">
                                <p>
                                    <lable class="fa fa-hand-pointer-o"></lable>
                                </p>
                                <p><span style="white-space:nowrap;">${item.name}</span></p>
                            </a>
                        </td>
                    </#list>
                    </table>
                </td>
            </tr>
        </table>
        <span style="float: right; padding-right: 10px; height: 50px; line-height: 50px;">
            <a href="javascript:void(0)" data-toggle="topjui-menubutton"
               data-options="iconCls:'fa fa-user',hasDownArrow:false"
               style="color:#fff;">${sysOperate.userName}</a>|

            <a href="javascript:void(0)" id="mb4" data-toggle="topjui-menubutton"
                       data-options="menu:'#mm4',iconCls:'fa fa-language',hasDownArrow:true" style="color:#fff;">语言切换|</a>
            <div id="mm4" style="width:74px;">
                <div data-options="iconCls:'fa fa-language'" onclick="changeLanguage('zh_CN')">中文</div>
                <div data-options="iconCls:'fa fa-language'" onclick="changeLanguage('en_US')">英文</div>
                <div data-options="iconCls:'fa fa-language'" onclick="changeLanguage('id_ID')">印尼文</div>
            </div>
           <a href="javascript:void(0)" id="mb3" data-toggle="topjui-menubutton"
              data-options="menu:'#mm3',iconCls:'fa fa-cog',hasDownArrow:true" style="color:#fff;">设置</a>
            <div id="mm3" style="width:74px;">
                <div data-options="iconCls:'fa fa-info-circle'" onclick="openUpdatePwd(${sysOperate.id})">修改密码</div>
                <div class="menu-sep"></div>
            </div>


            <a href="javascript:void(0)" id="mb2" data-toggle="topjui-menubutton"
               data-options="menu:'#mm2',iconCls:'fa fa-tree',hasDownArrow:true" style="color:#fff;">主题</a>|
            <div id="mm2" style="width:180px;">
                <div data-options="iconCls:'fa fa-language blue'" onclick="changeTheme('blue')">默认主题</div>
                <div data-options="iconCls:'fa fa-tree'" onclick="changeTheme('black')">黑色主题</div>
                <div data-options="iconCls:'fa fa-tree'" onclick="changeTheme('blacklight')">黑色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree red'" onclick="changeTheme('red')">红色主题</div>
                <div data-options="iconCls:'fa fa-tree red'" onclick="changeTheme('redlight')">红色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree green'" onclick="changeTheme('green')">绿色主题</div>
                <div data-options="iconCls:'fa fa-tree green'" onclick="changeTheme('greenlight')">绿色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree purple'" onclick="changeTheme('purple')">紫色主题</div>
                <div data-options="iconCls:'fa fa-tree purple'" onclick="changeTheme('purplelight')">紫色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree blue'" onclick="changeTheme('blue')">蓝色主题</div>
                <div data-options="iconCls:'fa fa-tree blue'" onclick="changeTheme('bluelight')">蓝色主题-亮</div>
                <div data-options="iconCls:'fa fa-tree orange'" onclick="changeTheme('yellow')">橙色主题</div>
                <div data-options="iconCls:'fa fa-tree orange'" onclick="changeTheme('yellowlight')">橙色主题-亮</div>
            </div>
            <a href="javascript:void(0)" onclick="logout()" data-toggle="topjui-menubutton"
               data-options="iconCls:'fa fa-sign-out',hasDownArrow:false" style="color:#fff;">注销</a>
        </span>
    </div>

    <div id="west"
         data-options="region:'west',split:true,width:230,border:false,headerCls:'border_right',bodyCls:'border_right'"
         title="" iconCls="fa fa-dashboard">
        <div id="RightAccordion"></div>
        <!--<div id="menuTab" class="topjui-tabs" data-options="fit:true,border:false">
            <div title="导航菜单" data-options="iconCls:'fa fa-sitemap'" style="padding:0;">
                <div id="RightAccordion" class="topjui-accordion"></div>
            </div>
            <div title="常用链接" data-options="iconCls:'fa fa-star',closable:true">
                <ul id="channgyongLink"></ul>
            </div>
        </div>-->
    </div>

    <div id="center" data-options="region:'center',border:false" style="overflow:hidden;">
        <div id="index_tabs" style="width:100%;height:100%">
            <div title="系统首页" iconCls="fa fa-home" data-options="border:true,
            content:'<iframe src=\'./home\' scrolling=\'auto\' frameborder=\'0\' style=\'width:100%;height:100%;\'></iframe>'"></div>
        </div>
    </div>

    <div data-options="region:'south',border:true"
         style="text-align:center;height:30px;line-height:30px;border-bottom:0;overflow:hidden;">
        <span style="float:left;padding-left:5px;width:30%;text-align: left;">当前用户：${sysOperate.userName}</span>
        <span style="padding-right:5px;width:40%">
            Copyright@2015
            <a href="" target="_blank">杭州煎饼网络技术有限公司 版权所有</a>
            <a href="" target="_blank">浙ICP备12xxxxx号-1</a>
        </span>
    </div>
</div>

<!--[if lte IE 8]>
<div id="ie6-warning">
    <p>您正在使用低版本浏览器，在本页面可能会导致部分功能无法使用，建议您升级到
        <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">IE9或以上版本的浏览器</a>
        或使用<a href="http://se.360.cn/" target="_blank">360安全浏览器</a>的极速模式浏览
    </p>
</div>
<![endif]-->

<div id="themeStyle" data-options="iconCls:'fa fa-tree'" style="display:none;width:600px;height:340px">
    <table style="width:100%; padding:20px; line-height:30px;text-align:center;">
        <tr>
            <td>
                <div class="skin-common skin-black"></div>
                <input type="radio" name="themes" value="black" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-red"></div>
                <input type="radio" name="themes" value="red" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-green"></div>
                <input type="radio" name="themes" value="green" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-purple"></div>
                <input type="radio" name="themes" value="purple" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-blue"></div>
                <input type="radio" name="themes" value="blue" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-yellow"></div>
                <input type="radio" name="themes" value="yellow" class="topjuiTheme"/>
            </td>
        </tr>
        <tr>
            <td>
                <div class="skin-common skin-blacklight"></div>
                <input type="radio" name="themes" value="blacklight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-redlight"></div>
                <input type="radio" name="themes" value="redlight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-greenlight"></div>
                <input type="radio" name="themes" value="greenlight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-purplelight"></div>
                <input type="radio" name="themes" value="purplelight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-bluelight"></div>
                <input type="radio" name="themes" value="bluelight" class="topjuiTheme"/>
            </td>
            <td>
                <div class="skin-common skin-yellowlight"></div>
                <input type="radio" name="themes" value="yellowlight" class="topjuiTheme"/>
            </td>
        </tr>
    </table>
    <table style="width: 100%; padding: 20px; line-height: 30px; text-align: center;">
        <tr>
            <td>
                <input type="radio" name="menustyle" value="accordion" checked="checked"/>手风琴
            <td>
                <input type="radio" name="menustyle" value="tree"/>树形
            </td>
            <td>
                <input type="checkbox" checked="checked" name="topmenu" value="topmenu"/>开启顶部菜单
            </td>
        </tr>
    </table>
    <div id="ijhioh"></div>


</div>

<form id="pwdDialog"
      data-options="title: '修改密码',
      iconCls:'fa fa-key',
      width: 400,
      height: 300,
      href: _ctx+'/html/user/modifyPassword.html'"></form>
</body>
</html>
<script>
    function openUpdatePwd(id) {
        var $editDialog = $('<form></form>');
        $editDialog.iDialog({
            title: 'My Dialog',
            width: 800,
            height: 800,
            closed: false,
            cache: false,
            href: _ctx+'/sys/sysOperate/editOperatePwdPage?operateId='+ id,
            modal: true,
            buttons: [
                {
                    text: '更新',
                    iconCls: 'fa fa-save',
                    btnCls: 'topjui-btn-brown',
                    handler: function () {
                        var obj=$editDialog.serializeObject();
                        console.log(obj);
                        $.ajax({
                            type: 'POST',
                            url: _ctx+'/sys/sysOperate/updateOperatePwd',
                            data: obj,
                            success: function (data) {
                                if (data.code == 200) {
                                    // 提示信息
                                    alert("修改成功");
                                    $editDialog.iDialog('close');
                                    $('#userDatagridId').iDatagrid('reload');
                                } else {
                                    $.iMessager.alert(data.title,data.msg,'info');
                                }
                            },
                            error: function () {
                                $.iMessager.alert("提示信息","网络错误",'error');
                            }
                        });
                    }
                }
            ]

        });
        $("#themeStyle").after("<div id =\"ijhioh\"></div>");
    }

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