package com.sen.myshop.commons.persistence;

import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/8/9 19:42
 * @Description:
 */
public interface BaseTreeDao<T extends BaseEntity> {
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
     * @param ids
     */
    void deleteMulti(String[] ids);

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
     * 根据父id查找
     * @param parentId
     * @return
     */
    List<T> findForTree(Long parentId);

}
