package com.aaa.blog.mapper;

import com.aaa.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ydxstar
 * @create 2022-01-05 15:00
 */
@Mapper
public interface BlogMapper {
    public Blog getBlogbyId(Integer id);

    public List<Blog> selectAll();

    public void insertBlog(Blog blog);

    public void updateBlog(Blog blog);
}
