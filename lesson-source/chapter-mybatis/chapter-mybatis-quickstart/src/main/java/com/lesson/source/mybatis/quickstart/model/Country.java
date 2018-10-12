package com.lesson.source.mybatis.quickstart.model;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/10/12.
 */
public class Country{

    private Integer id;

    private String countryname;

    private String countrycode;


    private List<City> cityList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
