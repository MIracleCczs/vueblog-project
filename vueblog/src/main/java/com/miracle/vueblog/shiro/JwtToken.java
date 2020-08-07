package com.miracle.vueblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @program: vueblog-project
 * @description: token
 * @author: miracle
 * @create: 2020-08-07 21:00
 **/
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
