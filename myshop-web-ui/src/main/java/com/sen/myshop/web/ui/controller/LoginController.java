package com.sen.myshop.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.utils.EmailSendUtils;
import com.sen.myshop.web.ui.api.UserApi;
import com.sen.myshop.web.ui.constant.SystemConstant;
import com.sen.myshop.web.ui.dto.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 16:27
 * @Description:
 */
@Controller
public class LoginController {
    @Autowired
    EmailSendUtils emailSendUtils;
    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "login" ,method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {
        TbUser user = UserApi.loginApi(tbUser);
        if (!checkValidationCode(tbUser, request)) {
            model.addAttribute("baseResult", BaseResult.failed("验证码错误，请重新输入"));
            return "login";
        }
        //登录失败
        if (user == null) {
            model.addAttribute("baseResult", BaseResult.failed("用户名或者密码错误，请重试"));
            return "login";
        }
        //登录成功
        else {
            emailSendUtils.sendEmail("登录信息提示",
                    "您的账号于00点50分登录myshop如果不是本人操作请及时修改密码", "pizzaling@outlook.com");
            request.getSession().setAttribute(SystemConstant.LOGIN_USER, user);
            return "redirect:/index";
        }
    }

    /**
     * 检验验证码是否通过
     * @param tbUser
     * @return
     */
    private boolean checkValidationCode(TbUser tbUser,HttpServletRequest request) {
        String validationCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals(validationCode, tbUser.getValidation())) {
            return true;
        }
        return false;
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();//清空session
        return "redirect:/login";
    }

}
