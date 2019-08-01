package com.van.demo.controller;

import com.van.demo.dao.mapper.TestMapper;
import com.van.demo.dto.Name;
import com.van.demo.util.QrCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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



    @RequestMapping(value = "/png",produces = MediaType.IMAGE_JPEG_VALUE)
    public Object aaa() throws IOException {
        File file = ResourceUtils.getFile("file:src/main/resources/static/refund.png");
        boolean exists = file.exists();
        boolean b = file.canRead();
        boolean b1 = file.canWrite();
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[is.available()];
        is.read(bytes, 0, is.available());
        return bytes;
    }

    /**
     * 二维码
     * @param request
     * @param response
     */
    @RequestMapping("/qrcode")
    public void qrcode(HttpServletRequest request, HttpServletResponse response) {
        String requestUrl = "http://www.sina.com";
        try {
            OutputStream os = response.getOutputStream();
            QrCodeUtils.encode(requestUrl, null, os, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
