package com.sen.myshop.web.admin.web.controller;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.domain.TbUser;
import com.sen.myshop.web.admin.abstracts.AbstractBaseController;
import com.sen.myshop.web.admin.service.TbUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {

    /**
     * 用于user_list展示所有的用户信息
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "user_list";
    }

    /**
     * 跳转表单页面
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "user_form";
    }

    /**
     * 提供对象返回页面模型给form标签使用
     * @param id
     * @return
     */
    @ModelAttribute
    public TbUser getTbUserByID(Long id) {
        TbUser tbUser = null;
        if (id != null) {
            tbUser = service.getById(id);
        } else {
            tbUser = new TbUser();//spring自动注入
        }
        return tbUser;
    }

    /**
     * 保存或者修改用户的相关处理
     * @param tbUser
     * @param redirectAttributes
     * @param model
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = service.save(tbUser);

        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);//把保存成功的信息重定向到用户列表页面
            return "redirect:/user/list";
        }
        model.addAttribute("failedmessage", baseResult);
        return "user_form";
    }
    /**
     * 查看用户详情
     * @return
     */
    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "user_detail";
    }
}
