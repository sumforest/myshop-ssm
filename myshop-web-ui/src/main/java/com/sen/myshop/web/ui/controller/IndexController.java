package com.sen.myshop.web.ui.controller;

import com.sen.myshop.web.ui.api.ConstantApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: Sen
 * @Date: 2019/8/11 22:58
 * @Description:
 */
@Controller
public class IndexController {
    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public String index(Model model) {
        try {
            //调用查找幻灯片（一个方法只做一件事）
           ppt(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 查找幻灯片
     * @param model
     */
    private void ppt(Model model) throws Exception {
        model.addAttribute("ppt", ConstantApi.ppt());
    }
}
