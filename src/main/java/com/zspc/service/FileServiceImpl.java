package com.zspc.service;

import com.zspc.dao.PersonInfoDao;
import com.zspc.entity.PersonInfoDepartment;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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


    //视频分类，目前支持
    // 性，剧情, 喜剧，动作，爱情，惊悚，犯罪，悬疑，战争，科幻，动画，恐怖，家庭，冒险，专辑，奇幻，武侠，历史，运动，歌舞，音乐，记录，儿童，伦理
    // sex, plot, comedy, action, love, horror, crime, suspense, war, science fiction, animation, horror, family, adventure, album, fantasy, martial arts, history, sports, song and dance, music, record, children, ethics
    private String[] CATEGORIES = {"sex", "plot", "comedy", "action", "love", "horror", "crime", "suspense", "war", "science" ,"fiction", "animation", "horror", "family", "adventure", "album", "fantasy", "martialArts", "history", "sports", "songAndDance", "music", "record", "children", "ethics"};


    @Override
    public String generateDate(Random random,List<String> userList) {

        //视频唯一id，11位字符串
        String context =UUID.randomUUID().toString().replace("-", "").substring(0, 11)+"\t";


        //视频上传的用户名 String
        if(random.nextInt(10) >= 2){
            context += userList.get(random.nextInt(200))+"\t";
        }else {
            context += "U"+UUID.randomUUID().toString().replace("-", "").substring(0, random.nextInt(5)+7)+"\t";
        }

        //视频上传的日期，整数天

        context += random.nextInt(1000)+1+"\t";


        //视频分类  数组形式  xxx & yyy & zzz
        int n = random.nextInt(3);

        for (int i = 1; i<n;i++){
            context += CATEGORIES[random.nextInt(25)]+" & ";
        }
        context += CATEGORIES[random.nextInt(25)]+"\t";

        //视频长度 单位秒

        context += random.nextInt(7200)+"\t";

        //观看次数
        context += random.nextInt(10000)+"\t";


        //视频评分 满分五分 3.34
        context += random.nextInt(4)+1;
        context += "."+random.nextInt(100)+"\t";


        //视频流量
        context += random.nextInt(10000)+"\t";


        //评论数
        context += random.nextInt(10000)+"\t";


        //相关视频id  这个没有办法，只能随机生成了。
        n = random.nextInt(20);
        for (int i = 0 ;i < n; i++){
            context += UUID.randomUUID().toString().replace("-", "").substring(0, 11)+" ";
        }
        return context+"\n";
//        return null;
    }


    /**
     * 维护300个用户
     */
    @Override
    public List<String> generateUser(){
        List<String> userList = new ArrayList<>();
        Random random = new Random();
        int ran = 0;
        String userName;
        for (int i=0;i<300;i++) {
            //生成5-10位的随机数
            ran = random.nextInt(5)+5;
            userName = "U"+UUID.randomUUID().toString().replace("-", "").substring(0, ran);
            userList.add(userName);
        }
        return userList;
    }


    @Override
    public String generateFile(String path, String context) {

        try {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();

            if(context != null && !"".equals(context)) {
                FileOutputStream fos = new FileOutputStream(file,true);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                osw.write(context);
                osw.flush();
                osw.close();
                return "ok";
            }
        }catch (Exception ex){
            logger.info("文件生成失败,抛出异常");
            logger.error(ex.getMessage(),ex);
        }

        return null;
    }





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
