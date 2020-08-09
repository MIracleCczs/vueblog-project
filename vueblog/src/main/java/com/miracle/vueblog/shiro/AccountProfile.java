package com.miracle.vueblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: vueblog-project
 * @description:
 * @author: miracle
 * @create: 2020-08-09 09:39
 **/
@Data
public class AccountProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String avatar;

    private String email;

}
