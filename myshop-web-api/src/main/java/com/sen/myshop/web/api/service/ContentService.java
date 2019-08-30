package com.sen.myshop.web.api.service;

import com.sen.myshop.domain.Content;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/8/11 16:10
 * @Description:
 */
@Repository
public interface ContentService {
    /**
     * 根据categoryId查找
     * @param categoryId
     * @return
     */
    List<Content> findByCategoryId(Long categoryId);

}
