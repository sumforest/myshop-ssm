package com.sen.myshop.web.admin.dao;

import com.sen.myshop.commons.persistence.BaseDao;
import com.sen.myshop.domain.Content;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDao extends BaseDao<Content> {
    /**
     * 批量删除
     * @param categoryIds
     */
    void deleteByCategoryId(String[] categoryIds);
}
