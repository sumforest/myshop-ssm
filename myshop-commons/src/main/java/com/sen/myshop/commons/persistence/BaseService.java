package com.sen.myshop.commons.persistence;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.dto.PageInfo;

import java.util.List;

/**
 * 业务层的基类
 * @param <T>
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 查询所有
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 增加或者修改用户信息
     *
     * @param tbUser
     * @return
     */
    BaseResult save(T tbUser);

    /**
     * 通过ID删除用户
     * @param id
     */
    void delete(Long id);

    /**
     * 通过id查找用户信息
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMutilServic(String[] ids);

    /**
     * 分页查询
     * @return
     */
    PageInfo<T> findByPage(int start, int length, int draw, T entity);

    /**
     * 查询所有记录数
     * @return
     */
    int getTotalCount(T entity);
}
