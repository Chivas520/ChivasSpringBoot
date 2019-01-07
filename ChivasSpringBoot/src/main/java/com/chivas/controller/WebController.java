package com.chivas.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WebController {

    @RequestMapping("/index")
    public String index(final HttpServletRequest request){
        return "WebController！";
    }
}
