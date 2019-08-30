package com.sen.myshop.web.api.service.impl;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.domain.TbUser;
import com.sen.myshop.web.api.dao.TbUserDao;
import com.sen.myshop.web.api.service.TbUserService;
import com.sen.myshop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 15:17
 * @Description:
 */
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public BaseResult login(TbUser tbUser) {
        TbUser loginUser = tbUserDao.login(tbUser);
        if (loginUser == null) {
            return BaseResult.failed("用户名或密码错误");
        } else {
            String md5Pass = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if (md5Pass.equals(loginUser.getPassword())) {
                TbUserDTO tbUserDTO = new TbUserDTO();
                BeanUtils.copyProperties(loginUser,tbUserDTO);
                return BaseResult.failed("登陆成功", tbUserDTO);
            }
            return BaseResult.failed("用户名或密码错误");
        }
    }
}
