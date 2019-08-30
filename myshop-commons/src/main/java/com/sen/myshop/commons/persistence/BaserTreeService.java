package com.sen.myshop.commons.persistence;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.dto.PageInfo;

import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/8/9 19:50
 * @Description:
 */
public interface BaserTreeService<T extends BaseEntity> {
    /**
     * 查询所有
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 增加或者修改用户信息
     *
     * @param entity
     * @return
     */
    BaseResult save(T entity);

    /**
     * 通过ID删除用户
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 通过id查找用户信息
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 根据parentid查找用户信息
     * @param parentId
     * @return
     */
    List<T> findForTree(Long parentId);
}
