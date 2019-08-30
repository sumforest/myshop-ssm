package com.sen.myshop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据传输对象
 * @Auther: Sen
 * @Date: 2019/8/11 18:08
 * @Description:
 */
@Data
public class ContentDTO implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
