package com.rqh.system.mapper;

import com.rqh.system.domain.UserLog;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLogMapper {

    int insert(UserLog record);

    UserLog selectByUuid(@Param("uUuid") String uUuid);

    int updateByPrimaryKey(UserLog record);
}