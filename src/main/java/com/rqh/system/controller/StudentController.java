package com.rqh.system.controller;

import com.rqh.system.bean.ResultBean;
import com.rqh.system.domain.Rela;
import com.rqh.system.domain.Student;
import com.rqh.system.domain.Teachers;
import com.rqh.system.service.RelaService;
import com.rqh.system.service.StudentService;
import com.rqh.system.service.TeachersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
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
@RequestMapping(value = "/student",method = RequestMethod.POST)
@Api(value = "UserLogController",description = "学生信息")
public class StudentController {

    @Resource
    StudentService studentService;
    @Resource
    RelaService relaService;
    @Resource
    TeachersService teachersService;

    @ApiOperation(value = "新增学生信息")
    @RequestMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuId",value = "招生老师微信编号",required = true),
            @ApiImplicitParam(name = "typeId",value = "类型编号",required = true)
    })
    @ResponseBody
    @CrossOrigin
    public ResultBean insert(@RequestBody Student student, String uuId,Integer typeId){
            Teachers teachers = teachersService.selectByUuId(uuId);
            Rela rela = new Rela();
            if(teachers!=null){
                rela.setSupId(teachers.gettId());
            }else {
                return new ResultBean("200", "您尚未注册！", false);
            }
            Student student1 = studentService.selectByNid(student.getNid());
//            System.out.println(student1);
            if(student1==null) {
                studentService.insert(student);
                Student student2 = studentService.selectByNid(student.getNid());
                rela.setSubId(student2.getsId());
                rela.setType(typeId);
                relaService.insert(rela);
            }else{
                return  new ResultBean("200","此人已被招入！",false);
            }
            return  new ResultBean("200","新增成功！",true);


    }


    @ApiOperation(value = "删除学生信息")
    @RequestMapping("/deleteByPrimaryKey")
    @ApiImplicitParam(name = "sId",value = "学生编号",required = true)
    @ResponseBody
    @CrossOrigin
    public Boolean deleteByPrimaryKey(Integer sId){
        try {
           studentService.deleteByPrimaryKey(sId);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @ApiOperation(value = "查找我的所有学生信息")
    @RequestMapping("/selectByTid")
    @ApiImplicitParam(name = "uuId",value = "招生老师微信编号",required = true)
    @ResponseBody
    @CrossOrigin
    public String selectByTid(String uuId){
        JSONArray jsonArray= new JSONArray();
        try {
            jsonArray = studentService.selectByTid(uuId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    @ApiOperation(value = "查找我以及我所有招生老师的学生信息")
    @RequestMapping("/selectAllByTid")
    @ApiImplicitParam(name = "uuId",value = "招生老师微信编号",required = true)
    @ResponseBody
    @CrossOrigin
    public String selectAllByTid(String uuId){
        JSONArray jsonArray= new JSONArray();
        try {
            jsonArray = studentService.selectAllByTid(uuId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    @ApiOperation(value = "按学生姓名查找学生信息")
    @RequestMapping("/selectByName")
    @ApiImplicitParam(name = "name",value = "学生姓名",required = true)
    @ResponseBody
    @CrossOrigin
    public List<Student> selectByPrimaryKey(String name) {
        List<Student> studentList = new ArrayList <>();
        try{
            studentList = studentService.selectByName(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return studentList;
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
