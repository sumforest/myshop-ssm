package com.sen.myshop.web.admin.abstracts;

import com.sen.myshop.commons.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**通用的Tree service层业务实现
 * @Auther: Sen
 * @Date: 2019/8/9 22:07
 * @Description:
 */

public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity,D extends BaseTreeDao<T>> implements BaserTreeService<T> {
    @Autowired
    protected D dao;
    /**
     * 查询全部数据
     *
     * @return
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }
    /**
     * 新增
     *
     * @param entity
     */
    public void insert(T entity){
        dao.insert(entity);
    }

    /**
     * 删除
     *
     * @param ids
     */
    @Override
    public void deleteMulti(String[] ids){
        dao.deleteMulti(ids);
    }

    /**
     * 根据 ID 查询信息
     *
     * @param id
     * @return
     */
    @Override
    public T getById(Long id){
        return dao.getById(id);
    }

    /**
     * 更新
     *
     * @param entity
     */
    public void update(T entity){
        dao.update(entity);
    }

    /**
     * 根据父id查找
     * @param parentId
     * @return
     */
    @Override
    public List<T> findForTree(Long parentId){
         return dao.findForTree(parentId);
     }

}
