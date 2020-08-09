package com.miracle.vueblog.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: vueblog-project
 * @description:
 * @author: miracle
 * @create: 2020-08-09 10:48
 **/
@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "昵称不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
