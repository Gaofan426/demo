package com.van.demo.controller;

import com.van.demo.annotation.My;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("my")
public class MyController {

    @My(value = "调用controller")
    @RequestMapping("/my")
    public void testAnnotation(){
        System.out.println("1111");
    }
}
