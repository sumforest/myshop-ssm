package com.sen.myshop.web.api.dao;

import com.sen.myshop.domain.TbUser;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 15:10
 * @Description:
 */
public interface TbUserDao {
    /**
     * 根据用户名、手机号或者邮箱登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
