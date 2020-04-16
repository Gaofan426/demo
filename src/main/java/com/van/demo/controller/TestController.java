package com.van.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.van.demo.dao.TestMapper;
import com.van.demo.dao.entity.Name;
import com.van.demo.dao.service.INameService;
import com.van.demo.util.QrCodeUtils;
import com.van.demo.util.ZipUtil;
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
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("test")
public class TestController {

    @Value("${jump.url}")
    private String url;

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private INameService iNameService;

    @GetMapping("url")
    public Object getUrl() {
        return "you need to skip to : " + url;
    }

    @GetMapping("name")
    public Object getNameList() {

        Page<Name> namePage = iNameService.selectPage(new Page<>(2, 5), new EntityWrapper<>());

//        PageHelper.startPage(1,10);
//        List<Name> nameList = testMapper.getNameList();
        return namePage;
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

    /**
     * 获取静态资源
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/png", produces = MediaType.IMAGE_JPEG_VALUE)
    public Object getPng() throws IOException {
        File file = ResourceUtils.getFile("file:src/main/resources/static/refund.png");
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[is.available()];
        is.read(bytes, 0, is.available());
        return bytes;
    }

    /**
     * 生成二维码
     *
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

    /**
     * 压缩成zip
     *
     * @throws IOException
     */
    @GetMapping("/zipFile")
    public void zipFile() throws IOException {
        File outFile = ResourceUtils.getFile("file:src/main/resources/static/refund.zip");
        FileOutputStream fos = new FileOutputStream(outFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = ResourceUtils.getFile("file:src/main/resources/static/refund.png");

        ZipUtil.zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
    }

    @GetMapping("builder")
    public Object testBuilder() {
        com.van.demo.dto.Name van = com.van.demo.dto.Name.builder().id(2).name("van").build();
        van.toBuilder().id(3).name("v").build();
        return van;
    }
}
