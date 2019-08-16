package com.rqh.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Rela {
    private Integer id;

    private Integer type;

    private Integer supId;

    private Integer subId;

    private Date insertdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSupId() {
        return supId;
    }

    public void setSupId(Integer supId) {
        this.supId = supId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getInsertdate() {
        return insertdate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public void setInsertdate(Date insertdate) {
        this.insertdate = insertdate;
    }
}