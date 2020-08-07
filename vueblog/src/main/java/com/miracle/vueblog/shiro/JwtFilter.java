package com.miracle.vueblog.shiro;

import cn.hutool.json.JSONUtil;
import com.miracle.vueblog.common.Result;
import com.miracle.vueblog.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: vueblog-project
 * @description: filter
 * @author: miracle
 * @create: 2020-08-07 20:57
 **/
@Component
public class JwtFilter extends AuthenticatingFilter {

    public static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)) {
            return null;
        }
        return new JwtToken(jwt);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)) {
            return true;
        }
        // 校验jwt
        Claims claims = jwtUtils.getClaimByToken(jwt);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            throw new ExpiredCredentialsException("token已失效，请重新登录");
        }
        // 执行登录
        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Throwable throwable = StringUtils.isEmpty(e.getCause()) ? e : e.getCause();
        Result result = Result.fail(throwable.getMessage());
        String json = JSONUtil.toJsonStr(result);
        try {
            httpServletResponse.getWriter().print(json);
        } catch (IOException ex) {
            LOGGER.error("write error", ex);
        }
        return super.onLoginFailure(token, e, request, response);
    }
}