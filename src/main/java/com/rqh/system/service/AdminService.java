package com.rqh.system.service;

import com.rqh.system.domain.Admin;
import com.rqh.system.domain.UserLog;

import java.util.List;

public interface AdminService {
    int insert(Admin record);

    Admin selectByAdmin(Admin admin);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin admin);
}
