package com.example.a12306test.bean;

/**
 * Created by 昭阳 on 2019/4/16.
 */
public class CityBean {
    private  String citycode;
    private  String cityName;

    public CityBean(String citycode, String cityName) {
        this.citycode = citycode;
        this.cityName = cityName;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "citycode='" + citycode + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
