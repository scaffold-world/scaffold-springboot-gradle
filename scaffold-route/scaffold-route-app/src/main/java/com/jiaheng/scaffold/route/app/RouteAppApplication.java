package com.jiaheng.scaffold.route.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.jiaheng.scaffold"})
@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.jiaheng.scaffold.**.dao")
@EnableAsync//异步执行
public class RouteAppApplication {
    public static void main(String[] args) {
        System.setProperty("projectName","scaffold-route-app");
        SpringApplication.run(RouteAppApplication.class, args);
    }
}
