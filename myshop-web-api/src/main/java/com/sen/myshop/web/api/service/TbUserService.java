package com.sen.myshop.web.api.service;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.domain.TbUser;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 15:16
 * @Description:
 */
public interface TbUserService {
    /**
     * 登录
     * @param tbUser
     * @return
     */
    BaseResult login(TbUser tbUser);
}
