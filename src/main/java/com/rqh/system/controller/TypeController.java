package com.rqh.system.controller;

import com.rqh.system.domain.Type;
import com.rqh.system.service.TypeService;
import io.swagger.annotations.Api;
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
@RequestMapping(value = "/type",method = RequestMethod.POST)
@Api(value = "RelaController",description = "类型")
public class TypeController {
    @Resource
    TypeService typeService;

    @ApiOperation(value = "人员类型")
    @RequestMapping("/perType")
    @ResponseBody
    @CrossOrigin
    public List<Type> perType(){
        List<Type> typeList = new ArrayList <>();
        try {
            typeList = typeService.selectAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return typeList;
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
