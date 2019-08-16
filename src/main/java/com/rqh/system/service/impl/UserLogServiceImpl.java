package com.rqh.system.service.impl;

import com.rqh.system.domain.UserLog;
import com.rqh.system.mapper.UserLogMapper;
import com.rqh.system.service.UserLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Resource
    UserLogMapper userLogMapper;
    @Override
    public int insert(UserLog record) {
        try {
            if(userLogMapper.selectByUuid(record.getuUuid()) ==null) {
                userLogMapper.insert(record);
            }else{
                userLogMapper.updateByPrimaryKey(record);
            }
        }catch (Exception e){
            throw e;
        }
        return 0;
    }


    @Override
    public UserLog selectByUuid(String uuid) {
        UserLog userLog;
        try {
            userLog = userLogMapper.selectByUuid(uuid);
        }catch (Exception e){
            throw e;
        }
        return userLog;
    }

    @Override
    public int updateByPrimaryKey(UserLog record) {
        try{
            userLogMapper.updateByPrimaryKey(record);
        }catch (Exception e){
            throw e;
        }
        return 0;
    }


}
