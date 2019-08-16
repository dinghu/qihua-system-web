package com.rqh.system.mapper;

import com.rqh.system.domain.Admin;
import java.util.List;

public interface AdminMapper {
    int insert(Admin record);

    Admin selectByAdmin(Admin admin);

    List<Admin> selectAll();
}