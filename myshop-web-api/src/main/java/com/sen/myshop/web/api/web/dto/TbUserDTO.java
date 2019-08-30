package com.sen.myshop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 17:47
 * @Description:
 */
@Data
public class TbUserDTO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
}
