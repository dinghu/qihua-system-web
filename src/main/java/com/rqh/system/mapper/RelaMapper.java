package com.rqh.system.mapper;

import com.rqh.system.domain.Rela;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rela record);

    List<Rela> selectBySupId(Integer supId);

    List<Rela> selectByAllSupId(Integer supId);

    List<Rela> orderDate();

    Rela  selectByPidAndSubId(@Param("supId")Integer supId,@Param("subId") Integer subId);
}