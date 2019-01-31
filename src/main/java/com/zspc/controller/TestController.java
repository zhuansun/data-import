package com.zspc.controller;


import com.zspc.dao.PersonInfoDao;
import com.zspc.entity.PersonInfoDepartment;
import com.zspc.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * 测试专用controller
 *
 * @author zhuansunpengcheng
 * @create 2018-04-09 上午10:16
 **/

@RestController
public class TestController {


    Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    FileService fileService;

    @RequestMapping("/ok")
    @ResponseBody
    public String ok1() throws Exception {
        logger.info("aaa");
        return "ok";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public String ok(@RequestParam(value = "files") MultipartFile files, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String sourceName = files.getOriginalFilename(); // 原始文件名
        String fileType = sourceName.substring(sourceName.lastIndexOf("."));
        if (files.isEmpty() || StringUtils.isBlank(fileType)) {
            return "-1";
        }
        if (!".txt".equals(fileType.toLowerCase()) && !".csv".equals(fileType.toLowerCase())) {
            return "-1";
        }

        // 存放文件临时路径
        String base = request.getSession().getServletContext().getRealPath("/upload//");  //获取文件上传的路径，在webapp下的upload中
        File file = new File(base);
        if (!file.exists()) {
            file.mkdirs();
        }

        // 讲文件上传到临时目录
        String path = base + File.separator + sourceName;
        File upload = new File(path);
        try {
            files.transferTo(upload);
        } catch (IOException e) {
            return "-1";
        }


        try {

            System.out.println(upload.getName() + "---" + upload.length());
            fileService.parsingData(upload);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        logger.info("okokokokokokokokokokokokokokokokokokokokokokokokokokokokokok");
        return "okokokokokokokokokok";
    }


    @RequestMapping("/generate")
    @ResponseBody
    public String ok1(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 存放文件临时路径//获取文件上传的路径，在webapp下的upload中
        String base = request.getSession().getServletContext().getRealPath("/upload/0.txt");


        String context = null;


        Random random= new Random();
        List<String> userList = fileService.generateUser();

        for (int i=0; i< 10;i++){
            context = fileService.generateDate(random,userList);
            fileService.generateFile(base, context);

        }

//        File file = new File(base);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//
//        // 讲文件上传到临时目录
//        String path = base + File.separator + sourceName;
//        File upload = new File(path);
//        try {
//            files.transferTo(upload);
//        } catch (IOException e) {
//            return "-1";
//        }
//
//
//        try {
//
//            System.out.println(upload.getName()+"---"+upload.length());
//            fileService.parsingData(upload);
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }

        logger.info("okokokokokokokokokokokokokokokokokokokokokokokokokokokokokok");
        return "okokokokokokokokokok";
    }


}
