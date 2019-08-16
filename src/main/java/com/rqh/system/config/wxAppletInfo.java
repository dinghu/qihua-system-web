package com.rqh.system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: wxAppletInfo
 * @Auther: 王振科
 * @Date: 2019/1/9 10:51
 * @Version: 1.0
 * @Description:
 */
@Component
@ConfigurationProperties(prefix ="wx.after")
public class wxAppletInfo {
    private  String  appid;
    private  String secret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "wxAppletInfo{" +
                "appid='" + appid + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
