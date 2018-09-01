package com.zspc.mapper;


import com.zspc.entity.PersonInfoDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonInfoDepartmentMapper {
    int insert(PersonInfoDepartment record);


    int batchInsert(@Param("list") List<PersonInfoDepartment> list);
}