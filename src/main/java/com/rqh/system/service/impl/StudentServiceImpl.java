package com.rqh.system.service.impl;

import com.rqh.system.domain.Rela;
import com.rqh.system.domain.Student;
import com.rqh.system.domain.Teachers;
import com.rqh.system.domain.Type;
import com.rqh.system.mapper.RelaMapper;
import com.rqh.system.mapper.StudentMapper;
import com.rqh.system.mapper.TeachersMapper;
import com.rqh.system.mapper.TypeMapper;
import com.rqh.system.service.StudentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentMapper studentMapper;
    @Resource
    TeachersMapper teachersMapper;
    @Resource
    RelaMapper relaMapper;
    @Resource
    TypeMapper typeMapper;

    @Override
    public int deleteByPrimaryKey(Integer sId) {
        try {
            studentMapper.deleteByPrimaryKey(sId);
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    @Override
    public int insert(Student record) {
        try {
            if (studentMapper.selectByNid(record.getNid()) == null) {
                studentMapper.insert(record);
            } else {
                studentMapper.updateByPrimaryKey(record);
            }
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    @Override
    public Student selectByPrimaryKey(Integer sId) {
        Student student;
        try {
            student = studentMapper.selectByPrimaryKey(sId);
        } catch (Exception e) {
            throw e;
        }
        return student;
    }

    @Override
    public List<Student> selectByName(String name) {
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = studentMapper.selectByName(name);
        } catch (Exception e) {
            throw e;
        }
        return studentList;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        try {
            studentMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    @Override
    public Student selectByNid(String nid) {
        Student student;
        try {
            student = studentMapper.selectByNid(nid);
        } catch (Exception e) {
            throw e;
        }
        return student;
    }


    @Override
    public JSONArray selectAllByTid(String uuId) {
        JSONArray jsonArray = new JSONArray();
        try {
            Teachers teachers = teachersMapper.selectByUuId(uuId);
            if (teachers != null) {
                List<Rela> relaList = relaMapper.selectByAllSupId(teachers.gettId());
                for (int i = 0; i < relaList.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    if (!relaList.get(i).getType().equals(2)) {
                        Student student = studentMapper.selectByPrimaryKey(relaList.get(i).getSubId());
                        String s1 = typeMapper.selectById(relaList.get(i).getType()).getName();
                        jsonObject.put("name", student.getName());
                        jsonObject.put("graduate", student.getGraduate());
                        jsonObject.put("major", student.getMajor());
                        jsonObject.put("nid", student.getNid());
                        jsonObject.put("phone", student.getPhone());
                        jsonObject.put("sex", student.getSex());
                        jsonObject.put("sup", teachers.getName());
                        jsonObject.put("type", s1);
                        jsonArray.put(jsonObject);
                    } else {
                        JSONArray jsonArray1 = new JSONArray();
                        Teachers teachers1 = teachersMapper.selectByPrimaryKey(relaList.get(i).getSubId());
                        jsonObject.put("name", teachers1.getName());
                        jsonObject.put("workId", teachers1.getWorkId());
                        jsonObject.put("place", teachers1.getPlace());
                        jsonObject.put("nid", teachers1.getNid());
                        jsonObject.put("phone", teachers1.getPhone());
                        jsonObject.put("sex", teachers1.getSex());
                        jsonObject.put("sup", teachers.getName());
                        jsonObject.put("type", "合伙人");
                        List<Rela> relaList1 = relaMapper.selectByAllSupId(teachers1.gettId());
                        for (int j = 0; j < relaList1.size(); j++) {
                            JSONObject jsonObject1 = new JSONObject();
                            if (!relaList1.get(j).getType().equals(2)) {
                                Student student = studentMapper.selectByPrimaryKey(relaList1.get(j).getSubId());
                                String s2 = typeMapper.selectById(relaList.get(j).getType()).getName();
                                jsonObject1.put("name", student.getName());
                                jsonObject1.put("graduate", student.getGraduate());
                                jsonObject1.put("major", student.getMajor());
                                jsonObject1.put("nid", student.getNid());
                                jsonObject1.put("phone", student.getPhone());
                                jsonObject1.put("sex", student.getSex());
                                jsonObject1.put("sup", teachers.getName());
                                jsonObject1.put("type", s2);
                                jsonArray1.put(jsonObject1);
                            } else {
                                JSONArray jsonArray2 = new JSONArray();
                                Teachers teachers2 = teachersMapper.selectByPrimaryKey(relaList1.get(j).getSubId());
                                jsonObject1.put("name", teachers2.getName());
                                jsonObject1.put("workId", teachers2.getWorkId());
                                jsonObject1.put("place", teachers2.getPlace());
                                jsonObject1.put("nid", teachers2.getNid());
                                jsonObject1.put("phone", teachers2.getPhone());
                                jsonObject1.put("sex", teachers2.getSex());
                                jsonObject1.put("sup", teachers1.getName());
                                jsonObject1.put("type", "合伙人");
                                List<Rela> relaList2 = relaMapper.selectByAllSupId(teachers2.gettId());
                                for (int k = 0; k < relaList2.size(); k++) {
                                    JSONObject jsonObject2 = new JSONObject();
                                    if (!relaList2.get(k).getType().equals(2)) {
                                        Student student = studentMapper.selectByPrimaryKey(relaList2.get(k).getSubId());
                                        String s3 = typeMapper.selectById(relaList.get(k).getType()).getName();
                                        jsonObject2.put("name", student.getName());
                                        jsonObject2.put("graduate", student.getGraduate());
                                        jsonObject2.put("major", student.getMajor());
                                        jsonObject2.put("nid", student.getNid());
                                        jsonObject2.put("phone;", student.getPhone());
                                        jsonObject2.put("sex", student.getSex());
                                        jsonObject2.put("sup", teachers.getName());
                                        jsonObject2.put("type", s3);
                                        jsonArray2.put(jsonObject2);
                                    }
                                }
                                jsonObject1.put("sub", jsonArray2);
                                jsonArray1.put(jsonObject1);
                            }
                        }
                        jsonObject.put("sub", jsonArray1);
                        jsonArray.put(jsonObject);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        System.out.println(jsonArray.toString());
        return jsonArray;
    }

    @Override
    public JSONArray selectByTid(String uuId) {
        JSONArray jsonArray = new JSONArray();
        try {
            List<Rela> relaList = new ArrayList<>();
            Teachers teachers = teachersMapper.selectByUuId(uuId);
//            System.out.println(teachers.gettId());
            if (teachers != null) {
                relaList = relaMapper.selectByAllSupId(teachers.gettId());
            }
            for (int i = 0; i < relaList.size(); i++) {
                if (relaList.get(i).getType() != 2) {
                    Student student = studentMapper.selectByPrimaryKey(relaList.get(i).getSubId());
                    Type type = typeMapper.selectById(relaList.get(i).getType());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", student.getName());
                    jsonObject.put("sex", student.getSex());
                    jsonObject.put("nid", student.getNid());
                    jsonObject.put("phone", student.getPhone());
                    jsonObject.put("major", student.getMajor());
                    jsonObject.put("graduate", student.getGraduate());
                    jsonObject.put("type", type.getName());

                    jsonArray.put(jsonObject);
                }

            }
        } catch (Exception e) {
            throw e;
        }
        return jsonArray;
    }
}
