package com.aaa.blog.service;

import com.aaa.blog.entity.Blog;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ydxstar
 * @create 2022-01-05 17:27
 */

public interface BlogService {
    public Blog getbyID(Integer id);

    public List<Blog> selectAllBlog(Integer pageNum, Integer pageSize);

    public void  insertBlog(Blog blog);

    public void  updateBlog(Blog blog);
}
