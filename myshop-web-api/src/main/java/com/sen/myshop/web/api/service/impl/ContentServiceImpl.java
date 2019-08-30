package com.sen.myshop.web.api.service.impl;

import com.sen.myshop.domain.Content;
import com.sen.myshop.domain.ContentCategory;
import com.sen.myshop.web.api.dao.ContentDao;
import com.sen.myshop.web.api.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/8/11 16:11
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentDao contentDao;
    @Override
    public List<Content> findByCategoryId(Long categoryId) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setId(categoryId);
        Content content = new Content();
        content.setContentCategory(contentCategory);

        return contentDao.findByCategoryId(content);
    }
}
