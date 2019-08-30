package com.sen.myshop.web.ui.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 16:50
 * @Description:
 */
@Data
public class TbUser implements Serializable {
    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    private String validation;
}
