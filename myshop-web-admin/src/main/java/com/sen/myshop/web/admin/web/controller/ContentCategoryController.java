package com.sen.myshop.web.admin.web.controller;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.domain.ContentCategory;
import com.sen.myshop.web.admin.abstracts.AbstractBaseTreeController;
import com.sen.myshop.web.admin.service.ContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<ContentCategory, ContentCategoryService> {


    /**
     * 跳转到内容分类管理页面
     * @param model
     * @return
     */
    @Override
    @RequestMapping(value = "list")
    public String cateGorylist(Model model) {
        List<ContentCategory> all = service.selectAll();
        List<ContentCategory> targetList = new ArrayList<>();
        sortContentCategory(all, targetList, 0L);
        model.addAttribute("ContentCategories", targetList);
        return "content_category_list";
    }

    /**
     * 为zTree插件提供json数据用于from页面的树形结构
     * @param id
     * @return
     */
    @Override
    @RequestMapping(value = "tree",method = RequestMethod.POST)
    @ResponseBody
    public List<ContentCategory> findForTree(Long id) {
        if (id == null) {
            id = 0L;
        }
        return service.findForTree(id);
    }

    /**
     * 提供给contentCategory的form表单用的数据，
     * 解决保存失败后数据都没有的情况。
     * @param id
     * @return
     */
    @ModelAttribute
    public ContentCategory getContentByID(Long id) {
        ContentCategory contentCategory = null;
        if (id != null) {
            contentCategory = service.getById(id);
        } else {
            contentCategory = new ContentCategory();//spring自动注入
        }
        return contentCategory;
    }

    /**
     * 增加或修改分类信息
     * @param contentCategory
     * @param redirectAttributes
     * @param model
     * @return
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ContentCategory contentCategory, RedirectAttributes redirectAttributes, Model model) {
        BaseResult baseResult = service.save(contentCategory);

        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);//把保存成功的信息重定向到用户列表页面
            return "redirect:/content/category/list";
        }
        model.addAttribute("failedmessage", baseResult);
        return "content_category_form";
    }

    /**
     * 跳转到form页面
     * @param contentCategory 把list的相关参数保存到这，在访问此方法前必先访问@ModelAttribute
     *                        注解下的方法，由于没有id所以会有一个空的Contentcategory对象返回
     *                        此方法，而此方法的contentCategory把返回的Contentcategory覆盖。
     * @return
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String contentcategoryForm(ContentCategory contentCategory) {
        return "content_category_form";
    }

    @Override
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleMutil(String[] ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNoneBlank(ids)) {
            service.deleteMulti(ids);
            baseResult = BaseResult.success("删除当前目录，及所有的子目录及目录内容成功");
        } else {
            baseResult = BaseResult.failed("删除作失败，发生位置错误");
        }
        return baseResult;
    }
}
