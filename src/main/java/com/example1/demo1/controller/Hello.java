package com.example1.demo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程二狗
 * @since 2019/7/8 0008 11:36
 */
@RestController
public class Hello {
    @RequestMapping("/hello")
    public  String sayHello(){
        return  "hello spring！";
    }
}
