package com.sen.myshop.web.admin.abstracts;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.persistence.BaseTreeEntity;
import com.sen.myshop.commons.persistence.BaserTreeService;
import com.sen.myshop.domain.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * 树形Controller的抽象
 * @Auther: Sen
 * @Date: 2019/8/10 02:14
 * @Description:
 */
public abstract class AbstractBaseTreeController<T extends BaseTreeEntity,S extends BaserTreeService<T>> {
    @Autowired
    protected S service;

    /**
     * 跳转信息展示
     * @param model
     * @return
     */
    public abstract String cateGorylist(Model model);

    /**
     * 用户TreeTable的排序方法
     * @param sourcesList 原数据
     * @param targetList 排序好的数据
     * @param parentId 父id
     * @return
     */
    protected void sortContentCategory(List<T> sourcesList, List<T> targetList, Long parentId) {
        for (T soutcesEntity : sourcesList) {
            if (soutcesEntity.getParent().getId().equals(parentId)) {
                targetList.add(soutcesEntity);
                if (soutcesEntity.getIsParent()) {
                    for (T currentEntity : sourcesList) {
                        if (currentEntity.getParent().getId().equals(soutcesEntity.getId())) {
                            sortContentCategory(sourcesList, targetList, soutcesEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
    /**
     * 为zTree插件提供json数据用于from页面的树形结构
     * @param id
     * @return
     */
    @RequestMapping(value = "tree",method = RequestMethod.POST)
    @ResponseBody
    public abstract List<T> findForTree(Long id);

    /**
     * 增加或保存
     * @param entity
     * @param redirectAttributes
     * @param model
     * @return
     */
    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);

    /**
     * 带着数据跳转修改页面
     * entity
     * @return
     */
    public abstract String contentcategoryForm(T entity);

    /**
     * 批量删除
     * @param ids
     */
    public abstract BaseResult deleMutil(String[] ids);
}
