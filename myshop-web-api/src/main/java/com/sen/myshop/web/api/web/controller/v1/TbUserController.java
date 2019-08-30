package com.sen.myshop.web.api.web.controller.v1;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.domain.TbUser;
import com.sen.myshop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 15:25
 * @Description:
 */
@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {
    @Autowired
    private TbUserService service;

    /**
     * 登录（用户名、手机号、邮箱）
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser) {
        BaseResult baseResult = service.login(tbUser);
        return baseResult;
    }

}
