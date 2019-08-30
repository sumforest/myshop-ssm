package com.sen.myshop.domain;

import com.sen.myshop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class Content extends BaseEntity {

    @Length(min = 2,max = 10,message = "标题长度在2—10之间")
    private String title;

    @Length(min = 2,max = 20,message = "子标题长度在2-20之间")
    private String subTitle;

    @Length(min = 1,max = 30,message = "标题描述长度在1—30之间")
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    @Length(min = 1,message = "内容不能为空")
    private String content;

    @NotNull(message = "父级类目不能为空")
    private ContentCategory contentCategory;

}
