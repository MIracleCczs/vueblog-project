package com.miracle.vueblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miracle.vueblog.entity.Blog;
import com.miracle.vueblog.mapper.BlogMapper;
import com.miracle.vueblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
