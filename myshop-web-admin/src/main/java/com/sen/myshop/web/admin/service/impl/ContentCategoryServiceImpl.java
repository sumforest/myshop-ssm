package com.sen.myshop.web.admin.service.impl;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.validator.BeanValidator;
import com.sen.myshop.domain.ContentCategory;
import com.sen.myshop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.sen.myshop.web.admin.dao.ContentCategoryDao;
import com.sen.myshop.web.admin.service.ContentCategoryService;
import com.sen.myshop.web.admin.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<ContentCategory, ContentCategoryDao> implements ContentCategoryService {
    @Autowired
    private ContentService contentService;
    /**
     * 保存类目
     *
     * @param contentCategory
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public BaseResult save(ContentCategory contentCategory) {
        String message = BeanValidator.validator(contentCategory);
            //是否通过验证
            if (message != null) {
                return BaseResult.failed(message);
            } else {
                contentCategory.setUpdated(new Date());
                //判断当前类目是否为根目录
                ContentCategory parent = contentCategory.getParent();
                if (parent == null || parent.getId() == null) {
                    //根目录的id默认为0
                    parent.setId(0L);
                }
                //判断是新增还是修改业务
                if (contentCategory.getId() == null) {
                    contentCategory.setCreated(new Date());
                    //新添加的非根目录默认没有子目录
                    contentCategory.setIsParent(false);
                    //判断所添加的目录是否有父目录
                    //是否为非根目录
                    if (parent.getId() != 0L) {
                        ContentCategory currentParent = getById(contentCategory.getParent().getId());
                        //若有父目录
                        if (currentParent != null) {
                            currentParent.setIsParent(true);
                            dao.update(currentParent);
                        }
                    }
                    //若为根据目录
                    else {
                        //根目录默认有子目录
                        contentCategory.setIsParent(true);
                    }
                    dao.insert(contentCategory);
                } else {
                    dao.update(contentCategory);
                }
                return BaseResult.success("内容分类业务操作成功");
            }

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] ids) {
        Long parentId = Long.parseLong(ids[0]);
        List<String> tempIds = new ArrayList<>();
        findAllChildern(tempIds,parentId);
        String[] targetIds = tempIds.toArray(new String[tempIds.size()]);
        dao.deleteMulti(targetIds);
        contentService.deleteByCategoryId(targetIds);
    }

    /**
     * 查找所要删除的id的所有子目录
     * @param tagetList
     * @param parenId
     */
    public void findAllChildern(List<String> tagetList, Long parenId) {
        tagetList.add(String.valueOf(parenId));
        for (ContentCategory contentCategory : findForTree(parenId)) {
            findAllChildern(tagetList, contentCategory.getId());
        }
    }
    // /**
    //  * 非空验证
    //  * @param contentCategory
    //  * @return
    //  */
    // public BaseResult checkContentCagory(ContentCategory contentCategory) {
    //     BaseResult baseResult = BaseResult.success();
    //     if (contentCategory.getParentId().equals(null)) {
    //         baseResult = BaseResult.failed("父类目id不能为空");
    //     } else if (StringUtils.isBlank(contentCategory.getName())) {
    //         baseResult = BaseResult.failed("添加的分类名称不能为空");
    //     } else if (contentCategory.getSortOrder() == null) {
    //         baseResult = BaseResult.failed("排序编号不能为空");
    //     }
    //     return baseResult;
    // }

}
