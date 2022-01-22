package com.aaa.blog.service.impl;

import com.aaa.blog.entity.User;
import com.aaa.blog.mapper.UserMapper;
import com.aaa.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ydxstar
 * @create 2022-01-05 17:28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public User getbyid(Long id) {
        User user = userMapper.getUserbyid(id);
        return user;
    }

    @Override
    public User getbyUsername(String username) {
        User user = userMapper.getbyUsername(username);
        return user;
    }
}
