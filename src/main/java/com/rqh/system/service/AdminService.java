package com.rqh.system.service;

import com.rqh.system.domain.Admin;

import java.util.List;

public interface AdminService {
    int insert(Admin record);

    Admin selectByAdmin(Admin admin);

    List<Admin> selectAll();
}
