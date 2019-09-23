package com.rqh.system.service;

import com.rqh.system.domain.Teachers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachersService {
    int deleteByPrimaryKey(@Param("tId") Integer tId, @Param("tUuid") String tUuid);

    int insert(Teachers record);

    Teachers selectByPrimaryKey(@Param("tId") Integer tId);

    int updateByPrimaryKey(Teachers record);

    List<Teachers> selectByName(String name);

    Teachers selectByNameAndNid(String name, String nid);

    Teachers selectByUuId(@Param("tUuId") String uuId);

    Teachers selectByNid(String nid);

    Teachers selectByInvCode(String invCode);
}
