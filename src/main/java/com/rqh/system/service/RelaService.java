package com.rqh.system.service;

import com.rqh.system.domain.Rela;
import org.json.JSONArray;

import java.util.List;

public interface RelaService {
    int deleteByPrimaryKey(Integer id);

    int insert(Rela record);

    List<Rela> selectBySupId(Integer supId,Integer type);

    List<Rela> selectByAllSupId(Integer supId);

    JSONArray orderInfo(Integer num);

    Rela selectBySubId(Integer subId);
}
