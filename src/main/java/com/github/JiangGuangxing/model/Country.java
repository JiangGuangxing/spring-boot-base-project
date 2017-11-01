package com.github.JiangGuangxing.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @author 姜广兴
 * @since 2017/11/1
 */
public class Country extends BaseEntity {

    @NotEmpty(message = "国家不能为空")
    @Size(min = 2, max = 8, message = "国家长度必须大于 2 且小于 20 字")
    private String countryname;

    @NotEmpty(message = "代码不能为空")
    private String countrycode;

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
}