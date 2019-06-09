package com.jiaheng.scaffold.route.operate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.jiaheng.scaffold"})
@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.jiaheng.scaffold.**.dao")
@EnableAsync//异步执行
public class RouteOperateApplication {
    public static void main(String[] args) {
        System.setProperty("projectName","scaffold-route-operate");
        SpringApplication.run(RouteOperateApplication.class, args);
    }


}
