package com.rqh.system.service.impl;

import com.rqh.system.domain.Rela;
import com.rqh.system.domain.Student;
import com.rqh.system.domain.Teachers;
import com.rqh.system.mapper.RelaMapper;
import com.rqh.system.mapper.StudentMapper;
import com.rqh.system.mapper.TeachersMapper;
import com.rqh.system.service.RelaService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RelaServiceImpl implements RelaService {
    @Resource
    RelaMapper relaMapper;
    @Resource
    TeachersMapper teachersMapper;
    @Resource
    StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        try {
            relaMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    @Override
    public int insert(Rela record) {
        try {
            List<Rela> relaList = relaMapper.selectBySupId(record.getSupId());
            boolean flag = false;
            for (int i = 0; i < relaList.size(); i++) {
                if (relaList.get(i).getSubId().equals(record.getSubId()) && relaList.get(i).getType().equals(1)) {
                    flag = true;
                }
            }
            if (flag == false) {
                record.setInsertdate(new Date());
                relaMapper.insert(record);
            }
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    @Override
    public List<Rela> selectBySupId(Integer supId, Integer type) {
        List<Rela> relaList = new ArrayList<>();
        try {
//            System.out.println(supId);
//            System.out.println(type);
            List<Rela> relas = new ArrayList<>();
            relas = relaMapper.selectBySupId(supId);
            for (int i = 0; i < relas.size(); i++) {
                if (relas.get(i).getType().equals(type)) {
                    relaList.add(relas.get(i));
                }
            }
            System.out.println(relaList.toString());
        } catch (Exception e) {
            throw e;
        }
        return relaList;
    }

    @Override
    public List<Rela> selectByAllSupId(Integer supId) {
        List<Rela> relaList = new ArrayList<>();
        try {
//            System.out.println(supId);
//            System.out.println(type);
            List<Rela> relas = new ArrayList<>();
            relas = relaMapper.selectBySupId(supId);
            for (int i = 0; i < relas.size(); i++) {
                relaList.add(relas.get(i));
            }
            System.out.println(relaList.toString());
        } catch (Exception e) {
            throw e;
        }
        return relaList;
    }

    @Override
    public JSONArray orderInfo(Integer num) {
        JSONArray jsonArray = new JSONArray();
        try {
            List<Rela> relaList = relaMapper.orderDate();
            int x = relaList.size();
            int l = relaList.size() > num ? num : relaList.size();
            for (int i = x - 1; i >= x - l; i--) {
                JSONObject all = new JSONObject();

                Teachers teachers = teachersMapper.selectByPrimaryKey(relaList.get(i).getSupId());
                if (teachers != null) {
                    all.put("teacherName", teachers.getName());
                }
//                if (relaList.get(i).getType().equals(0))
                if (!relaList.get(i).getType().equals(2)) {//合伙人
                    Student student = studentMapper.selectByPrimaryKey(relaList.get(i).getSubId());
                    if (student != null) {
                        all.put("studentName", student.getName());
                    }

                } else {
                    Teachers teachers1 = teachersMapper.selectByPrimaryKey(relaList.get(i).getSubId());
                    if (teachers1 != null) {
                        all.put("studentName", teachers1.getName());
                    }
                }

                all.put("type", relaList.get(i).getType());
                all.put("insertDate", relaList.get(i).getInsertdate());
                jsonArray.put(all);
            }

        } catch (Exception e) {
            throw e;
        }
        return jsonArray;
    }

    @Override
    public Rela selectBySubId(Integer subId) {
        Rela rela = null;
        try {
            relaMapper.selectBySubId(subId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rela;
    }
}
