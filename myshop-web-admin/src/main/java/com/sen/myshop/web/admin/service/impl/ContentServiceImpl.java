package com.sen.myshop.web.admin.service.impl;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.validator.BeanValidator;
import com.sen.myshop.domain.Content;
import com.sen.myshop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.sen.myshop.web.admin.dao.ContentDao;
import com.sen.myshop.web.admin.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class ContentServiceImpl extends AbstractBaseServiceImpl<Content,ContentDao> implements ContentService {
    /**
     * 增加或者修改信息
     * @param content
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public BaseResult save(Content content) {
        String message = BeanValidator.validator(content);
        if (message != null) {
            return BaseResult.failed(message);
        }else{
            //验证是否有id，若没有就执行插入操作
            if (content.getId() == null) {
                content.setCreated(new Date());
                content.setUpdated(new Date());
                dao.insert(content);
            } else {
                //若有id执行更新操作
                content.setUpdated(new Date());
                dao.update(content);
            }
            return BaseResult.success("操作内容业务成功");
        }
    }

    /**
     * 根据categoryIds批量删除
     * @param categoryIds
     */
    @Override
    public void deleteByCategoryId(String[] categoryIds) {
        dao.deleteByCategoryId(categoryIds);
    }

    // /**
    //  * 用户修改或者添加时的非空验证
    //  * @param content
    //  * @return
    //  */
    // public BaseResult ckeck(Content content) {
    //     BaseResult baseResult = BaseResult.success();
    //     if (content.getCategoryId() == null) {
    //         baseResult = BaseResult.failed("内容id类目不能为空");
    //     } else if (StringUtils.isBlank(content.getTitle())) {
    //         baseResult = BaseResult.failed("内容标题不能为空");
    //     } else if (StringUtils.isBlank(content.getSubTitle())) {
    //         baseResult = BaseResult.failed("自辨体不能为空");
    //     } else if (StringUtils.isBlank(content.getTitleDesc())) {
    //         baseResult = BaseResult.failed("比标题描述不能为空");
    //     } else if (StringUtils.isBlank(content.getUrl())) {
    //         baseResult = BaseResult.failed("连接不能为空");
    //     }else if (StringUtils.isBlank(content.getPic())) {
    //         baseResult = BaseResult.failed("图片的绝对路径不能为空");
    //     }else if (StringUtils.isBlank(content.getPic2())) {
    //         baseResult = BaseResult.failed("图片二的绝对路径泵为空");
    //     }else if (StringUtils.isBlank(content.getContent())) {
    //         baseResult = BaseResult.failed("内容不能为空");
    //     }
    //     return baseResult;
    // }
}
