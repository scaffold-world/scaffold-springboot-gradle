# scaffold 【脚手架】
<span>项目登录页</span>
![登录页展示](https://i.loli.net/2019/06/22/5d0da2f09cc0131950.png)
<span>项目登录页</span>
![index](https://s2.ax1x.com/2019/06/22/ZpW3vt.png)
### 简介
**一个轻量级的项目开发脚手架:**
> 项目创建初衷为搭建一个随时即可用的项目框架，为以后快速开发打下基础<br>
> 后台管理的前端框架第一阶段使用免费开源的`WeAdmin` —— [WeAdmin开源地址](https://gitee.com/lovetime/WeAdmin)<br>
> `weadmin`是以layui为基础构建的后台管理框架，相较layui-admin还是有不少差距，但是胜在免费开源，这个项目好像很久没有更新了，不过还是感谢作者能够提供这样一个开源的后台管理模板<br>

### 项目技术栈
- SpringBoot-1.5.9
- MyBatis
- MySQL
- Redis
- flyway-db

### 项目结构
- scaffold-business: 业务模块。service和dao层所在模块，主要业务代码都集中在这个模块
    - scaffold-business-sys： \scaffold\scaffold-business\scaffold-business-sys\src\main\resources\db\migration目录下存放数据库脚本文件
    - scaffold-business-job: 定时任务模块 主要是定时任务的启动核心配置类
- scaffold-core: 核心代码包。其中该模块主要负责一些工具类、核心配置类、aop切面等的代码以及自动代码生成插件的编写
- scaffold-route: 路由包。主要负责接口的编写，分为app和operate两个模块即前端接口和后台管理系统

### 项目启动
- 安装mysql和redis服务
- 修改配置文件配置：mysql和redis
- 数据库脚本使用flawaydb管理，无需关注sql初始化脚本
- 直接运行route中的启动类启动程序，首次运行会初始化

----
> 开发者：陈年风楼<br>

博客: [【陈年风楼的博客】](http://zhangjiaheng.cn)
