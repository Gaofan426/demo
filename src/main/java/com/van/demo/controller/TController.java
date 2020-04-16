package com.van.demo.controller;

import com.van.demo.annotation.Ignore;
import com.van.demo.annotation.Na;
import com.van.demo.dao.entity.Name;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("T")
public class TController {

    @RequestMapping("/test")
    public Object testAnnotation(@Na Name name){
        return name;
    }

    @RequestMapping("/test1")
    public Object testAnnotation1(){
        return "1111111";
    }

    @Ignore
    @RequestMapping("/test2")
    public Object testAnnotation2(){
        return "2222222";
    }
}
