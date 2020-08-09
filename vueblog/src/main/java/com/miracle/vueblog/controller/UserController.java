package com.miracle.vueblog.controller;


import com.miracle.vueblog.common.Result;
import com.miracle.vueblog.entity.User;
import com.miracle.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miracle
 * @since 2020-08-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresAuthentication
    @GetMapping("/{id}")
    public Object index(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.success(user);
    }

}
