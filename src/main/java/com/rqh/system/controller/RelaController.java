package com.rqh.system.controller;

import com.rqh.system.bean.ResultBean;
import com.rqh.system.domain.Rela;
import com.rqh.system.domain.Teachers;
import com.rqh.system.service.RelaService;
import com.rqh.system.service.TeachersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.expr.NewArray;
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
@RequestMapping(value = "/rela",method = RequestMethod.POST)
@Api(value = "RelaController",description = "二级关系")
public class RelaController {

    @Resource
    RelaService relaService;
    @Resource
    TeachersService teachersService;

    @ApiOperation(value = "新增二级关系")
    @RequestMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid",value = "我的微信编号",required = true),
            @ApiImplicitParam(name = "nid",value = "身份证号码",required = true)
    })
    @ResponseBody
    @CrossOrigin
    public ResultBean insert(String uuid, String nid){
            Rela rela = new Rela();
            Teachers teachers = teachersService.selectByUuId(uuid);
            Teachers teachers1 = teachersService.selectByNid(nid);
            if(teachers!=null){
                rela.setSupId(teachers.gettId());
            }else {
                return  new ResultBean("200","您尚未注册！",false,false);
            }
            if(teachers1 != null){
                if(teachers1.gettUuid().equals(uuid)){
                    return  new ResultBean("200","您不能添加自己为合伙人！",false,false);
                }else {
                    rela.setSubId(teachers1.gettId());
                    rela.setSupId(teachers.gettId());
                    rela.setType(2);
                    rela.setInsertdate(new Date());
                    relaService.insert(rela);
                    return new ResultBean("200", "新增成功", true, true);
                }
            }
            else{
                return new ResultBean("200","该招生老师未注册！",false,false);
            }
    }


    @ApiOperation(value = "删除二级关系")
    @RequestMapping("/updateByPrimaryKey")
    @ApiImplicitParam(name = "id",value = "关系序号",required = true)
    @ResponseBody
    @CrossOrigin
    public Boolean deleteByPrimaryKey(Integer id){
        try {
            relaService.deleteByPrimaryKey(id);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @ApiOperation(value = "查找我的招生老师或我的学生")
    @RequestMapping("/selectBySupId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuId",value = "上级招生老师微信编号",required = true),
            @ApiImplicitParam(name = "type",value = "类型",required = true)
    })
    @ResponseBody
    @CrossOrigin
    public List<Rela> selectBySupId(String uuId,Integer type) {
        List<Rela> relaList = new ArrayList <>();
        try{
            Teachers teachers = teachersService.selectByUuId(uuId);
//            System.out.println(teachers.gettId());
            if(teachers!=null) {
                relaList = relaService.selectBySupId(teachers.gettId(), type);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return relaList;
    }
    @ApiOperation(value = "最新消息")
    @RequestMapping("/orderInfo")
    @ResponseBody
    @CrossOrigin
    public String orderInfo(Integer num){
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray= relaService.orderInfo(num);

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
