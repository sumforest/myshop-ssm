package com.sen.myshop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * dao层的基类
 * @param <T>
 */
public interface BaseDao<T extends BaseEntity> {
    /**
     * 查询全部数据
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     *
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 根据 ID 查询信息
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新
     *
     * @param entity
     */
    void update(T entity);


    /**
     * 根据id数组批量删除用户
     * @param ids
     */
    void deleteMutil(String[] ids);

    /**
     * 分页查询
     * @param map 查询的参数
     * @return
     */
    List<T> findByPage(Map<String, Object> map);

    /**
     * 查询所有记录条数
     * @return 记录条数
     */
    int totalcount(T tbUser);
}
