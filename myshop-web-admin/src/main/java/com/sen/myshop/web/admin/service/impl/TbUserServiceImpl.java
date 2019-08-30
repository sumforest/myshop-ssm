package com.sen.myshop.web.admin.service.impl;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.validator.BeanValidator;
import com.sen.myshop.domain.TbUser;
import com.sen.myshop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.sen.myshop.web.admin.dao.TbUserDao;
import com.sen.myshop.web.admin.service.TbUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService {

    /**
     * 登录
     * @param email
     * @param password
     * @return
     */
    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail(email);
        if ( tbUser != null) {
            //明文密码加密
            String md5pass = DigestUtils.md5DigestAsHex(password.getBytes());
            //判断明文密码和查询到用户的密码是否一致
            if ( md5pass.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }

    /**
     * 增加或者修改用户信息
     * @param tbUser
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public BaseResult save(TbUser tbUser) {
        String message = BeanValidator.validator(tbUser);
        if (message != null) {
            return BaseResult.failed(message);
        }else {
            //验证是否有id，若没有就执行插入操作
            if (tbUser.getId() == null) {
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUser.setUpdated(new Date());
                dao.insert(tbUser);
            } else {
                //若有id执行更新操作
                tbUser.setUpdated(new Date());
                dao.update(tbUser);
            }
            return BaseResult.success("操作用户成功");
        }

    }

    /**
     * 用户修改或者添加时的非空验证
     * @param tbUser
     * @return
     */
    // public BaseResult ckeck(TbUser tbUser) {
    //     BaseResult baseResult = BaseResult.success();
    //     if (StringUtils.isBlank(tbUser.getEmail())) {
    //         baseResult = BaseResult.failed("邮箱不能空");
    //     } else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
    //         baseResult = BaseResult.failed("邮箱格式错误");
    //     } else if (StringUtils.isBlank(tbUser.getPassword())) {
    //         baseResult = BaseResult.failed("密码不能为空");
    //     } else if (StringUtils.isBlank(tbUser.getUsername())) {
    //         baseResult = BaseResult.failed("用姓名不能为空");
    //     } else if (StringUtils.isBlank(tbUser.getPhone())) {
    //         baseResult = BaseResult.failed("手机号码不能为空");
    //     } else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
    //         baseResult = BaseResult.failed("手机号码格式错误");
    //     }
    //     return baseResult;
    // }
}
