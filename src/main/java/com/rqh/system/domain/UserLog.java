package com.rqh.system.domain;

import java.util.Date;

public class UserLog {
    private String uUuid;

    private Integer uId;

    private String nick;

    private String head;

    private Date logTime;

    public String getuUuid() {
        return uUuid;
    }

    public void setuUuid(String uUuid) {
        this.uUuid = uUuid == null ? null : uUuid.trim();
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}