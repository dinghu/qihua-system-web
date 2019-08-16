package com.rqh.system.service.impl;

import com.rqh.system.domain.Teachers;
import com.rqh.system.mapper.TeachersMapper;
import com.rqh.system.service.TeachersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeachersServiceImpl implements TeachersService {
    @Resource
    TeachersMapper teacherMapper;
    @Override
    public int deleteByPrimaryKey(Integer tId, String tUuid) {
        try{
            teacherMapper.deleteByPrimaryKey(tId,tUuid);
        }catch (Exception e){
            throw e;
        }
        return 0;
    }

    @Override
    public int insert(Teachers record) {
        try{
            if(teacherMapper.selectByUuId(record.gettUuid()) ==null){
            teacherMapper.insert(record);
            }else{
                teacherMapper.updateByPrimaryKey(record);
            }
        }catch (Exception e){
            throw e;
        }
        return 0;
    }

    @Override
    public Teachers selectByPrimaryKey(Integer tId) {
        Teachers teacher;
        try{
            teacher = teacherMapper.selectByPrimaryKey(tId);
        }catch (Exception e){
            throw e;
        }
        return teacher;
    }

    @Override
    public int updateByPrimaryKey(Teachers record) {
        try{
            teacherMapper.updateByPrimaryKey(record);
        }catch (Exception e){
            throw e;
        }
        return 0;
    }

    @Override
    public List<Teachers> selectByName(String name) {
        List<Teachers> teacherList = new ArrayList <>();
        try{
            teacherList = teacherMapper.selectByName(name);
        }catch (Exception e){
            throw e;
        }
        return teacherList;
    }

    @Override
    public Teachers selectByUuId(String uuId) {
        Teachers teacher;
        try {
            teacher = teacherMapper.selectByUuId(uuId);
        }catch (Exception e){
            throw e;
        }
        return teacher;
    }

    @Override
    public Teachers selectByNid(String nid) {
        Teachers teachers;
        try {
            teachers = teacherMapper.selectByNid(nid);

        }catch (Exception e){
            throw e;
        }
        return teachers;
    }

    @Override
    public Teachers selectByInvCode(String invCode) {
        Teachers teachers;
        try {
            teachers = teacherMapper.selectByInvCode(invCode);
        }catch (Exception e){
           throw e;
        }
        return teachers;
    }
}
