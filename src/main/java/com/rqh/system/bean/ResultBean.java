package com.rqh.system.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description= "响应Model")
public class ResultBean<T> {
    @ApiModelProperty(value = "响应码")
    private String code;

    @ApiModelProperty(value = "响应信息")
    private String msg;

    @ApiModelProperty(value = "响应数据体")
    private T data;

    @ApiModelProperty(value = "响应是否")
    private boolean res;

     public ResultBean(){

     }

     public ResultBean(String code,String msg,T data){
         this.code=code;
         this.msg=msg;
         this.data=data;
     }

    public ResultBean(String code,String msg,T data,boolean res){
        this.code=code;
        this.msg=msg;
        this.data=data;
        this.res=res;
    }
     public ResultBean(T data){
         this.data=data;
     }

     public ResultBean(String code,boolean res){
         this.code = code;
         this.res = res;
     }

    public ResultBean(String code,T data){
        this.code=code;
        this.data=data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }
}
