package com.sen.myshop.web.admin.web.controller;

import com.sen.myshop.commons.constant.ConstantUtils;
import com.sen.myshop.domain.TbUser;
import com.sen.myshop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/main")
    public String loginSuccess() {
        return "main";
    }

    /**
     * 登录逻辑
     *
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest httpServletRequest,HttpServletResponse response , Model model) throws IOException {
        TbUser tbUser = tbUserService.login(email, password);
        //登录失败
        if (tbUser == null) {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return login();
        } else {
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }

    }

    /**
     * 注销
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return login();
    }
}
