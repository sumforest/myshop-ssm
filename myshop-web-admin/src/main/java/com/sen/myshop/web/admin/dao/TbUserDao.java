package com.sen.myshop.web.admin.dao;

import com.sen.myshop.commons.persistence.BaseDao;
import com.sen.myshop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    /**
     * 根据邮箱查询用户信息
     *  登录方法
     * @param email
     * @return
     */
    TbUser getByEmail(String email);
}
