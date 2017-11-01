package com.github.JiangGuangxing.service.impl;

import com.github.JiangGuangxing.core.BaseMapper;
import com.github.JiangGuangxing.model.BaseEntity;
import com.github.JiangGuangxing.service.BaseService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author 姜广兴
 * @since 2017/11/1
 */
public class AbstractBaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    @Autowired
    protected BaseMapper<T> baseMapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractBaseServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T model) {
        baseMapper.insertSelective(model);
    }

    public void saveOrUpdate(T model) {
        if (model.getId() != null)
            baseMapper.updateByPrimaryKey(model);
        else
            baseMapper.insert(model);
    }

    public void save(List<T> models) {
        baseMapper.insertList(models);
    }

    public void deleteById(Integer id) {
        baseMapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(String ids) {
        baseMapper.deleteByIds(ids);
    }

    public void update(T model) {
        baseMapper.updateByPrimaryKeySelective(model);
    }

    public T findById(Integer id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * fieldName不能为id,id在BaseEntity类中，反射会报field不存在错
     *
     * @param fieldName
     * @param value
     * @return
     * @throws TooManyResultsException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        T model = modelClass.newInstance();
        Field field = modelClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(model, value);
        return baseMapper.selectOne(model);
    }

    public List<T> findByIds(String ids) {
        return baseMapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition) {
        return baseMapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return baseMapper.selectAll();
    }
}
