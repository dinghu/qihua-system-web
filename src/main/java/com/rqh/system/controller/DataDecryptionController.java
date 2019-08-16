package com.rqh.system.controller;

import com.rqh.system.bean.DecryptionRequestBean;
import com.rqh.system.bean.EncrypteDataBean;
import com.rqh.system.bean.ResultBean;
import com.rqh.system.config.WxConfig;
import com.rqh.system.domain.UserLog;
import com.rqh.system.service.UserLogService;
import com.rqh.system.utils.DataDecryption.DataDecryption;
import com.rqh.system.utils.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


@RestController
@RequestMapping(value = "/dataDecryption",method = RequestMethod.POST)
@Api(value = "DataDecryptionController",description ="信息解密")
public class DataDecryptionController {


    @Resource
    private WxConfig wxConfig;

    @Resource
    private UserLogService userLogService;

    @ApiOperation(value = "获取解密后的用户数据，返回用户信息")
    @RequestMapping("/getData")
    public ResultBean getData(@RequestBody @ApiParam(name = "请求参数",value = "{decryptionRequestBean:人员对象}") DecryptionRequestBean decryptionRequestBean){
        DataDecryption dataDecryption=new DataDecryption(wxConfig.getAppid(),wxConfig.getSecret());
        String sessionKey=dataDecryption.sendGet(decryptionRequestBean.getCode());
        if(sessionKey==null){
            return new ResultBean("200","获取sessionkey失败",false,false);
        }
        String res=dataDecryption.decryptData(decryptionRequestBean.getEncryptedData(),decryptionRequestBean.getIv(),sessionKey);
//        System.out.println(res);
        JSONObject resMap= JSON.parseObject(res);
        if(resMap.get("msg").toString().equals("false")){
            return new ResultBean("200","信息解密失败",false,false);
        }
        String result=resMap.get("userInfo").toString();
        EncrypteDataBean encrypteDataBean= JSONUtil.parse( result,EncrypteDataBean.class);

        UserLog userLog=new UserLog();
         if(userLogService.selectByUuid(encrypteDataBean.getUnionId())!=null){
             UserLog user=userLogService.selectByUuid(encrypteDataBean.getUnionId());
             userLog.setuId(user.getuId());
             userLog.setNick(user.getNick());
             userLog.setHead(user.getHead());
             userLog.setuUuid(user.getuUuid());
             userLog.setLogTime(new Date());
        }else{
             Integer id=0;
             userLog.setuUuid(encrypteDataBean.getUnionId());
             userLog.setLogTime(new Date());
             userLog.setHead(encrypteDataBean.getAvatarUrl());
             userLog.setNick(encrypteDataBean.getNickName());
             userLog.setuId(id);
             userLogService.insert(userLog);
         }

        return new ResultBean("200","信息解密成功",userLog,true);
    }

}
