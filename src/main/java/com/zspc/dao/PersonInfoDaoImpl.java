package com.zspc.dao;

import com.zspc.entity.PersonInfoDepartment;
import com.zspc.mapper.PersonInfoDepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhuansunpengcheng
 * @create 2018-08-31 上午10:59
 **/

@Repository
public class PersonInfoDaoImpl implements PersonInfoDao{


    @Autowired
    PersonInfoDepartmentMapper personInfoDepartmentMapper;


    @Override
    public void insert(PersonInfoDepartment record) {
        personInfoDepartmentMapper.insert(record);
    }


    @Override
    public int batchInsert(List<PersonInfoDepartment> list) {
        return personInfoDepartmentMapper.batchInsert(list);
    }
}
