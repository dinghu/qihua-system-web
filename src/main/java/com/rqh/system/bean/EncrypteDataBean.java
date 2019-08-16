package com.rqh.system.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EncrypteDataBean {
    private  String opendId;
    private  String  nickName;
    private  int gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
    private WatermarkBean watermark;

    public String getOpendId() {
        return opendId;
    }

    public void setOpendId(String opendId) {
        this.opendId = opendId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public WatermarkBean getWatermark() {
        return watermark;
    }

    public void setWatermark(WatermarkBean watermark) {
        this.watermark = watermark;
    }

    @Override
    public String toString() {
        return "EncrypteDataBean{" +
                "opendId='" + opendId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", unionId='" + unionId + '\'' +
                ", watermark=" + watermark +
                '}';
    }
}
