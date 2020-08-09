package com.miracle.vueblog.utils;

import com.miracle.vueblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @program: vueblog-project
 * @description:
 * @author: miracle
 * @create: 2020-08-09 10:58
 **/
public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
