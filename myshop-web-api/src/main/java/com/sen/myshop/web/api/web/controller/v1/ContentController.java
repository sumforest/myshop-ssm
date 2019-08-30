package com.sen.myshop.web.api.web.controller.v1;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.domain.Content;
import com.sen.myshop.web.api.service.ContentService;
import com.sen.myshop.web.api.web.dto.ContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/8/11 16:16
 * @Description:
 */
//此注解是类级别的返回类型是JSON数据
@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "ppt",method = RequestMethod.GET)
    public BaseResult findByCategoryId() {
        List<Content> contents = contentService.findByCategoryId(122L);
        List<ContentDTO> contentDTOS = new ArrayList<>();
        for (Content content : contents) {
            ContentDTO contentDTO = new ContentDTO();
            //把Entity转换成DTO数据传输对象
            BeanUtils.copyProperties(content, contentDTO);
            contentDTOS.add(contentDTO);
        }
        BaseResult baseResult = BaseResult.success("成功",contentDTOS);
        return baseResult;
    }
}
