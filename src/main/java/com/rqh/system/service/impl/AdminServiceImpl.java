package com.rqh.system.service.impl;

import com.rqh.system.domain.Admin;
import com.rqh.system.mapper.AdminMapper;
import com.rqh.system.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;

    @Override
    public int updateByPrimaryKey(Admin admin) {
        try {
            adminMapper.updateByPrimaryKey(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    @Override
    public int insert(Admin record) {
        try {
            adminMapper.insert(record);
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    @Override
    public Admin selectByAdmin(Admin admin) {
        Admin admin1;
        try {
            admin1 = adminMapper.selectByAdmin(admin);
        } catch (Exception e) {
            throw e;
        }
        return admin1;
    }

    @Override
    public List<Admin> selectAll() {
        List<Admin> adminList = new ArrayList<>();
        try {
            adminList = adminMapper.selectAll();
        } catch (Exception e) {
            throw e;
        }
        return adminList;
    }
}
