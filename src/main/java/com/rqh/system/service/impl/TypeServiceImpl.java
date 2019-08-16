package com.rqh.system.service.impl;


import com.rqh.system.domain.Type;
import com.rqh.system.mapper.TypeMapper;
import com.rqh.system.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    TypeMapper typeMapper;

    @Override
    public List<Type> selectAll() {
        List<Type> typeList = new ArrayList <>();
        try{
            typeList = typeMapper.selectAll();
        }catch (Exception e){
            throw e;
        }
        return typeList;
    }
}
