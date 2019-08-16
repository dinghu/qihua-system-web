package com.rqh.system.controller;

import com.rqh.system.bean.ResultBean;
import com.rqh.system.domain.Admin;
import com.rqh.system.domain.Rela;
import com.rqh.system.domain.Teachers;
import com.rqh.system.domain.UserLog;
import com.rqh.system.service.AdminService;
import com.rqh.system.service.RelaService;
import com.rqh.system.service.TeachersService;
import com.rqh.system.service.UserLogService;
import com.rqh.system.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.util.TextUtils;
import org.json.JSONArray;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/admin", method = RequestMethod.POST)
@Api(value = "AdminController", description = "管理员")
public class Adminontroller {

    @Resource
    AdminService adminService;
    @Resource
    UserLogService userLogService;

    @ApiOperation(value = "新增管理员")
    @RequestMapping("/insert")
    @ResponseBody
    @CrossOrigin
    public ResultBean insert(@RequestBody Admin admin) {
        Admin admin1 = adminService.selectByAdmin(admin);
        if (admin1 == null) {
            adminService.insert(admin);
        } else {
            return new ResultBean("200", "该账号已存在", false, false);
        }
        return new ResultBean("200", "新增管理员成功", true, true);
    }

    @ApiOperation(value = "管理员登录校验")
    @RequestMapping("/selectByAdmin")
    @ResponseBody
    @CrossOrigin
    public ResultBean selectByAdmin(@RequestBody Admin admin) {
        Admin admin1 = adminService.selectByAdmin(admin);
        if (admin1 == null) {
            return new ResultBean("200", "账号或密码错误", false, false);
        }
        //登录成功后 ；分配uuid；存在则不分配
        if (TextUtils.isBlank(admin1.getUuid())) {
            admin1.setUuid(UUIDUtils.getUUID());
        }

        HashMap<String, String> data = new HashMap<>();
        data.put("uuid", admin1.getUuid());
        return new ResultBean("200", "登陆成功", data, true);
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
