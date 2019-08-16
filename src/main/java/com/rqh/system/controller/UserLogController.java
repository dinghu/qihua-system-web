package com.rqh.system.controller;

import com.rqh.system.domain.UserLog;
import com.rqh.system.service.UserLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/userlog",method = RequestMethod.POST)
@Api(value = "UserLogController",description = "用户授权信息")
public class UserLogController {

    @Resource
    UserLogService userLogService;

    @ApiOperation(value = "授权")
    @RequestMapping("/insert")
    @ResponseBody
    @CrossOrigin
    public Boolean insert(@RequestBody UserLog userLog){
        try {
            userLogService.insert(userLog);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @ApiOperation(value = "修改授权")
    @RequestMapping("/updateByPrimaryKey")
    @ResponseBody
    @CrossOrigin
    public Boolean updateByPrimaryKey(@RequestBody UserLog userLog){
        try {
            userLogService.updateByPrimaryKey(userLog);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
