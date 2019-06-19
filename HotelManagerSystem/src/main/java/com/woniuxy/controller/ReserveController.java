package com.woniuxy.controller;

import com.woniuxy.service.ReserveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/reserve")
public class ReserveController {
    @Resource
    private ReserveService reserveService;


    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println("测试代码");

        return "测试";
    }

}
