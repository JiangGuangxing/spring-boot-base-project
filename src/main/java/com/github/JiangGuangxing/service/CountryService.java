package com.github.JiangGuangxing.service;


import com.github.JiangGuangxing.model.Country;

import java.util.List;

/**
 * @author 姜广兴
 * @since 2017/11/1
 */
public interface CountryService extends BaseService<Country> {
    /**
     * 查询列表
     *
     * @param country
     * @return
     */
    List<Country> getList(Country country);

}
