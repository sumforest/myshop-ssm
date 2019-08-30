package com.sen.myshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sen.myshop.commons.persistence.BaseEntity;
import com.sen.myshop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class TbUser extends BaseEntity {
    @Length(min = 6, max = 20, message = "用户名必须再6-20之间")
    private String username;
    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码长度必须在6-20之间")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号码格式错误")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式错误")
    private String email;

}
