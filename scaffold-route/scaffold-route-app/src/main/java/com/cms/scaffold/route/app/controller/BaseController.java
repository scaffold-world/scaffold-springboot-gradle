package com.cms.scaffold.route.app.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private HttpServletRequest request;



    protected Cookie[] getCookieArray() {
        return request.getCookies();
    }

}
