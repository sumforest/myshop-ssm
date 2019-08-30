package com.sen.myshop.web.admin.service;

import com.sen.myshop.commons.persistence.BaseService;
import com.sen.myshop.domain.TbUser;

public interface TbUserService extends BaseService<TbUser> {
    /**
     * 用户登录
     *
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);

}
