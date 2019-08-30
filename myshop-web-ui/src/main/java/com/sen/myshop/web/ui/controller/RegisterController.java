package com.sen.myshop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 18:14
 * @Description:
 */
@Controller
public class RegisterController {
    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register() {
        return "register";
    }
}
