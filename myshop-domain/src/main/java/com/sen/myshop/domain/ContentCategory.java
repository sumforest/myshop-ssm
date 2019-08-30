package com.sen.myshop.domain;

import com.sen.myshop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
/**
 * 内容分类管理类
 */
@Data
public class ContentCategory extends BaseTreeEntity {
    @Length(min = 1,message = "类目名称不能为零")
    private String name;
    private Integer status;
    @NotNull(message = "排列序号不能为空")
    private Integer sortOrder;
    private Boolean isParent;

    private ContentCategory parent;

}
