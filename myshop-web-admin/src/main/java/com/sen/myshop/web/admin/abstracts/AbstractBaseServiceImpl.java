package com.sen.myshop.web.admin.abstracts;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.dto.PageInfo;
import com.sen.myshop.commons.persistence.BaseDao;
import com.sen.myshop.commons.persistence.BaseEntity;
import com.sen.myshop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基本Service层实现的抽象
 * @Auther: Sen
 * @Date: 2019/8/9 23:19
 * @Description:
 */
public abstract class AbstractBaseServiceImpl<T extends BaseEntity,D extends BaseDao> implements BaseService<T> {
    @Autowired
    protected D dao;
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }


    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public T getById(Long id) {
        return (T) dao.getById(id);
    }

    @Override
    public void deleteMutilServic(String[] ids) {
        dao.deleteMutil(ids);
    }

    @Override
    public PageInfo<T> findByPage(int start, int length, int draw, T entity) {
        Map<String,Object> map = new HashMap<>();
        map.put("start", start);
        map.put("length", length);
        map.put("pageParam", entity);
        List<T> data = dao.findByPage(map);
        PageInfo<T> pageInfo = new PageInfo<>();
        int count = getTotalCount(entity);
        pageInfo.setDraw(draw);
        pageInfo.setData(data);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setError("");
        return pageInfo;
    }

    @Override
    public int getTotalCount(T entity) {
        return dao.totalcount(entity);
    }
}
