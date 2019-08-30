package com.sen.myshop.web.api.dao;

import com.sen.myshop.domain.Content;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/8/11 16:09
 * @Description:
 */
@Repository
public interface ContentDao {
    /**
     * 根据CategoryId查找
     * @param content
     * @return
     */
    List<Content> findByCategoryId(Content content);
}
