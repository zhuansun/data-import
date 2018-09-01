package com.zspc.dao;


import com.zspc.entity.PersonInfoDepartment;

import java.util.List;

/**
 * @author zhuansunpengcheng
 * @create 2018-08-30 下午7:40
 **/
public interface PersonInfoDao {

    void insert(PersonInfoDepartment record);



    int batchInsert(List<PersonInfoDepartment> list);
}
