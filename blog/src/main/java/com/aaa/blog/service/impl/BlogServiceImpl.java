package com.aaa.blog.service.impl;

import com.aaa.blog.entity.Blog;
import com.aaa.blog.mapper.BlogMapper;
import com.aaa.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ydxstar
 * @create 2022-01-06 12:02
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Override
    public Blog getbyID(Integer id) {
        Blog blog = blogMapper.getBlogbyId(id);
        return blog;
    }

    @Override
    public List<Blog> selectAllBlog(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogs = blogMapper.selectAll();
        return blogs;
    }

    @Override
    public void insertBlog(Blog blog) {
        blogMapper.insertBlog(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogMapper.updateBlog(blog);
    }
}
