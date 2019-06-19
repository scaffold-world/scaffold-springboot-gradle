package com.cms.scaffold.route.operate.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/3/16.
 */
@Controller
@Api(tags = "HomeController", description = "主页跳转")
public class HomeController extends BaseController{

    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }
}
