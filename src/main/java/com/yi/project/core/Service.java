package com.yi.project.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Service 层基础接口,其他Service 接口 需要继承该接口
 * @author YI
 * @date 2018-6-27 16:11:01
 */
public interface Service<T> {
    /**
     * 保存
     * @param model
     */
    void save(T model);

    /**
     * 批量保存
     * @param models
     */
    void save(List<T> models);

    /**
     * 通过主键删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 通过质检批量刪除 eg：ids -> “1,2,3,4”
     * @param ids
     */
    void deleteByIds(String ids);

    /**
     * 根据条件删除
     * @param condition Example
     */
    void deleteByExample(Condition condition);

    /**
     * 更新
     * @param model
     */
    void update(T model);

    /**
     * 根据条件更新
     * @param model JavaBean对象
     * @param condition Example
     */
    void updateByExampleSelective(T model, Condition condition);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 通过某个成员属性查找,value需符合unique约束
     * @param property
     * @param value
     * @return
     * @throws TooManyResultsException
     */
    T findBy(String property, Object value) throws TooManyResultsException;

    /**
     * 通过多个ID查找//eg：ids -> “1,2,3,4”
     * @param ids
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据条件查找
     * @param condition Example
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 自定义查询
     * @param condition Example
     * @return
     */
    List<T> findByExample(Condition condition);

    /**
     * 获取所有数据
     * @return
     */
    List<T> findAll();
}
