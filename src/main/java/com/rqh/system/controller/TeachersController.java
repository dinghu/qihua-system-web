package com.rqh.system.controller;

import com.rqh.system.bean.ResultBean;
import com.rqh.system.domain.Rela;
import com.rqh.system.domain.Teachers;
import com.rqh.system.domain.UserLog;
import com.rqh.system.service.RelaService;
import com.rqh.system.service.TeachersService;
import com.rqh.system.service.UserLogService;
import com.rqh.system.service.impl.RelaServiceImpl;
import com.rqh.system.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/teacher", method = RequestMethod.POST)
@Api(value = "TeacherController", description = "招生老师信息")
public class TeachersController {

    @Resource
    TeachersService teacherService;
    @Resource
    UserLogService userLogService;
    @Resource
    RelaService relaService;

    @ApiOperation(value = "新增招生老师")
    @RequestMapping("/insert")
    @ResponseBody
    @CrossOrigin
    public ResultBean insert(@RequestBody Teachers teacher, String invCode) {

        Teachers teachers = teacherService.selectByInvCode(invCode);
        if (teachers == null) {
            return new ResultBean("200", "邀请码不正确！", teacher, false);
        } else {
            UserLog userLog = userLogService.selectByUuid(teacher.gettUuid());
            if (teacherService.selectByUuId(teacher.gettUuid()) == null && teacherService.selectByNid(teacher.getNid()) == null) {
                teacher.setNick(userLog.getNick());
                teacher.setWorkId(UUIDUtils.getUUIDInOrderId());
                teacher.setInvCode(UUIDUtils.generateShortUuid());
                teacherService.insert(teacher);
                return new ResultBean("200", "注册成功！", teacher, true);
            } else {
                return new ResultBean("200", "您已经成功注册了！", teacher, false);
            }
        }
    }


    @ApiOperation(value = "修改招生老师信息")
    @RequestMapping("/updateByPrimaryKey")
    @ResponseBody
    @CrossOrigin
    public Boolean updateByPrimaryKey(@RequestBody Teachers teacher) {
        try {
            teacherService.updateByPrimaryKey(teacher);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @ApiOperation(value = "按招生老师编号查找招生老师信息")
    @RequestMapping("/selectByPrimaryKey")
    @ApiImplicitParam(name = "tId", value = "招生老师编号", required = true)
    @ResponseBody
    @CrossOrigin
    public Teachers selectByPrimaryKey(Integer tId) {
        Teachers teacher;
        try {
            teacher = teacherService.selectByPrimaryKey(tId);
        } catch (Exception e) {
            throw e;
        }
        return teacher;
    }

    @ApiOperation(value = "按招生老师微信编号查找招生老师信息")
    @RequestMapping("/selectByUuId")
    @ApiImplicitParam(name = "uuId", value = "招生老师微信编号", required = true)
    @ResponseBody
    @CrossOrigin
    public ResultBean selectByUuId(String uuId) {
        Teachers teacher;
        teacher = teacherService.selectByUuId(uuId);
        if (teacher != null) {
            return new ResultBean("200", "查找成功", teacher, true);
        } else {
            return new ResultBean("200", "查找失败", teacher, false);
        }
    }

    @ApiOperation(value = "按招生老师姓名查找招生老师信息")
    @RequestMapping("/selectByName")
    @ApiImplicitParam(name = "name", value = "招生老师姓名", required = true)
    @ResponseBody
    @CrossOrigin
    public List<Teachers> selectByPrimaryKey(String name) {
        List<Teachers> teacherList = new ArrayList<>();
        try {
            teacherList = teacherService.selectByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacherList;
    }

    @ApiOperation(value = "按招生老师姓名查找招生老师信息")
    @RequestMapping("/selectByNameAndNid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "招生老师姓名", required = true),
            @ApiImplicitParam(name = "nid", value = "招生老师身份证号码", required = true)
    })
    @ResponseBody
    @CrossOrigin
    public Teachers selectByNameAndNid(String name, String nid) {
        Teachers teacher = null;
        try {
            teacher = teacherService.selectByNameAndNid(name, nid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @ApiOperation(value = "查找我的所有招生老师信息")
    @RequestMapping("/selectByTid")
    @ApiImplicitParam(name = "uuId", value = "我的微信编号", required = true)
    @ResponseBody
    @CrossOrigin
    public List<Teachers> selectByTid(String uuId) {
        List<Teachers> teachersList = new ArrayList<>();
        try {
            List<Rela> relaList = new ArrayList<>();
            Teachers teachers = teacherService.selectByUuId(uuId);
//            System.out.println(teachers.gettId());
            if (teachers != null) {
                relaList = relaService.selectBySupId(teachers.gettId(), 2);
            }
            for (int i = 0; i < relaList.size(); i++) {
                teachersList.add(teacherService.selectByPrimaryKey(relaList.get(i).getSubId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teachersList;
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
