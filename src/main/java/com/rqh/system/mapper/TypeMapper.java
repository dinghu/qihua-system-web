package com.rqh.system.mapper;

import com.rqh.system.domain.Type;
import java.util.List;

public interface TypeMapper {
    int insert(Type record);

    List<Type> selectAll();

    Type selectById(Integer id);
}