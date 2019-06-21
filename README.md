# scaffold 【脚手架】

### 简介
**一个轻量级的项目开发脚手架:**
> 项目创建初衷为搭建一个随时即可用的项目框架，为以后快速开发打下基础<br>
> 后台管理的前端框架第一阶段使用免费开源的`WeAdmin`<br>
> `weadmin`是以layui为基础构建的后台管理框架，相较layui-admin还是有不少差距，但是胜在免费开源<br>

### 项目结构
- scaffold-business: 业务模块。service和dao层所在模块，主要业务代码都集中在这个模块
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
