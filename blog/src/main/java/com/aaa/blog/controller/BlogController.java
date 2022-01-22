package com.aaa.blog.controller;

import cn.hutool.core.map.MapUtil;
import com.aaa.blog.common.Result;
import com.aaa.blog.entity.Blog;
import com.aaa.blog.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.Rdn;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author ydxstar
 * @create 2022-01-11 14:56
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer pageNum ,@RequestParam Integer pageSize){
        List<Blog> blogs = blogService.selectAllBlog(pageNum, pageSize);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);


      return   Result.succ(blogPageInfo);


    }

    @RequestMapping("/blog/{id}") //详情
    public Result detail(@PathVariable(name = "id") Integer id){
        Blog blog = blogService.getbyID(id);

        return Result.succ(blog);
    }

    @RequestMapping("/blog/edit")
    public Result edit( @Validated @RequestBody Blog blog){
        if(blog.getId() != null){
            blog.setCreatetime(blogService.getbyID(blog.getId()).getCreatetime());
            blog.setUpdatetime(LocalDateTime.now());
            blogService.updateBlog(blog);
        }else {
        blog.setCreatetime(LocalDateTime.now());
        blogService.insertBlog(blog);
        }

        return  Result.succ(200,"编辑成功",null);


    }
}
