package com.miracle.vueblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miracle.vueblog.entity.User;
import com.miracle.vueblog.mapper.UserMapper;
import com.miracle.vueblog.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miracle
 * @since 2020-08-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
