package com.sen.myshop.web.admin.web.controller;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.dto.PageInfo;
import com.sen.myshop.domain.Content;
import com.sen.myshop.web.admin.abstracts.AbstractBaseController;
import com.sen.myshop.web.admin.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("content")
public class ContentController extends AbstractBaseController<Content,ContentService> {

    /**
     * 用于content_list展示所有的用户信息
     * @return
     */
    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "content_list";
    }

    /**
     * 跳转表单页面
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }

    /**
     * 提供对象返回页面模型给form标签使用
     * @param id
     * @return
     */
    @ModelAttribute
    public Content getContentByID(Long id) {
        Content content = null;
        if (id != null) {
            content = service.getById(id);
        } else {
            content = new Content();//spring自动注入
        }
        return content;
    }

    /**
     * 保存或者修改用户的相关处理
     * @param content
     * @param redirectAttributes
     * @param model
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Content content, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = service.save(content);

        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);//把保存成功的信息重定向到用户列表页面
            return "redirect:/content/list";
        }
        model.addAttribute("failedmessage", baseResult);
        return "content_form";
    }

    /**
     * 查看用户详情
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "user_detail";
    }
}
