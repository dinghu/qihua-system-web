package com.rqh.system.service;

import com.rqh.system.domain.Student;
import org.json.JSONArray;

import java.util.List;

public interface StudentService {
    int deleteByPrimaryKey(Integer sId);

    int insert(Student record);

    Student selectByPrimaryKey(Integer sId);

    List<Student> selectByName(String name);

    int updateByPrimaryKey(Student record);

    Student selectByNid(String nid);

    JSONArray selectAllByTid(String uuId);

    JSONArray selectByTid(String uuId);


}
