package com.sen.myshop.web.admin.service;

import com.sen.myshop.commons.persistence.BaseService;
import com.sen.myshop.domain.Content;

public interface ContentService extends BaseService<Content> {
    void deleteByCategoryId(String[] categoryIds);
}
