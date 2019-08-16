package com.rqh.system.service;

import com.rqh.system.domain.UserLog;
import org.apache.ibatis.annotations.Param;

public interface UserLogService {
    int insert(UserLog record);

    UserLog selectByUuid(@Param("uUuid") String uUuid);

    int updateByPrimaryKey(UserLog record);
}
