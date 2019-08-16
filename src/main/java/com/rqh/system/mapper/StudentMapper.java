package com.rqh.system.mapper;

import com.rqh.system.domain.Student;
import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Student record);

    Student selectByPrimaryKey(Integer sId);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    List<Student> selectByName(String name);

    Student selectByNid(String nid);
}