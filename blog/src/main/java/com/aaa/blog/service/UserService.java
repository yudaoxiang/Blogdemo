package com.aaa.blog.service;

import com.aaa.blog.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author ydxstar
 * @create 2022-01-05 17:25
 */

public interface UserService {
    public User getbyid(Long id);
    public User getbyUsername(String username);
}
