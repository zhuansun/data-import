package com.zspc.service;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * @author zhuansunpengcheng
 * @create 2018-08-31 下午4:24
 **/
public interface FileService {


    int parsingData(File file);


    /**
     * 生成符合格式的数据，并返回
     * @return
     */
    String generateDate(Random random, List<String> userList);


    /**
     * 在本地生成文件
     * @param path 文件的路径
     * @param context 文件内容
     * @return
     */
    String generateFile(String path,String context);



    List<String> generateUser();
}
