package com.van.demo.controller;

import com.van.demo.dao.mapper.TestMapper;
import com.van.demo.dto.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Value("${jump.url}")
    private String url;

    @Autowired
    private TestMapper testMapper;

    @GetMapping("url")
    public Object getUrl() {
        return "you need to skip to " + url;
    }

    @GetMapping("name")
    public Object getNameList() {
        List<Name> nameList = testMapper.getNameList();
        return nameList;
    }

    @GetMapping("time")
    public Object getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "当前时间：" + sdf.format(new Date());
    }

    @PostMapping
    public Object insertName(@RequestParam(value = "name") String name) {
        testMapper.insertName(name);
        return getNameList();
    }
}
