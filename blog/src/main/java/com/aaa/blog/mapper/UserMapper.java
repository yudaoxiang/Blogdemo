package com.aaa.blog.mapper;

import com.aaa.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ydxstar
 * @create 2022-01-05 15:22
 */
@Mapper
public interface UserMapper {
    public User getUserbyid(Long id);
    public User getbyUsername(String username);
}
