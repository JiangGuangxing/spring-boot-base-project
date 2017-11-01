package com.github.JiangGuangxing.service;

import com.github.JiangGuangxing.model.BaseEntity;
import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Service基础接口
 *
 * @author 姜广兴
 * @since 2017/11/1
 */

public interface BaseService<T extends BaseEntity> {
    void save(T model);//新增

    void saveOrUpdate(T model);//新增或者删除

    void save(List<T> models);//批量新增

    void deleteById(Integer id);//通过主鍵刪除

    void deleteByIds(String ids);//批量刪除 eg：ids -> “1,2,3,4”

    void update(T model);//更新

    T findById(Integer id);//通过ID查找

    T findBy(String fieldName, Object value) throws TooManyResultsException, IllegalAccessException, InstantiationException, NoSuchFieldException; //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束

    List<T> findByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”

    List<T> findByCondition(Condition condition);//根据条件查找

    List<T> findAll();//获取所有
}
