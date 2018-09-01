package com.zspc.service;

import com.zspc.dao.PersonInfoDao;
import com.zspc.entity.PersonInfoDepartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhuansunpengcheng
 * @create 2018-08-31 下午4:25
 **/
@Service
public class FileServiceImpl implements FileService{

    @Autowired
    PersonInfoDao personInfoDao;


    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 每次导入的数量
     */
    private static final Integer SIZE = 700;

    @Override
    public int parsingData(File file) {


        // 解析文件
        BufferedReader br = null;
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            br = new BufferedReader(reader);
            String line = "";
            PersonInfoDepartment personInfoDepartment = new PersonInfoDepartment();
            List<PersonInfoDepartment> list = new ArrayList<>();
            long i = 1L;
            while ((line = br.readLine()) != null) {
                try {
                    String[] args = line.split(",");

                    //依据个人数据需求，对数据进行过滤
                    if (args.length != 33){
                        continue;
                    }

                    String name = args[0].trim();

                    if (!checkname(name)){

                        continue;
                    }

                    if (args[5].length() > 2){
                        continue;
                    }

                    if (args[8].length() > 6){
                        continue;
                    }

                    if (args[6].length() > 9){
                        continue;
                    }

                    if (args[22].length() > 30){
                        continue;
                    }
                    personInfoDepartment = new PersonInfoDepartment();
                    //姓名
                    personInfoDepartment.setName(name);
                    //证件类型
                    personInfoDepartment.setCtftp(args[3]);
                    //证件号
                    personInfoDepartment.setCtfid(args[4]);
                    //性别
                    personInfoDepartment.setGender(args[5]);
                    //生日
                    personInfoDepartment.setBirthday(args[6]);
                    //地址
                    personInfoDepartment.setAddress(args[7]);
                    //邮编
                    personInfoDepartment.setZip(args[8]);
                    //手机号
                    personInfoDepartment.setMobile(args[19]);
                    //电话
                    personInfoDepartment.setTel(args[20]);
                    //传真
                    personInfoDepartment.setFax(args[21]);
                    //邮箱
                    personInfoDepartment.setEmail(args[22]);
                    //入住时间
                    personInfoDepartment.setVersion(args[31]);


                    list.add(personInfoDepartment);

                    if (list.size() >= SIZE){
                        //批量导入
                        int count = personInfoDao.batchInsert(list);
                        logger.info("---------------------------------------------->"+i);
                        i++;
                        list.clear();
                    }
                }catch (Exception ex){
                    logger.error("发生异常",ex);
                }
            }

            //while循环结束之后，list中可能会有残留数据 < SIZE 的。在这里处理。
            personInfoDao.batchInsert(list);

        } catch (Exception e) {
            return -1;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                return -1;
            }
            // 删除临时文件
            if (file.isFile()) {
                file.delete();
            }

        }
        return 1;
    }


    /**
     * 匹配汉字（全为汉字）
     * @param name
     * @return
     */
    public boolean checkname(String name) {
        int n = 0;
        for(int i = 0; i < name.length(); i++) {
            n = (int)name.charAt(i);
            if(!(19968 <= n && n <40869)) {
//                logger.info("----->"+name+"-----");
                return false;
            }
        }
        return true;
    }


    /**
     * 匹配汉字（包含汉字）
     * @param countname
     * @return
     */
    public boolean checkcountname(String countname) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(countname);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
