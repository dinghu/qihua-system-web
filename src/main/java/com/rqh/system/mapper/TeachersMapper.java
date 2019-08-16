package com.rqh.system.mapper;

import com.rqh.system.domain.Teachers;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeachersMapper {
    int deleteByPrimaryKey(@Param("tId") Integer tId, @Param("tUuid") String tUuid);

    int insert(Teachers record);

    Teachers selectByPrimaryKey(@Param("tId") Integer tId);

    List<Teachers> selectByName(String name);
    int updateByPrimaryKey(Teachers record);

    Teachers selectByUuId(@Param("tUuid") String uuId);

    Teachers selectByNid(String nid);

    Teachers selectByInvCode(String invCode);
}